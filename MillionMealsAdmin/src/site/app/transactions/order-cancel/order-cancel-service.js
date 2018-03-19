(function () {
    var service = function (systemConfig, $http) {
        
        this.findOrderByOrderNo = function (orderNo) {
            return $http.get(systemConfig.apiUrl + "/api/v1/millionMeals/transasction/order/find-orders-by-orderNo/"+orderNo);
        };
        this.canscelOrderDetailbyIndexNo = function (model) {
            return $http.post(systemConfig.apiUrl + "/api/v1/millionMeals/transasction/order-detail/cansel-orders-detail" ,model);
        };
        this.saveOrderDetail = function (model) {
            return $http.post(systemConfig.apiUrl + "/api/v1/millionMeals/transasction/order/save-orders" ,model);
        };
        
    };
    angular.module("appModule")
            .service("orderCanselService", service);
}());