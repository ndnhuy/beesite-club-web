'use strict'

angular
	.module('app.services')
	.factory('AuthService', function($http, $log, $cookies, config) {
		var data = {
			'getAccessToken': getAccessToken,
			'signup': signup
		};

		function getAccessToken(username, password) {
			$log.info("Get access token for " + username + " : " + password);
			return $http({
				'url': config.url + 'oauth/token',
				'method': 'POST',
				'headers': {
					'Accept': 'application/json',
					'Content-Type': 'application/x-www-form-urlencoded',
					'Authorization': 'Basic YmVlc2l0ZV9jbGllbnQ6MTIz'
				},
				'transformRequest': function(obj) {
			        var str = [];
			        for(var p in obj)
			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			        return str.join("&");
			    },
			    'data': {
			    	'username': username,
			    	'password': password,
			    	'grant_type': 'password',
			    	'scope': 'read write',
			    	'client_secret': '123',
			    	'client_id': 'beesite_client'
			    }
			})
			.success(function(data) {
				$cookies.put('access-token', data.access_token);
			})
			.catch(dataServiceError);
		}

		function signup(username, password, email, name) {
			$log.info("Register username: " + username);
			return $http({
				'url': config.url + 'account',
				'method': 'POST',
			    'data': {
			    	'username': username,
			    	'password': password,
			    	'email': email,
			    	'name': name
			    }
			})
			.success(function(data) {
				$log.info("Register successfully " + JSON.stringify(data));
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for AuthService');
	        $log.error(JSON.stringify(errorResponse.data));
	        return errorResponse;
	    }
		return data;
	});