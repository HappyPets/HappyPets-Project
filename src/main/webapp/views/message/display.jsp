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
<!-- START MESSAGE DISPLAY VIEW -->
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
		<div class="row">
			<div class="col-md-3">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/create.do'" value="<spring:message code="message.new" />"/>
			</div>
			<div class="col-md-3">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/create.do'" value="<spring:message code="message.reply" />"/>
			</div>
			<div class="col-md-6 text-right">
				<jstl:if test="${box eq 'inbox' }">
					<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/delete.do?messageId=${msg.id}&box=inbox'" value="<spring:message code="message.delete" />"/>
				</jstl:if>
				<jstl:if test="${box eq 'outbox' }">
					<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/delete.do?messageId=${msg.id}&box=outbox'" value="<spring:message code="message.delete" />"/>
				</jstl:if>
			</div>
		</div>
		<div class="row">
			<!-- START MESSAGES MENU -->
			<div class="col-md-3">
				<br>
				<div class="list-group">
					<a href="message/user/inbox.do" class="list-group-item list-group-item-action"><spring:message code="messages.inbox"/></a>
					<a href="message/user/outbox.do" class="list-group-item list-group-item-action"><spring:message code="messages.outbox"/></a>
				</div>
			</div>
			<!-- END MESSAGES MENU -->
			<!-- START MESSAGE DISPLAY -->
			<div class="col-md-9">
				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="list-group">
							<div class="list-group-item">
								<div class="row">
									<div class="col-md-12">
										<h3><jstl:out value="${msg.subject}" /></h3>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<small><i><jstl:out value="${msg.sendMoment}" /></i></small>
									</div>
								</div><br>
								<div class="row">
									<div class="col-md-6">
										<strong><spring:message code="message.from" />:</strong> <a href="profile/subActor/display.do?subActorId=${msg.sender.id}&box=inbox"> <jstl:out value="${msg.sender.name}" /> <jstl:out value="${msg.sender.surname}" /></a>
									</div>
									<div class="col-md-6">
										<strong><spring:message code="message.to" />:</strong> <a href="profile/subActor/display.do?subActorId=${msg.receiver.id}&box=inbox"> <jstl:out value="${msg.receiver.name}" /> <jstl:out value="${msg.receiver.surname}" /></a>
									</div>
								</div><hr>
								<div class="row">
									<div class="col-md-12">
										<p><jstl:out value="${msg.text}" /></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- start back button -->
				<div class="row">
					<div class="col-md-6">
						<acme:back code="message.back"/>
						<br/><br/>
					</div>
				</div>
				<!-- end back button -->
			</div>
			<!-- END MESSAGE DISPLAY -->
		</div>
	</jstl:if>
</div>