var editProjectApp = angular.module('editProjectApp', []);

editProjectApp.service('dataService', ['$http', function ($http) {
    "use strict";
    this.testActuator = function (url) {
        return $http.get('actuator/', {params: {url: url}});
    };
}]);

editProjectApp.controller('formCtrl', ['$scope', 'dataService', function ($scope, dataService) {
    "use strict";

    $scope.testUrl = function (url) {
        dataService.testActuator(url)
            .success(function (data) {
                console.log(data);
            }).error(function (data) {

            });
    };
}]);