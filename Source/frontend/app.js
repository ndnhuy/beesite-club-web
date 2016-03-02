'use strict';

angular.module('app', ['ngRoute', 'app.services', 'app.routes', 'app.core', 'ngCookies', 'ngSanitize'])
    .filter('unsafe', function($sce) {

         return function(val) {
            return $sce.trustAsHtml(val);
        };

    });