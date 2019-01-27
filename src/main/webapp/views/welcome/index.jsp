<%--
 * index.jsp
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


<div class="container-fluid">

	<div class="row">	
		<div class="col-md-7">
		</div>
		<div class="col-md-5">
		<jstl:if test="${unread != 0}">
				<input class="btn btn-sg btn-success" type="button" onclick="window.location.href='message/user/inbox.do'" value="<spring:message code="message.unread"/> <jstl:out value="${unread}"/>"/>
		</jstl:if>	
		</div>
	</div>
	<div class="row">	
		<div class="col-md-12">
			<jstl:if test="${errorMessage != null}">
				<span class="message" style="color:red"><spring:message code="${errorMessage}" /></span>
			</jstl:if>	
			<jstl:if test="${errorMessage == null}">
				<jstl:if test="${name eq null }">
					<p><spring:message code="welcome.greeting.prefix" /> <spring:message code="welcome.unknown.user" /><spring:message code="welcome.greeting.suffix" /></p>
				</jstl:if>
				<jstl:if test="${name ne null }">
					<p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>
				</jstl:if>
				
				
				
				<!-- Anuncio aleatorio -->
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<a href="${advertisement.targetPage}">
							<img class="img-responsive center-block very-small-img" src="${advertisement.banner}" />
						</a>
					</div>
				</div>
			</jstl:if>
		</div>
	</div>
</div> 
<br><br>