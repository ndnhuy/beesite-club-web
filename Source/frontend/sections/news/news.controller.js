'use strict';

angular
    .module('app.core')
    .controller('NewsController', function($window, $scope, $log, news) {
        var vm = this;
        
        vm.news = news;


        console.log(JSON.stringify(vm.news));
    });