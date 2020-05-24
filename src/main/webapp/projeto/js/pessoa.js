
(function () {
    'use strict';
    
    angular.module('cadastro', [])
        .controller('PessoaController', function ( $scope, $http, $window, $location ) {
        	let url = "/recursos/pessoas";
        	let user = null;
        	$scope.view = false;
        	
        	if( $window.localStorage.user ) {
        		user = JSON.parse($window.localStorage.user);
        		$scope.view = user.role == "ADM" ? true : false;
        		$http.defaults.headers.common.Authorization = 'Bearer ' + user.jwt;
        	} else {
        		location.href = 'login.html';
        	}
        	
        	$scope.pessoa = {
        		nome: '', documento: '', nome_mae: '', nome_pai: '', tipoPessoa: '', loginOperador: user.username
        	};
        	$scope.telefone = {};
        	
        	$http.get( url ).then(function (response) {
        		$scope.pessoas = (response.data);
        	});
            
            $scope.cadastrar = function () {
            	let method = "post";
            	$scope.pessoa.id ? method = "put" : method;
                $http[method]( url , $scope.pessoa ).then(function (response) {
                	alert("Registro cadastrado com sucesso.")
                	$http.get( url ).then(function (response) {
                		$scope.pessoas = (response.data);
                	})
                });
                $scope.pessoa = {};
            };
            
            $scope.selecionar = function( pessoa ) {
            	$scope.pessoa = { 
            		id: pessoa[0],
            		nome: pessoa[5],
            		documento: pessoa[3],
//            		dataNascimento: pessoa[2],
            		nome_mae: pessoa[6],
            		nome_pai: pessoa[7],
            		tipoPessoa: pessoa[8],
            		loginOperador: pessoa[4]
            	};
            }
            
            $scope.cancelar = function() {
            	$scope.pessoa = {};
            	$scope.pessoa.id = '';
            }

            $scope.deletar = function( pessoa ) {
            	$http.delete( url + '/'+ pessoa[0]).then(function (response) {
            		alert('Registro excluido com sucesso');
            		$http.get( url ).then(function (response) {
                		$scope.pessoas = response.data;
                	});
                });
            }
            
            $scope.loadPessoa = function( pessoa ) {
            	$scope.telefone.loginOperador = user.username;
            	$scope.telefone.pessoa = pessoa[0];
            }
            
            $scope.cadastrarTelefone = function() {
            	console.log( $scope.telefone );
            	let method = "post";
            	$scope.telefone.id ? method = "put" : method;
                $http[method]( "/recursos/telefones" , $scope.telefone ).then(function (response) {
                	alert("Registro cadastrado com sucesso.")
                	$http.get( "/recursos/telefones" ).then(function (response) {
                		$scope.telefone = (response.data);
                	})
                });
                $scope.telefone = {};
            }
            
            $scope.logout = function () {
        		delete $window.localStorage.user;
        		$http.defaults.headers.common.Authorization = '';
        		location.href = 'login.html';
    	    }
        });

})();