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
	
	<!-- END ERROR PANEL -->
		
		<!-- START SUBACTORS LISTS -->
	
			<div class="list-group">
				<jstl:forEach var="x" items="${subActors}">
					<div class="row list-group-item list-group-item-action">
						<div class="col-md-9">
							<div class="row">
								<div class="col-md-3">
									<strong><jstl:out value="${x.name}" /></strong>
								</div>
								<div class="col-md-3">
									<strong><jstl:out value="${x.surname}" /></strong>
								</div>
								<div class="col-md-6">
									<strong>(<a href="profile/subActor/display.do?subActorId=${x.id }"><spring:message code="subActor.display" /></a>)</strong>
								</div>
							</div>
						</div>
					</div>
				</jstl:forEach>
			</div>
		<!-- END PETS LISTS -->
	<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<acme:cancel url="/welcome/index.do" code="pet.back"/>
			<br/><br/>
		</div>
	</div>
	<!-- end back button -->
</div>
