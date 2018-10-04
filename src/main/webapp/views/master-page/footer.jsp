<%--
 * footer.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<%--  <div class="footer-copyright text-center py-3">
 	<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> HAPPY PETS, Inc.</b>
     <a href='terms/terms.do'><spring:message code="terms.display" /></a>
 </div>

 --%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12 text-center">
			<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> HAPPY PETS, Inc.</b>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 text-center">
			<a href='terms/terms.do'><spring:message code="terms.display" /></a>
		</div>
	</div>
</div> 