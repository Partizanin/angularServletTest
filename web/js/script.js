/**
 * Created by Partizanin on 03.09.2016.
 */
var app = angular.module("app", ['ui.bootstrap']);

app.controller("filmsController", function ($scope, $http, $anchorScroll) {
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 5;
    $scope.pagesCoutns = 1000;

    $scope.$watch('currentPage + numPerPage', function () {
        var currentPage = $scope.currentPage;

        console.log("currentPage: " + currentPage);
        $scope.loaderStatys = true;

        $http({
            url: '/Servlet',
            method: 'GET',
            params: {
                page: currentPage
            }
        }).success(function (data) {
            /** @namespace data.films */
            $scope.films = data;
            $scope.loaderStatys = false;
            $anchorScroll();
        });

    });
});

