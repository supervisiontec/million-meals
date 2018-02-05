(function () {
    //controller
    angular.module("appModule")
            .controller("customerController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //ClientList
                $scope.model.customerList = [];
                //Client Model
                $scope.model.customer = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#title")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.customer = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (customer, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.customer = customer;
                    $scope.model.customerList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Customer
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-customer/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.customerList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Customer Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Customer
                $scope.ui.save = function () {
                    var path = "/save-customer";
                    var detail = $scope.model.customer;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.customerList.push(data);
                                Notification.success(data.indexNo + " - " + "Customer Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Customer
                $scope.ui.findAll = function () {
                    var path = "/get-all-customer";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.customerList = data;
                            },
                            function (data) {
                                console.log("Error find All Customer");
                            }
                    );
                };
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                };
                
                //Call Function
                $scope.ui.init();
            });
}());