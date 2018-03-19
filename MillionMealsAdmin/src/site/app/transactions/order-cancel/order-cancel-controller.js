(function () {
//controller
    angular.module("appModule")
            .controller("orderCancelController", function ($scope, orderCanselService, Notification, $timeout, ConfirmPane) {

                $scope.model = {};
                $scope.ui = {};
                $scope.ui.mode = null;

                $scope.model.order = {
//                    "indexNo": null,
//                    "orderNo": null,
//                    "date": null,
//                    "totalSub": null,
//                    "totalTax": null,
//                    "totalAmount": null,
//                    "orderType1": null,
//                    "orderType2": null,
//                    "status": null,
//                    "kotTime": null,
//                    "kotStatus": null,
//                    "temporaryBill": null,
//                    "orderCancel": null,
//                    "cancelReason": null,
//                    "cancelUser": null,
//                    "tOrderDetailssByIndexNo": [
//                        {
//                            "indexNo": null,
//                            "unit": null,
//                            "itemType": null,
//                            "itemType2": null,
//                            "qty": null,
//                            "price": null,
//                            "value": null,
//                            "discount": null,
//                            "status": null,
//                            "isChange": null,
//                            "itemName": null,
//                            "date": null,
//                            "cancelStatus": null,
//                            "cancelReason": null,
//                            "kotStatus": null
//                        }]
                };

                $scope.ui.focus = function (id) {
                    $timeout(function () {
                        document.querySelectorAll("#" + id + "")[0].focus();
                    }, 10);
                };
                $scope.ui.reset = function () {
                    $scope.model.item = null;
                };
                $scope.ui.resetAll = function () {
                    $scope.model.item = null;
                    $scope.model.product = null;
                };
                $scope.ui.new = function () {
                    $scope.ui.mode = "NEW";
                    $scope.ui.focus("orderNo");
                };
                $scope.model.findOrderByOrderNo = function (orderNo, e) {
                    var code = e ? e.keyCode || e.which : 13;
                    if (code === 13) {
                        orderCanselService.findOrderByOrderNo(orderNo)
                                .success(function (data) {
                                    console.log(data);
                                    $scope.model.order = data;
                                })
                                .error(function (data) {
                                    Notification.error("Order number not found..!!!");
                                });
                    }

                };
                $scope.ui.cancel = function (model, index) {
                    if (model.kotStatus) {
                        console.log("if");
                        ConfirmPane.primaryConfirm("DO YOU WANT TO CANCEL THIS ORDER AND KOT !")
                                .confirm(function () {

                                    var total = $scope.model.order.totalAmount - model.value;

                                    $scope.model.order.totalAmount = total;
                                    $scope.model.order.totalSub = total;

                                    $scope.model.order.tOrderDetailssByIndexNo.splice(index, 1);
                                    model.cancelStatus = true;


                                    $scope.model.order.tOrderDetailssByIndexNo.push(model);
                                    orderCanselService.saveOrderDetail($scope.model.order)
                                            .success(function (data) {
                                                $scope.model.order = {};
                                                $scope.model.order = data;
                                                Notification.success("Order save success...!!!");
                                            })
                                            .error(function (data) {
                                                Notification.error("Order number not found...!!!");
                                            });
                                });



                    } else {
                        console.log("else");
                        var total = $scope.model.order.totalAmount - model.value;

                        $scope.model.order.totalAmount = total;
                        $scope.model.order.totalSub = total;

                        $scope.model.order.tOrderDetailssByIndexNo.splice(index, 1);
                        model.cancelStatus = true;


                        $scope.model.order.tOrderDetailssByIndexNo.push(model);
                        orderCanselService.saveOrderDetail($scope.model.order)
                                .success(function (data) {
                                    $scope.model.order = {};
                                    $scope.model.order = data;
                                    Notification.success("Order save success...!!!");
                                })
                                .error(function (data) {
                                    Notification.error("Order number not found...!!!");
                                });
                    }
                };
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                };
                $scope.ui.init();
            });
}());