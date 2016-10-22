//js/restapp.js

(function() {
    var app = angular.module('restapp', ['ngRoute']);

    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.
        when('/home', {
                templateUrl: 'partials/main.html',
                controller: 'MainController'
            })
	.when('/developer', {
 		templateUrl: 'partials/developers.html',
  		controller: 'DevelopersController'
  	})
            .otherwise({
                redirectTo: '/home'
            });
    }]);


    app.controller('MainController', ['$scope', function($scope) {

    }]);


    /** DEVELOPERS **/
    app.controller('DevelopersController', ['$scope', 'DevelopersService', function($scope, DevelopersService) {

    function resetParams() {
        $scope.actionName = "New Developer";
        $scope.submitButtonLabel = "Insert";
        $scope.currentDeveloper = {
            id: null,
            "name": null
        };
        $scope.isFormClean = true;
        $scope.name = "";
    }

    function developerList() {
        DevelopersService.list()
            .then(function(response) {
                $scope.developers = response.data;
            });
    }

    function resetParamsAndLoadDeveloperList() {
        resetParams();
        developerList();
    }

    $scope.selectDeveloper = function(developer) {
        $scope.actionName = "Update Developer";
        $scope.submitButtonLabel = "Update";
        $scope.currentDeveloper = developer;
        $scope.name = $scope.currentDeveloper.name;
        $scope.isFormClean = false;
    };

    $scope.submit = function() {
        if ($scope.isFormClean) {
            $scope.insert($scope.name);
        } else  {
            $scope.currentDeveloper.name = $scope.name;
            $scope.update($scope.currentDeveloper);
        }
        resetParamsAndLoadDeveloperList();
    };

    $scope.insert = function(name) {
        DevelopersService.insert(name)
            .then(function(response) {
                resetParamsAndLoadDeveloperList();
            });
    };

    $scope.update = function(developer) {
        DevelopersService.update(developer)
            .then(function(response) {
                resetParamsAndLoadDeveloperList();
            });
    };

    $scope.delete = function(developerId) {
        DevelopersService.delete(developerId)
            .then(function(response) {
                resetParamsAndLoadDeveloperList();
            });
    };

    $scope.cancel = function() {
        resetParams();
    };

    resetParamsAndLoadDeveloperList();

}]);

app.service('DevelopersService', function($http) {
    var service = this;
    var path = '/developer/';

    function getPath() {
        return path;
    }

    function getUrl(action) {
        return getPath() + action;
    }

    service.list = function() {
        return $http.get(getUrl('list'));
    };

    service.insert = function(name) {
        return $http.post(getUrl('create'), name);
    };

    service.update = function(developer) {
        return $http.put(getUrl('update/' + developer.id), developer);
    };

    service.delete = function(id) {
        return $http.delete(getUrl('delete/' + id), {
            'id': id
        });
    };
});
    
    /** BUGS **/

    /** STORIES **/

    /** ASSIGNMENTS **/


})();
