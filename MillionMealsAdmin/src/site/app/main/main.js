(function () {
    //controller
    angular.module("appModule")
            .controller("mainController", function ($scope) {
                //data models 
                $scope.model = {};

                //ui models
                $scope.ui = {};


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                };

                $scope.ui.init();
            });
}());