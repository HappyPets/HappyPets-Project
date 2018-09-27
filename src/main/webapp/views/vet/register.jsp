<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- New Vet View -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form:form action="${formAction}" modelAttribute="vetForm">
				<form:hidden path="id" />
				<form:hidden path="version" />
				
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<h3><spring:message code="register.personal.info" /></h3>
						</div>
					</div><hr>
					<div class="row">
						<div class="col-md-4">
							<acme:textbox code="vet.licenseNumber" path="licenseNumber"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="vet.name" path="name"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="vet.surname" path="surname"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="vet.dni" path="DNI"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="vet.birthDate" path="birthDate"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="vet.email" path="email"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="vet.phone" path="phone"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<acme:textbox code="vet.address" path="address"/>
						</div>
						<div class="col-md-4">
							<acme:textbox code="vet.city" path="city"/>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<h3><spring:message code="register.register.info" /></h3>
						</div>
					</div><hr>
					<div class="row">
						<div class="col-md-12">
							<acme:textbox code="vet.username" path="username"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:password code="vet.password" path="password"/>
						</div>
					<div class="col-md-6">
							<acme:password code="vet.repeatPassword" path="repeatPassword"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<jstl:if test="${message eq 'vet.password.error'}">
								<font color="red">
									<b><spring:message code="noMatchPass" /></b>
								</font>
							</jstl:if>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<a href='terms/show.do'><spring:message code="accept.terms" /></a>
							<form:checkbox path="acceptLOPD"/> 
							<jstl:if test="${LOPDerror == 1}">
								<font color="red">
									<b><spring:message code="vet.disagree" /></b>
								</font>
							</jstl:if>
						</div>
					</div><br>
					<acme:submit name="save" code="vet.save"/>
					<acme:cancel url="/welcome/index.do" code="vet.cancel"/>
				</div>
			</form:form>
		</div>
	</div>
</div>