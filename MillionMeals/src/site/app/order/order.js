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
            factory.saveOrder = function (order, callback) {
                var url = systemConfig.apiUrl + "/api/restaurant/order/save-order";
                $http.post(url, order)
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
        .controller("orderController", function ($scope, orderFactory, $uibModal, $uibModalStack) {
            //data models
            $scope.model = {};
            $scope.http = {};
            $scope.style = {};
            $scope.ui = {};

            $scope.tempOderDetails =
                {
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
                    discount: null
                };

            $scope.order = {
                // indexNo :null
                mCustomer: 1,
                mBranch: 1,
                orderNo: 123,
                date: null,
                totalPrice: 200,
                orderType1: 'oncall',
                orderType2: 'take away',
                status: 'pending',
                tOrderDetailssByIndexNo: [
                    // {
                    // indexNo :null
                    // tOrder: null,
                    // mProduct: null,
                    // unit: null,
                    // itemType: null,
                    // qty: null,
                    // price: null,
                    // value: null,
                    // status: null,
                    // isChange: null,
                    // itemName: null,
                    // discount: null
                    // }


                ]
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

            $scope.http.saveOrder = function () {
                orderFactory.saveOrder(JSON.stringify($scope.order), function (data) {
                    console.log(data.tOrderDetailssByIndexNo);
                    $scope.selectedItemList = data.tOrderDetailssByIndexNo;
                });
            };

            /////////////////////ui funtions /////////////////////

            $scope.ui.selectDineIn = function () {
                $scope.ui.mode = 'dineIn';
                $scope.style.selectItemHeight = '389px';
                $scope.style.MenuItemHeight = '218px';
                $scope.style.MainItemHeight = '172px';
            };

            $scope.ui.selectTakeAway = function () {
                $scope.ui.mode = 'takeAway';
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
            // on call or walk in select
            $scope.ui.selectType1 = function () {

            };

            $scope.ui.addToOrder = function () {
                angular.forEach($scope.productList, function (val) {
                    $scope.tempOderDetails = {};
                    if (val.selectQty) {
                        $scope.tempOderDetails.mProduct = $scope.mProduct;
                        $scope.tempOderDetails.type = $scope.type;
                        $scope.tempOderDetails.itemName = $scope.itemName;
                        $scope.tempOderDetails.unit = val.mUnit.name;
                        $scope.tempOderDetails.price = val.price;
                        $scope.tempOderDetails.qty = val.selectQty;

                        //total amount price * qty
                        var total = val.price * val.selectQty;

                        $scope.tempOderDetails.discount = val.discount;
                        if (val.discount) {
                            $scope.tempOderDetails.value = total - val.discount;
                        } else {
                            $scope.tempOderDetails.value = total;
                        }
                        $scope.order.tOrderDetailssByIndexNo.push($scope.tempOderDetails);
                        console.log($scope.order)
                    }
                });

                $scope.http.saveOrder();
                $uibModalStack.dismissAll();

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

            $scope.ui.init = function () {
                $scope.ui.mode = 'takeAway';
                $scope.style.selectItemHeight = '323px';
                $scope.style.MainItemHeight = '106px';
                $scope.style.MenuItemHeight = '152px';
                //set ideal mode
                $scope.ui.mode = "IDEAL";

                //http
                orderFactory.findAllMainCategory(function (data) {
                    $scope.mainCategoryList = data;
                });
            };

            $scope.ui.init();
        });
}());