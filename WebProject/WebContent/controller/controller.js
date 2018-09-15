app.controller('loginController', function($scope, $rootScope, authenticationFactory) {

	$scope.login = function(username, password) {
		console.log('usao u kontroler');
		authenticationFactory.login(username, password).success(function(data) {
			$rootScope.$broadcast('ulogovan');
		})
	};
	
});

app.controller('registerController', function($scope, korisnikFactory, $location) {
	
	$scope.register = function(korisnik) {
		korisnikFactory.register(korisnik).success(function(data) {
			console.log(data);
			$location.path('/home');
		});
	}
});

app.controller('usersController', function($scope, korisnikFactory, $location) {
	function init() {
		korisnikFactory.getKorisnici().success(function(data) {
			$scope.korisnici = data;
		});
	}

	$scope.updateRedirect = function(id) {
		$location.path('/korisnik/' + id);
	}

	init();

});

app.controller('updateUserController', function($scope, korisnikFactory, $routeParams, $location) {
	function init() {
		korisnikFactory.getKorisnik($routeParams.id).success(function(data) {
			$scope.korisnik = data;
		});
	}

	$scope.changeRole = function(korisnik) {
		korisnikFactory.changeRole(korisnik).success(function(data) {
			$location.path('/korisnici');
		});
	}

	init();
});

app.controller('restaurantArtikliController', function($scope, artikalFactory, $routeParams, $location) {
	function init() {
		artikalFactory.getForRestoran($routeParams.id).success(function(data) {
			$scope.artikli = data;
		});
	}

	init();
});


app.controller('restaurantController', function($scope, restoranFactory, $location) {
	
	function init() {
		console.log('usao u init restorana');
		restoranFactory.getRestorani().success(function(data) {
			$scope.restorani = data;
		});

		$scope.pretrazeni = [];
	}

	$scope.addRestaurant = function(restoran) {
		restoranFactory.addRestaurant(restoran).success(function(data) {
			console.log(data);
			$location.path('/restorani')
		});
		
	}
	
	$scope.deleteRestaurant = function(id) {
		restoranFactory.deleteRestaurant(id).success(function(data) {
			init();
			$location.path('/restorani');
		});
	}

	$scope.updateRedirect = function(id) {
		$location.path('/restoran/' + id)
	}

	$scope.searchRestoran = function(naziv, adresa, kategorija) {
		restoranFactory.searchRestaurant(naziv,adresa,kategorija).success(function(data) {
			$scope.pretrazeni = data;
		});
	}

	init();
	
});

app.controller('kategorijaRestoranController', function($scope, restoranFactory,  $routeParams, $location) {
	function init() {
		console.log('usao u init restorana');
		restoranFactory.getRestoraniByKat($routeParams.kategorija).success(function(data) {
			$scope.restorani = data;
		});
	}

	$scope.go = function(id) {
		$location.path('/restoranDet/' + id);
	}

	init();

});

app.controller('itemController', function($scope, artikalFactory, restoranFactory, $location) {
	
	function init() {
		console.log('usao u init artikla');
		artikalFactory.getItems().success(function(data) {
			$scope.artikli = data;
		});
		restoranFactory.getRestorani().success(function(data) {
			$scope.restorani = data;
		});

		$scope.pretrazeni = [];
	}

	$scope.addItem = function(artikal) {
		artikalFactory.addItem(artikal).success(function(data) {
			console.log(data);
			$location.path('/artikli');
		});
		
	}
	
	$scope.deleteItem = function(id) {
		artikalFactory.deleteItem(id).success(function(data) {
			init();
			$location.path('/artikli');
		});
	}

	$scope.updateRedirect = function(id) {
		$location.path('artikal/' + id)
	}

	$scope.searchItem = function(naziv, cena, tip, restoran) {
		artikalFactory.searchArtikal(naziv, cena, tip, restoran).success(function(data) {
			$scope.pretrazeni = data;
		});
	}

	init();
});

app.controller('vehicleController', function($scope, voziloFactory, $location) {
	function init() {
		console.log('usao u init vozila');
		voziloFactory.getVozila().success(function(data) {
			$scope.vozila = data;
		});
	}

	$scope.addVehicle = function(vozilo) {
		voziloFactory.addVehicle(vozilo).success(function(data) {
			console.log(data);
			$location.path('/vozila');
		});
	}
	
	$scope.deleteVehicle = function(id) {
		voziloFactory.deleteVehicle(id).success(function(data) {
			init();
			$location.path('/vozila');
		});
	}

	$scope.updateRedirect = function(id) {
		$location.path('/vozilo/' + id);
	}

	init();
	
});

app.controller('updateVehicleController', function($scope, voziloFactory, $routeParams, $location) {
	function init() {
		voziloFactory.getVozilo($routeParams.id).success(function(data) {
			console.log(data);
			$scope.vozilo = data;
		});
	}

	$scope.updateVehicle = function(vozilo) {
		voziloFactory.updateVehicle(vozilo).success(function(data) {
			console.log(data);
			$location.path('/vozila');
		});
	}

	init();
});

app.controller('updateItemController', function($scope, artikalFactory, restoranFactory, $routeParams, $location) {
	function init() {
		artikalFactory.getItem($routeParams.id).success(function(data) {
			$scope.artikal = data;
		});

		restoranFactory.getRestorani().success(function(data) {
			$scope.restorani = data;
		});
	}

	$scope.updateItem = function(artikal) {
		artikalFactory.updateItem(artikal).success(function(data) {
			console.log(data);
			$location.path('/artikli');
		});
	}

	init();
});

app.controller('updateRestaurantController', function($scope, restoranFactory, $routeParams, $location) {
	function init() {
		restoranFactory.getRestoran($routeParams.id).success(function(data) {
			console.log(data);
			$scope.restoran = data;
		});

	}

	$scope.updateRestaurant = function(restoran) {
		restoranFactory.updateRestaurant(restoran).success(function(data) {
			console.log(data);
			$location.path('/restorani');
		});
	}
	
	init();
});

app.controller('indexController', function($scope, $rootScope, $localStorage, $http, $location ) {
	$scope.ulogovan = false;
	$scope.ulogovanRole = '';
	$scope.admin = false;
	$scope.dostavljac = false;

	function init() {
        $rootScope.$on('ulogovan', function () {
            $scope.ulogovan = true;
            $scope.ulogovanRole = $localStorage.currentUser.role;

            if ($scope.ulogovanRole == 'ADMINISTRATOR') {
                $scope.admin = true;
                $scope.dostavljac = false;
            }
            if ($scope.ulogovanRole == 'DOSTAVLJAC') {
                $scope.dostavljac = true;
                $scope.admin = false;
			}
			
			console.log('DOSTAVLJAC?' + $scope.dostavljac);
			console.log('ADMIN?' + $scope.admin)
        });
	};

	$scope.logOut = function () {
        $scope.ulogovan = false;
        $scope.admin = false;
        $scope.dostavljac = false;
        $scope.ulogovanRole = '';
        $localStorage.currentUser = {};
        $http.defaults.headers.common.Authorization = '';
        $location.path('/login');
    }
	
	init();
});


