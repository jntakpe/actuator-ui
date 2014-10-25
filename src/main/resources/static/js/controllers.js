auiApp.controller('HomeController', ['$scope', function ($scope) {
    "use strict";
}]);

auiApp.controller('LoginController', ['$scope', '$location', 'AuiAuthService',
    function ($scope, $location, AuiAuthService) {
        "use strict";
        $scope.login = function () {
            AuiAuthService.login($scope.username, $scope.password, $scope.rememberMe, function () {
                $location.path('/home');
            });
        };
    }]);

auiApp.controller('LogoutController', ['$scope', '$location', 'AuiAuthService',
    function ($scope, $location, AuiAuthService) {
        "use strict";
        AuiAuthService.logout(function () {
            $location.path('');
        });
    }]);