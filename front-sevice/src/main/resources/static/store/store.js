angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPathCart = 'http://localhost:5555/cart/api/v1';
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                p: pageIndex,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null

            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.filter = null;
                $scope.generatePagesList($scope.ProductsPage.totalPages);
            });
    };


    $scope.addToCart = function (productId) {
        $http.get(contextPathCart + '/cart/' + $localStorage.marketGuestCartId + '/add/' + productId)
            .then(function (response) {

            });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.loadProducts();

});