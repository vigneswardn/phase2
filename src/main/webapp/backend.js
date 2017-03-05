var app = angular.module('newApp', ['ngRoute', 'newDirectiveApp','LocalStorageModule', 'base64']);
/* Write the route*/
app.config(function($routeProvider) {
	$routeProvider
	.when('/home', {
		templateUrl: 'home.html',
		controller  : 'bloggingHomeController'
	})
	.when('/newPost', {
		templateUrl: 'partials/postNewBlog.html',
		controller  : 'newPostController'
	})
	.when('/register', {
		templateUrl: 'partials/register.html',
		controller  : 'registerController'
	})
	.when('/', {
		templateUrl: 'partials/login.html',
		controller  : 'loginController'
	});
});
app.controller('blogHomeController',function($scope){

    /*      
    Here you can handle controller for specific route as well.
    */
});
app.service('validationService', function() {
	this.validatePhone = function(phone) { 
		var phonePattern = /^\d{10}$/;
		if(phonePattern.test(phone)) {  
			return true;  
		} else {  
			return false;  
		}  
	 };
	 this.validatePassword = function(pass, confirmPass) {
		 if(pass == confirmPass) {
			 return true;
		 } else {
			 return false;
		 }
	 };
});


/* Common Service to share data between different controllers. */
app.service('searchStringService', function() {
	var searchString = '';
	var homeSelectedTab = {};
	var loggedInUserId = '';
	this.getSearchString = function() {
		return searchString;
	}
	this.setSearchString = function(data) {
		searchString = data;
	}
	this.getSelectedTab = function() {
		return homeSelectedTab;
	}
	this.setSelectedTab = function(tabData) {
		homeSelectedTab = tabData;
	}
	this.setloggedInUserId = function(data) {
		loggedInUserId = data;
	}
	this.getloggedInUserId = function() {
		return loggedInUserId;
	}
});

app.controller("bloggingHomeController", function($scope, $location, searchStringService, localStorageService){

    /*      
    Here you can handle controller for specific route as well.
    */
	
	$scope.tab = 'allBlogs';
	//$scope.tab = searchStringService.getSelectedTab();
	$scope.loggedInUserName = localStorageService.get('loggedInUserName');
	$scope.loggedInUserfirstName = localStorageService.get('loggedInUserfirstName');
	$scope.selectTab = function(selectedTab) {
		//$scope.tab = selectedTab;
		searchStringService.setSelectedTab(selectedTab);
		$scope.tab = searchStringService.getSelectedTab();
	}
	
	$scope.isSelected = function(checkTab) {
		return $scope.tab == checkTab;
	}
	$scope.setSearchQuery = function() {
		searchStringService.setSearchString($scope.searchQuery);
	}
	//searchStringService.setSearchString($scope.searchQuery);
	$scope.logoutUser = function() {
		localStorageService.clearAll();
		var tempName = localStorageService.get('loggedInUserName');
		$location.path('/');
	};
	
});

