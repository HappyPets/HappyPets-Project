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
<br>
<!-- START JOB OFFER DISPLAY VIEW -->
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
	<jstl:if test="${inscription eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="inscription.ok" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscription eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="inscription.error" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${edit eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="jobOffer.ok.edit" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${edit eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="jobOffer.error.edit" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${create eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="jobOffer.ok.create" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${delete eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="jobOffer.error.create" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionAccept eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="ok.accept.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionAccept eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="error.accept.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionDeny eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="ok.deny.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionDeny eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="error.deny.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionCancel eq true }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-success text-center"><spring:message code="ok.cancel.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${inscriptionCancel eq false }">
		<div class="row">
			<div class="col-md-12">
				<br />
				<p class="bg-danger text-center"><spring:message code="error.cancel.inscription" /></p>
			</div>
		</div>
	</jstl:if>
	<!-- END ERROR PANEL -->
	<jstl:if test="${error eq ''}">
		<!-- START JOB OFFER DISPLAY -->
		<div class="list-group">
			<div class="row list-group-item">
				<div class="col-md-3">
					<div class="row">
						<div class="col-md-12">
							<img class="img-responsive center-block img-circle" src="${jobOffer.pet.picture}" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 text-center">
							<h5><strong><jstl:out value="${jobOffer.pet.name }" /></strong></h5>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="row">
						<div class="col-md-12">
							<h4><a href="profile/subActor/display.do?subActorId=${jobOffer.owner.id }"><jstl:out value="${jobOffer.owner.name }" /></a></h4>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<h4><jstl:out value="${jobOffer.title }" /></h4>
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<small><i><fmt:formatDate value="${jobOffer.startDate }" pattern="yyyy-MM-dd" /> - <fmt:formatDate value="${jobOffer.endDate }" pattern="yyyy-MM-dd" /></i></small>
						</div>
						<div class="col-md-4">
							<small><jstl:out value="${jobOffer.city }" /></small>
						</div>
						<div class="col-md-3">
							<strong><jstl:out value="${jobOffer.salary }" /> &euro;</strong>
						</div>
					</div><br>
					<div class="row">
						<div class="col-md-12">
							<jstl:out value="${jobOffer.description }" />
						</div>
					</div><br>
					<div class="row">
						<jstl:if test="${jobOffer.inscriptions.size() eq 0 and own eq true }">
							<div class="col-md-6">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='jobOffer/user/create.do?jobOfferId=${jobOffer.id}'" value="<spring:message code="jobOffer.edit" />"/>
							</div>
						</jstl:if>
						<jstl:if test="${jobOffer.inscriptions.size() ne 0 }">
							<div class="col-md-6"></div>
						</jstl:if>
						<jstl:if test="${own eq true and jobOffer.isClosed eq false }">
							<div class="col-md-6">
								<input class="btn btn-sg btn-danger" type="button" onclick="window.location.href='jobOffer/user/delete.do?jobOfferId=${jobOffer.id}'" value="<spring:message code="jobOffer.delete" />"/>
							</div>
						</jstl:if>
						<jstl:if test="${own ne true and jobOffer.isClosed eq false and inscripted ne true}">
							<div class="col-md-6">
								<input class="btn btn-sg btn-primary" type="button" onclick="window.location.href='inscription/user/sendInscription.do?jobOfferId=${jobOffer.id}'" value="<spring:message code="inscription.send" />"/>
							</div>
						</jstl:if>
					</div><br>
				</div>
			</div>
		</div><br>
		<jstl:if test="${own}" >
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h3><spring:message code="jobOffer.inscriptions" /></h3>
				</div>
			</div>
			<jstl:if test="${jobOffer.inscriptions.size() eq 0}">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<p><spring:message code="inscriptions.no.results" /></p>
					</div>
				</div>
			</jstl:if>
			<jstl:if test="${jobOffer.inscriptions.size() ne 0}">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<jstl:forEach var="x" items="${jobOffer.inscriptions}">
							<div class="list-group">
								<div class="row list-group-item">
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-3">
												<div class="row">
													<div class="col-md-12">
														<img class="img-responsive center-block img-circle" src="${x.inscripter.picture}" />
													</div>
												</div>
											</div>
											<div class="col-md-9">
												<div class="row">
													<div class="col-md-8">
														<div class="row">
															<div class="col-md-6">
																<h4><jstl:out value="${x.inscripter.name }" /> <jstl:out value="${x.inscripter.surname }" /></h4>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<p><jstl:out value="${x.inscripter.biography }" /></p>
															</div>
														</div><br>
														<jstl:if test="${x.status eq 'PENDING' }">
															<div class="row">
																<div class="col-md-12">
																	<div class="row">
																		<jstl:if test="${own eq true }">
																			<div class="col-md-6">
																				<input class="btn btn-sg btn-success" type="button" onclick="window.location.href='inscription/user/accept.do?inscriptionId=${x.id }&jobOfferId=${jobOffer.id}'" value="<spring:message code="inscription.accept" />"/>	
																			</div>
																			<div class="col-md-6">
																				<input class="btn btn-sg btn-danger" type="button" onclick="window.location.href='inscription/user/deny.do?inscriptionId=${x.id }&jobOfferId=${jobOffer.id}'" value="<spring:message code="inscription.deny" />"/>	
																			</div>
																		</jstl:if>
																		<jstl:if test="${own ne true }">
																			<div class="col-md-6"></div>
																			<div class="col-md-6">
																				<input class="btn btn-sg btn-danger" type="button" onclick="window.location.href='inscription/user/cancel.do?inscriptionId=${x.id }&jobOfferId=${jobOffer.id}'" value="<spring:message code="inscription.cancel" />"/>	
																			</div>
																		</jstl:if>
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
															</div><br>
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
																<p><fmt:formatDate value="${x.inscriptionMoment}" pattern="yyyy-MM-dd" /></p>
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
		<!-- END JOB OFFER DISPLAY -->
	</jstl:if>
	<!-- start back button -->
	<div class="row back-button">
		<div class="col-md-6">
			<jstl:if test="${own eq true}">
				<acme:cancel url="/jobOffer/user/own.do" code="jobOffer.back"/>
			</jstl:if>
			<jstl:if test="${own eq false}">
				<acme:cancel url="/jobOffer/user/list.do" code="jobOffer.back"/>
			</jstl:if>
			
			<br/><br/>
		</div>
	</div>
	<!-- end back button -->
</div>