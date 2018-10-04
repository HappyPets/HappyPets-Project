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

<!-- START ADOPTION REQUESTS LIST VIEW -->
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
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<br>
		<div class="row">
			<!-- START ADOPTION REQUESTS LISTS -->
			<div class="col-md-12">
				<jstl:if test="${adoptionRequests.size() eq 0}" >
					<br><br>
					<div class="row">
						<div class="col-md-12 text-center">
							<p><spring:message code="adoptionRequest.no.results" /></p>
						</div>
					</div>
				</jstl:if>
				<jstl:if test="${adoptionRequests.size() ne 0}">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<jstl:forEach var="x" items="${adoptionRequests}">
								<div class="list-group">
									<div class="row list-group-item">
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-3">
													<div class="row">
														<div class="col-md-12">
															<img class="img-responsive center-block img-circle" src="${x.pet.picture}" />
														</div>
													</div>
												</div>
												<div class="col-md-9">
													<div class="row">
														<div class="col-md-8">
															<div class="row">
																<div class="col-md-6">
																	<h4><jstl:out value="${x.pet.name }" /></h4>
																</div>
																<div class="col-md-6">
																	<h4>(<a href="profile/subActor/display.do?subActorId=${x.pet.owner.id }"><jstl:out value="${x.pet.owner.name }" /></a>)</h4>
																</div>
															</div>
															<div class="row">
																<div class="col-md-6">
																	<small><strong><spring:message code="pet.age" />:</strong> <jstl:out value="${x.pet.age}" /></small>
																</div>
															</div>
															<div class="row">
																<div class="col-md-6">
																	<small><strong><spring:message code="pet.genre" />:</strong> <jstl:out value="${x.pet.genre}" /></small>
																</div>
															</div><br>
															<div class="row">
																<div class="col-md-12">
																	<p><jstl:out value="${x.pet.description }" /></p>
																</div>
															</div>
															<jstl:if test="${x.status eq 'PENDING' }">
																<div class="row">
																	<div class="col-md-12">
																		<div class="row">
																			<div class="col-md-6">
																				<input class="btn btn-sg btn-secondary" type="button" onclick="window.location.href='adoptionRequest/user/cancel.do?petId=${x.pet.id}'" value="<spring:message code="pet.cancel.request" />"/>	
																			</div>
																		</div>
																	</div>
																</div>
															</jstl:if>
														</div>
														<div class="col-md-4 text-right">
															<br>
															<jstl:if test="${x.status eq 'CANCELLED' }">
																<div class="row">
																	<div class="col-md-12">
																		<span class="label label-default"><jstl:out value="${x.status }" /></span>
																	</div>
																</div>
															</jstl:if>
															<jstl:if test="${x.status eq 'PENDING' }">
																<div class="row">
																	<div class="col-md-12">
																		<span class="label label-warning"><jstl:out value="${x.status }" /></span>
																	</div>
																</div>
															</jstl:if>
															<jstl:if test="${x.status eq 'ACCEPTED' }">
																<div class="row">
																	<div class="col-md-12">
																		<span class="label label-success"><jstl:out value="${x.status }" /></span>
																	</div>
																</div>
															</jstl:if>
															<jstl:if test="${x.status eq 'DENIED' }">
																<div class="row">
																	<div class="col-md-12">
																		<span class="label label-danger"><jstl:out value="${x.status }" /></span>
																	</div>
																</div>
															</jstl:if><br>
															<div class="row">
																<div class="col-md-12">
																	<p><fmt:formatDate value="${x.requestMoment}" pattern="yyyy-MM-dd" /></p>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</jstl:forEach>
						</div>
					</div>
				</jstl:if>
				<!-- start back button -->
				<div class="row back-button">
					<div class="col-md-6">
						<acme:cancel url="/welcome/index.do" code="adoptionRequest.back"/>
						<br/><br/>
					</div>
				</div>
				
				
				<!-- end back button -->
			</div>
			<!-- END ADOPTION REQUESTS LISTS -->
		</div>
	</jstl:if>
</div>
