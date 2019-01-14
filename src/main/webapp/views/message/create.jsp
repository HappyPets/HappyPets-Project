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
<!-- START MESSAGE CREATE VIEW -->
<div class="container-fluid">
	<!-- START ERROR PANEL -->
	<jstl:if test="${error ne ''}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="${error}" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${create eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="message.error.create" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<security:authorize access="hasRole('USER')">
			<!-- START MESSAGE CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="messageForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-8">
										<acme:textbox code="message.subject" path="subject"/>
									</div>
									<div class="col-md-4">
										<acme:select items="${users }" itemLabel="userAccount.username" code="message.receiver" path="receiver"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<acme:textarea code="message.text" path="text"/>
									</div>
								</div><br>
							</div>
						</div><br>
						<acme:submit name="save" code="message.save"/>
						<acme:cancel url="/message/user/inbox.do" code="message.cancel"/>
					
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END MESSAGE CREATE -->
		</security:authorize>
	</jstl:if>
</div>
