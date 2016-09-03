/**
 * Created by Partizanin on 03.09.2016.
 */
var app = angular.module("app", []);

app.controller("myCtrl", function ($scope, $http) {

    $scope.changeColor = function () {

        var data = {color: $("#span").css("color")};

        $http({
            url: '/Servlet',
            method: 'GET',
            params: {
                jsonData: JSON.stringify(data)
            }
        }).success(function (data) {
            $scope.myStyle = data;
        });


    };
});