/**
 * Created by nidura on 2018-02-27.
 */
(function () {
    //controller
    angular.module("appModule")
        .controller("userPrivilegeController",function ($scope, factory, Notification, $timeout) {
            //Data Models
            $scope.model = {};
            //UI Models
            $scope.ui = {};

            //Focus
            $scope.ui.focus = function () {
                $timeout(function () {
                    document.querySelectorAll("#no")[0].focus();
                }, 10);
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

            //find All privileges by user type
            $scope.ui.findPrivilegeByUserType = function (typeIndex) {
                var path = "/get-all-privileges/"+typeIndex;
                factory.findByIndex(path,
                    function (data) {
                        $scope.model.privilegesList = data;
                    }
                );
            };

            //approvel
            $scope.ui.approvel = function (indexNo,status) {
                if(angular.isUndefined(status)){
                    status = false;
                }
                var path = "/user-approvel/"+indexNo+"/"+status;
                factory.updateUserApprovel(path,
                    function (data) {
                    console.log(data)
                    }
                );
            };
            //find All  user types
            $scope.ui.findAllUserTypes = function () {
                var path = "/get-all-usertypes";
                factory.findAll(path,
                    function (data) {
                        $scope.model.userTypeList = data;
                    }
                );
            };

            //Init Function
            $scope.ui.init = function () {
                $scope.ui.mode = "IDEAL";
                $scope.ui.findAllUserTypes();
            };

            //Call Function
            $scope.ui.init();
        });
}());