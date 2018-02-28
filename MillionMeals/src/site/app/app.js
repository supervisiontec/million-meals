(function () {
    //index module
    angular.module("appModule", [
        "ngRoute",
        "ngCookies",
        "ui.bootstrap",
        "ui-notification"
    ]);

    //constants
    angular.module("appModule")
        .constant("systemConfig", {
            apiUrl: location.hostname === 'localhost'
                ? "http://localhost:8070"
                : location.protocol + "//" + location.hostname + (location.port ? ":" + location.port : "")
        });

    //route config
    angular.module("appModule")
        .config(function ($routeProvider) {
            $routeProvider
            //system
                .when("/", {
                    redirectTo: "/order"
                })
                .when("/login", {
                    templateUrl: "app/system/login/login.html",
                    controller: "LoginController"
                })
                .when("/order", {
                    templateUrl: "app/order/order.html",
                    controller: "orderController"
                })
                .when("/pop", {
                    templateUrl: "app/popup/popup.html",
                    controller: "popupController"
                })
                .when("/main", {
                    templateUrl: "app/main/main.html",
                    controller: "mainController"
                })
                .otherwise({
                    redirectTo: "/"
                });
        });

    angular.module("appModule")
        .controller("appController", function () {

        });

}());
