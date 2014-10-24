var auiApp = angular.module('auiApp', ['ngRoute', 'ngResource', 'http-auth-interceptor']);

auiApp.config(['$routeProvider',
    function ($routeProvider) {
        "use strict";
        $routeProvider
            .when('/home', {templateUrl: 'views/home.html', controller: 'HomeController'})
            .when('/', {templateUrl: 'views/login.html', controller: 'LoginController'})
            .when('/logout', {templateUrl: 'views/login.html', controller: 'LogoutController'})
            .when('/projects', {templateUrl: 'views/project.html', controller: 'ProjectController'})
            .otherwise({redirectTo: '/home'});
    }]).run(['$rootScope', '$location', 'AuiAuthService', 'UserResource',
    function ($rootScope, $location, AuiAuthService, UserResource) {
        "use strict";
        //Fonction utilisée lors d'un changement d'url pour vérifier si l'utilisateur est authentifié.
        $rootScope.$on("$routeChangeStart", function () {
            AuiAuthService.authenticate(function () {
                $rootScope.authOK = true;
            });
        });

        //Fonction utilisée lorsque le serveur renvoi le code 401
        $rootScope.$on('event:auth-loginRequired', function () {
            $rootScope.authOK = false;
            if ($location.path() !== '/' && $location.path() !== "") {
                $location.path('/').replace();
            }
        });

        //Fonction utilisée lorsque l'utilisateur s'est authentifié
        $rootScope.$on('event:auth-authConfirmed', function () {
            $rootScope.authOK = true;
            $rootScope.user = UserResource.get();
            if ($location.path() === '/') { //Si la page de login est demandée alors que l'utilisateur est déjà loggé
                $location.path('/home').replace();
            }
        });

        //Fonction utilisée lorsque l'utilisateur se connecte
        $rootScope.$on('event:auth-loginConfirmed', function () {
            $rootScope.authOK = true;
            $rootScope.user = UserResource.get();
            $location.path('/home').replace();
        });

        //Fonction utilisée lorsque l'utilisateur se déconnecte
        $rootScope.$on('event:auth-loginCancelled', function () {
            $rootScope.authOK = false;
            $location.path('/');
        });
    }]);