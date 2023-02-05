angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    if ($localStorage.marketUser) {
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        if ($localStorage.marketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
        }
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market-core/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market-core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.loadCart = function () {
        $http.get('http://localhost:8190/market-cart/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:8190/market-cart/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.createOrder = function () {
        $http.post('http://localhost:8189/market-core/api/v1/orders')
            .then(function (response) {
                alert("Order was created!");
            });
    }

    $scope.clearCart = function () {
        $http.get('http://localhost:8190/market-cart/api/v1/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.loadProducts();
    $scope.loadCart();
});