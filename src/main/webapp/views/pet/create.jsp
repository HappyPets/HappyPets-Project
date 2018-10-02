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
	<jstl:if test="${create eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="error.create" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<security:authorize access="hasRole('USER')">
			<!-- START PETS CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="petForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						<div class="row">
							<div class="col-md-4">
								<br>
								<div class="row">
									<div class="col-md-12">
										<jstl:if test="${petForm.picture eq null or petForm.picture eq '' }">
											<img class="img-responsive center-block" src="https://pbs.twimg.com/profile_images/584375366720081920/DNU-vZts.png" />
										</jstl:if>
										<jstl:if test="${petForm.picture ne null }">
											<img class="img-responsive center-block" src="${petForm.picture}" />
										</jstl:if>
										<br>
										<acme:textbox code="pet.picture" path="picture"/>
									</div>
								</div>
								<br>
							</div>
							<div class="col-md-8">
								<div class="row">
									<div class="col-md-8">
										<acme:textbox code="pet.name" path="name"/>
									</div>
									<div class="col-md-4">
										<acme:select items="${categories}" itemLabel="name" code="pet.category" path="category"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">
										<acme:textbox code="pet.age" path="age"/>
									</div>
									<div class="col-md-3">
										<br>
										<form:select items="${typeAges}" path="typeAge"/>
									</div> 
								</div>
								<div class="row">
									<div class="col-md-4">
										<acme:textbox code="pet.weight" path="weight"/>
									</div>
									<div class="col-md-4">
										<acme:textbox code="pet.height" path="height"/>
									</div>
									<div class="col-md-2">
										<b><spring:message code="pet.genre"/></b>
										<form:select items="${genres}" path="genre"/>
									</div> 
								</div>
								<div class="row">
									<div class="col-md-12">
										<acme:textbox code="pet.city" path="city"/>
									</div>
									
								</div><br>
								<div class="row">
									<div class="col-md-12">
										<acme:textarea code="pet.description" path="description"/>
									</div>
								</div><br>
								<div class="row">
									<div class="col-md-12">
										<acme:textarea code="pet.healthDescription" path="healthDescription"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<strong><spring:message code="pet.inAdoption" /></strong> <form:checkbox class="checkbox-inline" path="inAdoption"/>
									</div>
								</div><br>
							</div>
						</div><br>
						<acme:submit name="save" code="pet.save"/>
						<acme:cancel url="/pet/user/list.do" code="pet.cancel"/>
						
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END PETS CREATE -->
		</security:authorize>
	</jstl:if>
</div>
