<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- New Taboo Word View -->
<div class="container-fluid">
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="tabooWordsForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						<form:hidden path="words"/>
						<div class="row">
							<div class="col-md-12">
								<acme:textbox code="tabooWords.newWord" path="newWord"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<acme:submit name="save" code="tabooWords.save"/>
								<acme:cancel url="/welcome/index.do" code="tabooWords.cancel"/>
						
							</div>
						</div>
					</form:form>
			</div>
		</div><br>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6 text-center">
				<h2><spring:message code="tabooWords.list" /></h2><br>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<table class="table table-hover">
					<jstl:forEach var="x" items="${tabooWordsForm.words }">
						<tr>
							<td class="text-center"><jstl:out value="${x}" /></td>
							<td class="text-center"><a href="tabooWords/administrator/delete.do?word=${x}"><spring:message code="tabooWords.delete"/> </a></td>
						</tr>
					</jstl:forEach>
				</table>
			</div>
		</div>
	</security:authorize>
	<security:authorize access="isAnonymous() or hasAnyRole('USER') or hasRole('AGENT')">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
					<div class="row">
						<div class="col-md-12">
							<spring:message code="error.notAuthorize" /><br>
						</div>
					</div>
			</div>
		</div>
	</security:authorize>
</div>
