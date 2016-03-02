'use strict';

angular
    .module('app.core')
    .controller('HomeController', function($window, $scope, $log, $cookies, AuthService) {
        var vm = this;
        
     

        if ($cookies.get('access-token') == null) {
            vm.authenticated = false;
        }
        else {
            vm.authenticated = true;
            vm.username = $cookies.get('username');
        }
        
        
        vm.login = function() {
            if ($cookies.get('access-token') == null) {
                AuthService.getAccessToken(vm.username, vm.password).then(function(data) {
                    if (data.data.error != null) {
                        vm.error = "Your username or password is incorrect. Please try again.";
                        vm.authenticated = false;
                    }
                    else {
                        vm.authenticated = true;
                        $cookies.put('username', vm.username);
                    }
                });
            }
        }

        vm.logout = function() {
            $cookies.remove('access-token');
            $cookies.remove('username');

            vm.authenticated = false;
            vm.username = null;
            vm.password = null;
        }

         vm.showRegister = false;
        vm.showRegisterForm = function() {
            vm.showRegister = true;
        }

        vm.register = function() {
            AuthService.signup(vm.username, vm.password, vm.email, vm.name).then(function(data) {
                if (data.data.status === 201) {
                    alert("Register successfully");
                    vm.showRegister = false;
                    vm.error = null;
                    vm.registerError = null;
                }
                else {
                    alert("Register fail");
                    vm.registerError = data.data.developerMessage;
                }
            });
        }
        
        vm.showLoginForm = function() {
            vm.showRegister = false;
        }
    });