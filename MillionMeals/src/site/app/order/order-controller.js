(function () {
    //controller
    angular.module("appModule")
        .controller("orderController", function ($scope, orderFactory, $filter, $uibModal, $uibModalStack, optionPane, Notification, systemConfig) {
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
                $scope.order.orderType1 = 'walk_in';
                $scope.order.orderType2 = 'take_away';
                $scope.ui.selectMainCategory(1);
                $scope.ui.searchByMobile(111);
            };

            ////////////////// http funtions ////////////////////

            $scope.http.findAllSubCategory = function (index) {
                orderFactory.findAllSubCategory(index, function (data) {
                    $scope.subCategoryList = data;
                });
            };

            $scope.http.findAllItems = function (index) {
                orderFactory.findAllItems(index, function (data) {
                    $scope.itemList = data;

                });
            };

            $scope.http.findImage = function (image) {
                return systemConfig.apiUrl + "/api/restaurant/order/food-image/" + image;
                // $scope.itemImage = url;
            };


            $scope.http.findProductByItem = function (index) {
                orderFactory.findProductByItem(index, function (data) {
                    $scope.productList = [];
                    angular.forEach(data, function (val) {
                        val.selectQty = null;
                        val.discount = null;
                        $scope.productList.push(val);
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
                orderFactory.saveOrder(JSON.stringify($scope.order), parseInt($scope.orderIndex), $scope.ui.selectedTableIndex, function (data) {
                    if (data) {
                        $scope.order.tOrderDetailssByIndexNo = [];
                        $scope.orderIndex = data.indexNo;
                        $scope.selectedItemList = data.tOrderDetailssByIndexNo;

                    } else {
                        //TODO reaset some field
                    }


                });
            };

            $scope.http.updateOrder = function (item, index, subTotal, totalAmount) {
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
                        $scope.mobile = data.mobile;
                        $scope.order.mCustomer = data.indexNo;
                        $scope.customerName = data.name;
                    } else {
                        $scope.order.mCustomer = null;
                        $scope.customerName = null;
                        $scope.ui.emptyEmployee = 'true';
                    }
                });
            };

            $scope.http.completeOrder = function (payment) {
                orderFactory.completeOrder(payment, function (data) {
                    if (data) {
                        $uibModalStack.dismissAll();
                        $scope.resetModel();
                        optionPane.successMessage("", "ORDER PAYMENT SUCCESS");
                        orderFactory.printInvoice($filter('date')(data.date, "yyyy-MM-dd"), data.invoiceNo, function (data) {

                        });
                    }
                });
            };

            $scope.http.findTableOrderDetails = function (tableIndex) {
                orderFactory.findTableOrderDetails(tableIndex, function (data) {
                    console.log(data)
                    if(data){
                        $scope.order.tOrderDetailssByIndexNo = [];
                        $scope.orderIndex = data.tOrder.indexNo;
                        $scope.selectedItemList = data.tOrder.tOrderDetailssByIndexNo;
                    }else {
                        $scope.selectedItemList = [];
                    }

                });
            };


            /////////////////////ui funtions /////////////////////

            $scope.ui.selectDineIn = function (type) {
                $scope.ui.mode = 'dineIn';
                // $scope.order.orderType2 = type;
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

            $scope.ui.selectTable = function (table) {
                $scope.ui.selectedTableIndex = table.indexNo;
                $scope.http.findTableOrderDetails(table.indexNo);
            };

            //new customer save
            $scope.ui.saveCustomer = function () {

                if ($scope.customerName !== "" && $scope.mobile !== "") {
                    if ($scope.customerName !== null && $scope.mobile !== null) {
                        $scope.customer.name = $scope.customerName;
                        $scope.customer.mobile = $scope.mobile;

                        $scope.http.saveCustomer(JSON.stringify($scope.customer));
                    } else {
                        Notification.error("Customer Details Empty...");
                    }
                } else {
                    Notification.error("Customer Details Empty...");
                }

            };


            /////////////////order funtions //////////////////////////

            $scope.ui.addToOrder = function () {
                if ($scope.ui.mode === 'dineIn') {
                    if ($scope.ui.selectedTableIndex) {
                        $scope.ui.addtoOrderCalc();
                    } else {
                        Notification.error("Please Select a Table");
                    }
                } else if ($scope.ui.mode === 'takeAway') {
                    if ($scope.order.mCustomer) {
                        $scope.ui.addtoOrderCalc();
                    } else {
                        Notification.error("Please save customer first..");
                    }
                }

            };

            $scope.ui.addtoOrderCalc = function () {
                angular.forEach($scope.productList, function (val) {
                    $scope.tempOderDetails = {};
                    if (val.selectQty) {
                        $scope.tempOderDetails.mProduct = val.indexNo;
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
                    $scope.payment.discountRate = Math.round(val);
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
                if (cash) {

                    $scope.removeCashAmount = cash;
                    $scope.ui.addCash = 'false';
                    $scope.paymentInformation = {};
                    if (angular.isUndefined($scope.payAmount) || $scope.payAmount == null || $scope.payAmount == 0) {

                        if (cash >= $scope.payment.finalAmount) {
                            var balance = cash - $scope.payment.finalAmount;
                            $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                            $scope.paymentInformation.amount = cash - balance;
                        } else {
                            $scope.payment.tPaymentByIndexNo.cashAmount = cash;
                            $scope.paymentInformation.amount = cash;
                        }
                    } else {
                        var havetopay = $scope.payment.finalAmount - $scope.payAmount
                        if (cash >= havetopay) {
                            var balance = cash - havetopay;
                            $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                            $scope.paymentInformation.amount = cash - balance;
                        } else {
                            $scope.payment.tPaymentByIndexNo.cashAmount = cash - balance;
                            $scope.paymentInformation.amount = cash - balance;
                        }
                    }

                    $scope.ui.payAmountCalculation(cash);
                    $scope.paymentInformation.type = 'CASH';
                    $scope.paymentInformation.form = "INVOICE_FORM";
                    $scope.payment.tPaymentByIndexNo.tPaymentDetailssByIndexNo.push($scope.paymentInformation);
                    $scope.paymentInformation = {};
                }
            };

            $scope.ui.addCardAmount = function (information) {
                $scope.paymentInformation = {};
                if (information.amount && information.cardType && information.number) {
                    if (angular.isUndefined($scope.payAmount) || $scope.payAmount == null || $scope.payAmount == 0) {
                        if (information.amount >= $scope.payment.finalAmount) {
                            var balance = information.amount - $scope.payment.finalAmount;
                            $scope.payment.tPaymentByIndexNo.cardAmount = information.amount - balance;
                            $scope.paymentInformation.amount = information.amount - balance;
                        } else {
                            $scope.payment.tPaymentByIndexNo.cardAmount = information.amount;
                            $scope.paymentInformation.amount = information.amount;
                        }

                    } else {
                        var havetopay = $scope.payment.finalAmount - $scope.payAmount
                        if (information.amount >= havetopay) {
                            var balance = information.amount - havetopay;
                            if ($scope.payment.tPaymentByIndexNo.cardAmount !== null) {
                                $scope.payment.tPaymentByIndexNo.cardAmount += information.amount;
                            } else {
                                $scope.payment.tPaymentByIndexNo.cardAmount = information.amount - balance;
                            }
                            $scope.paymentInformation.amount = information.amount - balance;
                        } else {
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

                } else {
                    Notification.error("Card Details Empty");
                }
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
                    }
                });
            };

            $scope.ui.completePayment = function () {
                if (angular.isUndefined($scope.payAmount) || $scope.payAmount === 0) {
                    Notification.error("Pay Amount Empty");
                } else {
                    $scope.payment.tOrder = $scope.orderIndex;
                    $scope.payment.tPaymentByIndexNo.totalAmount = $scope.payment.finalAmount;
                    $scope.payment.tPaymentByIndexNo.payAmount = $scope.payAmount;
                    $scope.payment.tPaymentByIndexNo.balance = $scope.balance;

                    $scope.http.completeOrder(JSON.stringify($scope.payment));
                }
            };


            ////////////// uib pop up ///////////////////

            $scope.ui.selectItem = function (item) {
                // $scope.mProduct = item.indexNo;
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
                $scope.edit.qty = parseInt(item.qty);
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
                console.log("2018-02-23 - updated")
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

                $scope.ui.searchByMobile(111);

                //load main category
                orderFactory.findAllMainCategory(function (data) {
                    $scope.mainCategoryList = data;
                });

                //load tables
                orderFactory.findAllTables(function (data) {
                    $scope.tableList = data;
                });
            };

            $scope.ui.init();
        });
}());