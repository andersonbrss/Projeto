
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
        	}).catch( e => {
        		if( e.status == 401 )
        			$scope.logout();
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
                $scope.cancelar();
            };
            
            $scope.selecionar = function( pessoa ) {
            	$scope.pessoa = pessoa;
            	delete $scope.pessoa.dataNascimento;
            }
            
            $scope.cancelar = function() {
            	$scope.pessoa = {
                	loginOperador: user.username	
                };
            }

            $scope.deletar = function( pessoa ) {
            	$http.delete( url + '/'+ pessoa.id).then(function (response) {
            		alert('Registro excluido com sucesso');
            		$http.get( url ).then(function (response) {
                		$scope.pessoas = response.data;
                	});
                });
            }
            
            $scope.loadPessoa = function( pessoa ) {
            	listarTelefones( pessoa.id );
            	$scope.telefone.pessoa = pessoa;
            }
            
            $scope.selecionarTelefone = function( telefone ) {
            	$scope.telefone = telefone;
            }
            
            $scope.cadastrarTelefone = function() {
            	if( !$scope.telefone.id )
            		$scope.telefone.loginOperador = user.username;

            	delete $scope.telefone.pessoa.dataNascimento;
            	let method = "post";
            	$scope.telefone.id ? method = "put" : method;
                $http[method]( "/recursos/telefones" , $scope.telefone ).then(function (response) {
                	listarTelefones( $scope.telefone.pessoa.id );
                	alert("Registro cadastrado com sucesso.")
                });
                $scope.cancelarTelefone();
            }
            
            function listarTelefones( id ) {
            	$http.get( "/recursos/telefones/" + id ).then(function (response) {
            		$scope.telefones = (response.data);
            	}).catch(function(e){
            		console.log("err", e);
            	});
            }
            
            $scope.deletarTelefone = function ( telefone ) {
            	$http.delete( "/recursos/telefones/" + telefone.id).then(function (response) {
            		alert('Registro excluido com sucesso');
            		listarTelefones( telefone.pessoa.id );
                });
            }
            
            $scope.cancelarTelefone = function () {
            	$scope.telefone = {
        			pessoa: $scope.telefone.pessoa,
                	loginOperador: user.username
            	}
            }
            
            $scope.logout = function () {
        		delete $window.localStorage.user;
        		$http.defaults.headers.common.Authorization = '';
        		location.href = 'login.html';
    	    }
        });

})();