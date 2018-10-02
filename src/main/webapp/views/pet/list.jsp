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

<!-- START PET LIST VIEW -->
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
		<jstl:if test="${delete eq true }">
			<div class="row">
				<div class="col-md-12">
					<br />
					<p class="bg-success text-center"><spring:message code="ok.delete" /></p>
				</div>
			</div>
		</jstl:if>
		<!-- START PETS LISTS -->
		<jstl:if test="${owns ne true }">
			<div class="row">
				<div class="col-md-12">
					<h2><spring:message code="${category}" /></h2><hr>
				</div>
			</div>
		</jstl:if>
		<jstl:if test="${owns eq true }">
			<div class="row">
				<div class="col-md-12">
					<h2><spring:message code="pet.owns" /></h2><hr>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='pet/user/create.do?petId=0'" value="<spring:message code="pet.new" />"/>
				</div>
			</div><br>
		</jstl:if>
		<jstl:if test="${pets.size() eq 0}" >
			<div class="row">
				<div class="col-md-12 text-center">
					<p><spring:message code="pets.no.results" /></p>
				</div>
			</div>
		</jstl:if>
		<jstl:if test="${pets.size() ne 0}">
			<div class="list-group">
				<jstl:forEach var="x" items="${pets}">
					<div class="row list-group-item list-group-item-action">
						<div class="col-md-3">
							<img class="img-responsive img-circle small-img" src="${x.picture}" />
						</div>
						<div class="col-md-9">
							<div class="row">
								<div class="col-md-6">
									<strong><jstl:out value="${x.name}" /></strong>
								</div>
								<div class="col-md-6">
									<strong>(<a href="profile/subActor/display.do?subActorId=${x.owner.id }"><jstl:out value="${x.owner.name }" /></a>)</strong>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<small><jstl:out value="${x.age}" /> <jstl:out value="${x.typeAge}" /></small>
								</div>
								
								<div class="col-md-2">
									<small><jstl:out value="${x.city}" /></small>
								</div>
							</div><br>
							<div class="row">
								<div class="col-md-12">
									<jstl:out value="${x.description}" />
								</div>
							</div><br>
							<div class="row">
								<div class="col-md-12">
									<security:authorize access="hasRole('USER')">
										<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='pet/user/display.do?petId=${x.id}'" value="<spring:message code="pet.display" />"/>
									</security:authorize>
									<security:authorize access="isAnonymous() or hasRole('ADMINISTRATOR') or hasRole('VET') or hasRole('COMPANY')">
										<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='pet/display.do?petId=${x.id}'" value="<spring:message code="pet.display" />"/>
									</security:authorize>
								</div>
							</div>
						</div>
					</div>
				</jstl:forEach>
			</div>
		</jstl:if>
		<!-- END PETS LISTS -->
	</jstl:if>
	<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<acme:cancel url="/welcome/index.do" code="pet.back"/>
			<br/><br/>
		</div>
	</div>
	<!-- end back button -->
</div>
