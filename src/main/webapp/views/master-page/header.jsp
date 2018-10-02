<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid">
	<div class="row"><br>
		<div class="col-md-12 navbar-header">
			<div class="row">
				<div class="col-md-4">
				</div>
				<div class="col-md-6 text-center">
					<div class="row">
						<div class="col-md-2">
							<img src="images/logo.png" class="img-responsive img-circle" alt="Acme-newspaper, Inc." width="100px" height="100px" />
						</div>
						<div class="col-md-8">
							<br><a class="fNiv navbar-brand" href="welcome/index.do">HappyPets</a>
						</div>
					</div>
				</div>
				<div class="col-md-2 text-right">
					<a href="?language=en">en</a> | <a href="?language=es">es</a>
				</div>
			</div>
		</div>
	</div><br>
	<div class="row">
		<div class="col-md-10">
			<ul id="jMenu" class="nav navbar-nav menu">
				<li><a class="fNiv" href="welcome/index.do"><spring:message code="master.page.index" /></a></li>
		  		<li class="dropdown">
			  		<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.pets" /><span class="caret"></span></a>
			  		<ul class="dropdown-menu">
			  			<li><a href="pet/list.do?category=DOG"><spring:message code="master.page.pets.dog.list" /></a></li>
			  			<li><a href="pet/list.do?category=CAT"><spring:message code="master.page.pets.cat.list" /></a></li>
			  			<li><a href="pet/list.do?category=BIRD"><spring:message code="master.page.pets.bird.list" /></a></li>
			  			<li><a href="pet/list.do?category=RODENT"><spring:message code="master.page.pets.rodent.list" /></a></li>
			  			<li><a href="pet/list.do?category=OTHER"><spring:message code="master.page.pets.other.list" /></a></li>
			  		</ul>
			  	</li>
			  	<security:authorize access="hasRole('USER')">
					<li><a class="fNiv" href="pet/user/list.do"><spring:message code="master.page.own.pets" /></a></li>
					<li><a class="fNiv" href="adoptionRequest/user/list.do"><spring:message code="master.page.own.adoption.requests" /></a></li>
					<li class="dropdown">
						<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.jobOffers" /><span class="caret"></span></a>
				  		<ul class="dropdown-menu">
							<li><a href="jobOffer/user/own.do"><spring:message code="master.page.own.jobOffers" /></a></li>
							<li><a href="jobOffer/user/list.do"><spring:message code="master.page.all.jobOffers" /></a></li>
							<li><a href="inscription/user/list.do"><spring:message code="master.page.inscriptions" /></a></li>
						</ul>
					</li>
				</security:authorize>
			  	<security:authorize access="hasRole('VET')">
					
				</security:authorize>
			  	<security:authorize access="hasRole('COMPANY')">
					
				</security:authorize>
			  	<security:authorize access="hasRole('ADMINISTRATOR')">
				  	<li><a class="fNiv" href="cause/subActor/list.do"><spring:message code="master.page.causes" /></a></li>
					<li class="dropdown">
				  		<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.administration" /><span class="caret"></span></a>
				  			<ul class="dropdown-menu">
				  				<li><a href="tabooWords/administrator/create.do"><spring:message code="master.page.taboo" /></a></li>
				  				<li><a href="tabooWords/administrator/listTabooJobOffer.do"><spring:message code="master.page.tabooWords.jobOffers" /></a></li>
				  				<li><a href="tabooWords/administrator/listTabooComment.do"><spring:message code="master.page.tabooWords.comment" /></a></li>
				  				<li><a href="dashboard/administrator/list.do"><spring:message code="master.page.dashboard" /></a></li>
				  			</ul>
				  	</li>
				  	<li><a class="fNiv" href="advertisement/administrator/list.do"><spring:message code="master.page.advertisements" /></a></li>
				  	<li><a class="fNiv" href="priceAdvertisement/administrator/edit.do"><spring:message code="master.page.priceAdvertisement" /></a></li>
				</security:authorize>
				<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
					<li><a class="fNiv" href="cause/subActor/list.do"><spring:message code="master.page.causes" /></a></li>
					<li><a class="fNiv" href="profile/subActor/list.do"><spring:message code="master.page.actors" /></a></li>
					<li class="dropdown">
						<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.donations" /><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="donation/subActor/list.do"><spring:message code="master.page.listDonations" /></a></li>
						</ul>
					</li>
					<li><a class="fNiv" href="advertisement/subActor/listOwn.do"><spring:message code="master.page.listOwn" /></a></li>	
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.comments" /><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="comment/subActor/list.do?category=DOG"><spring:message code="master.page.commentsDog" /></a></li>
							<li><a href="comment/subActor/list.do?category=CAT"><spring:message code="master.page.commentsCat" /></a></li>
							<li><a href="comment/subActor/list.do?category=BIRD"><spring:message code="master.page.commentsBird" /></a></li>
							<li><a href="comment/subActor/list.do?category=RODENT"><spring:message code="master.page.commentsRodent" /></a></li>
							<li><a href="comment/subActor/list.do?category=OTHER"><spring:message code="master.page.commentsOther" /></a></li>
						</ul>
					</li>
				</security:authorize>
			</ul>
		</div>
		<div class="col-md-2">
			<ul id="jMenu" class="nav navbar-nav menu">
			  	<security:authorize access="isAnonymous()">
			  		<li class="dropdown">
						<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="master.page.access" /><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
							<li><a href="user/register.do?edit=0"><spring:message code="master.page.registerUser" /></a></li>
							<li><a href="company/register.do?edit=0"><spring:message code="master.page.registerCompany" /></a></li>
							<li><a href="vet/register.do?edit=0"><spring:message code="master.page.registerVet" /></a></li>
						</ul>
					</li>
			  	</security:authorize>
			  	<security:authorize access="isAuthenticated()">			
					<li class="dropdown">
						<a class="fNiv dropdown-toggle" data-toggle="dropdown" href="#"><security:authentication property="principal.username" /><span class="caret"></span></a>
						<ul class="dropdown-menu">
			  				<security:authorize access="hasRole('USER')">
			  					<li><a href="message/user/inbox.do"><spring:message code="master.page.messages" /></a></li>
							</security:authorize>
			  				<security:authorize access="hasRole('ADMINISTRATOR')">
			  					
							</security:authorize>
			  				<security:authorize access="hasRole('VET')">
			  					
							</security:authorize>
			  				<security:authorize access="hasRole('COMPANY')">
			  					
							</security:authorize>
							<security:authorize access="hasRole('COMPANY') or hasRole('VET') or hasRole('USER')">
			  					<li><a href="profile/subActor/display.do?subActorId=0"><spring:message code="master.page.profile" /></a></li>
							</security:authorize>
							<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
						</ul>
					</li>
					
				</security:authorize>
			</ul>
		</div>
	</div>
</div>
