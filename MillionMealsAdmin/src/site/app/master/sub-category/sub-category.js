(function () {
    //controller
    angular.module("appModule")
            .controller("subCategoryController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //SubCategory List
                $scope.model.subCategoryList = [];
                //SubCategory Model
                $scope.model.subCategory = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#name")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.subCategory = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (subCategory, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.subCategory = subCategory;
                    $scope.model.subCategoryList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete SubCategory
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-subCategory/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.subCategoryList.splice(index, 1);
                                Notification.success(indexNo + " - " + "SubCategory Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save SubCategory
                $scope.ui.save = function () {
                    var path = "/save-subCategory";
                    var detail = $scope.model.subCategory;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.subCategoryList.push(data);
                                Notification.success(data.indexNo + " - " + "SubCategory Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All SubCategory
                $scope.ui.findAll = function () {
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
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                };
                
                //Call Function
                $scope.ui.init();
            });
}());