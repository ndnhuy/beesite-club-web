'use strict';

angular
	.module('app.routes', ['ngRoute'])
	.config(config);

function config($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: 'sections/home-page/homepage.html',
			controller: 'HomeController as homeCtrl'
		})
		.when('/articles', {
			templateUrl: 'sections/article/events_main.html',
			controller: 'ArticleController as articleCtrl',
			resolve: {
				articles: function(ArticleService) {
					return ArticleService.getArticles();
				}
 			}
		})
		.when('/members', {
			templateUrl: 'sections/member/member.html',
			controller: 'MemberController as memberCtrl',
			resolve: {
				membersJSON: function(MemberService) {
					return MemberService.getMembers();
				}
 			}
		})
		.when('/articles/:id', {
			templateUrl: 'sections/article-detail/article-details.html',
			controller: 'ArticleDetailController as articleDetailCtrl',
			resolve: {
				article: function(ArticleService, $route) {
					return ArticleService.getArticleById($route.current.params.id);
				}
 			}
		})
		.when('/admin/articles', {
			templateUrl: 'sections/admin/admin.html',
			controller: 'ArticleManagementController as articleManagementCtrl',
			resolve: {
				articles: function(ArticleService) {
					return ArticleService.getArticles();
				}
			}
			
		})
		.when('/admin/articles/:id/update', {
			templateUrl: 'sections/update-article/update.html',
			controller: 'UpdateController as updateCtrl',
			resolve: {
				article: function(ArticleService, $route) {
					return ArticleService.getArticleById($route.current.params.id);
				}
			}
			
		})
		.when('/admin/articles/create', {
			templateUrl: 'sections/write-article/new-article.html',
			controller: 'NewArticleController as newArticleCtrl'
			
		})
		.when('/news', {
			templateUrl: 'sections/news/news.html',
			controller: 'NewsController as newsCtrl',
			resolve: {
				news: function(NewsService) {
					console.log("loading...");
					return NewsService.getNews();
				}
 			}
		})
		.when('/about', {
			templateUrl: 'sections/about/about.html',
			controller: 'AboutController as aboutCtrl'
		})
		.otherwise({
            redirectTo: '/'
        });
}