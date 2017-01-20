<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<title>Welcome</title>

<script type="text/javascript">
	
	function clearErrors() {
		if(document.getElementById("userName.errors")) {
			document.getElementById("userName.errors").innerHTML = "";
		}
        
        $("#userName").parents(".control-group").removeClass("text-warning");
        $("#userName").siblings("#result").html("");
	}

	function markErrors(errors) {
        $("#userName").parents(".control-group").addClass("text-warning");
        $("#userName").siblings("#result").html(errors['userName']);
	}
	
	function onChangeOfName() {
		clearErrors();
		var userNameEntered = $("#userName")[0].value;
		
		if (userNameEntered != '') {
	        var data = {userName:userNameEntered};
	        var validationUrl = "validateUserName";
	        $.get(validationUrl, data, function(response) {
	        	
	            if (response.status == "FAIL") {
	            	console.log('Earlier --> ', response.errors)
	            	markErrors(response.errors);
	            }
	            
	        }, "json");
		}

    }
	
</script>

</head>
<body>
	<div class="container">

		<!--
		novalidate is set here on the form. I was inclined to put a required attribute for both the 
		username and the password but chrome/edge show a message that these are not filled if the user clicks 
		on the submit button without entering any values to these fields. 
		If that message is acceptable to be shown, please remove the novalidate attribute in the below line.
		-->
		<form:form action="saveUser" method="post" commandName="userDetails"
			class="form-horizontal" novalidate="novalidate">

			<div class="form-group">

				<h3 class="h3 col-sm-offset-2 col-sm-10">
					<spring:message code="welcome" />
				</h3>

				<label class="control-label col-sm-2" for="userName"><spring:message
						code="userName" /></label>
				<div class="control-group col-sm-5">
					<form:input type="text" path="userName" onchange="onChangeOfName()"
						class="form-control col-sm-5" required="required"
						placeholder="Enter User Name" />
					<form:errors path="userName" cssClass="text-warning" />
					<span id="result"></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="password"><spring:message
						code="password" /></label>
				<div class="control-group col-sm-5">
					<form:input type="password" path="password"
						class="form-control col-sm-5" required="required"
						placeholder="Enter Password" />
					<form:errors path="password" cssClass="text-warning" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-success" type="submit">Submit</button>
				</div>
			</div>

			<h3 class="h3 text-success col-sm-offset-2 col-sm-10">${message}</h3>

		</form:form>

	</div>

</body>
</html>