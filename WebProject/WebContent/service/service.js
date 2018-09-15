app.factory("korisnikFactory", function($http) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };
  var url = "http://localhost:8080/WebProject/webapi/korisnik";

  factory.register = function(korisnik) {
    console.log(korisnik);
    var data = {
      korisnickoIme: "" + korisnik.korisnickoIme,
      sifra: "" + korisnik.sifra,
      ime: "" + korisnik.ime,
      prezime: "" + korisnik.prezime,
      kontaktTelefon: korisnik.kontaktTelefon,
      email: korisnik.email,
      datumRegistracije: new Date()
    };

    return $http.post(url, data, config);
  };

  factory.getKorisnici = function() {
    return $http.get(url);
  };

  factory.getKorisnik = function(id) {
    return $http.get(url + "/" + id);
  };

  factory.changeRole = function(korisnik) {
    return $http.put(url, korisnik, config);
  };

  return factory;
});

app.factory("artikalFactory", function($http) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };
  var url = "http://localhost:8080/WebProject/webapi/artikal";

  factory.addItem = function(artikal) {
    console.log(artikal);

    var data = {
      naziv: "" + artikal.naziv,
      cena: "" + artikal.cena,
      opis: "" + artikal.opis,
      tip: "" + artikal.tip,
      kolicina: "" + artikal.kolicina,
      restoranId: "" + artikal.restoranId
    };

    return $http.post(url, data, config);
  };

  factory.getItems = function() {
    return $http.get(url);
  };

  factory.getItem = function(id) {
    return $http.get(url + "/" + id);
  };

  factory.updateItem = function(artikal) {
    console.log(artikal);
    return $http.put(url, artikal, config);
  };

  factory.deleteItem = function(id) {
    console.log(id);
    return $http.delete(url + "/" + id, config);
  };

  factory.searchArtikal = function(naziv, cena, tip, restoran) {
    console.log(
      url +
        "/pretraga?naziv=" +
        naziv +
        "&cena=" +
        cena +
        "&tip=" +
        tip +
        "&restoran=" +
        restoran
    );
    return $http.get(
      url +
        "/pretraga?naziv=" +
        naziv +
        "&cena=" +
        cena +
        "&tip=" +
        tip +
        "&restoran=" +
        restoran
    );
  };

  factory.getForRestoran = function(id) {
    return $http.get(url + '/restoran/' + id);
  };

  return factory;
});

app.factory("restoranFactory", function($http) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };
  var url = "http://localhost:8080/WebProject/webapi/restoran";

  factory.addRestaurant = function(restoran) {
    console.log(restoran);

    var data = {
      naziv: "" + restoran.naziv,
      adresa: "" + restoran.adresa,
      kategorija: "" + restoran.kategorija
    };

    return $http.post(url, data, config);
  };

  factory.getRestorani = function() {
    return $http.get(url);
  };

  factory.getRestoran = function(id) {
    return $http.get(url + "/" + id);
  };

  factory.updateRestaurant = function(restoran) {
    console.log(restoran);
    return $http.put(url, restoran, config);
  };

  factory.deleteRestaurant = function(id) {
    console.log(id);
    return $http.delete(url + "/" + id, config);
  };

  factory.searchRestaurant = function(naziv, adresa, kategorija) {
    console.log(
      url +
        "/pretraga?naziv=" +
        naziv +
        "&adresa=" +
        adresa +
        "&kategorija=" +
        kategorija
    );
    return $http.get(
      url +
        "/pretraga?naziv=" +
        naziv +
        "&adresa=" +
        adresa +
        "&kategorija=" +
        kategorija
    );
  };

  factory.getRestoraniByKat = function(kategorija) {
    return $http.get(url + '/kategorija?kategorija=' + kategorija);
  }

  return factory;
});

app.factory("voziloFactory", function($http) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };

  var url = "http://localhost:8080/WebProject/webapi/vozilo";

  factory.addVehicle = function(vozilo) {
    console.log(vozilo);

    var data = {
      marka: "" + vozilo.marka,
      model: "" + vozilo.model,
      tip: "" + vozilo.tip,
      regOznaka: "" + vozilo.regOznaka,
      godinaProizvodnje: "" + vozilo.godinaProizvodnje,
      uUpotrebi: "" + true,
      napomena: "" + vozilo.napomena
    };
    return $http.post(url, data, config);
  };

  factory.getVozila = function() {
    return $http.get(url + "/vozila");
  };

  factory.getVozilo = function(id) {
    return $http.get(url + "/" + id);
  };

  factory.updateVehicle = function(vozilo) {
    console.log(vozilo);

    return $http.put(url, vozilo, config);
  };

  factory.deleteVehicle = function(id) {
    console.log(id);
    console.log(url + "/" + id);
    return $http.delete(url + "/" + id, config);
  };

  return factory;
});

app.factory("porudzbinaFactory", function($http) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };

  var url = "http://localhost:8080/WebProject/webapi/porudzbina";

  factory.newOrder = function(porudzbina) {
    var data = {};

    $http.post(url, data, config);
  };

  return factory;
});

app.factory("authenticationFactory", function($http, $localStorage, $location) {
  var factory = {};

  var config = {
    headers: {
      "Content-Type": "application/json"
    }
  };

  var url = 'http://localhost:8080/WebProject/webapi/authentication';

  factory.login = function (username, password) {
	console.log('usao u servis')
	return $http.post(url, { username: username, password: password }).success(function (response) {
		if (response.token) {
			$localStorage.currentUser = {
				username: username,
				token: response.token,
				role: response.role
			};
			$localStorage.loggedIn = true;

			$http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
			console.log('Logged in');
      console.log('Current user: ' + $localStorage.currentUser.username);
      console.log('Role: ' + $localStorage.currentUser.role);
			$location.path('/');
		} 
	});
	}

	return factory;
});
