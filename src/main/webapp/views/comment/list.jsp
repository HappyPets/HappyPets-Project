<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- START COMMENT LIST VIEW -->
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
		
<!-- START COMMENT LISTS -->
	<jstl:if test="${comments.size() eq 0}" >
		<div class="row">
			<div class="col-md-12">
				<p><spring:message code="comments.no.results" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${comments.size() ne 0}">
		<div class="list-group">
			<div class="row">
				<div class="col-md-12">
					<h3><jstl:out value="${category}"></jstl:out> </h3>
				</div>
			</div><hr>
			<jstl:forEach var="x" items="${comments}">
				<div class="row list-group-item list-group-item-action">
					<jstl:if test="${x.isBanned eq false }">
						<jstl:if test="${commentsVet.contains(x)}">
							<div class="row">
								<div class="col-md-6">
									<strong><spring:message code="comment.publicationMoment" />: <jstl:out value="${x.publicationMoment}" /></strong>
									
																
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<strong><spring:message code="comment.title" />: <jstl:out value="${x.title}" /></strong>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<strong><spring:message code="comment.text" />: <jstl:out value="${x.text}" /></strong>
								</div>
							</div>
						</jstl:if>
					
						<jstl:if test="${!commentsVet.contains(x)}">
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.publicationMoment" />: <jstl:out value="${x.publicationMoment}" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.title" />: <jstl:out value="${x.title}" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.text" />: <jstl:out value="${x.text}" />
								</div>
							</div>
						</jstl:if>
					</jstl:if>
					<jstl:if test="${(x.isBanned eq true) and (subActorLogueadoId eq x.subActor.id or admin eq true) }">
						<div class="error">
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.publicationMoment" />: <jstl:out value="${x.publicationMoment}" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.title" />: <jstl:out value="${x.title}" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<spring:message code="comment.text" />: <jstl:out value="${x.text}" />
								</div>
							</div>
							<div class="row">
								<br/>
								<div class="col-md-6">
									<spring:message code="comment.isBanned" />
								</div>
							</div>
						</div>
					</jstl:if>
					<div class="row back-button">
						<div class="col-md-6">
							<jstl:if test="${admin eq true and x.isBanned eq false}">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='comment/administrator/ban.do?commentId=${x.id}'" value="<spring:message code="comment.ban" />"/>
								<br/><br/>
							</jstl:if>
						</div>
					</div>
				</div>
			</jstl:forEach>
		</div>
	</jstl:if>
<!-- END COMMENT LISTS -->
		
<!-- start back and new button -->
		<div class="row back-button">
			<div class="col-md-6">
				<jstl:if test="${admin ne true }">
					<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='comment/subActor/create.do?category=${category}'" value="<spring:message code="comment.create" />"/>
					<br/><br/>
				</jstl:if>
			</div>
		</div>
		<div class="row back-button">
			<div class="col-md-6">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='welcome/index.do'" value="<spring:message code="comment.back" />"/>
				<br/><br/>
			</div>
		</div>
<!-- end back button -->
</div>
