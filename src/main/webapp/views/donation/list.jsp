<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- START DONATION LIST VIEW -->
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
		
<!-- START DONATIONS LISTS -->
	<jstl:if test="${donations.size() eq 0}" >
		<div class="row">
			<div class="col-md-12">
				<p><spring:message code="donations.no.results" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${donations.size() ne 0}">
		<div class="list-group">
			<jstl:forEach var="x" items="${donations}">
				<div class="row list-group-item list-group-item-action">
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-10">
								<strong><jstl:out value="${x.causa.title}" /></strong>
							</div>
						</div><br>
						<div class="row">
							<div class="col-md-10">
								<strong><jstl:out value="${x.donationMoment}" /></strong>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<strong><jstl:out value="${x.quantity}" /> Euros</strong>
							</div>
						</div><br>
					</div>
				</div>
			</jstl:forEach>
		</div>
	</jstl:if>
<!-- END CAUSES LISTS -->
		
<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<acme:cancel url="/welcome/index.do" code="donation.back"/>
			<br/><br/>
		</div>
	</div>
<!-- end back button -->
</div>
