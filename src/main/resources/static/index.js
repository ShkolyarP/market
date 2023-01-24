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

    $scope.addToCart = function (id) {
        $http.get(contextPath + '/cart/' + id)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    }

    $scope.loadProducts();
});