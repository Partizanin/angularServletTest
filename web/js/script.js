/**
 * Created by Partizanin on 03.09.2016.
 */
var app = angular.module("app", []);

app.controller("myCtrl", function ($scope, $http) {
    $http({
        url: '/Servlet',
        method: 'GET'
    }).success(function (data) {
        /** @namespace data.films */
        $scope.films = data.films.splice(0, (5));
    });
});