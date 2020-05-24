(function () {
    'use strict';

    angular.module('cadastro', [])
        .controller('UsuarioController', function ( $scope, $http, $window, $location ) {
        	let url = "/recursos/operador";
        	
        	if( $window.localStorage.user ) {
        		let user = JSON.parse($window.localStorage.user);
        		$http.defaults.headers.common.Authorization = 'Bearer ' + user.jwt;
        	} else {
        		location.href = 'login.html';
        	}
        	
        	$http.get( url ).then(function (response) {
        		$scope.usuarios = (response.data);
        	});
        	$scope.usuario = {
        		nome: '', senha: '', login: '', perfil: ''
        	};
        	
            $scope.cadastrar = function () {
            	let method = "post";
            	$scope.usuario.id ? method = "put" : method;
                $http[method]( url , $scope.usuario).then(function (response) {
                	alert("Registro cadastrado com sucesso.")
                	$http.get( url ).then(function (response) {
                		$scope.usuarios = (response.data);
                	})
                });
                $scope.usuario = {};
                $scope.statusPassword = {};
            };
            
            $scope.selecionar = function( usuario) {
            	$scope.usuario = { 
            		id: usuario[0],
            		nome: usuario[3],
            		login: usuario[2],
            		perfil: usuario[4],
            		senha: usuario[5]
            	};
            }

            $scope.deletar = function( usuario ) {
            	$http.delete( url + '/' + usuario[0]).then(function (response) {
            		alert('Registro excluido com sucesso');
            		$http.get( url ).then(function (response) {
                		$scope.usuarios = response.data;
                	});
                });
            }
            
            $scope.logout = function () {
        		delete $window.localStorage.user;
        		$http.defaults.headers.common.Authorization = '';
        		location.href = 'login.html';
    	    }
            
            $scope.cancelar = function() {
            	$scope.usuario = {}
            }
            
            $scope.validarSenha = function () {
                $scope.statusSenha = {
                    classe: '',
                    icone: '',
                    mensagem: ''
                };
            
                if($scope.usuario.senha && $scope.usuario.senha.length >= 6) {
                    $scope.statusSenha.classe = 'has-success';
                    $scope.statusSenha.icone = 'glyphicon-ok';
                    $scope.statusSenha.mensagem = 'Senha forte';
                }else {
                    $scope.statusSenha.classe = 'has-error';
                    $scope.statusSenha.icone = 'glyphicon-remove';
                    $scope.statusSenha.mensagem = 'Senha fraca';
                }
            };
        });

})();