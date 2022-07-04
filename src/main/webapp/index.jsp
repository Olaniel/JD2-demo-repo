<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
	<h2>Hello World!</h2>
	
	<h3>Cookie list:</h3>
	<c:forEach var="cookie1" items="${cookies}" >
		<p>
			Cookie name -
			<c:out value="${cookie1.key }" /> <br>
			Cookie value -
			<c:out value="${cookie1.value }" />
		</p>
	</c:forEach>

	<h3>SessionId -</h3>
	<c:out value="${sessionId}"></c:out>
	
	<h3>Session attributes</h3>
	<c:forEach var="attribute" items="${session_attributes}">
		<p>
			Name:
			<c:out value="${attribute.key}" />
			Value:
			<c:out value="${attribute.value}" />
		</p>
	</c:forEach>
	
	<h3>Input variable to save it as attribute in session</h3>
	
	<p>
		requestScope.encodeURL before comparison with null - ${requestScope.encodeURL}
	</p>
	
	<c:if test="${requestScope.encodeURL == null}">
		<c:set var="encodeURL" value="controller" scope="request"></c:set>
	</c:if>
	
	<form action="${requestScope.encodeURL}" method="post">
		<p>
			<label> Variable</label> <input type="text" name="var_name">
		</p>
		<p>
			<label> Value</label> <input type="text" name="var_value">
		</p>

		<p>
			<input type="submit" value="Send">
		</p>

	</form>
	
	<p>
		requestScope.encodeURL - ${requestScope.encodeURL}
	</p>
</body>
</html>
