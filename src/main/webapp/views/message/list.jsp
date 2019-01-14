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

<!-- START MESSAGES LIST VIEW -->
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
			<div class="col-md-9">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/create.do'" value="<spring:message code="message.new" />"/>
			</div>
		</div>
		<jstl:if test="${delete eq true }">
			<div class="row">
				<div class="col-md-12">
					<br />
					<p class="bg-success text-center"><spring:message code="message.ok.delete" /></p>
				</div>
			</div>
		</jstl:if>
		<jstl:if test="${create eq true }">
			<div class="row">
				<div class="col-md-12">
					<br />
					<p class="bg-success text-center"><spring:message code="message.ok.create" /></p>
				</div>
			</div>
		</jstl:if>
		<div class="row">
			<!-- START MESSAGES MENU -->
			<div class="col-md-3">
				<br>
				<div class="list-group">
					<jstl:if test="${inbox }">
						<a href="message/user/inbox.do" class="list-group-item list-group-item-action active"><spring:message code="messages.inbox"/></a>
						<a href="message/user/outbox.do" class="list-group-item list-group-item-action"><spring:message code="messages.outbox"/></a>
					</jstl:if>
					<jstl:if test="${outbox }">
						<a href="message/user/inbox.do" class="list-group-item list-group-item-action"><spring:message code="messages.inbox"/></a>
						<a href="message/user/outbox.do" class="list-group-item list-group-item-action active"><spring:message code="messages.outbox"/></a>
					</jstl:if>
				</div>
			</div>
			<!-- END MESSAGES MENU -->
			<!-- START MESSAGES LISTS -->
			<div class="col-md-9">
				<jstl:if test="${messages.size() eq 0}" >
					<br><br>
					<div class="row">
						<div class="col-md-12 text-center">
							<p><spring:message code="messages.no.results" /></p>
						</div>
					</div>
				</jstl:if>
				<jstl:if test="${messages.size() ne 0}">
					<br>
					<div class="row">
						<div class="col-md-12">
							<display:table name="${messages}" pagesize="5" class="displaytag table table-hover" requestURI="${requestURI}" id="row">
								<spring:message code="message.subject" var="headerTag" />
								<display:column title="${headerTag}">
									<jstl:if test="${inbox }">
										<a href="message/user/display.do?messageId=${row.id}&box=inbox">
											<jstl:out value="${row.subject}" />
										</a>
									</jstl:if>
									<jstl:if test="${outbox }">
										<a href="message/user/display.do?messageId=${row.id}&box=outbox">
											<jstl:out value="${row.subject}" />
										</a>
									</jstl:if>
								</display:column>
								<jstl:if test="${inbox }">
									<spring:message code="message.from" var="headerTag" />
									<display:column title="${headerTag}">
										<a href="profile/subActor/display.do?subActorId=${row.sender.id}&box=inbox">
											<jstl:out value="${row.sender.name}" /> <jstl:out value="${row.sender.surname}" />
										</a>
									</display:column>
								</jstl:if>
								<jstl:if test="${outbox }">
									<spring:message code="message.from" var="headerTag" />
									<display:column title="${headerTag}">
										<a href="profile/subActor/display.do?subActorId=${row.receiver.id}&box=inbox">
											<jstl:out value="${row.receiver.name}" /> <jstl:out value="${row.receiver.surname}" />
										</a>
									</display:column>
								</jstl:if>
								<spring:message code="message.sendMoment" var="headerTag" />
								<display:column property="sendMoment" title="${headerTag}"/>
								<display:column title="">
									<jstl:if test="${inbox }">
										<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/delete.do?messageId=${row.id}&box=inbox'" value="<spring:message code="message.delete" />"/>
									</jstl:if>
									<jstl:if test="${outbox }">
										<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='message/user/delete.do?messageId=${row.id}&box=outbox'" value="<spring:message code="message.delete" />"/>
									</jstl:if>
								</display:column>
							</display:table>
						</div>
					</div>
				</jstl:if>
				<!-- start back button -->
			<div class="row back-button">
			<div class="col-md-6">
				<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='welcome/index.do'" value="<spring:message code="comment.back" />"/>
				<br/><br/>
			</div>
		</div>
				<!-- end back button -->
			</div>
			<!-- END MESSAGES LISTS -->
		</div>
	</jstl:if>
</div>
