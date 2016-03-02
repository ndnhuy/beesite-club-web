'use strict';

angular
    .module('app.core')
    .controller('UpdateController', function($window, $scope, $log, $cookies, article, ArticleService) {
        var vm = this;
        
        vm.article = article.data.data;
        
        vm.title = vm.article.title;
        vm.content = vm.article.content;
        vm.tag = vm.article.tag;
        vm.image = vm.article.image;
        
        vm.send = function() {
            ArticleService.createArticle(vm.article.id, vm.title, vm.content, vm.tag, vm.image)
                .then(function(data) {
                    if (data.data.status === 200) {
                        alert("Your article has been sent");
                        $window.location.href = '#/articles';
                    }
                });
        }
        
        
        
        
        
        
        
        
        

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
            
            $window.location.href = '#/';
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