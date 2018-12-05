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

<!-- START CAUSE DISPLAY VIEW -->
<div class="container-fluid">
	<!-- START ERROR PANEL -->
	<jstl:if test="${error eq 'Error'}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="${error}" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	
	<!-- START CAUSE DISPLAY -->
	<div class="list-group">
		<div class="list-group-item">
			<div class="row">
				<div class="col-md-3">
					<br>
					<div class="row">
						<div class="col-md-12">
							<img class="img-responsive center-block img-circle" src="${cause.picture}" />
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="row">
						<div class="col-md-12">
							<h2><jstl:out value="${cause.title}" /></h2>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<small><strong><spring:message code="cause.description" />:</strong> <jstl:out value="${cause.description}" /></small>
						</div>
						<div class="col-md-3">
							<small><strong><spring:message code="cause.priority" />:</strong> <jstl:out value="${cause.priority}" /></small>
						</div>
						
					</div>
					<div class="row">
						<div class="col-md-6">
							<small><strong><spring:message code="cause.donations" />:</strong> <jstl:out value="${donations}"/> eur.</small>
						</div>
							
						<div class="col-md-3">
						<small><strong><spring:message code="cause.status"/>:</strong></small>
							<jstl:if test="${cause.isActive eq true}">
								<small><spring:message code="cause.active"/></small>
							</jstl:if>
							<jstl:if test="${cause.isActive eq false}">
								<small><spring:message code="cause.deactivated"/></small>
							</jstl:if>
						</div>
					</div>
					<br><br>
						<div class="row">
						<div class="col-md-2">
							<jstl:if test="${cause.donations.size() eq 0 }">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/administrator/create.do?causeId=${cause.id}'" value="<spring:message code="cause.edit" />"/>
							</jstl:if>
						</div>
						<jstl:if test="${canCancel eq true }">
							<div class="col-md-2">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/administrator/cancel.do?causeId=${cause.id}'" value="<spring:message code="cause.deactive" />"/>	
							</div>
						</jstl:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CAUSE DISPLAY -->
	
	<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<acme:back code="cause.back"/>
			<br/><br/>
		</div>
	</div>
	<!-- end back button -->
</div>
