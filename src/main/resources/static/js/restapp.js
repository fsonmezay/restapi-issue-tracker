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
		  	.when('/bug', {
                templateUrl: 'partials/bugs.html',
                controller: 'BugsController'
            })
            .when('/bug/:bugId', {
                templateUrl: 'partials/bug-detail.html',
                controller: 'BugsController'
            })
            .when('/story', {
                templateUrl: 'partials/stories.html',
                controller: 'StoriesController'
            })
            .when('/story/:storyId', {
                templateUrl: 'partials/story-detail.html',
                controller: 'StoriesController'
            })
            .when('/assign', {
                templateUrl: 'partials/issue-assignment.html',
                controller: 'IssueAssignmentController'
            })
            .otherwise({
                redirectTo: '/home'
            });
    }]);


    app.controller('MainController', ['$scope', function($scope) {

    }]);


    /** DEVELOPERS * */
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
app.controller('BugsController', ['$scope', '$routeParams', '$location', 'BugsService', 'DevelopersService', function($scope, $routeParams, $location, BugsService, DevelopersService) {

    function resetParams() {
        $scope.actionName = "New Bug";
        $scope.submitButtonLabel = "Insert";
        $scope.currentBug = {
            id: null,
            "name": null
        };
        $scope.isFormClean = true;
        $scope.bug = {
            'priority': 1,
            'status': 1
        };
    }

    function bugList() {
        BugsService.list()
            .then(function(response) {
                $scope.bugs = response.data;
            });
    }

    function resetParamsAndLoadBugList() {
        resetParams();
        bugList();
    }

    function getDevelopers() {
        DevelopersService.list()
            .then(function(response) {
                $scope.developers = response.data;
            });
    }

    function getPriorityList() {
        BugsService.prioritylist()
            .then(function(response) {
                $scope.priorities = response.data;
            });
    }

    function getStatusList() {
        BugsService.statuslist()
            .then(function(response) {
                $scope.statuses = response.data;
            });
    }

    $scope.selectBug = function(bug) {
        $scope.actionName = "Update Bug";
        $scope.submitButtonLabel = "Update";
        $scope.currentBug = bug;
        $scope.bug = bug;
        $scope.isFormClean = false;
        $scope.selectedDeveloperId = bug.developerId;
    };

    $scope.submit = function() {
        if ($scope.isFormClean) {
            $scope.insert($scope.bug);
        } else  {
            $scope.currentBug = $scope.bug;
            $scope.update($scope.currentBug);
        }
        resetParamsAndLoadBugList();
    };

    $scope.insert = function(bug) {
        BugsService.insert(bug)
            .then(function(response) {
                resetParamsAndLoadBugList();
            });
    };

    $scope.update = function(bug) {
        BugsService.update(bug)
            .then(function(response) {
                resetParamsAndLoadBugList();
            });
    };

    $scope.getBug = function(bugId) {
        BugsService.getBug(bugId)
            .then(function(response) {
                $scope.detailBug = response.data;
            });
    };

    $scope.delete = function(bugId) {
        BugsService.delete(bugId)
            .then(function(response) {
                resetParamsAndLoadBugList();
            });
    };

    $scope.cancel = function() {
        resetParams();
    }

    function checkRouteParams() {
        if ($routeParams.bugId) {
            $scope.getBug($routeParams.bugId);
        }
    }

    resetParamsAndLoadBugList();
    getDevelopers();
    getPriorityList();
    getStatusList();
    checkRouteParams();

}]);


app.service('BugsService', function($http) {
    var service = this;
    var path = '/bug/';

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

    service.update = function(bug) {
        return $http.put(getUrl('update/' + bug.id), bug);
    };

    service.delete = function(id) {
        return $http.delete(getUrl('delete/' + id), {
            'id': id
        });
    };

    service.getBug = function(id) {
        return $http.get(getUrl(id));
    };

    service.statuslist = function() {
        return $http.get(getUrl('list/status'));
    };

    service.prioritylist = function() {
        return $http.get(getUrl('list/priorities'));
    };
});


