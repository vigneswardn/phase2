describe('new App Test', function () {
	beforeEach(function() {
		module('newDirectiveApp');
		module('newApp');
	});
	
	var $controller;
    var $httpBackend;
    var $service;
    
    beforeEach(inject(function(_$controller_, _$httpBackend_) {
        $controller = _$controller_;
        $httpBackend = _$httpBackend_;
    }));
    /*
    describe('Test User Registration', function() {
    	
    	it("should register a new user", function() {
    		var $scope = {};
    		var controller = $controller('registerController', { $scope: $scope });
    		
    		var details = {};
    		details.firstName = 'new1';
    		details.lastName = 'new1Last';
    		details.userName = 'new1';
    		details.password = '123';
    		details.confirmPassword = '123';
    		details.email = 'new1@gmail.com';
    		details.phone = '9986513765';
    		//$scope.registerUser(details);
    		var dataPart =  { 	
				"userName" : "new1",    		
				"firstName" : "new1",
				"lastName" : "new1Last",
				"password" : "123",
				"email" : "new1@gmail.com",
				"phone" : "9986513765"
		     };
    		$scope.testfunc();
    		expect($scope.testVar).toEqual('newDee');
    	});
    });
    
    describe('Test New Post', function() {
    	it("should post a new post", function() {
    		var $scope = {};
    		var controller = $controller('newPostController', { $scope: $scope });
    		var newPostDetails = {};
    		newPostDetails.title = 'aaa';
    		newPostDetails.tags = 'aaa';
    		newPostDetails.content = 'aaa';
    		$scope.saveNewPost(newPostDetails);
    		var dataPart = {
    			"title": "aaa",
    			"tags": "aaa",
    			"content":"aaa"
    		};
    		expect($scope.newPostResult.title).toEqual('aaa');
    	});
    });
    */
    describe('Fake http backend', function () {
        it('should post blog', inject(function($http) {
          
          var $scope = {};
          
          // Code Under Test
          $http.post('/phase1/blogger/blog/addBlog/', { 
        	  title: 'aa',
        	  content: 'aa',
        	  createdBy: 'aa'
            })
            .success(function(data, status, headers, config) {
              $scope.postDetail = data;
            });
          // End Code 
          
          $httpBackend
            .when('POST', '/phase1/blogger/blog/addBlog/', { 
            	title: 'aa',
          	  	content: 'aa',
          	  	createdBy: 'aa' 
            })
            .respond({ 
            	title: 'aa',
            	content: 'aa'
            });
          $httpBackend.flush();
          expect($scope.postDetail.title).toEqual('aa');
        }));
    });
    /*
    describe('Fake http backend with service', function () {
        it('should register a user', inject(function($http) {
          var $scope = {};
    	  var service = $controller('registerService', { $scope: $scope });
    	  var dataPart =  { 	
    				"userName" : "new1",    		
    				"firstName" : "new1",
    				"lastName" : "new1Last",
    				"password" : "123",
    				"confirmPassword" : "123",
    				"email" : "new1@gmail.com",
    				"phone" : "9986513765"
    		     };
          var details = {};
    		details.firstName = 'new1';
    		details.lastName = 'new1Last';
    		details.userName = 'new1';
    		details.password = '123';
    		details.confirmPassword = '123';
    		details.email = 'new1@gmail.com';
    		details.phone = '9986513765';
    		
          $httpBackend
            .when('POST', '/phase1/blogger/user/addUser/', { 
            	userName : 'aa',
    			firstName : 'aa',
    			lastName : 'aa',
    			password : '123',
    			confirmPassword: '123',
    			email : 'aa',
    			phone : '9986513765' 
            })
            .respond({ 
            	userName : 'aa',
    			firstName : 'aa',
    			lastName : 'aa',
    			password : '123',
    			confirmPassword: '123',
    			email : 'aa',
    			phone : '9986513765'
            });
          var returnedPromise = $scope.register(details);
          var result;
          returnedPromise.then(function (response) {
              result = response.data;
          });
          
          $httpBackend.flush();
          expect(result.userName).toEqual('aa');
        }));
    });
    */
});