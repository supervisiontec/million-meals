(function () {
    //controller
    angular.module("appModule")
            .controller("itemController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //Item List
                $scope.model.itemList = [];
                //Category List
                $scope.model.categoryList = [];
                //SubCategory List
                $scope.model.subCategoryList = [];
                //MainCategory List
                $scope.model.mainCategoryList = [];

                //Item Model
                $scope.model.item = {};

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
                                console.log(data);
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
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                    $scope.ui.findAllCategory();
                    $scope.ui.findAllMainCategory();
                    $scope.ui.findAllSubCategory();
                };

                //Call Function
                $scope.ui.init();
            });
}());