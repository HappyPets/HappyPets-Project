<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<br>
<!-- START COMMENT CREATE VIEW -->
<div class="container-fluid">
	<!-- START ERROR PANEL -->
	<jstl:if test="${error ne null}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="${error}" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	
	<jstl:if test="${error eq null}">
		<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
			<!-- START COMMENT CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="commentForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						
						<div class="row">
							<div class="row">
								<div class="col-md-6">
									<acme:textbox code="comment.title" path="title"/>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<acme:textbox code="comment.text" path="text"/>
								</div>
							</div>
						</div><br>
						<acme:submit name="save" code="comment.save"/>
						<acme:cancel url="/comment/subActor/list.do?category=${commentForm.category}" code="comment.cancel"/>
					
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END COMMENT CREATE -->
		</security:authorize>
	</jstl:if>
</div>