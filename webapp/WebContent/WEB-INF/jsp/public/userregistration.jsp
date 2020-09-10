<%@ include file="../layouts/commontags.jsp"%>

<spring:url var="registerUrl" value="/register" />

<div class="container-fluid">
	<form:form commandName="userRegistrationForm" method="post" action="${registerUrl}" role="form" class="form-horizontal">
				
	<form:errors element="div" cssClass="errorblock" path="" />
	
		<div class="row">
			<div class="container col-md-8">
			  	<div class="col-md-12 card">
			  		<div class="card-header title">
			  			<h3><spring:message code="user.registration.title" /></h3>
			  		</div>
			  		
			  		<div class="card-body">
	    
			  			<div class="form-group row">
				  			<spring:bind path="person.fullName">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=fullName>
									<spring:message code="common.label.fullName" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-8 ${status.error ? 'errorblock' : ''}">
									<form:input path="person.fullName" class="form-control input-sm" id="fullName"/>
									<form:errors path="person.fullName" />
								</span>
			   				</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="person.identityType">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=identityType>
									<spring:message code="common.label.identityType" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-8 ${status.error ? 'errorblock' : ''}">
									<form:select path="person.identityType" id="identityType" style="width:100%" class="form-control input-sm" >
			    						<form:option value=""><spring:message code='common.label.pleaseSelect'/></form:option>
										<form:options items="${userRegistrationForm.identityTypes}" itemLabel="name" itemValue="code"/>
									</form:select>
									<form:errors path="person.identityType" />
								</span>
							</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="person.identityNo">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=identityNo>
									<spring:message code="common.label.identityNo" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-5 ${status.error ? 'errorblock' : ''}">
									<form:input path="person.identityNo" class="form-control input-sm" id="identityNo"/>
									<form:errors path="person.identityNo" />
								</span>
							</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="person.email">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=email>
									<spring:message code="common.label.email" />
								</label>
								<span class="col-sm-8 ${status.error ? 'errorblock' : ''}">
									<form:input path="person.email" class="form-control input-sm" id="email"/>
									<form:errors path="person.email" />
								</span>
							</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="person.mobileNo">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=mobileNo>
									<spring:message code="common.label.mobileNo" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-4 ${status.error ? 'errorblock' : ''}">
									<form:input path="person.mobileNo" class="form-control input-sm" id="mobileNo"/>
									<form:errors path="person.mobileNo" />
								</span>
							</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="user.password">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=password>
									<spring:message code="common.label.password" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-4 ${status.error ? 'errorblock' : ''}">
									<form:password path="user.password" class="form-control input-sm" id="password"/>
									<form:errors path="user.password" />
								</span>
							</spring:bind>
						</div>
						
			  			<div class="form-group row">
				  			<spring:bind path="user.confirmPassword">
								<label class="control-label col-sm-4 ${status.error ? 'errorblock' : ''}" for=confirmPassword>
									<spring:message code="common.label.confirmPassword" /><span class="mandatory">*</span>
								</label>
								<span class="col-sm-4 ${status.error ? 'errorblock' : ''}">
									<form:password path="user.confirmPassword" class="form-control input-sm" id="confirmPassword"/>
									<form:errors path="user.confirmPassword" />
								</span>
							</spring:bind>
						</div>
	
			  		</div>
			  		
			  		<div class="card-footer" align="right">
						<form:button name="action" value="register" class="btn btn-primary"><spring:message code="button.register" /></form:button>
			  		</div>
			  	</div>
			</div> 	
		</div>
		
	</form:form>
</div>