
angular
	.module('app.services')
	.factory('ArticleService', function($http, $log, $cookies, config) {
		var data = {
			'getArticles': getArticles,
			'getArticleById': getArticleById,
			'postCommentOnArticle': postCommentOnArticle,
			'deleteArticleById': deleteArticleById,
			'createArticle': createArticle
		};

		function getArticles() {
			return $http({
				'url': config.url + 'articles',
				'method': 'GET'
			})
		}

		function getArticleById(id) {
			return $http({
				'url': config.url + 'articles/' + id,
				'method': 'GET'
			})
			.catch(dataServiceError);
		}

		function postCommentOnArticle(articleId, comment) {
			return $http({
				'url': config.url + 'articles/' + articleId +'/comments?content=' + comment,
				'method': 'POST',
				'headers': {
					'Authorization': 'bearer ' + $cookies.get('access-token')
				}
			})
			.catch(dataServiceError);
		}
		
		function deleteArticleById(articleId) {
			$log.info("DELETE article " + config.url + 'articles/' + articleId + '?delete=true');
			return $http({
				'url': config.url + 'articles/' + articleId + '?delete=true',
				'method': 'POST'
			})
			.catch(dataServiceError);
		}

		function createArticle(id, title, content, tag, image) {
			$log.info("UPDATE article localhost:8182/articles");
			return $http({
				'url': config.url + 'articles',
				'method': 'POST',
				'data': {
					'id': id,
					'title': title,
					'content': content,
					'tag': tag,
					'image': image
				}
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for ArticleService');
	        $log.error(errorResponse);
	        // if (errorResponse.data.developerMessage != null)
	        // 	alert(errorResponse.data.developerMessage);
	        return errorResponse;
	    }

		return data;
	});