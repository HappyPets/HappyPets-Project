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
<!-- START JOB OFFER CREATE VIEW -->
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
	
	<%-- <jstl:if test="${error eq null}"> --%>
		<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
			<!-- START DONATION CREATE -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="donationForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>	
						
						<div class="row">
								<div class="row">
									<div class="col-md-3">
										<acme:textbox code="donation.quantity" path="quantity"/>
									</div>
								</div>
							<!-- CREDIT CARD -->
							<div class="row">
								<div class="col-md-12">
									<h3><spring:message code="creditCard.info" /></h3>
								</div>
							</div><hr>
							<div class="row">
								<div class="col-md-4">
									<acme:textbox code="creditCard.holderName" path="holderName"/>
								</div>
								<div class="col-md-4">
									<acme:textbox code="creditCard.brandName" path="brandName"/>
								</div>
								<div class="col-md-4">
									<acme:textbox code="creditCard.number" path="number"/>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<acme:textbox code="creditCard.expirationMonth" path="expirationMonth"/>
								</div>
								<div class="col-md-4">
									<acme:textbox code="creditCard.expirationYear" path="expirationYear"/>
								</div>
								<div class="col-md-4">
									<acme:textbox code="creditCard.cvv" path="cvv"/>
								</div>
							</div>
							<!-- END CREDIT CARD -->
						</div><br>
						<acme:submit name="save" code="donation.save"/>
						<acme:cancel url="/cause/subActor/display.do?causeId=${donationForm.causeId}" code="donation.cancel"/>
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END DONATION CREATE -->
		</security:authorize>
	<%-- </jstl:if> --%>
</div>