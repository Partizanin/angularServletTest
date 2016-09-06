/**
 * Created by Partizanin on 03.09.2016.
 */
var app = angular.module("app", ['ui.bootstrap']);

app.controller("filmsController", function ($scope, $http, $anchorScroll) {
    $scope.currentPage = 1;
    $scope.numPerPage = 10;
    $scope.maxSize = 10;
    $scope.pagesCoutns = 1000;

    $scope.$watch('currentPage + numPerPage', function () {
        var currentPage = $scope.currentPage;

        var sendData = {
            page: currentPage,
            operationCall: "getData"
        };

        console.log("currentPage: " + currentPage);
        $scope.loaderStatys = true;

        $http({
            url: '/Servlet',
            method: 'GET',
            params: {
                jsonData: JSON.stringify(sendData)
            }
        }).success(function (data) {
            /** @namespace data.films */
            /** @namespace data.pagesCount */
            $scope.films = data.films;
            $scope.loaderStatys = false;
            $scope.pagesCoutns = data.pagesCount * 10;
            $anchorScroll();
        });

    });
});

