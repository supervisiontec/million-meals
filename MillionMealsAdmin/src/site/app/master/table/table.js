(function () {
    //controller
    angular.module("appModule")
            .controller("tableController",function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //Table List
                $scope.model.tableList = [];
                //Table Model
                $scope.model.table = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#no")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.table = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (customer, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.table = customer;
                    $scope.model.tableList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Table
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-table/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.tableList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Table Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Table
                $scope.ui.save = function () {
                    var path = "/save-table";
                    var detail = $scope.model.table;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.tableList.push(data);
                                Notification.success(data.indexNo + " - " + "Table Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Table
                $scope.ui.findAll = function () {
                    var path = "/get-all-table";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.tableList = data;
                            },
                            function (data) {
                                console.log("Error find All Table");
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