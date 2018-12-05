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



<!-- START PET CREATE VIEW -->
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
	
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<!-- START CAUSE CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="causeForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						<div class="row">
							<div class="col-md-4">
								<br>
								<div class="row">
									<div class="col-md-12">
										<jstl:if test="${causeForm.picture eq null or causeForm.picture eq '' }">
											<img class="img-responsive center-block" src="https://pbs.twimg.com/profile_images/584375366720081920/DNU-vZts.png" />
										</jstl:if>
										<jstl:if test="${causeForm.picture ne null }">
											<img class="img-responsive center-block" src="${causeForm.picture}" />
										</jstl:if>
										<br>
										
									</div>
								</div>
								<br>
							</div>
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-8">
										<acme:textbox code="cause.title" path="title"/>
									</div>
									
								</div>
								<div class="row">
									<div class="col-md-8">
										<acme:textarea code="cause.description" path="description"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8">
										<acme:textbox code="cause.picture" path="picture"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<b><spring:message code="cause.priority"/></b>
									</div>
									<div class="col-md-4">
										<form:select items="${priorities}" path="priority"/>
									</div>
								</div>
								
							</div>
						</div><br>
						<acme:submit name="save" code="cause.save"/>
						<acme:cancel url="/cause/subActor/list.do" code="cause.cancel"/>
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END PETS CREATE -->
		</security:authorize>
	</jstl:if>
</div>
