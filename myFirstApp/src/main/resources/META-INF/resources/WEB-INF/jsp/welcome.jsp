<%@ include file="common/navigation.jspf" %>
<%@ include file="common/header.jspf" %>
	<div class = "container">
		${name} Welcome!
		<h1> Welcome to my app</h1>
		<div>Your name is  ${name}</div>
		<div>Your password is  ${password}</div>
		<div><a href = "list-todos">Manage</a> your todos</div>
	</div>
<%@ include file="common/footer.jspf" %>