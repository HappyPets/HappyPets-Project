<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<!-- -----------DATAPICKER------------- -->
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy', changeYear: true, yearRange: "1955:2001"});});
  } );
  </script>
  
<!-- ---------FIN DATAPICKER-------------- -->



<!-- New Company View -->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form:form action="${formAction}" modelAttribute="companyForm">
				<form:hidden path="id" />
				<form:hidden path="version" />
				
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<h3><spring:message code="register.personal.info" /></h3>
						</div>
					</div><hr>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="company.businessName" path="businessName"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="company.vat" path="VAT"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="company.name" path="name"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="company.surname" path="surname"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="company.dni" path="DNI"/>
						</div>
						<div class="col-md-6">
							<b><spring:message code="user.birthDate"/></b><input type="text" id="datepicker" name="birthDate" form="userForm">
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:textbox code="company.email" path="email"/>
						</div>
						<div class="col-md-6">
							<acme:textbox code="company.phone" path="phone"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<acme:textbox code="company.address" path="address"/>
						</div>
						<div class="col-md-4">
							<acme:textbox code="company.city" path="city"/>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<h3><spring:message code="register.register.info" /></h3>
						</div>
					</div><hr>
					<div class="row">
						<div class="col-md-12">
							<acme:textbox code="company.username" path="username"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<acme:password code="company.password" path="password"/>
						</div>
					<div class="col-md-6">
							<acme:password code="company.repeatPassword" path="repeatPassword"/>
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
									<b><spring:message code="company.disagree" /></b>
								</font>
							</jstl:if>
						</div>
					</div><br>
					<acme:submit name="save" code="company.save"/>
					<acme:cancel url="/welcome/index.do" code="company.cancel"/>
				</div>
			</form:form>
		</div>
	</div>
</div>