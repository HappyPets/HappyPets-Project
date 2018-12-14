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

<!-- -----------DATAPICKER------------- -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepickerStart" ).datepicker({ dateFormat: 'dd-mm-yy', minDate: "-0D",});
    $( "#datepickerEnd" ).datepicker({ dateFormat: 'dd-mm-yy', minDate: "-0D",});
  } );
  </script>
  
<!-- ---------FIN DATAPICKER-------------- -->


<!-- START JOB OFFER CREATE VIEW -->
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
	
	<jstl:if test="${create eq true}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="jobOffer.ok.create" /></p>
			</div>
			
		</div>
	</jstl:if>
	<jstl:if test="${create eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="jobOffer.error.create" /></p>
			</div>
			<div class="row">
			
		</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<security:authorize access="hasRole('USER')">
			<!-- START JOB OFFER CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="jobOfferForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12">
										<acme:textbox code="jobOffer.title" path="title"/>
									</div>
									<div class="col-md-4">
										<acme:select items="${pets }" itemLabel="name" code="jobOffer.pet" path="pet"/>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<acme:textbox code="jobOffer.salary" path="salary"/>
									</div>
								</div>
								<div class="row">
								
									<div class="col-md-6">
										<b><spring:message code="jobOffer.startDate"/></b><input type="text" id="datepickerStart" name="startDate" form="jobOfferForm">
									</div>
									<div class="col-md-6">
										<b><spring:message code="jobOffer.endDate"/></b><input type="text" id="datepickerEnd" name="endDate" form="jobOfferForm">
									</div>
									
								</div>
								<div class="row">
									<div class="col-md-12">
										<acme:textbox code="jobOffer.city" path="city"/>
									</div>
								</div><br>
								<div class="row">
									<div class="col-md-12">
										<acme:textarea code="jobOffer.description" path="description"/>
									</div>
								</div><br>
							</div>
						</div><br>
						<acme:submit name="save" code="jobOffer.save"/>
						<acme:cancel url="/jobOffer/user/own.do" code="jobOffer.cancel"/>
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END JOB OFFER CREATE -->
		</security:authorize>
	</jstl:if>
</div>