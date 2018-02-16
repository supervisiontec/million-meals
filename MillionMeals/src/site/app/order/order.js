(function () {

    angular.module("appModule")
        .factory("orderFactory", function ($http, systemConfig) {
            var factory = {};

            //find all main category
            factory.findAllMainCategory = function (callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/all-maincategory";
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };

            //find all sub category
            factory.findAllSubCategory = function (index, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/all-subcategory/" + index;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };

            //find all Items
            factory.findAllItems = function (index, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/all-items/" + index;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //find products
            factory.findProductByItem = function (index, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/find-product/" + index;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //save order
            factory.saveOrder = function (order, index, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/save-order/" + index;
                $http.post(url, order)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //update order details
            factory.updateOrder = function (item, index, subTotal, totalAmount, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/update-order/" + index + "/" + subTotal + "/" + totalAmount;
                $http.post(url, item)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //delete order details
            factory.deleteOrder = function (index, index2, subTotal, totalAmount, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/delete-order/" + index + "/" + index2 + "/" + subTotal + "/" + totalAmount;
                $http.delete(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //search by mobile no
            factory.searchByMobileNo = function (mobile, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/find-employee-mobile/" + mobile;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //completeOrder
            factory.completeOrder = function (payment, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/payment/save";
                $http.post(url, payment)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };
            //save customer
            factory.saveCustomer = function (customer, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/save-customer";
                $http.post(url, customer)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {

                    });
            };

            return factory;
        });

    //controller
    angular.module("appModule")
        .controller("orderController", function ($scope, orderFactory, $uibModal, $uibModalStack, optionPane, Notification) {
            //data models
            $scope.model = {};
            $scope.http = {};
            $scope.style = {};
            $scope.ui = {};
            $scope.edit = {};
            $scope.cardInfromation = {};
            $scope.orderIndex = 0;
            $scope.tableIndex = null;
            $scope.selectedItemList = [];
            $scope.edit.selectedItem = {};
            $scope.selectedTempList = [];

            $scope.customer = {
                name: null,
                mobile: null
            };

            $scope.model.cardTypeList = [
                {
                    indexNo: 1,
                    name: 'VISA'
                },
                {
                    indexNo: 2,
                    name: 'MASTER'
                }
            ];

            $scope.tempOderDetails = {
                // tOrder: null,
                mProduct: null,
                unit: null,
                type: null,
                qty: null,
                price: null,
                value: null,
                status: null,
                isChange: null,
                itemName: null,
                itemType: null,
                discount: null
            };

            $scope.order = {
                // indexNo :null
                mCustomer: null,
                mBranch: 1,
                orderNo: null,
                date: null,
                totalSub: null,
                totalTax: null,
                totalAmount: null,
                orderType1: null,
                orderType2: null,
                status: 'pending',
                tOrderDetailssByIndexNo: []
            };

            $scope.payment = {
                // "indexNo": null,
                "tOrder": null,
                "invoiceNo": null,
                "date": null,
                "branch": 1,
                "discountRate": null,
                "discountValue": null,
                "finalAmount": null,
                "tPaymentByIndexNo": {
                    // "indexNo": null,
                    "totalAmount": null,
                    "cashAmount": null,
                    "cardAmount": null,
                    "chequeAmount": null,
                    "overPayment": null,
                    "date": null,
                    "balance": null,
                    "payAmount": null,
                    "tPaymentDetailssByIndexNo": []
                }
            };

            $scope.resetModel = function () {
                $scope.payment = {
                    // "indexNo": null,
                    "tOrder": null,
                    "invoiceNo": null,
                    "date": null,
                    "branch": 1,
                    "discountRate": null,
                    "discountValue": null,
                    "finalAmount": null,
                    "tPaymentByIndexNo": {
                        // "indexNo": null,
                        "totalAmount": null,
                        "cashAmount": null,
                        "cardAmount": null,
                        "chequeAmount": null,
                        "overPayment": null,
                        "date": null,
                        "tPaymentDetailssByIndexNo": []
                    }
                };
                $scope.cardInfromation = {};
                $scope.orderIndex = 0;
                $scope.tableIndex = null;
                $scope.selectedItemList = [];
                $scope.edit.selectedItem = {};
                $scope.selectedItemList = [];
                $scope.payAmount = 0;
                $scope.balance = 0;
                $scope.ui.addCash = 'true';

                $scope.order = {
                    // indexNo :null
                    mCustomer: null,
                    mBranch: 1,
                    orderNo: null,
                    date: null,
                    totalSub: null,
                    totalTax: null,
                    totalAmount: null,
                    orderType1: null,
                    orderType2: null,
                    status: 'pending',
                    tOrderDetailssByIndexNo: []
                };
                $scope.mobile = '111';
                $scope.ui.selectMainCategory(1);
                $scope.ui.searchByMobile(111);
            };

            ////////////////// http funtions ////////////////////

            $scope.http.findAllSubCategory = function (index) {
                orderFactory.findAllSubCategory(index, function (data) {
                    console.log(data)
                    $scope.subCategoryList = data;
                });
            };

            $scope.http.findAllItems = function (index) {
                orderFactory.findAllItems(index, function (data) {
                    console.log(data)
                    $scope.itemList = data;
                });
            };

            $scope.http.findProductByItem = function (index) {
                orderFactory.findProductByItem(index, function (data) {
                    $scope.productList = [];
                    angular.forEach(data, function (val) {
                        val.selectQty = null;
                        val.discount = null;
                        $scope.productList.push(val);
                        console.log($scope.productList)
                    });
                });
            };

            $scope.http.saveCustomer = function (customer) {
                orderFactory.saveCustomer(customer, function (data) {
                    $scope.order.mCustomer = data.indexNo;
                    $scope.ui.emptyEmployee = 'false';
                    optionPane.successMessage("", "CUSTOMER SAVE SUCCESS");
                });
            };

            $scope.http.saveOrder = function () {
                console.log($scope.orderIndex)
                orderFactory.saveOrder(JSON.stringify($scope.order), parseInt($scope.orderIndex), function (data) {
                    console.log(data + "sssssssssss")
                    if (data) {
                        $scope.order.tOrderDetailssByIndexNo = [];
                        $scope.orderIndex = data.indexNo;
                        $scope.selectedItemList = data.tOrderDetailssByIndexNo;
                        $scope.selectedTempList = data.tOrderDetailssByIndexNo;
                    } else {
                        //TODO reaset some field
                    }


                });
            };

            $scope.http.updateOrder = function (item, index, subTotal, totalAmount) {
                console.log("dddddddddddd")
                orderFactory.updateOrder(item, index, subTotal, totalAmount,
                    function (data) {
                        if (data) {
                            // Notification.success("Update Success....");
                            $uibModalStack.dismissAll();
                        }
                    });
            };


            $scope.http.deleteOrder = function (index, index2, subTotal, totalAmount) {
                orderFactory.deleteOrder(index, index2, subTotal, totalAmount,
                    function (data) {
                        if (data) {
                            $scope.selectedItemList.splice($scope.tableIndex, 1);
                            $uibModalStack.dismissAll();
                        }
                    })
            };

            $scope.http.searchByMobileNo = function (mobile) {
                orderFactory.searchByMobileNo(mobile, function (data) {
                    if (data) {
                        $scope.order.mCustomer = data.indexNo;
                        $scope.customerName = data.name;
                    } else {
                        $scope.customerName = "";
                        $scope.ui.emptyEmployee = 'true';
                    }
                });
            };

            $scope.http.completeOrder = function (payment) {
                orderFactory.completeOrder(payment, function (data) {
                    console.log(data)
                    if (data) {
                        $uibModalStack.dismissAll();
                        $scope.resetModel();
                        optionPane.successMessage("", "ORDER PAYMENT SUCCESS");
                    }
                });
            };


            /////////////////////ui funtions /////////////////////

            $scope.ui.selectDineIn = function (type) {
                $scope.ui.mode = 'dineIn';
                $scope.order.orderType2 = type;
                $scope.selectedButton2 = type;
                $scope.style.selectItemHeight = '389px';
                $scope.style.MenuItemHeight = '218px';
                $scope.style.MainItemHeight = '172px';
            };

            $scope.ui.selectTakeAway = function (type) {
                $scope.ui.mode = 'takeAway';
                $scope.order.orderType2 = type;
                $scope.selectedButton2 = type;
                $scope.style.selectItemHeight = '323px';
                $scope.style.MainItemHeight = '106px';
                $scope.style.MenuItemHeight = '152px';
            };

            $scope.ui.selectMainCategory = function (index) {
                $scope.ui.menumode = 'subcategory';
                $scope.ui.selectedMainCategory = index;
                $scope.http.findAllSubCategory(index);
            };

            $scope.ui.selectSubCategory = function (index) {
                $scope.ui.menumode = 'item';
                $scope.http.findAllItems(index);
            };

            // on call or walkin select
            $scope.ui.selectType = function (type) {
                $scope.order.orderType1 = type;
                $scope.selectedButton = type;
            };

            $scope.ui.searchByMobileNo = function ($event, mobile) {
                if ($event.keyCode == 13) {
                    $scope.http.searchByMobileNo(mobile);
                }
            };
            $scope.ui.searchByMobile = function (mobile) {
                $scope.http.searchByMobileNo(mobile);
            };

            //new customer save
            $scope.ui.saveCustomer = function () {
                console.log($scope.customerName)
                console.log($scope.mobile)

                if ($scope.customerName !== "" && $scope.mobile !== "") {
                    if ($scope.customerName !== null && $scope.mobile !== null) {
                        $scope.customer.name = $scope.customerName;
                        $scope.customer.mobile = $scope.mobile;

                        $scope.http.saveCustomer(JSON.stringify($scope.customer));
                    }
                } else {
                    Notification.error("Customer Details Empty...");
                }

            };


            /////////////////order funtions //////////////////////////

            $scope.ui.addToOrder = function () {
                angular.forEach($scope.productList, function (val) {
                    $scope.tempOderDetails = {};
                    if (val.selectQty) {
                        $scope.tempOderDetails.mProduct = $scope.mProduct;
                        $scope.tempOderDetails.itemType = $scope.type;
                        $scope.tempOderDetails.itemName = $scope.itemName;
                        $scope.tempOderDetails.unit = val.mUnit.name;
                        $scope.tempOderDetails.price = val.price;
                        $scope.tempOderDetails.qty = val.selectQty;
                        $scope.tempOderDetails.status = 'pending';

                        //total amount calc price * qty
                        var total = val.price * val.selectQty;

                        $scope.tempOderDetails.discount = val.discount;
                        if (val.discount) {
                            $scope.tempOderDetails.value = total - val.discount;
                        } else {
                            $scope.tempOderDetails.value = total;
                        }
                        $scope.order.tOrderDetailssByIndexNo.push($scope.tempOderDetails);

                        //sub total calc
                        $scope.order.totalSub += $scope.tempOderDetails.value;

                        //total amount calc
                        $scope.order.totalAmount = $scope.order.totalSub + $scope.order.totalTax;
                        console.log($scope.order)
                    }
                });

                $scope.http.saveOrder();
                $uibModalStack.dismissAll();

            };

            $scope.ui.updateOrder = function () {
                $scope.edit.selectedItem.qty = $scope.edit.qty;
                $scope.edit.selectedItem.discount = $scope.edit.discount;

                //total amount calc price * qty
                var total = $scope.edit.selectedItem.price * $scope.edit.qty;

                var beforeItemTotal = $scope.edit.selectedItem.value;

                if ($scope.edit.discount) {
                    $scope.edit.selectedItem.value = total - $scope.edit.discount;
                } else {
                    $scope.edit.selectedItem.value = total;
                }

                //sub total calc
                if (beforeItemTotal > $scope.edit.selectedItem.value) {
                    var value = beforeItemTotal - $scope.edit.selectedItem.value;
                    $scope.order.totalSub -= value;
                } else {
                    var value = $scope.edit.selectedItem.value - beforeItemTotal;
                    $scope.order.totalSub += value;
                }

                //total amount calc
                $scope.order.totalAmount = $scope.order.totalSub + $scope.order.totalTax;

                console.log($scope.edit.selectedItem)
                $scope.http.updateOrder($scope.edit.selectedItem, $scope.orderIndex, $scope.order.totalSub, $scope.order.totalAmount);
            };

            $scope.ui.deleteOrder = function () {
                //sub total calc
                $scope.order.totalSub -= $scope.edit.selectedItem.value;

                //total amount calc
                $scope.order.totalAmount = $scope.order.totalSub + $scope.order.totalTax;

                $scope.http.deleteOrder($scope.orderIndex, $scope.edit.selectedItem.indexNo, $scope.order.totalSub, $scope.order.totalAmount);
            };

            ////////////////////// payment funtions///////////////////////////////

            $scope.ui.addDiscountValue = function (value) {
                if (value === 0) {
                    $scope.payment.discountRate = null;
                    $scope.payment.finalAmount = $scope.order.totalAmount;
                } else {
                    $scope.payment.finalAmount = 0.00;
                    $scope.payment.finalAmount = $scope.order.totalAmount - value;

                    var val = value / $scope.order.totalAmount * 100;
                    $scope.payment.discountRate = val;
                }

                if ($scope.payAmount != null) {
                    $scope.balance = $scope.payAmount - $scope.payment.finalAmount;
                }
            };

            $scope.ui.addDiscountRate = function (rate) {
                if (rate === "" || rate === null || rate === 0) {
                    $scope.payment.discountValue = null;
                    $scope.payment.finalAmount = $scope.order.totalAmount;
                } else {
                    $scope.payment.finalAmount = 0.00;

                    var val = $scope.order.totalAmount / 100 * rate;
                    $scope.payment.discountValue = val;

                    $scope.payment.finalAmount = $scope.order.totalAmount - val;
                }

                if ($scope.payAmount !== null) {
                    $scope.balance = $scope.payAmount - $scope.payment.finalAmount;
                }
            };

            $scope.ui.addCashAmount = function (cash) {
                $scope.removeCashAmount = cash;
                $scope.ui.addCash = 'false';
                $scope.paymentInformation = {};
                if (angular.isUndefined($scope.payAmount) || $scope.payAmount == null || $scope.payAmount == 0) {

                    console.log("1")
                    if (cash >= $scope.payment.finalAmount) {
                        console.log("A")
                        var balance = cash - $scope.payment.finalAmount;
                        $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                        $scope.paymentInformation.amount = cash - balance;
                    } else {
                        console.log("B")
                        $scope.payment.tPaymentByIndexNo.cashAmount = cash;
                        $scope.paymentInformation.amount = cash;
                    }
                } else {
                    console.log("2")
                    var havetopay = $scope.payment.finalAmount - $scope.payAmount
                    if (cash >= havetopay) {
                        console.log("A")
                        var balance = cash - havetopay;
                        $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                        $scope.paymentInformation.amount = cash - balance;
                    } else {
                        console.log("B")
                        $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                        $scope.paymentInformation.amount = cash - balance;
                    }
                }

                $scope.ui.payAmountCalculation(cash);
                $scope.paymentInformation.type = 'CASH';
                $scope.paymentInformation.form = "INVOICE_FORM";
                $scope.payment.tPaymentByIndexNo.tPaymentDetailssByIndexNo.push($scope.paymentInformation);
                $scope.paymentInformation = {};
                console.log($scope.payment)
            };

            $scope.ui.addCardAmount = function (information) {
                $scope.paymentInformation = {};

                if (angular.isUndefined($scope.payAmount) || $scope.payAmount == null || $scope.payAmount == 0) {
                    console.log("1")
                    if (information.amount >= $scope.payment.finalAmount) {
                        console.log("A")
                        var balance = information.amount - $scope.payment.finalAmount;
                        $scope.payment.tPaymentByIndexNo.cardAmount = information.amount - balance;
                        $scope.paymentInformation.amount = information.amount - balance;
                    } else {
                        console.log("B")
                        $scope.payment.tPaymentByIndexNo.cardAmount = information.amount;
                        $scope.paymentInformation.amount = information.amount;
                    }

                } else {
                    console.log("2")
                    var havetopay = $scope.payment.finalAmount - $scope.payAmount
                    if (information.amount >= havetopay) {
                        console.log("A")
                        var balance = information.amount - havetopay;
                        if ($scope.payment.tPaymentByIndexNo.cardAmount !== null) {
                            $scope.payment.tPaymentByIndexNo.cardAmount += information.amount;
                        } else {
                            $scope.payment.tPaymentByIndexNo.cardAmount = information.amount - balance;
                        }
                        $scope.paymentInformation.amount = information.amount - balance;
                    } else {
                        console.log("B")
                        if ($scope.payment.tPaymentByIndexNo.cardAmount !== null) {
                            $scope.payment.tPaymentByIndexNo.cardAmount += information.amount;
                        } else {
                            $scope.payment.tPaymentByIndexNo.cardAmount = information.amount;
                        }
                        $scope.paymentInformation.amount += information.amount;
                    }
                }

                $scope.ui.payAmountCalculation(information.amount);
                $scope.paymentInformation.type = "CARD";
                $scope.paymentInformation.form = "INVOICE_FORM";
                $scope.paymentInformation.cardType = information.cardType;
                $scope.paymentInformation.number = information.number;
                $scope.payment.tPaymentByIndexNo.tPaymentDetailssByIndexNo.push($scope.paymentInformation);
                $scope.cardInfromation = {};
                console.log($scope.payment)
            };

            $scope.ui.payAmountCalculation = function (amount) {
                if ($scope.payAmount == null || angular.isUndefined($scope.payAmount) || $scope.payAmount == 0) {
                    $scope.payAmount = amount;
                    $scope.balance = amount - $scope.payment.finalAmount;
                } else {
                    $scope.payAmount += amount;
                    $scope.balance = $scope.payAmount - $scope.payment.finalAmount;
                }
            };

            $scope.ui.removeCashAmount = function () {
                $scope.ui.addCash = 'true';
                $scope.payment.tPaymentByIndexNo.cashAmount = 0;
                $scope.payAmount = $scope.payAmount - $scope.removeCashAmount;

                if ($scope.payAmount === 0) {
                    $scope.balance = $scope.payAmount - $scope.payment.finalAmount;
                } else {
                    $scope.balance = $scope.payAmount - $scope.payment.finalAmount;
                }
                angular.forEach($scope.payment.tPaymentByIndexNo.tPaymentDetailssByIndexNo, function (val, $index) {
                    if (val.type === 'CASH') {
                        $scope.payment.tPaymentByIndexNo.tPaymentDetailssByIndexNo.splice($index, 1);
                        console.log($scope.payment)
                    }
                });
            };

            $scope.ui.completePayment = function () {
                $scope.payment.tOrder = $scope.orderIndex;
                $scope.payment.tPaymentByIndexNo.totalAmount = $scope.payment.finalAmount;
                $scope.payment.tPaymentByIndexNo.payAmount = $scope.payAmount;
                $scope.payment.tPaymentByIndexNo.balance = $scope.balance;
                // $scope.payment.finalAmount = $scope.order.totalSub - $scope.payment.discountValue;

                $scope.http.completeOrder(JSON.stringify($scope.payment));
            };

            ////////////// uib pop up ///////////////////

            $scope.ui.selectItem = function (item) {
                $scope.mProduct = item.indexNo;
                $scope.type = item.type;
                $scope.itemName = item.name;

                $scope.http.findProductByItem(item.indexNo);

                $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'item_selection_popup.html',
                    scope: $scope,
                    size: 'md'
                });
            };

            $scope.ui.editItem = function (item, $index) {
                $scope.tableIndex = $index;
                $scope.edit.selectedItem = item;
                $scope.edit.itemName = item.itemName;
                $scope.edit.price = item.price;
                $scope.edit.qty = item.qty;
                $scope.edit.discount = item.discount;

                $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'item_edit_popup.html',
                    scope: $scope,
                    size: 'md'
                });
            };


            $scope.ui.payment = function () {
                if ($scope.payAmount == null || $scope.payAmount == 0 || angular.isUndefined($scope.payAmount)) {
                    $scope.payment.finalAmount = $scope.order.totalAmount;
                }


                $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'payment_popup.html',
                    scope: $scope,
                    size: 'lg'
                });
            };


            $scope.ui.init = function () {
                $scope.selectedButton = 'walk_in';
                $scope.selectedButton2 = 'take_away';
                $scope.order.orderType1 = 'walk_in';
                $scope.order.orderType2 = 'take_away';
                $scope.ui.mode = 'takeAway';
                $scope.ui.addCash = 'true';
                $scope.ui.emptyEmployee === 'false';
                $scope.style.selectItemHeight = '323px';
                $scope.style.MainItemHeight = '106px';
                $scope.style.MenuItemHeight = '152px';
                $scope.ui.selectMainCategory(1);
                $scope.mobile = '111';
                $scope.ui.searchByMobile(111);

                //http
                orderFactory.findAllMainCategory(function (data) {
                    $scope.mainCategoryList = data;
                });
            };

            $scope.ui.init();
        });
}());