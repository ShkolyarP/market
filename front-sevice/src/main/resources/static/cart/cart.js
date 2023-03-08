angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPathCart = 'http://localhost:5555/cart/api/v1';
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadCart = function () {
        $http.get(contextPathCart + '/cart/' + $localStorage.marketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.deleteProduct = function (productId) {
        $http.get(contextPathCart + '/cart/' + $localStorage.marketGuestCartId + '/remove/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteAllFromCart = function () {
        $http.get(contextPathCart + '/cart/' + $localStorage.marketGuestCartId + '/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.changeQuantity = function (productId, delta) {
        $http({
            url: contextPathCart + '/cart/' + $localStorage.marketGuestCartId + '/change_quantity',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.createOrder = function () {
        $http.post(contextPath + '/orders')
            .then(function (response) {
                alert('Заказ оформлен')
                $scope.loadCart();
            });
    }
    $scope.loadCart();
});