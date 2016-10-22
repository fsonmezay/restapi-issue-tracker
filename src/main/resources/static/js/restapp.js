//js/restapp.js

(function() {
    var app = angular.module('restapp', ['ngRoute']);

    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.
        when('/home', {
                templateUrl: 'partials/main.html',
                controller: 'MainController'
            })
            .otherwise({
                redirectTo: '/home'
            });
    }]);


    app.controller('MainController', ['$scope', function($scope) {

    }]);


    /** DEVELOPERS **/

    /** BUGS **/

    /** STORIES **/

    /** ASSIGNMENTS **/


})();
