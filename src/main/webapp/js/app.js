var app = angular.module('PostModule',['ngRoute']);

/*Routing*/
app.config(['$routeProvider',
  function($routeProvider) {
      $routeProvider.
          when('/comment/:param', {
          templateUrl: '/comments.html',
          controller: 'CommentController'
      }).
          when('/post', {
          templateUrl: '/post.html',
          controller: 'PostController'
      }).
          when('/email', {
          templateUrl: '/email.html',
          controller: 'EmailController'
      })
      .otherwise('/post')
  }]);

/*Http Interceptor Facotry class*/
app.factory('PathInterceptor', ['$location',function($location) {  
    var path = {
        request: function(config) {
           	var path = $location.absUrl(); 
            var pathArray = path.split('/');
            var appContext = pathArray[3];
        	config.url = "/"+appContext+config.url;
            return config;
        },
        response: function(response) {
            return response;
        }
    };
    return path;
}]);

app.config(['$httpProvider', function($httpProvider) {  
    $httpProvider.interceptors.push('PathInterceptor');
}]);

/*Posts & Comments Controller and Service*/
app.controller('PostController', function($scope,$http,$document,PostService) {
	$scope.posts = [];
	
	PostService.getPosts(1).then(function(data){
		$scope.posts =  data;
	});
	$scope.loadPosts = function(event){
		var pageNumber = Number($scope.page);
		if(event.target.name=='forward'){
			pageNumber=pageNumber+1;
		}else{
			pageNumber=pageNumber-1;
		}
		
		PostService.getPosts(pageNumber).then(function(data){
			$scope.posts =  data;
		});
		$scope.page=pageNumber;
	}
	
});

/*Post Service */
app.service('PostService',function($http){
	this.getPosts = function(pageNumber){
		return $http.get('/rs/post?pageNumber='+pageNumber)
		.then(function (response){
		return response.data;
	})}
});

/*comments controller*/
app.controller('CommentController', function($scope,$http,$routeParams) {
	$scope.comments = [];
	var _post_id = $routeParams.param;
	$http.get('/rs/post/comment?postId='+_post_id)
	.then(function (response){
		$scope.comments = response.data;
	})
});
/*Email controller and service*/
app.controller('EmailController', function($scope,$http,EmailService) {
	$scope.email='';
	$scope.emails=[];
	$scope.validateEmail = function(){
		EmailService.getEmail($scope.email).then(function(data){
			$scope.validEmail = data[0].valid;
			$scope.emails =  data;
		});
	}
});

app.service('EmailService',function($http){
	this.getEmail = function(email){
		return $http.get('/rs/emailService?email='+email)
		.then(function (response){
		return response.data;
	})}
});