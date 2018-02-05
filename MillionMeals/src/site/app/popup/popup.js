(function () {
    //controller
    angular.module("appModule")
            .controller("popupController", function ($scope) {
                //data models 
                $scope.model = {};

                //ui models
                $scope.ui = {};

                $scope.ui.clickMee = function () {
                    console.log('click Me');
//                    $uibModal.open({
//
//                    });


                };

                $scope.ui.init = function () {
//                    set ideal mode
                    $scope.ui.mode = "IDEAL";
//                    $scope.animation = true,
//                    $scope.ariaLabelledBy = 'modal-title';
//                    $scope.ariaDescribedBy = 'modal-body';
//                    $scope.templateUrl = 'item.html';
//                    $scope.scope = $scope;
//                    $scope.size = 'lg';
                };

                $scope.ui.init();
            });
}());