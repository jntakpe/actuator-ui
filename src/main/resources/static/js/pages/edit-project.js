var editProjectApp = angular.module('editProjectApp', ['ngMessages']);

editProjectApp.service('dataService', ['$http', function ($http) {
    "use strict";
    this.testActuator = function (url) {
        return $http.get('actuator/', {params: {url: url}});
    };

    this.testVersion = function (url) {
        return $http.get('version/', {params: {url: url}});
    };
}]);

editProjectApp.controller('formCtrl', ['$scope', 'dataService', function ($scope, dataService) {
    "use strict";

    $scope.testActuatorUrl = function (url) {
        dataService.testActuator(url).success(function (data) {
            console.log(data);
        });
    };

    $scope.testVersionUrl = function (url) {
        dataService.testVersion(url).success(function (data) {
            $scope.version = data;
        });
    };

    $scope.$watch('useVersionUrl', function () {
        $scope.version = '';
    });
}]);