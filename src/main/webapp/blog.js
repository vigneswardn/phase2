$(document).ready(function(){
	var globalUserId = 0;
	var globalUserFirstName = 0;
	var globalUserName = 0;
	var urlPart1='http://localhost:8080/phase1/blogger';
	/* Hide fields Not required*/
	$("#loginFormDiv").show();
	$("#registerFormDiv").hide();
	$("#updateProfileDiv").hide();
	$("#headerBox").hide();
	$("#favoriteDiv").hide();
	$("#searchResultsDiv").hide();
	$("#newPostDiv").hide();
	$("#blogAreaDiv").hide();
	$("#chatAreaDiv").hide();
	
	/* Login Button click */
	$("#loginButton").click(function() {
		var dataPart = {
			userName : $("#loginUserName").val(),
			password : $("#loginPassword").val()
		};
		$.ajax({
			url : urlPart1+'/user/getUser/',
			type : 'POST',
			contentType: 'application/json',
			crossDomain : true,
			data : JSON.stringify(dataPart),
			success : function(response) {
				globalUserId = response.userId;
				globalUserFirstName = response.firstName;
				globalUserName = response.userName;
				$("#loginFormDiv").hide();
				$("#headerBox").show();
				//$("#updateProfile")= String(globalUserFirstName);
				$("#favoriteDiv").show(
					function() {
						var urlVal = urlPart1+'/blog/getFavourites/'+String(globalUserId);
						$.ajax({
							url : urlval,
							type : 'get',
							contenttype: 'application/json',
							accept : 'application/json',
							success : function(response) {
								/* todo put the data here*/				
								$('#favoritearea').val('');
								for (i in response) {
									$('#favoritearea').val($('#favoritearea').val()+response[i].title+"\n");
								}
						   }
						})
					}	
				);
				$("#blogAreaDiv").show(
						function() {
							var dataPartBlog = {
								userId : String(globalUserId)	
							};
							$.ajax({
								url : urlPart1+'/blog/getBlogs/',
								type : 'post',
								contentType: 'application/json',
								crossDomain : true,
								data : JSON.stringify(dataPartBlog),
								success : function(response) {
									$('#bloggingArea').val('');
									for (i in response) {
										$('#bloggingArea').val($('#bloggingArea').val()+"\n Title:" +response[i].title +" \n ");
										$('#bloggingArea').val($('#bloggingArea').val()+"\n Content:" +response[i].content +"\n");
									}
							   }
							});
						});
				$("#chatAreaDiv").show(
					function() {
						$.ajax({
							url : urlPart1+'/chat/getChats',
							type : 'get',
							contenttype: 'application/json',
							accept : 'application/json',
							success : function(response) {
								$('#chatarea').val('');
								for (i in response) {
									$('#chatarea').val($('#chatarea').val()+response[i].createdby +" | ");
									$('#chatarea').val($('#chatarea').val()+response[i].createddate +"\n");
									$('#chatarea').val($('#chatarea').val()+response[i].message +"\n");
								}
						   }
						})
					}	
				);
				//loadBlog(globalUserId);
		   }
		});
	});
	
	/* Click on Register Button on Login Page*/
	$("#registerButton").click(function() {
		$("#loginFormDiv").hide();
		$("#registerFormDiv").show();
	});
	
	/* Click on Register button of Register Page */
	$("#registerUserButton").click(function() {
		var dataPart = {
			userName : $("#registerUserName").val(),
			firstName: $("#registerFirstName").val(),
			lastName : $("#registerLastName").val(),
			password : $("#registerPassword").val(),
			email : $("#registerEmail").val(),
			phone : $("#registerPhone").val()
		};
		$.ajax({
			url : urlPart1+'/user/addUser/',
			type : 'POST',
			contentType: 'application/json',
			crossDomain : true,
			data : JSON.stringify(dataPart),
			success : function(response) {
				$("#loginFormDiv").show();
				$("#registerFormDiv").hide();
		   }
		});
	});
	
	/* Click on Cancel button of Register Page */
	$("#registerCancel").click(function() {
		$("#loginFormDiv").show();
		$("#registerFormDiv").hide();
		
	});
	
	/* Click on new post */
	$("#newPost").click(function() {
		$("#blogAreaDiv").hide();
		$("#updateProfileDiv").hide();
		$("#searchResultsDiv").hide();
		$("#newPostDiv").show();
	});
	
	/* Click on save of NEW Post */
	$("#saveNewPost").click(function() {
		var val = $("#tags").val();
		var arr = val.split(',');
		var dataUser = [{
			userId : globalUserId
		}];
		var dataPart = {
			title : $("#title").val(),
			content : $("#newPostData").val(),
			createdBy : String(globalUserName),			
			tags : arr,
			users : dataUser
		};
		$.ajax({
			url : urlPart1+'/blog/addBlog/',
			type : 'post',
			contentType: 'application/json',
			crossDomain : true,
			data : JSON.stringify(dataPart),
			success : function(response) {
				$("#newPostDiv").hide();
				$("#updateProfileDiv").hide();
				$("#searchResultsDiv").hide();
				$("#blogAreaDiv").show(
					function() {
						var dataPartBlog = {
							userId : String(globalUserId)	
						};
						$.ajax({
							url : urlPart1+'/blog/getBlogs/',
							type : 'post',
							contentType: 'application/json',
							crossDomain : true,
							data : JSON.stringify(dataPartBlog),
							success : function(response) {
								$('#bloggingArea').val('');
								for (i in response) {
									$('#bloggingArea').val($('#bloggingArea').val()+"\n Title:" +response[i].title +" \n ");
									$('#bloggingArea').val($('#bloggingArea').val()+"\n Content:" +response[i].content +"\n");
								}
						   }
						});
						/*
						$.ajax({
							url : urlPart1+'/blog/getBlogs/',
							type : 'post',
							contentType: 'application/json',
							crossDomain : true,
							
							data : JSON.stringify(dataPart),
							success : function(response) {
								for (i in response) {
									// Create a new text area for the blog
									var blogArea = document.createElement("textarea");
									blogArea.setAttribute(style="overflow:scroll;");
									blogArea.value(response[i].title);
									blogArea.value(response[i].content);
									$.ajax({
										url : urlPart1+'/blog/getComments/' + String(blogs[i].blogId),
										type : 'get',
										contentType: 'application/json',
										crossDomain : true,
										success : function(responseComment) {
											var comArea = document.createElement("textarea");
											comArea.setAttribute(style="overflow:scroll;");
											for (j in responseComment) {
												comArea.val(comArea.val() + responseComment[j].comment);
											}
										}//function close
									});
									$("#blogAreaDiv").appendChild(blogArea);
									$("#blogAreaDiv").appendChild(comArea);
								}//end of for
							} // end of function()		   
						});
						*/
					}
				);				
		   }
		});
	});
	
	/* Click on cancel of NEW Post*/
	$("#cancelNewPost").click(function() {
		$("#newPostDiv").hide();
		$("#updateProfileDiv").hide();
		$("#searchResultsDiv").hide();
		$("#blogAreaDiv").show();
	});
	
	/* Click on the username to update the profile*/
	$("#updateProfile").click(function() {
		$("#newPostDiv").hide();
		$("#searchResultsDiv").hide();
		$("#blogAreaDiv").hide();		
		$("#updateProfileDiv").show();
		/* Get the user info */
		$.ajax({
			url : urlPart1+'/user/getUser/'+ String(globalUserId),
			type : 'get',
			contentType: 'application/json',
			accept : 'application/json',
			data : JSON.stringify(dataPart),
			success : function(response) {
				$('#updateProfileFirstName').html(response.firstName);
				$('#updateProfileLastName').html(response.lastName);
				$('#updateProfileUserName').html(response.userName);
				$('#updateProfilePassword').html(response.password);
				$('#updateProfileEmail').html(response.email);
				$('#updateProfilePhone').html(response.phone);
				$('#updateProfileInterest').html(response.areaOfInterest);				
		   }
		});
	});
	/* Update Profile button click */
	$("#updateProfileButton").click(function() {
		var dataPart = {
			userName : $("#updateProfileUserName").val(),
			firstName: $("#updateProfileFirstName").val(),
			lastName : $("#updateProfileLastName").val(),
			password : $("#updateProfilePassword").val(),
			email : $("#updateProfileEmail").val(),
			phone : $("#updateProfilePhone").val(),
			areaOfInterest : $("#updateProfileInterest").val()
		};
		$.ajax({
			url : urlPart1+'/user/updateUser/',
			type : 'post',
			contentType: 'application/json',
			accept : 'application/json',
			data : JSON.stringify(dataPart),
			success : function(response) {
				$("#loginFormDiv").show();
				$("#registerFormDiv").hide();
		   }
		});
	});
	
	/* Cancel button click on the Profile*/
	$("#updateProfileCancel").click(function() {
		$("#updateProfileDiv").hide();
		$("#searchResultsDiv").hide();
		$("#newPostDiv").hide();
		$("#blogAreaDiv").show();		
	});
	
	/* Click on search blog*/
	$("#searchBlog").click(function() {
		var dataPart = {
			searchContent : $("#searchBlogInput").val()
		};
		$.ajax({
			url : urlPart1+'/blog/getBlog/',
			type : 'post',
			contentType: 'application/json',
			accept : 'application/json',
			data : JSON.stringify(dataPart),
			success : function(response) {
				$("#updateProfileDiv").hide();				
				$("#newPostDiv").hide();
				$("#blogAreaDiv").show();
				$("#searchResultsDiv").hide();
				$("#serachResultArea").html("Key :");
				$("#serachResultArea").html($("#searchBlogInput").val());
				/* TODO : Check the display part */
				for(var i in response) {
					var re = response[i].title + "\n by" + response[i].createdBy + "on" + response[i].createDate;
					/*
					$("#serachResultArea").html(re);
					$("#serachResultArea").html("\n");
					*/
					var box = $("#serachResultArea");
					box.val(box.val() + re);
				}
		    }
		});
	});
		
	/* Invite Users */
	$('#invites').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			/* TODO Invite friends*/
		}
	});
	
	/* Chat */
	/* TODO Get Chat */
	/* Should this be a function call ?*/
	
	/* Post Chat*/
	$("#postChatButton").click(function() {
		var dataPart = {
			message : $("#chatBox").val(),
			createdBy : String(globalUserName)		
		};
		$.ajax({
			url : urlPart1+'/chat/addChat',
			type : 'post',
			contentType: 'application/json',
			accept : 'application/json',
			data : JSON.stringify(dataPart),
			success : function(response) {
				/* TODO put the data here*/				
				$('#chatArea').val('');
				for (i in response) {
					$('#chatArea').val($('#chatArea').val()+response[i].message);
					$('#chatArea').val($('#chatArea').val()+response[i].createdBy+"\n");
				}
		   }
		});
		/*
		$("#chatAreaDiv").show(
				function() {
					$.ajax({
						url : urlPart1+'/chat/getChats',
						type : 'get',
						contenttype: 'application/json',
						accept : 'application/json',
						success : function(response) {
							$('#chatarea').val('');
							for (i in response) {
								$('#chatarea').val($('#chatarea').val()+response[i].createdby +" | ");
								$('#chatarea').val($('#chatarea').val()+response[i].createddate +"\n");
								$('#chatarea').val($('#chatarea').val()+response[i].message +"\n");
							}
					   }
					})
				}	
			);
			*/
	});
	
	/* Logout*/
	$("#logout").click(function() {
		$("#loginFormDiv").show();
		$("#registerFormDiv").hide();
		$("#updateProfileDiv").hide();
		$("#headerBox").hide();
		$("#favoriteDiv").hide();
		$("#searchResultsDiv").hide();
		$("#newPostDiv").hide();
		$("#blogAreaDiv").hide();
		$("#chatAreaDiv").hide();
	});
});
