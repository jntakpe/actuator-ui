auiApp.factory('UserResource', ['$resource', function ($resource) {
    "use strict";
    return $resource('user');
}]);

auiApp.factory('AuiAuthService', ['$rootScope', '$http', 'authService', function ($rootScope, $http, authService) {
    "use strict";
    return {
        /**
         * Vérifie si l'utilisateur est connecté
         * @param [authOK] callback en cas d'authentification
         * @param [authKO] callback en cas d'utilisateur non autorisé
         */
        authenticate: function (authOK, authKO) {
            $http.get('user')
                .success(function (data) {
                    if (data.login) {
                        $rootScope.$broadcast('event:auth-authConfirmed');
                        if (authOK) {
                            authOK(data);
                        }
                    } else {
                        $rootScope.$broadcast('event:auth-loginRequired');
                        if (authKO) {
                            authKO();
                        }
                    }
                });
        },

        /**
         * Tentative de connexion (envoi formulaire de login)
         * @param username valeur du nom de l'utilisateur
         * @param password valeur du mot de passe
         * @param rememberMe valeur de la checkbox 'Rester connecté ?'
         * @param [loginOK] callback en cas de réussite de l'authentification
         * @param [loginFail] callback en cas d'échec de l'authentification
         */
        login: function (username, password, rememberMe, loginOK, loginFail) {
            var data = "username=" + username + "&password=" + password + "&spring_security_remember_me=" +
                rememberMe + "&submit=Login";
            $http.post('login', data, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                ignoreAuthModule: 'ignoreAuthModule'
            }).success(function (data, status, headers, config) {
                $rootScope.authenticationError = false;
                authService.loginConfirmed();
                if (loginOK) {
                    loginOK(data, status, headers, config);
                }
            }).error(function (data, status, headers, config) {
                $rootScope.authenticationError = true;
                if (loginFail) {
                    loginFail(data, status, headers, config);
                }
            });
        },

        /**
         * Tentative de déconnexion
         * @param [lougoutOK] callback de déconnexion
         */
        logout: function (lougoutOK) {
            $rootScope.authenticationError = false;
            $http.get('logout').success(function (data, status, headers, config) {
                authService.loginCancelled();
                if (lougoutOK) {
                    lougoutOK(data, status, headers, config);
                }
            });
        }
    };
}]);