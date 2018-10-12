<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- START ADVERTISEMENT LIST VIEW -->
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
		
<!-- START ADVERTISEMENTS LISTS -->
	<jstl:if test="${advertisements.size() eq 0}" >
		<div class="row">
			<div class="col-md-12">
				<p><spring:message code="advertisement.no.results" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${advertisements.size() ne 0}">
		<div class="list-group">
			<jstl:forEach var="x" items="${advertisements}">
				<div class="row">
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-10">
								<strong><spring:message code="advertisement.ticker" />: <jstl:out value="${x.ticker}" /></strong>
							</div>
						</div>
					</div>
				</div>
				<div class="row list-group-item list-group-item-action">
					<div class="row">
						<div class="col-md-4">
							<a href="${x.targetPage }">
								<img class="img-responsive" src="${x.banner}" />
							</a>
						</div>
					</div>
				</div>
				<jstl:if test="${x.isBanned eq true }">
					<div class="error">
						<div class="row">
							<br/>
							<div class="col-md-6">
								<spring:message code="advertisement.isBanned" />
							</div>
						</div>
					</div>
				</jstl:if>
				<div class="row back-button">
						<div class="col-md-6">
							<jstl:if test="${admin eq true and x.isBanned eq false}">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='advertisement/administrator/ban.do?advertisementId=${x.id}'" value="<spring:message code="advertisement.ban" />"/>
								<br/><br/>
							</jstl:if>
						</div>
				</div>
				<div class="row back-button">
					<jstl:if test="${admin ne true}">
						<jstl:if test="${x.isBanned eq false }">
							<div class="col-md-2">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='advertisement/subActor/create.do?id=${x.id }'" value="<spring:message code="advertisement.edit" />"/>
								<br/>
							</div>
						</jstl:if>
						<div class="col-md-2">
							<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='advertisement/subActor/delete.do?id=${x.id }'" value="<spring:message code="advertisement.delete" />"/>
							<br/><br/>
						</div>
					</jstl:if>
				</div><br/>
			</jstl:forEach>
		</div>
	</jstl:if>
<!-- END ADVERTISEMENTS LISTS -->
		
<!-- start back and new button -->
		<div class="row back-button">
			<div class="col-md-6">
				<jstl:if test="${admin ne true }">
					<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='advertisement/subActor/create.do?id=0'" value="<spring:message code="advertisement.create" />"/>
					<br/><br/>
				</jstl:if>
			</div>
		</div>
		<div class="row back-button">
			<div class="col-md-6">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='welcome/index.do'" value="<spring:message code="advertisement.back" />"/>
				<br/><br/>
			</div>
		</div>
<!-- end back button -->
</div>
