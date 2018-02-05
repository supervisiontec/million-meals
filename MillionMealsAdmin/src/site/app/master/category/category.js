(function () {
    //controller
    angular.module("appModule")
            .controller("categoryController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //CategoryList
                $scope.model.categoryList = [];
                //Client Model
                $scope.model.category = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#name")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.category = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (customer, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.category = customer;
                    $scope.model.categoryList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Category
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-category/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.categoryList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Category Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Category
                $scope.ui.save = function () {
                    var path = "/save-category";
                    var detail = $scope.model.category;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.categoryList.push(data);
                                Notification.success(data.indexNo + " - " + "Category Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Category
                $scope.ui.findAll = function () {
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
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                };
                
                //Call Function
                $scope.ui.init();
            });
}());