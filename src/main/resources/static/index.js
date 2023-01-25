angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';

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
        $http.get(contextPath + '/cart/delete/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.deleteAllFromCart = function () {
        $http.get(contextPath + '/cart/delete')
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