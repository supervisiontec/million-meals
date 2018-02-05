(function () {
    //controller
    angular.module("appModule")
            .controller("productController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //Product List
                $scope.model.productList = [];
                //Item List
                $scope.model.itemList = [];
                //Unit List
                $scope.model.unitList = [];
                
                //Item Model
                $scope.model.product = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#item")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.product = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (product, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.product = product;
                    $scope.model.productList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Item
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-product/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.productList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Item Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Item
                $scope.ui.save = function () {
                    var path = "/save-product";
                    var detail = $scope.model.product;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.productList.push(data);
                                Notification.success(data.indexNo + " - " + "Item Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Product
                $scope.ui.findAll = function () {
                    var path = "/get-all-product";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.productList = data;
                            },
                            function (data) {
                                console.log("Error find All product");
                            }
                    );
                };
                //find All ItemUnit
                $scope.ui.findAllItemUnit = function () {
                    var path = "/get-all-unit";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.unitList = data;
                            },
                            function (data) {
                                console.log("Error find All Unit");
                            }
                    );
                };
                //find All Item
                $scope.ui.findAllItem = function () {
                    var path = "/get-all-item";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.itemList = data;
                            },
                            function (data) {
                                console.log("Error find All Item");
                            }
                    );
                };
                //ItemUnit label for get selected ItemUnit name
                $scope.ui.itemUnitLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.unitList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
                };
                //Item label for get selected Item name
                $scope.ui.itemLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.itemList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
                };
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                    $scope.ui.findAllItemUnit();
                    $scope.ui.findAllItem();
                };

                //Call Function
                $scope.ui.init();
            });
}());