app.service('newPostService', function($http, localStorageService) {
	this.save = function(blog) {
		var token = localStorageService.get('token');
		var loggedInUserId = localStorageService.get('loggedInUserId');
		var loggedInUserName = localStorageService.get('loggedInUserName');
		var arr = blog.tags.split(',');
		var dataUser = [{
			userId : loggedInUserId
		}];
		var dataPart = {
			title : blog.title,
			content : blog.content,
			createdBy : loggedInUserName,			
			tags : arr,
			userId : loggedInUserId
		};
		$http.post('/phase2/blogger/blog/addBlog/', dataPart, 
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
		.then(function(data) {
			return data;
		});
		//alert('Welcome, ' + blog.title + ' ' + blog.tags);
	};
});

/*
app.controller("newPostController", function($scope, $location, newPostService) {
	// Accept value based on click do necessary actions. Call the service
	$scope.newPost = '';
	$scope.newPostResult = '';
	$scope.tempNewPost = '';
	$scope.saveNewPost = function() {
		$scope.tempNewPost = 'dee1';
		if($scope.newPost.title != null && $scope.newPost.content != null) {
			$scope.tempNewPost = 'dee';
			
			var c = JSON.parse(JSON.stringify($scope.newPost));
			$scope.newPostResult = newPostService.save(c);
			$scope.newPost = '';
			$location.path('/home');
		} else {
			$scope.error = 'Title and Post are Mandatory';
		}
		
	};
	$scope.cancelNewPost = function($scope) {
	};
});
*/
/*TEMP*/
app.controller("newPostController", function($scope, $location, newPostService) {
	/* Accept value based on click do necessary actions. Call the service*/
	$scope.newPost = '';
	$scope.newPostResult = '';
	$scope.tempNewPost = '';
	$scope.saveNewPost = function(postDetails) {
		$scope.tempNewPost = 'dee1';
		if(postDetails.title != null && postDetails.content != null && postDetails.tags != null) {
			$scope.tempNewPost = 'dee';
			/* Get Title, tags, content 
			 * Pass the data to a service to insert into DB
			 */
			var c = JSON.parse(JSON.stringify(postDetails));
			$scope.newPostResult = newPostService.save(c);
			$scope.tempNewPost = 'dee3';
			$scope.newPost = '';
			$location.path('/home');
		} else {
			$scope.errorPost = 'Title and Post and Tags are Mandatory';
		}
		
	};
	$scope.cancelNewPost = function($scope) {
		/* Do Nothing function */
	};
});
app.service('commentsService', function($http, $q, localStorageService) {
	var deferred = $q.defer();

	this.getComments = function(id) {
		var token = localStorageService.get('token');
		return $http.get('/phase2/blogger/blog/getComments/' + String(id),
			{
				withCredentials: true,
				headers:{ 'Authorization':  'Bearer ' +  token}
			})
			.then(function(response) {
				//$scope.allBlogsList = response.data;
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
	this.postComments = function(msg, id) {
		/* TODO: userId should be populated dynamically.*/
		var userId = localStorageService.get('loggedInUserName');
		var token = localStorageService.get('token');
		var commentsList = new Array();
		commentsList.push({
			"comment" : msg,
			"createdBy" : userId
		});
		var dataPart = {
			"blogId" : id,
			"comments" : commentsList
		};
		return $http.post('/phase2/blogger/blog/addComments/', dataPart,
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
});

app.service('getAllBlogsService', function($http, $q, localStorageService) {
	var deferred = $q.defer();

	this.getAllBlogs = function() {
		 var token = localStorageService.get('token');
		return $http.get('/phase2/blogger/blog/getAllBlogs/',
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				//$scope.allBlogsList = response.data;
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
});

app.controller('showAllBlogsController', function($scope, getAllBlogsService, commentsService){
	$scope.tempServiceController = "hello controller";
	getAllBlogsService.getAllBlogs()
		.then(function(data) {
			$scope.allBlogs = data;
		}, function(error) {
			
		});
	
	$scope.sel = '';
	$scope.selectedComment = null;
	$scope.selectBlog = function(val1) {
		$scope.sel = val1;
	};
	$scope.isSelectedBlog =function(val2) {
		return $scope.sel == val2;
	};
	$scope.selectComment = function(val) {
		$scope.selectedComment = val;
	}
	$scope.isSelectedComment = function(val) {
		return $scope.selectedComment == val;
	};
	$scope.passBlogId = function(id) {
		var c = JSON.parse(JSON.stringify(id));
		commentsService.getComments(c)
			.then(function(dataComments) {
				$scope.allComments = dataComments;
			}, function(error) {
				
			});
	};
	$scope.clearComments = function() {
		$scope.allComments = null;
	};
	$scope.commentMessage = null;
	$scope.postCommentMessage = function(blogId) {
		var id = JSON.parse(JSON.stringify(blogId));
		var c = JSON.parse(JSON.stringify($scope.commentMessage));		
		commentsService.postComments(c, id)
		.then(function(data) {
			
		}, function(error) {
			
		});
		this.commentMessage = null;
	};
	
});

/***************************************************************************
 * ***********************   GET USER SPECIFIC BLOGS  **********************
 ***************************************************************************/
/* Service to get all My Blogs 
 * Here userId has to be specified.
 */
app.service('getMyBlogsService', function($http, $q, localStorageService) {
	var deferred = $q.defer();

	this.getMyBlogs = function(uId) {
		var dataPart = {
			userId : uId
		};
		var token = localStorageService.get('token');
		return $http.post('/phase2/blogger/blog/getBlogs/', dataPart, 
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				//$scope.allBlogsList = response.data;
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
});


/* Controller to get My Blogs */
app.controller('showMyBlogsController', function($scope, getMyBlogsService, localStorageService, commentsService){
	var uId = localStorageService.get('loggedInUserId');
	var u = JSON.parse(JSON.stringify(uId));
	getMyBlogsService.getMyBlogs(u)
		.then(function(data) {
			$scope.allMyBlogs = data;
		}, function(error) {
			
		});
	
	$scope.selectedComment = '';
	$scope.selectComment = function(val) {
		$scope.selectedComment = val;
	}
	$scope.isSelectedComment = function(val) {
		return $scope.selectedComment == val;
	};
	
	$scope.allMyComments = null;
	$scope.passBlogId = function(id) {
		var c = JSON.parse(JSON.stringify(id));
		commentsService.getComments(c)
			.then(function(data) {
				$scope.allMyComments = data;
			}, function(error) {
				
			});
	};
	
	$scope.clearComments = function() {
		$scope.allMyComments = null;
	};
	$scope.mycommentMessage = null;
	$scope.postCommentMessage = function(blogId) {
		var id = JSON.parse(JSON.stringify(blogId));
		var c = JSON.parse(JSON.stringify($scope.mycommentMessage));		
		commentsService.postComments(c, id)
		.then(function(data) {
			
		}, function(error) {
			
		});
		this.mycommentMessage = null;
	};
});

/***************************************************************************
 * ***********************    SEARCH A BLOG    *****************************
 ***************************************************************************/

/* Service for Searching Blogs */
app.service('searchBlogService', function($http, $q, localStorageService) {
	var deferred = $q.defer();

	this.searchBlog = function(query) {
		var token = localStorageService.get('token');
		return $http.get('/phase2/blogger/blog/searchBlog/' + String(query),
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				//$scope.allBlogsList = response.data;
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
});

/* Controller to search blog */
app.controller('searchBlogController',function($scope, searchBlogService, searchStringService, $location){
	$scope.searchString = searchStringService.getSearchString();
	/* Donot search if the query string is empty */
	if ($scope.searchString != '') {
		searchBlogService.searchBlog($scope.searchString)
			.then(function(data) {
				$scope.searchBlogs = data;
			}, function(error) {
				
			});
		searchStringService.setSearchString('');
	}
});

/* Service to update profile*/
app.service('updateProfileService', function($http, $q, localStorageService) {
	var deferred = $q.defer();

	this.getProfileDetails = function(userId) {
		var token = localStorageService.get('token');
		return $http.get('/phase2/blogger/user/getUser/' + String(userId), 
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
	this.save = function(userDetails, uId) {
		var token = localStorageService.get('token');
		/* TODO Why userId should be part of this request ?*/
		var dataPart = {
			userId : uId,
			userName : userDetails.userName,
			firstName : userDetails.firstName,			
			lastName : userDetails.lastName,
			password : userDetails.password,
			phone : userDetails.phone
		};
		return $http.put('/phase2/blogger/user/updateUser/', dataPart,
				{
					withCredentials: true,
					headers:{ 'Authorization':  'Bearer ' +  token}
				})
		.then(function(response) {
			deferred.resolve(response.data);
	        return deferred.promise;
		}, function(response) {
			// the following line rejects the promise 
	        deferred.reject(response);
	        return deferred.promise;
		});
	};
});

/* Controller to update the profile*/
app.controller('updateProfileController',function($scope, $location, updateProfileService, localStorageService, validationService){
	/* 1. Call the service to get the user details */
	var uId = localStorageService.get('loggedInUserId');
	//$scope.tempId = '2';
	updateProfileService.getProfileDetails(uId)
		.then(function(data) {
			$scope.updateProfile = data;
		}, function(error) {
			
		});
	$scope.updateProfileSave = function() {
		if ($scope.updateProfile.firstName != '' && $scope.updateProfile.phone != '' && $scope.updateProfile.password != '') {
		/* Get user Details. 
		 * Pass the data to a service to insert into DB
		 */
			if (validationService.validatePhone($scope.updateProfile.phone)) {
				if (validationService.validatePassword($scope.updateProfile.password, $scope.updateProfile.confirmPassword)) {
					var c = JSON.parse(JSON.stringify($scope.updateProfile));
					var uId = localStorageService.get('loggedInUserId');
					var u = JSON.parse(JSON.stringify(uId));
					updateProfileService.save(c, u)
						.then(function(data) {
							$scope.updateProfile = data;
							$scope.error = '';
						}, function(error) {
							alert("Updating User Details Failed");
						});
				} else {
					$scope.error = 'Password and Confirm Password donot match';
				}
			} else {
				$scope.error = 'Phone number not valid';
			}
		} else {
			$scope.error = 'Firstname or Password or Phone should not be empty';
		}
	};
	$scope.updateProfileCancel = function() {
		$location.path('/home');
	};
});

/****************************************************************** 
 ************************  USER LOGIN  ***************************
 ******************************************************************/
/* Service for login*/
app.service('loginService', function($http, $q, $base64) {
	var deferred = $q.defer();
	this.loginFn = function(userCredentials) {		
		var encodeduserNamePassword = $base64.encode(userCredentials.userName+ ':' + userCredentials.password);
		/* TODO : pass empty data here */
		var dataPart = {
			userName : userCredentials.userName,
			password : userCredentials.password
		};
		return $http.get('/phase2/blogger/user/validateUser/',
				{
	        		withCredentials: true,
	        		headers:{ 'Authorization':  'Basic ' +  encodeduserNamePassword}
				})
			.then(function(response) {
				//$scope.allBlogsList = response.data;
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
});

/* Controller for login*/
/* localStorageService -- Using this service the localStorage can used */
app.controller('loginController', function($scope, $location, loginService, searchStringService, localStorageService) {
	$scope.loginApp = function() {
		var c = JSON.parse(JSON.stringify($scope.login));
		loginService.loginFn(c)
			.then(function(data) {
				//searchStringService.setloggedInUserId(data.userName);
				localStorageService.set('loggedInUserId',data.userId);
				localStorageService.set('loggedInUserName',data.userName);
				localStorageService.set('loggedInUserfirstName',data.firstName);
				
				/* TODO Assuming that the access token is part of the response. Stored in the DB */
				//data.accessToken
				localStorageService.set('token', 'temporaryToken');
				alert(data.userName);
				$location.path('/home');
			}, function(error) {
				alert("Login Failure");
			});
	};
	$scope.registerUser = function() {
		$location.path('/register');
	};
});

/*****************************  REGISTER NEW USER	*****************************/

/* Service for Registration */
app.service('registerService', function($http, $q) {
	var deferred = $q.defer();
	this.register = function(userDetails) {
		/* TODO: Populate the userId in the fly */
		var dataPart = {
			userName : userDetails.userName,
			firstName : userDetails.firstName,
			lastName : userDetails.lastName,
			password : userDetails.password,
			email : userDetails.email,
			phone : userDetails.phone
		};
		return $http.post('/phase2/blogger/user/addUser/', dataPart)
			.then(function(response) {
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
	
	this.registerServiceTemp = function() {
		return 'registerServiceTemp';
	};
});

/* Controller for Registration*/
/* WORKING CODE.
app.controller('registerController', function($scope, $location, registerService, validationService) {
	$scope.register = '';
	$scope.newUserData = '';
	$scope.registerUser = function() {
		$scope.error = '';
		if ($scope.register != null && $scope.register.firstName != null && $scope.register.userName != null &&
			$scope.register.password != null && $scope.register.email != null && $scope.register.phone != null) {
			if (validationService.validatePhone($scope.register.phone)) {
				// Validate Password and Confirm Password 
				if (validationService.validatePassword($scope.register.password, $scope.register.confirmPassword)) {
				var c = JSON.parse(JSON.stringify($scope.register));
				registerService.register(c)
					.then(function(data) {
						$scope.newUserData = data;
						$location.path('/');
					}, function(error) {
						alert("Registration Failed");
						$location.path('/');
					});
				} else {
					$scope.error = 'Password and Confirm Password donot match';
				}
			} else {
				$scope.error = 'Enter a valid Phone number';
			}
		} else {
			$scope.error = 'Mandatory field UserName or FirstName or Password or Email or Phone empty';
		}
	};
	$scope.registerCancel = function() {
		$location.path('/');
	};
	$scope.testfunc = function() {
		return 'test';
	}
});
*/

/*TEMP*/
app.controller('registerController', function($scope, $location, registerService, validationService) {
	$scope.newUserData = '';
	$scope.temp = '';
	$scope.registerUser = function(userDetails) {		
		$scope.error = '';
		if (userDetails != null && userDetails.firstName != null && userDetails.userName != null &&
				userDetails.password != null && userDetails.email != null && userDetails.phone != null) {		
			if (validationService.validatePhone(userDetails.phone)) {
					// Validate Password and Confirm Password 
				if (validationService.validatePassword(userDetails.password, userDetails.confirmPassword)) {
					$scope.temp = 'dee2';
					var c = JSON.parse(JSON.stringify(userDetails));
					$scope.temp = 'after stringify';
					registerService.register(c)
						.then(function(data) {
							$scope.newUserData = data;
							$scope.temp = 'dee3';
							$location.path('/');
						}, function(error) {
							$scope.temp = 'dee3';
							alert("Registration Failed");
							$location.path('/');
						});
				} else {
					$scope.error = 'Password and Confirm Password donot match';
				}
			} else {
				$scope.error = 'Enter a valid Phone number';
			}
		} else {
			$scope.error = 'Mandatory field UserName or FirstName or Password or Email or Phone empty';
		}
	};
	$scope.registerCancel = function() {
		$location.path('/');
	};
	$scope.testVar = 'dee';
	$scope.testfunc = function() {
		$scope.testVar = registerService.registerServiceTemp();
		
	};
});
/***************************************************************************
 ********************   CHAT FUNCTIONALITY   *******************************
 ***************************************************************************/

app.service('chatService', function($http, $q, localStorageService) {
	var deferred = $q.defer();
	this.save = function(msg, uName) {
		var dataPart = {
			message : msg,
			createdBy : uName
		};
		var token = localStorageService.get('token');
		return $http.post('/phase2/blogger/chat/addChat/', dataPart, 
				{
    				withCredentials: true,
    				headers:{ 'Authorization':  'Bearer ' +  token}
				})
			.then(function(response) {
				deferred.resolve(response.data);
		        return deferred.promise;
			}, function(response) {
				// the following line rejects the promise 
		        deferred.reject(response);
		        return deferred.promise;
			});
	};
	
	this.getChat = function() {
		var token = localStorageService.get('token');
		return $http.get('/phase2/blogger/chat/getChats', 
			{
				withCredentials: true,
				headers:{ 'Authorization':  'Bearer ' +  token}
			})
		.then(function(response) {
			deferred.resolve(response.data);
	        return deferred.promise;
		}, function(response) {
			// the following line rejects the promise 
	        deferred.reject(response);
	        return deferred.promise;
		});
	};
});

app.controller('chatController', function($scope, $route, chatService, localStorageService) {
	$scope.chatMessage = '';
	chatService.getChat()
	.then(function(data) {
		$scope.allChats = data;
	}, function(error) {
		
	});
	$scope.postChatMessage = function() {
		if ($scope.chatMessage != '') {
			var c = JSON.parse(JSON.stringify($scope.chatMessage));
			var uName = localStorageService.get('loggedInUserName');
			var u = JSON.parse(JSON.stringify(uName));
			chatService.save(c, u)
			.then(function(data) {
				$scope.chatMessage = '';
			}, function(error) {
				
			})
			
			chatService.getChat()
			.then(function(data) {
				$scope.allChats = data;
				$route.reload();				
			}, function(error) {
				
			});
		} else {
			$scope.error = 'Enter Some Message';
		}
	};
});
