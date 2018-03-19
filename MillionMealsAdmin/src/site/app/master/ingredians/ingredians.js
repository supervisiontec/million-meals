(function () {
//controller
    angular.module("appModule")
            .controller("ingrediansController", function ($scope, factory, Notification, $timeout) {

                $scope.model = {};
                $scope.ui = {};
                $scope.ui.mode = null;

                $scope.model.item = {
                    code: null,
                    costPrice: null,
                    indexNo: null,
                    item: null,
                    product: null,
                    qty: null,
                    unit: null
                };

                $scope.model.product = {};
                $scope.model.productList = [];
                $scope.model.rowItemList = [];
                $scope.model.ingrediansList = [];
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
                    $scope.ui.focus("item");
                };
                $scope.ui.addData = function () {
                    var check = true;
                    if (!$scope.model.product.indexNo) {
                        check = false;
                        Notification.error("pro");
                    }
                    if (!$scope.model.item.qty) {
                        check = false;
                        Notification.error("qty");
                    }
                    if (!$scope.model.item.item) {
                        check = false;
                        Notification.error("item");
                    }

                    if (check) {
                        $scope.model.item.product = $scope.model.product.indexNo;
                        $scope.model.item.costPrice = $scope.model.item.costPrice * $scope.model.item.qty;
                        $scope.model.ingrediansList.push($scope.model.item);
                        $scope.ui.focus("code");
                        $scope.ui.reset();
                    }
                };
                $scope.ui.productLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.productList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.item + "-" + value.unit;
                            $scope.model.product.unit = value.unit;
                            $scope.model.product.price = parseFloat(value.price);
                            return;
                        }
                    });
                    return name;
                };
                $scope.ui.rowItemLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.rowItemList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.code + " - " + value.name;
                            $scope.model.item.unit = value.unit;
                            $scope.model.item.qty = value.qty;
                            $scope.model.item.costPrice = value.costPrice;
                            $scope.model.item.code = value.code;
                            return;
                        }
                    });
                    return name;
                };
                $scope.ui.unitLable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.unitList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.name;
                            return;
                        }
                    });
                    return name;
                };
                $scope.ui.rowItemLableforTable = function (indexNo) {
                    var name;
                    angular.forEach($scope.model.rowItemList, function (value) {
                        if (value.indexNo === parseInt(indexNo)) {
                            name = value.code + ' - ' + value.name;
                            return;
                        }
                    });
                    return name;
                };
                $scope.ui.loadSelectedRowItems = function (itemCode, e) {
                    var code = e ? e.keyCode || e.which : 13;
                    if (code === 13) {
                        var check = false;
                        angular.forEach($scope.model.rowItemList, function (value) {
                            if (value.code === itemCode) {
                                $scope.model.item.unit = value.unit;
                                $scope.model.item.qty = value.qty;
                                $scope.model.item.costPrice = value.costPrice;
                                $scope.model.item.item = value.indexNo;
                                check = true;
                                return;
                            }
                        });
                        if (!check) {
                            Notification.error('Item Not Found !');
                        }
                        $scope.ui.focus("qty");
                    }
                };
                $scope.ui.focusAddFunction = function (e) {
                    var code = e ? e.keyCode || e.which : 13;
                    if (code === 13) {
                        $scope.ui.addData();
                    }
                };
                $scope.ui.findAllRowItems = function () {
                    var path = "/get-all-product-by-type-rowitem";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.rowItemList = data;
                            },
                            function (data) {
                                console.log("Error find All product");
                            }
                    );
                };
                $scope.ui.findAllItemUnit = function () {
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
                $scope.ui.findAllproducts = function () {
                    var path = "/get-all-item-object";
                    factory.findAll(path,
                            function (data) {
                                $scope.model.productList = data;
                            },
                            function () {
                                console.log("Error find All product");
                            }
                    );
                };
                $scope.ui.save = function () {
                    var path = "/save-ingredians";
                    if ($scope.model.ingrediansList) {
                        factory.save(path, $scope.model.ingrediansList, function (data) {
                            Notification.success("Ingredians save success");
                            $scope.ui.resetAll();
                            $scope.model.ingrediansList = [];
                        }, function () {
                            Notification.error("Ingredians save fail...!");
                        });
                    } else {
                        Notification.error("Please Add Ingridians to save...!");
                    }
                };

                $scope.ui.edit = function (model, index) {
                    console.log(model);
                    $scope.model.item = model;
                };

                $scope.ui.delete = function (indexNo, index) {
                    var path = "/delete-ingredians/" + indexNo;
                    factory.delete(path, function () {
                        $scope.model.ingrediansList.splice(index, 1);
                        Notification.success("Item save success...!");
                    }, function () {
                        Notification.error("Item delete fail...!");
                    });
                };

                $scope.ui.loadIngrediansbyProduct = function (indexNo) {
                    console.log(indexNo);
                    var path = "/load-ingredians-by-product/";
                    factory.findByIndexNo(path, indexNo, function (data) {
                        console.log(data);
                        $scope.model.ingrediansList = data;
                    });
                };
                $scope.ui.init = function () {
                    $scope.ui.mode = "IDEAL";
                    $scope.ui.findAllproducts();
                    $scope.ui.findAllRowItems();
                    $scope.ui.findAllItemUnit();
                };
                $scope.ui.init();
            });
}());