/** STORIES **/
app.controller('StoriesController', ['$scope', '$routeParams', 'StoriesService', 'DevelopersService', function($scope, $routeParams, StoriesService, DevelopersService) {

    function resetParams() {
        $scope.actionName = "New Story";
        $scope.submitButtonLabel = "Insert";
        $scope.currentStory = {};
        $scope.isFormClean = true;
        $scope.story = {
            'status': 1
        };
    }

    function storyList() {
        StoriesService.list()
            .then(function(response) {
                $scope.stories = response.data;
            });
    }

    function resetParamsAndLoadStoryList() {
        resetParams();
        storyList();
    }

    function getDevelopers() {
        DevelopersService.list()
            .then(function(response) {
                $scope.developers = response.data;
            });
    }

    function getPointList() {
        StoriesService.pointlist()
            .then(function(response) {
                $scope.points = response.data;
            });
    }

    function getStatusList() {
        StoriesService.statuslist()
            .then(function(response) {
                $scope.statuses = response.data;
            });
    }

    $scope.selectStory = function(story) {
        $scope.actionName = "Update Bug";
        $scope.submitButtonLabel = "Update";
        $scope.currentStory = story;
        $scope.story = story;
        $scope.isFormClean = false;
        $scope.selectedDeveloperId = story.developerId;
    };

    $scope.submit = function() {
        if ($scope.isFormClean) {
            $scope.insert($scope.story);
        } else  {
            $scope.currentStory = $scope.story;
            $scope.update($scope.currentStory);
        }
        resetParamsAndLoadStoryList();
    };

    $scope.insert = function(story) {
        StoriesService.insert(story)
            .then(function(response) {
                resetParamsAndLoadStoryList();
            });
    };

    $scope.update = function(story) {
        StoriesService.update(story)
            .then(function(response) {
                resetParamsAndLoadStoryList();
            });
    };

    $scope.getStory = function(storyId) {
        StoriesService.getStory(storyId)
            .then(function(response) {
                $scope.detailStory = response.data;
            });
    };

    $scope.delete = function(storyId) {
        StoriesService.delete(storyId)
            .then(function(response) {
                resetParamsAndLoadStoryList();
            });
    };

    $scope.cancel = function() {
        resetParams();
    }

    function checkRouteParams() {
        if ($routeParams.storyId) {
            $scope.getStory($routeParams.storyId);
        }
    }


    resetParamsAndLoadStoryList();
    getDevelopers();
    getPointList();
    getStatusList();
    checkRouteParams();

}]);


app.service('StoriesService', function($http) {
    var service = this;
    var path = '/story/';

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

    service.update = function(story) {
        return $http.put(getUrl('update/' + story.id), story);
    };

    service.delete = function(id) {
        return $http.delete(getUrl('delete/' + id), {
            'id': id
        });
    };

    service.getStory = function(id) {
        return $http.get(getUrl(id));
    };

    service.statuslist = function() {
        return $http.get(getUrl('list/status'));
    };

    service.pointlist = function() {
        return $http.get(getUrl('list/point'));
    };
});

/** Make Assignment **/
app.controller('IssueAssignmentController', ['$scope', 'IssueAssignmentService', 'DevelopersService', function($scope, IssueAssignmentService, DevelopersService) {

    $scope.assignmentVariables = {};
    $scope.assignmentResult = null;

    $scope.variables = function() {
        IssueAssignmentService.view()
            .then(function(response) {
                $scope.assignmentVariables = response.data;
            });
    };

    $scope.makeAssignment = function() {
        IssueAssignmentService.make()
            .then(function(response) {
                $scope.getAssignmentList();
                $scope.getSummary();
            });
    };

    $scope.getSummary = function() {
        IssueAssignmentService.getsummary()
            .then(function(response) {
                $scope.assignmentVariables = response.data;
            });
    };

    $scope.getAssignmentList = function() {
        IssueAssignmentService.getassignmentlist()
            .then(function(response) {
                $scope.assignmentResult = response.data;
            });
    };

    function getDevelopers() {
        DevelopersService.list()
            .then(function(response) {
                $scope.developers = response.data;
            });
    }

    $scope.getSummary();
    $scope.getAssignmentList();
    getDevelopers();

}]);


app.service('IssueAssignmentService', function($http) {
    var service = this;
    var path = '/assign/';

    function getPath() {
        return path;
    }

    function getUrl(action) {
        return getPath() + action;
    }

    service.make = function() {
        return $http.get(getUrl('make'));
    };

    service.getassignmentlist = function() {
        return $http.get(getUrl('list'));
    };

    service.getsummary = function() {
        return $http.get(getUrl('summary'));
    };

});


})();
