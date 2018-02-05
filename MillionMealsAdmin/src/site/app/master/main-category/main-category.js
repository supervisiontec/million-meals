(function () {
    //controller
    angular.module("appModule")
            .controller("mainCategoryController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //MainCategoryList
                $scope.model.mainCategoryList = [];
                //MainCategory Model
                $scope.model.mainCategory = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#name")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.mainCategory = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (mainCategory, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.mainCategory = mainCategory;
                    $scope.model.mainCategoryList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete MianCategory
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-mainCategory/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.mainCategoryList.splice(index, 1);
                                Notification.success(indexNo + " - " + "MainCategory Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save MainCategory
                $scope.ui.save = function () {
                    var path = "/save-mainCategory";
                    var detail = $scope.model.mainCategory;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.mainCategoryList.push(data);
                                Notification.success(data.indexNo + " - " + "MainCategory Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All MainCategory
                $scope.ui.findAll = function () {
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
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                };
                
                //Call Function
                $scope.ui.init();
            });
}());