$(document).ready(function(){
		$("#display").hide(400,"linear");
		$("#email").hide(400,"linear");
		$("#password").hide(400,"linear");
		$("#passwordConfirm").hide(400,"linear");
		
		$("#display-input").focus(function(){
			$("#display").show(400,"linear");
		});
		$("#display-input").blur(function(){
			$("#display").hide(400,"linear");
		});

		$("#email-input").focus(function(){
			$("#email").show(400,"linear");
		});
		$("#email-input").blur(function(){
			$("#email").hide(400,"linear");
		});

		$("#password-input").focus(function(){
			$("#password").show(400,"linear");
		});
		$("#password-input").blur(function(){
			$("#password").hide(400,"linear");
		});

		$("#passwordConfirm-input").focus(function(){
			$("#passwordConfirm").show(400,"linear");
		});
		$("#passwordConfirm-input").blur(function(){
			$("#passwordConfirm").hide(400,"linear");
		});
	
	});