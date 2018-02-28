angular.module("appModule")
        .factory("factory", function ($http, systemConfig) {
            var factory = {};

            factory.findAll = function (path , callback,errorCallback) {
                var url = systemConfig.apiUrl + systemConfig.master + path ;

                $http.get(url)
                        .success(function (data, status, headers) {
                            callback(data);
                        })
                        .error(function (data, status, headers) {
                            errorCallback(data);
                        });
            };
            factory.save = function (path , model ,callback,errorCallback) {
                var url = systemConfig.apiUrl + systemConfig.master + path;

                $http.post(url, model)
                        .success(function (data, status, headers) {
                            callback(data);
                        })
                        .error(function (data, status, headers) {
                            errorCallback(data);
                        });
            };
            factory.delete = function (path, callback,errorCallback) {
                var url = systemConfig.apiUrl + systemConfig.master + path ;

                $http.delete(url)
                        .success(function (data, status, headers) {
                            callback(data);
                        })
                        .error(function (data, status, headers) {
                            errorCallback(data);
                        });
            };
            factory.findByIndex = function (path , callback,errorCallback) {
                var url = systemConfig.apiUrl + systemConfig.master + path;
                $http.get(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {
                        errorCallback(data);
                    });
            };

            factory.updateUserApprovel = function (path , callback,errorCallback) {
                var url = systemConfig.apiUrl + systemConfig.master + path;
                $http.post(url)
                    .success(function (data, status, headers) {
                        callback(data);
                    })
                    .error(function (data, status, headers) {
                        errorCallback(data);
                    });
            };

//            factory.getOnew = function (callback) {
//                var url = systemConfig.apiUrl + "/api/care-point/service/zmaster/customer-type";
//
//                $http.get(url)
//                        .success(function (data, status, headers) {
//                            callback(data);
//                        })
//                        .error(function (data, status, headers) {
//                            callback(data);
//                        });
//            };
//
//            factory.saveClientFactory = function (summary, callback, errorCallback) {
//                var url = systemConfig.apiUrl + "/api/care-point/master/client/insert-client";
//
//                $http.post(url, summary)
//                        .success(function (data, status, headers) {
//                            callback(data);
//                        })
//                        .error(function (data, status, headers) {
//                            if (errorCallback) {
//                                errorCallback(data);
//                            }
//                        });
//            };
//
//            //delete
//            factory.deleteClientFactory = function (indexNo, callback, errorCallback) {
//                var url = systemConfig.apiUrl + "/api/care-point/master/client/delete-client/" + indexNo;
//                $http.delete(url)
//                        .success(function (data, status, headers) {
//                            callback(data);
//                        })
//                        .error(function (data, status, headers) {
//                            if (errorCallback) {
//                                errorCallback(data);
//                            }
//                        });
//            };

            return factory;
        });

