'use strict';

angular
    .module('app.core')
    .controller('ArticleDetailController', function($window, $scope, $cookies, AuthService, $log, article, ArticleService) {
        var vm = this;
        
        vm.article = article.data.data;
        $log.info("Article: " + JSON.stringify(vm.article));

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


        vm.postCommentOnArticle = function(id) {
        	ArticleService.postCommentOnArticle(id, vm.comment).then(function(data) {
        		if (data.data.status === 201) {
        			$log.info("Post comment on articleId " + id + " with comment " + vm.comment);
        			ArticleService.getArticleById(vm.article.id).then(function(data) {
        				vm.article = data.data.data;
        				vm.comment = null;
        			});
        		}
        	});
        }
        
        vm.showLoginForm = function() {
            vm.showRegister = false;
        }
    });