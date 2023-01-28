angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token}

                    $scope.user.username = null;
                    $scope.user.password = null;
                    //
                    // $http.get(contextPath + '/cart/' + $localStorage.springWebGuestCartId + '/merge')
                    //     .then(function successCallback(response) {
                    //     });
                    //
                    // $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        // $location.path('/');
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


    if ($localStorage.marketUser){
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(otob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp){
                console.log("Token is expired!!!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e){
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
    }


    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null

            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.filter = null
            });
    };
    $scope.loadCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }


    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/cart/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteAllFromCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPath + '/cart/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadProducts();
    $scope.loadCart();

});