
angular
	.module('app.services')
	.factory('MemberService', function($http, $log, config) {
		var data = {
			'getMembers': getMembers
		};

		function getMembers() {
			return $http({
				'url': config.url + 'members',
				'method': 'GET'
			})
		}

		return data;
	});