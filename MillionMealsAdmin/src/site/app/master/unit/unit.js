(function () {
    //controller
    angular.module("appModule")
            .controller("unitController", function ($scope, factory, Notification, $timeout) {
                //Data Models
                $scope.model = {};
                //UI Models
                $scope.ui = {};
                //UI Mode
                $scope.ui.mode = null;
                //Unit List
                $scope.model.unitList = [];
                //Unit Model
                $scope.model.unit = {};

                //Focus
                $scope.ui.focus = function () {
                    $timeout(function () {
                        document.querySelectorAll("#name")[0].focus();
                    }, 10);
                };
                //Reset Model
                $scope.model.reset = function () {
                    $scope.model.unit = {};
                };
                //New Function
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";

                    $scope.ui.focus();
                };
                //Edit Funtion
                $scope.ui.edit = function (unit, index) {
                    $scope.ui.mode = "EDIT";
                    $scope.model.unit = unit;
                    $scope.model.unitList.splice(index, 1);

                    $scope.ui.focus();
                };
                //Delete Unit
                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-unit/" + indexNo;
                    factory.delete(path,
                            function () {
                                $scope.model.unitList.splice(index, 1);
                                Notification.success(indexNo + " - " + "Unit Delete Successfully");
                            },
                            function (data) {
                                Notification.error(data);
                            });
                };
                //Save Unit
                $scope.ui.save = function () {
                    var path = "/save-unit";
                    var detail = $scope.model.unit;
                    var detailJSON = JSON.stringify(detail);
                    factory.save(path, detailJSON,
                            function (data) {
                                $scope.model.unitList.push(data);
                                Notification.success(data.indexNo + " - " + "Unit Save Successfully");
                                $scope.model.reset();
                            },
                            function (data) {
                                Notification.error(data.message);
                            }
                    );
                };
                //find All Unit
                $scope.ui.findAll = function () {
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
                
                //Init Function
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAll();
                };
                
                //Call Function
                $scope.ui.init();
            });
}());