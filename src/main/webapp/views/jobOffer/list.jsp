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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- START JOB OFFERS LIST VIEW -->
<br>
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
	<jstl:if test="${delete eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="jobOffer.ok.delete" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${delete eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="jobOffer.error.delete" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${banned eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="jobOffer.ok.ban" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${banned eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="jobOffer.error.ban" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<br>
		<div class="row">
			<!-- START JOB OFFERS LISTS -->
			<div class="col-md-12">
				<jstl:if test="${own eq true }">
					<div class="row">
						<div class="col-md-12">
							<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='jobOffer/user/create.do?jobOfferId=0'" value="<spring:message code="jobOffer.new" />"/>
						</div>
					</div>
				</jstl:if>
				<jstl:if test="${jobOffers.size() eq 0}" >
					<br>
					<div class="row">
						<div class="col-md-12 text-center">
							<p><spring:message code="jobOffer.no.results" /></p>
						</div>
					</div>
					<br>
				</jstl:if>
				<jstl:if test="${jobOffers.size() ne 0}">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<jstl:forEach var="x" items="${jobOffers}">
								<div class="list-group">
									<div class="row list-group-item">
										<div class="col-md-3">
											<div class="row">
												<div class="col-md-12">
													<img class="img-responsive center-block img-circle" src="${x.pet.picture}" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-center">
													<h5><strong><jstl:out value="${x.pet.name }" /></strong></h5>
												</div>
											</div>
											<jstl:if test="${x.isBanned and (own or admin) }">
												<div class="row">
													<div class="col-md-12 text-center">
														<span class="label label-danger"><spring:message code="jobOffer.banned" /></span>
													</div>
												</div>
											</jstl:if>
										</div>
										<div class="col-md-9">
											<div class="row">
												<div class="col-md-12">
													<h4><jstl:out value="${x.title }" /></h4>
												</div>
											</div>
											<div class="row">
												<div class="col-md-5">
													<small><i><fmt:formatDate value="${x.startDate }" pattern="yyyy-MM-dd" /> - <fmt:formatDate value="${x.endDate }" pattern="yyyy-MM-dd" /></i></small>
												</div>
												<div class="col-md-4">
													<small><jstl:out value="${x.city }" /></small>
												</div>
												<div class="col-md-3">
													<strong><jstl:out value="${x.salary }" /> &euro;</strong>
												</div>
											</div><br>
											<div class="row">
												<div class="col-md-12">
													<jstl:out value="${x.description }" />
												</div>
											</div><br>
											<div class="row">
												<div class="col-md-6">
													<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='jobOffer/user/display.do?jobOfferId=${x.id}'" value="<spring:message code="jobOffer.display" />"/>
												</div>
												<jstl:if test="${own }">
													<div class="col-md-6">
														<jstl:if test="${x.inscriptions.size() ne 0 and x.isClosed ne true}">
															<input class="btn btn-sg btn-danger" type="button" onclick="var ask = window.confirm('<spring:message code='jobOffer.delete.confirm'/>');if (ask) { window.location.href = 'jobOffer/user/delete.do?jobOfferId=${x.id}';}" value="<spring:message code="jobOffer.delete" />"/>
														</jstl:if>
														<jstl:if test="${x.inscriptions.size() eq 0 }">
															<input class="btn btn-sg btn-danger" type="button" onclick="window.location.href='jobOffer/user/delete.do?jobOfferId=${x.id}'" value="<spring:message code="jobOffer.delete" />"/>
														</jstl:if>
													</div>
												</jstl:if>
												<jstl:if test="${admin and x.isBanned ne true}">
													<input class="btn btn-sg btn-danger" type="button" onclick="var ask = window.confirm('<spring:message code='jobOffer.ban.confirm'/>');if (ask) { window.location.href = 'jobOffer/administrator/ban.do?jobOfferId=${x.id}';}" value="<spring:message code="jobOffer.ban" />"/>
												</jstl:if>
											</div><br>
										</div>
									</div>
								</div>
							</jstl:forEach>
						</div>
					</div>
				</jstl:if>
				<!-- start back button -->
				<div class="row">
					<div class="col-md-6">
					<acme:cancel url="/welcome/index.do" code="jobOffer.back"/>
						<br/><br/>
					</div>
				</div>
				<!-- end back button -->
			</div>
			<!-- END JOB OFFERS LISTS -->
		</div>
	</jstl:if>
</div>
