angular.module('market').controller('ordersController', function ($scope, $http) {

    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.orders = response.data;
            });
    };

    $scope.loadOrders();
});