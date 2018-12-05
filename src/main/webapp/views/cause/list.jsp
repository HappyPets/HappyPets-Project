<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- START CAUSE LIST VIEW -->
<div class="container-fluid">
<!-- START ERROR PANEL -->
						
	<jstl:if test="${error eq 'error.list'}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="${error}" /></p>
			</div>
		</div>
	</jstl:if>
<!-- END ERROR PANEL -->

	<security:authorize access="hasRole('ADMINISTRATOR')">
		<div>
			<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/administrator/create.do?causeId=0'" value="<spring:message code="cause.create" />"/>
		</div>
	</security:authorize>
	<br>
<!-- START CAUSE LISTS -->
<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
	<jstl:if test="${causes.size() eq 0}" >
		<div class="row">
			<div class="col-md-12">
				<p><spring:message code="causes.no.results" /></p>
			</div>
		</div>
	</jstl:if>
</security:authorize>
	<jstl:if test="${causes.size() ne 0}">
		<div class="list-group">
			<jstl:forEach var="x" items="${causes}">
				<div class="row list-group-item list-group-item-action">
					<div class="col-md-3">
						<img class="img-responsive img-circle small-img" src="${x.picture}" />
					</div>
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-10">
								<strong><jstl:out value="${x.title}" /></strong>
							</div>
						</div>
						
						<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
						<div class="row">
							<div class="col-md-10">
								<small><jstl:out value="${x.description}" /></small>
							</div>
							<div class="col-md-2">
								<small><jstl:out value="${x.priority}" /></small>
							</div>
						</div><br>
						</security:authorize>
						
						<security:authorize access="hasRole('ADMINISTRATOR')">
						<div class="row">
							<div class="col-md-8">
								<small><jstl:out value="${x.description}" /></small>
							</div>
							<div class="col-md-2">
								<small><jstl:out value="${x.priority}" /></small>
							</div>
							<div class="col-md-2">
								<jstl:if test="${x.isActive eq true}">
									<small><spring:message code="cause.active"/></small>
								</jstl:if>
								<jstl:if test="${x.isActive eq false}">
									<small><spring:message code="cause.deactive"/></small>
								</jstl:if>
							</div>
						</div><br>
						</security:authorize>
					
						<div class="row">
						<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
								<div class="col-md-12">
									<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/subActor/display.do?causeId=${x.id}'" value="<spring:message code="cause.display" />"/>
								</div>
							</security:authorize>
							<security:authorize access="hasRole('ADMINISTRATOR')">
							<div class="col-md-12">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/administrator/display.do?causeId=${x.id}'" value="<spring:message code="cause.display" />"/>
							</div>
						</security:authorize>
						</div>
					</div>
				</div>
			</jstl:forEach>
		</div>
	</jstl:if>
<!-- END CAUSES LISTS -->


<!--START CAUSES DEACTIVATED ADMIN -->
<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:if test="${cancelCauses.size() ne 0}">
		<div class="list-group">
			<jstl:forEach var="x" items="${cancelCauses}">
				<div class="row list-group-item list-group-item-action">
					<div class="col-md-3">
						<img class="img-responsive img-circle small-img" src="${x.picture}" />
					</div>
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-10">
								<strong><jstl:out value="${x.title}" /></strong>
							</div>
						</div>
						<div class="row">
							<div class="col-md-8">
								<small><jstl:out value="${x.description}" /></small>
							</div>
							<div class="col-md-2">
								<small><jstl:out value="${x.priority}" /></small>
							</div>
							<div class="col-md-2">
								<jstl:if test="${x.isActive eq true}">
									<small><spring:message code="cause.active"/></small>
								</jstl:if>
								<jstl:if test="${x.isActive eq false}">
									<small><spring:message code="cause.deactive"/></small>
								</jstl:if>
							</div>
						</div><br>
						
						<div class="row">						
							<div class="col-md-12">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='cause/administrator/display.do?causeId=${x.id}'" value="<spring:message code="cause.display" />"/>
							</div>
						</div>
					</div>
				</div>
				<br>
			</jstl:forEach>
		</div>
	</jstl:if>
</security:authorize>
	
	<!--END CAUSES DEACTIVATED ADMIN -->
		
<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<acme:cancel url="/welcome/index.do" code="cause.back"/>
			<br/><br/>
		</div>
	</div>
<!-- end back button -->
</div>
