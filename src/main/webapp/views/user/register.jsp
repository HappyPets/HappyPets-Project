<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- New User View -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form:form action="${formAction}" modelAttribute="userForm">
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
							<acme:textbox code="user.picture" path="picture"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="user.name" path="name"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="user.surname" path="surname"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="user.dni" path="DNI"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="user.birthDate" path="birthDate"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="user.email" path="email"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="user.phone" path="phone"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<acme:textbox code="user.address" path="address"/>
						</div>
						<div class="col-md-4">
							<acme:textbox code="user.city" path="city"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<acme:textbox code="user.biography" path="biography"/>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<h3><spring:message code="register.register.info" /></h3>
						</div>
					</div><hr>
					<div class="row">
						<div class="col-md-12">
							<acme:textbox code="user.username" path="username"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:password code="user.password" path="password"/>
						</div>
					<div class="col-md-6">
							<acme:password code="user.repeatPassword" path="repeatPassword"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<jstl:if test="${message eq 'user.password.error'}">
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
									<b><spring:message code="user.disagree" /></b>
								</font>
							</jstl:if>
						</div>
					</div><br>
					<acme:submit name="save" code="user.save"/>
					<acme:cancel url="/welcome/index.do" code="user.cancel"/>
				</div>
			</form:form>
		</div>
	</div>
</div>