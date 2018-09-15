package filters;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import model.Korisnik;
import resource.Secured;
import services.KorisnikService;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it

		Class<?> resourceClass = resourceInfo.getResourceClass();
		List<Korisnik.Grupa> classRoles = extractRoles(resourceClass);

		// Get the resource method which matches with the requested URL
		// Extract the roles declared by it
		java.lang.reflect.Method resourceMethod = resourceInfo.getResourceMethod();
		List<Korisnik.Grupa> methodRoles = extractRoles(resourceMethod);

		try {

			// Check if the user is allowed to execute the method
			// The method annotations override the class annotations
			if (methodRoles.isEmpty()) {
				checkPermissions(classRoles, requestContext.getSecurityContext().getUserPrincipal().getName());
			} else {
				checkPermissions(methodRoles, requestContext.getSecurityContext().getUserPrincipal().getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
			requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
		} catch (Exception e) {
			e.printStackTrace();
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
		}
	}

	// Extract the roles from the annotated element
	private List<Korisnik.Grupa> extractRoles(AnnotatedElement annotatedElement) {

		if (annotatedElement == null) {
			return new ArrayList<Korisnik.Grupa>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
			if (secured == null) {
				return new ArrayList<Korisnik.Grupa>();
			} else {
				Korisnik.Grupa[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}

	private void checkPermissions(List<Korisnik.Grupa> allowedRoles, String username) throws Exception {
		// Check if the user contains one of the allowed roles
		// Throw an Exception if the user has not permission to execute the
		// method
		KorisnikService korisnikService = new KorisnikService();
		Korisnik korisnik = korisnikService.getKorisnik(username);
		for (Korisnik.Grupa role : allowedRoles) {
			if (korisnik.getUloga().equals(role)) {
				return;
			}
		}

		throw new Exception();
	}
}
