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
<!-- START PRICE ADVERTISEMENT EDIT VIEW -->
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
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<!-- START PRICE ADVERTISEMENT EDIT -->
			<div class="row">
				<div class="col-md-12">
					<!-- start form -->
					<form:form action="${actionForm}" modelAttribute="priceAdvertisementForm">
						<form:hidden path="id"/>
						<form:hidden path="version"/>
						
						<div class="row">
							<div class="col-md-2">
								<acme:textbox code="priceAdvertisement.value" path="value"/>
							</div>
						</div><br>
						<acme:submit name="save" code="priceAdvertisement.save"/>
						<acme:cancel url="/welcome/index.do" code="priceAdvertisement.cancel"/>
				
					</form:form>
					<!-- end form -->
				</div>
			</div>
			<!-- END PRICE ADVERTISEMENT -->
		</security:authorize>
	</jstl:if>
</div>