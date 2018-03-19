(function () {
    //controller
    angular.module("appModule")
            .controller("itemController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                $scope.model.item = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                $scope.ui.stockType = null;
                //Data List
                $scope.model.itemList = [];
                $scope.model.categoryList = [];
                $scope.model.subCategoryList = [];
                $scope.model.mainCategoryList = [];

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#name")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.item = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.setStockType = function (type) {
                    if (type === "row_item" || type ==="finish/row_item") {

                        $scope.ui.stockType = "row_item";

                        $scope.ui.focus = function () {
                            $timeout(function () {
                                document.querySelectorAll("#unit")[0].focus();
                            }, 10);
                        };
                    }else{
                        $scope.ui.stockType = "finish_item";
                    }
                };
                $scope.ui.edit = function (item, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.item = item;
                    $scope.model.itemList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Item
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-item/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.itemList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Item Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Item
                $scope.ui.save = function () {
                    var path = "/save-item";
                    var detail = $scope.model.item;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.itemList.push(data);
                                Notification.success(data.indexNo + " - " + "Item Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Item
                $scope.ui.findAll = function () {
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
                //find All Category
                $scope.ui.findAllCategory = function () {
                    var path = "/get-all-category";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.categoryList = data;
                            },
                            function (data) {
                                console.log("Error find All Category");
                            }
                    );
                };
                //find All MainCategory
                $scope.ui.findAllMainCategory = function () {
                    var path = "/get-all-mainCategory";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.mainCategoryList = data;
                            },
                            function (data) {
                                console.log("Error find All MainCategory");
                            }
                    );
                };
                //find All SubCategory
                $scope.ui.findAllSubCategory = function () {
                    var path = "/get-all-subCategory";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.subCategoryList = data;
                            },
                            function (data) {
                                console.log("Error find All SubCategory");
                            }
                    );
                };
                //MainCategory label for get selected mainCategory name
                $scope.ui.mainCategoryLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.mainCategoryList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
                };
                //SubCategory label for get selected subCategory name
                $scope.ui.subCategoryLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.subCategoryList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
                };
                //Category label for get selected category name
                $scope.ui.categoryLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.categoryList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
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
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.stockType = "finish_item";
                    $scope.ui.findAll();
                    $scope.ui.findAllCategory();
                    $scope.ui.findAllMainCategory();
                    $scope.ui.findAllSubCategory();
                    $scope.ui.findAllItemUnit();
                };

                //Call Function
                $scope.ui.init();
            });
}());