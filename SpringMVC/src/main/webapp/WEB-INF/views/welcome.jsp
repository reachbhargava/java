
<%@include file="common/header.jspf"%>
<%@include file="common/navbar.jspf"%>

<div class="container">
	<div class="row">

		<spring:message code="todo.welcome"/> ${nameOnWelcome} <br /> 
		Click<a href="/list-todos">	here </a> to go to ToDos.
	</div>
</div>
<%@include file="common/footer.jspf"%>