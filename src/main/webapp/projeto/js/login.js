
(function () {
    'use strict';
    
    angular.module('login', [])
        .controller('loginController', function ( $scope, $http, $location, $window ) {
        	
        	$scope.user = {
        		login: '', senha: ''
        	};

        	$scope.logar = function() {
        		$http.post('/recursos/login', $scope.user).then(function (response) {
                	$window.localStorage.setItem("user", JSON.stringify( response.data) );
                	if( response.data.role === "ADM" ) {
                		location.href = 'home.html';
                	} else {
                		location.href = 'pessoa.html';
                	}
                });
        	}
        	
        });
})();