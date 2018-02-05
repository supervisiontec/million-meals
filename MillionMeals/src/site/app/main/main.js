(function () {
    //controller
    angular.module("appModule")
            .controller("mainController", function ($scope,optionPane,$uibModal) {
                //data models 
                $scope.model = {};

                //ui models
                $scope.ui = {};

                $scope.ui.clickMe = function () {
                                   console.log('click Me');        
                            $uibModal.open({
                                animation: true,
                                ariaLabelledBy: 'modal-title',
                                ariaDescribedBy: 'modal-body',
                                templateUrl: 'item_selection_popup.html',
                                scope: $scope,
                                size: 'md'
                            });
                        
                    
                };


                $scope.ui.init = function () {
                    //set ideal mode
                    $scope.ui.mode = "IDEAL";

                };

                $scope.ui.init();
            });
}());