angular.module("appModule")
    .factory("orderFactory", function ($http, systemConfig) {
        var factory = {};

        //find all main category
        factory.findAllMainCategory = function (callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/all-maincategory";
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };

        //find all sub category
        factory.findAllSubCategory = function (index, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/all-subcategory/" + index;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };

        //find all Items
        factory.findAllItems = function (index, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/all-items/" + index;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //find all Tables
        factory.findAllTables = function (callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/all-tables";
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //find products
        factory.findProductByItem = function (index, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/find-product/" + index;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //find table recieve order details
        factory.findTableOrderDetails = function (index, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/find-tables-recieve/" + index;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //save order
        factory.saveOrder = function (order, index,tableIndex, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/save-order/" + index+"/"+tableIndex;
            $http.post(url, order)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //update order details
        factory.updateOrder = function (item, index, subTotal, totalAmount, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/update-order/" + index + "/" + subTotal + "/" + totalAmount;
            $http.post(url, item)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //delete order details
        factory.deleteOrder = function (index, index2, subTotal, totalAmount, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/delete-order/" + index + "/" + index2 + "/" + subTotal + "/" + totalAmount;
            $http.delete(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //search by mobile no
        factory.searchByMobileNo = function (mobile, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/find-employee-mobile/" + mobile;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //print inovice
        factory.printInvoice = function (date,invoiceNo, callback) {
            var url = systemConfig.apiUrl + "/api/care-point/print-service/print-invoice/" + date +"/"+invoiceNo;
            $http.get(url)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //completeOrder
        factory.completeOrder = function (payment, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/payment/save";
            $http.post(url, payment)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };
        //save customer
        factory.saveCustomer = function (customer, callback) {
            var url = systemConfig.apiUrl + "/api/restaurant/order/save-customer";
            $http.post(url, customer)
                .success(function (data, status, headers) {
                    callback(data);
                })
                .error(function (data, status, headers) {

                });
        };

        return factory;
    });