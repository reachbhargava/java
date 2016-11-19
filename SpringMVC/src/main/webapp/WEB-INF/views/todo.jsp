<%@include file="common/header.jspf"%>
<%@include file="common/navbar.jspf"%>

<div class="container">
	<h2>Adding a ToDO</h2>
	<form:form method="post" commandName="todo">
		<fieldset class="form-group">
			<form:label path="description">Description: </form:label>
			<form:input type="text" path="description" class="form-control"
				required="required" />
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="finishBy">Finish By </form:label>
			<form:input type="text" path="finishBy" class="form-control"
				required="required" />
			<form:errors path="finishBy" cssClass="text-warning" />
		</fieldset>

		<button class="btn btn-success" type="submit">Submit</button>
	</form:form>
</div>

<%@include file="common/footer.jspf"%>