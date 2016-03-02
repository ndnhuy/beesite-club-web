
angular
	.module('app.services')
	.factory('NewsService', function($http, $log, $cookies, config) {
		var data = {
			'getNews': getNews
		};

		function getNews() {
			return $http.jsonp("https://feeds.bbci.co.uk/sport/0/rss.xml")
			.success(function(data) {
				console.log("DONE");
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for ArticleService');
	        $log.error(errorResponse);

	        return errorResponse;
	    }

		return data;
	});