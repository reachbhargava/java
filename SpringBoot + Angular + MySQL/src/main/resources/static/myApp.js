var myApp = angular.module('myApp', [ 'ngRoute', 'jcs-autoValidate' ]);

myApp.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider.when('/home', {
				templateUrl : 'home.html',
				controller : 'homeController'
			}).when('/success', {
				templateUrl : 'success.html',
				controller : 'successController'
			}).otherwise({
				redirectTo : 'home'
			});

		} ]);

myApp.controller('homeController', homeController);
myApp.controller('successController', successController);

myApp.run(function (defaultErrorMessageResolver) {
		defaultErrorMessageResolver.getErrorMessages().then(function (errorMessages) {
			errorMessages['badPassword'] = 'Password must be at least 8 characters, should contain an UpperCase, a LowerCase and a Special Character.';
			errorMessages['userNameMinLength'] = 'UserName must be at least 5 characters.';
			errorMessages['userNameMaxLength'] = 'UserName cannot be more than 10 characters.';
		});
})

function homeController($http, $location) {
	this.greeting = "Hello Boss";
	this.user = {};
	this.users = {};

	this.registerUser = function() {
		var self = this;
		console.log('coming to register ' + this.user.username)
		$http.post('adduser', self.user).then(function(response) {
			console.log('response --> ', response)
			if (response.data && response.data.id) {
				$location.path('/success');
			} else {

			}
		}, function(error) {
			console.log('error --> ', error)
		})
	}

	this.showListOfUsers = function() {
		var self = this;

		$http.get('getUserdetails').then(function(response) {
			console.log('response --> ', response);
			self.users = response.data
		}, function(error) {
			console.log('error --> ', error)
		})
	}
}

function successController($location) {
	this.goBack = function() {
		$location.path('/home');
	}
}