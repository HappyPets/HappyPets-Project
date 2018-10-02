<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<br>
<!-- START PET DISPLAY VIEW -->
<div class="container-fluid">
	<!-- START ERROR PANEL -->
	<jstl:if test="${error ne ''}">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="${error}" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${delete eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="error.delete" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${create eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.create" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${edit eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.edit" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${adopt eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.adopt" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${adopt eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="error.adopt" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${cancel eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.cancel.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${cancel eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="error.cancel.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${accept eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.accept.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${accept eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="error.accept.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${deny eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center">
					<spring:message code="ok.deny.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${deny eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center">
					<spring:message code="error.deny.request" />
				</p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<!-- START PETS DISPLAY -->
		<div class="list-group">
			<div class="list-group-item">
				<div class="row">
					<div class="col-md-3">
						<br>
						<div class="row">
							<div class="col-md-12">
								<img class="img-responsive center-block img-circle"
									src="${pet.picture}" />
							</div>
						</div>
						<jstl:if test="${pet.inAdoption }">
							<br>
							<div class="row text-center">
								<div class="col-md-12">
									<jstl:if test="${own ne true}">
										<security:authorize
											access="isAnonymous() or hasRole('ADMINISTRATOR') or hasRole('VET') or hasRole('COMPANY')">
											<div class="acme-adoption">
												<spring:message code="pet.inAdoption" />
											</div>
										</security:authorize>
										<security:authorize access="hasRole('USER')">
											<jstl:if test="${adopted }">
												<input class="btn btn-sg btn-primary" type="button"
													onclick="window.location.href='adoptionRequest/user/cancel.do?petId=${pet.id}'"
													value="<spring:message code="pet.cancel.request" />" />
											</jstl:if>
											<jstl:if test="${adopted ne true }">
												<input class="btn btn-sg btn-primary" type="button"
													onclick="window.location.href='adoptionRequest/user/adopt.do?petId=${pet.id}'"
													value="<spring:message code="pet.adopt" />" />
											</jstl:if>
										</security:authorize>
									</jstl:if>
									<jstl:if test="${own}">
										<div class="acme-adoption">
											<spring:message code="pet.inAdoption" />
										</div>
									</jstl:if>
								</div>
							</div>
						</jstl:if>
						<jstl:if test="${pet.inAdoption eq false }">
							<br>
							<div class="row text-center">
								<div class="col-md-12">
									<div class="acme-no-adoption">
										<spring:message code="pet.no.inAdoption" />
									</div>
								</div>
							</div>
						</jstl:if>
					</div>
					<div class="col-md-9">
						<div class="row">
							<div class="col-md-6">
								<h2>
									<jstl:out value="${pet.name}" /> (<a href="profile/subActor/display.do?subActorId=${pet.owner.id }"><jstl:out value="${pet.owner.name }" /></a>)
								</h2>
							</div>
							<jstl:if test="${own}">
								<div class="col-md-6"></div>
								<div class="col-md-3 text-right">
									<jstl:if test="${pet.adoptionRequests.size() > 0 }">
										<input class="btn btn-sg btn-primary" type="button" onclick="var ask = window.confirm('<spring:message code='pet.edit.confirm'/>');if (ask) { window.location.href = 'pet/user/create.do?petId=${pet.id}';}" value="<spring:message code="pet.edit" />"/>
									</jstl:if>
									<jstl:if test="${pet.adoptionRequests.size() eq 0 }">
										<input class="btn btn-sg btn-primary" type="button"
											onclick="window.location.href='pet/user/create.do?petId=${pet.id}'"
											value="<spring:message code="pet.edit" />" />
									</jstl:if>
								</div>
								<div class="col-md-3">
									<jstl:if test="${pet.adoptionRequests.size() > 0 }">
										<input class="btn btn-sg btn-danger" type="button" onclick="var ask = window.confirm('<spring:message code='pet.delete.confirm'/>');if (ask) { window.location.href = 'pet/user/delete.do?petId=${pet.id}';}" value="<spring:message code="pet.delete" />"/>
									</jstl:if>
									<jstl:if test="${pet.adoptionRequests.size() eq 0 }">
										<input class="btn btn-sg btn-primary" type="button"
											onclick="window.location.href='pet/user/delete.do?petId=${pet.id}'"
											value="<spring:message code="pet.delete" />" />
									</jstl:if>
								</div>
							</jstl:if>
						</div>
						<div class="row">
							<div class="col-md-3">
								<small><strong><spring:message code="pet.age" />:</strong>
									<jstl:out value="${pet.age}"/> <jstl:out value="${pet.typeAge}"/></small>
							</div>
							<div class="col-md-3">
								<small><strong><spring:message
											code="pet.weight" />:</strong> <jstl:out value="${pet.weight}" /> Kg</small>
							</div>
							<div class="col-md-3">
								<small><strong><spring:message
											code="pet.height" />:</strong> <jstl:out value="${pet.height}" /> m</small>
							</div>
							<div class="col-md-3">
								<small><strong><spring:message code="pet.genre" />:</strong>
									<jstl:out value="${pet.genre}" /></small>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<small><strong><spring:message code="pet.city" />:</strong>
									<jstl:out value="${pet.city}" /></small>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<strong><spring:message code="pet.description" />:</strong><br>
								<jstl:out value="${pet.description}" />
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12">
								<strong><spring:message code="pet.healthDescription" />:</strong><br>
								<jstl:out value="${pet.healthDescription}" />
							</div>
						</div>
						<br>
					</div>
				</div>
			</div>
		</div>
		<br>
		<jstl:if test="${own}">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h3>
						<spring:message code="adoption.requests" />
					</h3>
				</div>
			</div>
			<jstl:if test="${pet.adoptionRequests.size() eq 0 }">
				<div class="row">
					<div class="col-md-12 text-center">
						<p>
							<spring:message code="adoptionRequest.no.results" />
						</p>
					</div>
				</div>
			</jstl:if>
			<jstl:if test="${pet.adoptionRequests.size() ne 0 }">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<jstl:forEach var="x" items="${pet.adoptionRequests}">
							<div class="list-group">
								<div class="row list-group-item">
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-3">
												<div class="row">
													<div class="col-md-12">
														<img class="img-responsive center-block img-circle"
															src="${x.adopter.picture}" />
													</div>
												</div>
											</div>
											<div class="col-md-9">
												<div class="row">
													<div class="col-md-8">
														<div class="row">
															<div class="col-md-6">
																<h4>
																	<jstl:out value="${x.adopter.name }" />
																	<jstl:out value="${x.adopter.surname }" />
																</h4>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<p>
																	<jstl:out value="${x.adopter.biography }" />
																</p>
															</div>
														</div>
														<br>
														<jstl:if test="${x.status eq 'PENDING' }">
															<div class="row">
																<div class="col-md-12">
																	<div class="row">
																		<div class="col-md-6">
																			<input class="btn btn-sg btn-success" type="button"
																				onclick="window.location.href='adoptionRequest/user/accept.do?adoptionRequestId=${x.id }&petId=${pet.id}'"
																				value="<spring:message code="pet.accept.request" />" />
																		</div>
																		<div class="col-md-6">
																			<input class="btn btn-sg btn-danger" type="button"
																				onclick="window.location.href='adoptionRequest/user/deny.do?adoptionRequestId=${x.id }&petId=${pet.id}'"
																				value="<spring:message code="pet.deny.request" />" />
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
																	<span class="label label-default"><jstl:out
																			value="${x.status }" /></span>
																</div>
															</div>
														</jstl:if>
														<jstl:if test="${x.status eq 'PENDING' }">
															<div class="row">
																<div class="col-md-12">
																	<span class="label label-warning"><jstl:out
																			value="${x.status }" /></span>
																</div>
															</div>
															<br>
														</jstl:if>
														<jstl:if test="${x.status eq 'ACCEPTED' }">
															<div class="row">
																<div class="col-md-12">
																	<span class="label label-success"><jstl:out
																			value="${x.status }" /></span>
																</div>
															</div>
														</jstl:if>
														<jstl:if test="${x.status eq 'DENIED' }">
															<div class="row">
																<div class="col-md-12">
																	<span class="label label-danger"><jstl:out
																			value="${x.status }" /></span>
																</div>
															</div>
														</jstl:if>
														<br>
														<div class="row">
															<div class="col-md-12">
																<p>
																	<fmt:formatDate value="${x.requestMoment}"
																		pattern="yyyy-MM-dd" />
																</p>
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
		</jstl:if>
		<!-- END PETS DISPLAY -->
	</jstl:if>
	<!-- start back button -->
	<div class="row back-button">
			<div class="col-md-6">
				<%-- <input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='pet/list.do?category=${pet.category.name}'" value="<spring:message code="pet.back" />"/> --%>
				<acme:cancel url="/pet/list.do?category=${pet.category.name}" code="pet.back"/>
				<br/><br/>
			</div>
		</div>
	<!-- end back button -->
</div>
