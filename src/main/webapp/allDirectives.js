/*
angular.module('newApp.directives', [])
	.directive("searchBlog", function(){
		// Runs during compile
		return {
			// name: '',
			// priority: 1,
			// terminal: true,
			// scope: {}, // {} = isolate, true = child, false/undefined = no change
			/
			controller: ['$scope', '$http', function($scope, $http){
				$http.get('data/pages.json').success(function(data){
					$scope.pages = data;
				});
			}],/
			// require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
			restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
			// template: '',
			templateUrl: 'partials/searchBlog.html',
			replace: true
			// transclude: true,
			// compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
		};
	});
*/
var appDirective = angular.module('newDirectiveApp', []);
appDirective.directive('searchBlogResults', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/searchBlog.html',
		replace: true
	};
});

/* This displays the placeholder for updating User Profile */
appDirective.directive('updateProfile', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/updateProfile.html',
		replace: true
	};
});

/* This displays the placeholder for Posting a new Blog */
appDirective.directive('postNewBlog', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/postNewBlog.html',
		replace: true
	};
});

/* This displays all the Blogs */
appDirective.directive('showAllBlogs', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/showAllBlogs.html',
		replace: true
	};
});

/* This displays all My Blogs */
appDirective.directive('showMyBlogs', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/showMyBlogs.html',
		replace: true
	};
});

/* This displays the chat */
appDirective.directive('showChat', function() {
	return {		
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		// template: '',
		templateUrl: 'partials/chat.html',
		replace: true
	};
});