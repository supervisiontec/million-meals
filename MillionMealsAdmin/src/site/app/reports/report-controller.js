/**
 * Created by nidura on 2018-02-21.
 */
(function () {
    angular.module("appModule")
        .factory("reportFactory", function ($http, systemConfig) {
            var factory = {};

            //find all branch
            factory.findAllBranch = function (callback) {
                var url = systemConfig.apiUrl + "/api/v1/millionMeals/master/get-all-branch";
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //find all main category
            factory.findAllMainCategory = function (callback) {
                var url = systemConfig.apiUrl + "/api/v1/millionMeals/master/get-all-mainCategory";
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //find all main category
            factory.findAllsubCategory = function (callback) {
                var url = systemConfig.apiUrl + "/api/v1/millionMeals/master/get-all-subCategory";
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };

            //search ProductWise
            factory.searchProductWise = function (fromDate, toDate, branch, mainCategory, subCategory, type, callback) {
                var url = systemConfig.apiUrl + "/api/v1/millionMeals/reports/product-wise/" + fromDate + "/" + toDate + "/" + branch + "/" + mainCategory + "/" + subCategory + "/" + type;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //invoice summery
            factory.getInvoiceSummery = function (fromDate, toDate, branch, callback) {
                var url = systemConfig.apiUrl + "/api/v1/millionMeals/reports/invoice-summery/" + fromDate + "/" + toDate + "/" + branch ;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };

            return factory;
        });

    //controller
    angular.module("appModule")
        .controller("reportController", function ($scope, reportFactory, $filter, Notification) {
            //data models
            $scope.model = {};
            $scope.http = {};
            $scope.ui = {};
            $scope.invoicesummery = {};

            ////////////product wise report//////////

            $scope.ui.SearchProductWise = function () {
                if ($scope.model.fromDate && $scope.model.toDate) {
                    reportFactory.searchProductWise($filter('date')($scope.model.fromDate, "yyyy-MM-dd"), $filter('date')($scope.model.toDate, "yyyy-MM-dd"),
                        $scope.model.branch, $scope.model.mainCategory, $scope.model.subCategory, $scope.model.type,
                        function (data) {
                            $scope.model.productWiseList = data;
                            $scope.getTotalQtyAndValues(data);
                        });
                } else {
                    Notification.error("insert required field");
                }
            };

            $scope.getTotalQtyAndValues = function (data) {
                $scope.totalQty = null;
                $scope.totalValue = null;
                angular.forEach(data, function (val) {
                    if ($scope.totalQty === null) {
                        $scope.totalQty = val[3]
                        $scope.totalValue = val[4]
                    } else {
                        $scope.totalQty += val[3];
                        $scope.totalValue += val[4]
                    }
                })

            };

            ////////// invoice summery ////////////////////

            $scope.ui.searchInvoiceSummery = function () {
                $scope.invoicesummery = {};
                if ($scope.model.invoiceFromDate && $scope.model.invoiceToDate) {
                    reportFactory.getInvoiceSummery($filter('date')($scope.model.invoiceFromDate, "yyyy-MM-dd"), $filter('date')($scope.model.invoiceToDate, "yyyy-MM-dd"),
                        $scope.model.invoiceBranch,
                        function (data) {
                            $scope.model.invoiceSummeryList = data;
                            $scope.getSummeryTotal(data);
                        });
                } else {
                    Notification.error("insert required field");
                }
            };

            $scope.getSummeryTotal = function (data) {
                angular.forEach(data, function (val) {
                    $scope.invoicesummery.totalItemValue = val[11];
                    $scope.invoicesummery.totalServiceCharge = val[12];
                    $scope.invoicesummery.totalDeliveryCharge = val[13];
                    $scope.invoicesummery.totalVat = val[14];
                    $scope.invoicesummery.totalNbt = val[15];
                    $scope.invoicesummery.totalTax = val[16];
                    $scope.invoicesummery.totalGross = val[17];
                    $scope.invoicesummery.totalDiscount = val[18];
                    $scope.invoicesummery.totalNet = val[19];
                    return;
                });
            };


            $scope.ui.init = function () {
                $scope.model.branch = null;
                $scope.model.invoiceBranch = null;
                $scope.model.mainCategory = null;
                $scope.model.subCategory = null;
                $scope.model.type = null;
                reportFactory.findAllBranch(function (data) {
                    $scope.model.branchList = data;
                });
                reportFactory.findAllMainCategory(function (data) {
                    $scope.model.mainCategoryList = data;
                });
                reportFactory.findAllsubCategory(function (data) {
                    $scope.model.subCategoryList = data;
                });
            };

            $scope.ui.init();
        });
}());