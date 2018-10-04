<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!-- Display Profile View -->
<div class="container-fluid">
	<!-- start error panel -->
	<div class="row">
		<div class="col-md-12">
			<jstl:if test="${errorMessage != null}">
				<span class="message" style="color:red"><spring:message code="${errorMessage}" /></span>
			</jstl:if>
			<jstl:if test="${valoratedOK eq true }">
				<div class="row">
					<div class="col-md-12">
						<br />
						<p class="bg-success text-center"><spring:message code="ok.valoration" /></p>
					</div>
				</div>
			</jstl:if>
			<jstl:if test="${valoratedOK eq false }">
				<div class="row">
					<div class="col-md-12">
						<br />
						<p class="bg-danger text-center"><spring:message code="error.valoration" /></p>
					</div>
				</div>
			</jstl:if>	
		</div>
	</div>
	<!-- end error panel -->

	<div class="row">
		<security:authorize access="hasRole('USER') or hasRole('VET') or hasRole('COMPANY')">
			<div class="row">
				<jstl:if test="${own }">
					<jstl:if test="${type eq 'U' }">
						<div class="col-md-5">
							<input class="btn btn-mg btn-primary" type="button" name="back" onclick="window.location.href='user/register.do?edit=1'" value="<spring:message code="subActor.edit"/>" />
						</div>
					</jstl:if>
					<jstl:if test="${type eq 'V' }">
						<div class="col-md-5">
							<input class="btn btn-mg btn-primary" type="button" name="back" onclick="window.location.href='vet/register.do?edit=1'" value="<spring:message code="subActor.edit"/>" />
						</div>
					</jstl:if>
					<jstl:if test="${type eq 'C' }">
						<div class="col-md-5">
							<input class="btn btn-mg btn-primary" type="button" name="back" onclick="window.location.href='company/register.do?edit=1'" value="<spring:message code="subActor.edit"/>" />
						</div>
					</jstl:if>
				</jstl:if>
			</div>
			<jstl:if test="${type eq 'U' }">
				<div class="row">
					<div class="col-md-4">
						<img class="img-responsive very-small-img" src="${subActor.picture}" />
					</div>
				</div>
			</jstl:if>
			<jstl:if test="${type eq 'C' }">
				<div class="row">
					<div class="col-md-8">
						<h3><spring:message code="subActor.businessName"/>: <jstl:out value="${subActor.businessName }" /></h3><br>
					</div>
					<div class="col-md-4">
						<h3><spring:message code="subActor.vat"/>: <jstl:out value="${subActor.VAT }" /></h3><br>
					</div>
				</div>
			</jstl:if>
			<jstl:if test="${isUser eq true and own ne true and valorado ne true}">
				<div class="row">
					<div class="col-md-6">
						<h3><jstl:out value="${subActor.name }" /> <jstl:out value="${subActor. surname }" /></h3>
						<fieldset class="rating">
							<span class="align-middle"><strong><spring:message code="subActor.valoration" /></strong></span><br>
							<jstl:if test="${valoration eq 5 }">
								<input type="radio" id="star5" name="rating" value="5" checked onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 4 }">
								<input type="radio" id="star5" name="rating" value="5" onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" checked onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 3 }">
								<input type="radio" id="star5" name="rating" value="5" onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" checked onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 2 }">
								<input type="radio" id="star5" name="rating" value="5" onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" checked onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 1 }">
								<input type="radio" id="star5" name="rating" value="5" onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" checked onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 0 }">
								<input type="radio" id="star5" name="rating" value="5" onclick="window.location.href='valoration/user/create.do?stars=${5 }&subActorId=${subActor.id}'" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" name="rating" value="4" onclick="window.location.href='valoration/user/create.do?stars=${4 }&subActorId=${subActor.id}'" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" name="rating" value="3" onclick="window.location.href='valoration/user/create.do?stars=${3 }&subActorId=${subActor.id}'" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" name="rating" value="2" onclick="window.location.href='valoration/user/create.do?stars=${2 }&subActorId=${subActor.id}'" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" name="rating" value="1" onclick="window.location.href='valoration/user/create.do?stars=${1 }&subActorId=${subActor.id}'" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
						</fieldset>
					</div>
				</div><br>
			</jstl:if>
			<jstl:if test="${isVet eq true or isCompany eq true or (isUser eq true and (own eq true or valorado eq true))}">
				<div class="row">
					<div class="col-md-6">
						<h3><jstl:out value="${subActor.name }" /> <jstl:out value="${subActor. surname }" /></h3>
						<fieldset class="valorado">
							<span class="align-middle"><strong><spring:message code="subActor.valoration" /></strong></span><br>
							<jstl:if test="${valoration eq 5 }">
								<input type="radio" id="star5" disabled name="valorado" checked value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 4 }">
								<input type="radio" id="star5" disabled name="valorado" value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" checked value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 3 }">
								<input type="radio" id="star5" disabled name="valorado" value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" checked value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 2 }">
								<input type="radio" id="star5" disabled name="valorado" value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" checked value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 1 }">
								<input type="radio" id="star5" disabled name="valorado" value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" checked value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
							<jstl:if test="${valoration eq 0 }">
								<input type="radio" id="star5" disabled name="valorado" value="5" /><label class = "full" for="star5" title="5 stars"></label>
								<input type="radio" id="star4" disabled name="valorado" value="4" /><label class = "full" for="star4" title="4 stars"></label>
								<input type="radio" id="star3" disabled name="valorado" value="3" /><label class = "full" for="star3" title="3 stars"></label>
								<input type="radio" id="star2" disabled name="valorado" value="2" /><label class = "full" for="star2" title="2 stars"></label>
								<input type="radio" id="star1" disabled name="valorado" value="1" /><label class = "full" for="star1" title="1 stars"></label>
							</jstl:if>
						</fieldset>
					</div>
				</div><br>
			</jstl:if>
			<jstl:if test="${type eq 'V' }">
				<div class="row">
					<div class="col-md-6">
						<h3><spring:message code="subActor.licenseNumber"/>: <jstl:out value="${subActor.licenseNumber }" /></h3><br>
					</div>
				</div>
			</jstl:if>
			<div class="row">
				<div class="well well-lg">
					<div class="row">
						<div class="col-md-4">
							<p>
								<i class="acme-icons fa fa-map-marker" style="font-size:24px"></i> ${subActor.address}<br/>
								<i class="acme-icons fa fa-phone" style="font-size:24px"></i> <a href="tel:${subActor.phone}">${subActor.phone}</a><br/>
								<i class="acme-icons fa fa-envelope-o" style="font-size:24px"></i> <a href="mailto:${subActor.email}">${subActor.email}</a><br>
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<b><spring:message code="subActor.dni"/>: </b><jstl:out value="${subActor.DNI }" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<b><spring:message code="subActor.birthDate"/>: </b><jstl:out value="${subActor.birthDate }" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<b><spring:message code="subActor.city"/>: </b><jstl:out value="${subActor.city }" />
						</div>
					</div>
					<jstl:if test="${type eq 'U' }">
						<div class="row">
							<div class="col-md-10">
								<b><spring:message code="subActor.biography"/>: </b><jstl:out value="${subActor.biography }" />
							</div>
						</div>
					</jstl:if>
					
				</div>
			</div>
		</security:authorize>
	</div>
	
	<div class="row back-button">
		<div class="col-md-12">
			<%-- <acme:cancel url="/profile/subActor/list.do" code="subActor.back"/> --%>
			<acme:cancel url="/welcome/index.do" code="subActor.back"/>
		<br/><br/>
		</div>
	</div>
</div>
