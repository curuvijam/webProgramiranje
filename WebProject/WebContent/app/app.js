var app = angular.module('app', ['ngRoute', 'ngStorage']);

app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('/',{
		templateUrl: 'html/home.html'
	}).when('/registracija',{
		templateUrl: 'html/registration.html'
	}).when('/home',{
		templateUrl: 'html/home.html'
	}).when('/prijava', {
		templateUrl: 'html/login.html'
	}).when('/restorani', {
		templateUrl: 'html/restaurants.html'
	}).when('/artikli', {
		templateUrl: 'html/items.html'
	}).when('/dodajRestoran', {
		templateUrl: 'html/addRestaurant.html'
	}).when('/restoran/:id', {
		templateUrl: 'html/updateRestaurant.html'
	}).when('/artikal/:id', {
		templateUrl: 'html/updateItem.html'
	}).when('/restoranDet/:id', {
		templateUrl: 'html/restaurant.html'
	}).when('/vozilo/:id', {
		templateUrl: 'html/updateVehicle.html'
	}).when('/korisnik/:id', {
		templateUrl: 'html/changeRole.html'
	}).when('/pretragaArtikala', {
		templateUrl: 'html/searchItems.html'
	}).when('/pretragaRestorana', {
		templateUrl: 'html/searchRestaurant.html'
	}).when('/vozila', {
		templateUrl: 'html/vehicles.html'
	}).when('/restoranKat/:kategorija', {
		templateUrl: 'html/restoranKat.html'
	}).when('/dodajVozilo', {
		templateUrl: 'html/addVehicle.html'
	}).when('/dodajArtikal', {
		templateUrl: 'html/addItem.html'
	}).when('/korisnici', {
		templateUrl: 'html/users.html'
	}).when('/porudzbine', {
		templateUrl: 'html/orders.html'
	}).when('/profil', {
		templateUrl: 'html/profile.html'
	}).otherwise({
		redirectTo: '/'
	});
});
