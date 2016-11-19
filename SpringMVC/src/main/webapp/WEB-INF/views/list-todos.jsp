

	<%@include file="common/header.jspf" %>
	<%@include file="common/navbar.jspf" %>
	
	<div class="container">
		<br>
		<spring:message code="todo.hello" /> ${nameOnWelcome}
		<br /> <spring:message code="todo.message" />
		<br />		
		<table class="table table-striped">
			<thead>
				<th>Description</th>
				<th>Finish By</th>
				<th>Is Done?</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td><fmt:formatDate value="${todo.finishBy}" pattern="dd/MM/YYYY"/> </td>
						<td>${todo.done}</td>
						<td>
							<a class="btn btn-primary" href="/update-todo?id=${todo.id}">Update</a>
							<a class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br> <a class="btn btn-primary" href="/add-todo">Add ToDo</a>
	</div>
	
	
	<%@include file="common/footer.jspf" %>