<%@ include file="commontags.jsp"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><tiles:getAsString name="title"/></title>
    
    <!-- Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Profail Penduduk Sabah">
	<meta name="keywords" content="Sabah">
	
	<!-- Favicon icon -->
	<link rel="icon" href="<spring:url value="resources/image/favicon.png"/>" type="image/png" sizes="16x16">
	
	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="<spring:url value="resources/css/bootstrap-material-design.min.css"/>" media="all" />
	
	<!-- Font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,300,500,700,600" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" >
		
	<!-- Ionic Icons -->
	<link rel="stylesheet" href="<spring:url value="resources/css/ionicons.min.css"/>"> 
	
	<!-- Font Awesome -->
    <link rel="stylesheet" type="text/css" href="<spring:url value="resources/css/font-awesome.min.css"/>">
	
	<link rel="stylesheet" href="<spring:url value="resources/css/style-sticky-footer-navbar.css"/>" type="text/css" media="all" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/site.css" />" type="text/css" />
</head>

<body>
	<div class="backgroundImage"></div>
    <header>
      	<!-- Fixed navbar -->
	    <nav class="navbar navbar-expand-md navbar-dark fixed-top">
			<div class="container-fluid">
			  	<div class="navbar-header">
					<a href="#" class="navbar-brand" style="padding-left:0px; padding-top:0px">
					<span class="navbar-branding">
						<img class="w-100 pl-3" src=<spring:url value="/resources/image/logo_jpkn.png"/> alt="JPKN" ></img>
					</span>
					<spring:message code="home.header"/></a>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>				
						<span class="icon-bar"></span>				
						<span class="icon-bar"></span>				
					</button>      			
				</div>
				
				<div class="collapse navbar-collapse" id="registrationBar">
					<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
					<div>
						<a href="<%=request.getContextPath()%>/register">REGISTER</a>
					</div>
				</div>
				    		
				<div class="collapse navbar-collapse" id="myNavbar">
				    <ul class="nav navbar-nav navbar-right">
				      <li>
				      	<a href="#" data-toggle="modal" data-target="#optionsDlg">
						<span class="glyphicon glyphicon-cog"></span> <spring:message code="options" />
						</a>		        
				       </li>
				     </ul>
				</div>   		
		   	</div>
		</nav>
    </header>

	<tiles:insertAttribute name="options-modal" />
	<tiles:insertAttribute name="additional-modal" />	
	
    <!-- Begin page content -->
    <main role="main" class="container">
    	<div class="panel panel-primary">
			<tiles:insertAttribute name="content" />
		</div>
    </main>

    <footer class="footer">
      <div class="container text-center">
        <span class="text-muted"><spring:message code="home.footer" htmlEscape="false" /></span>
      </div>
    </footer>
	
	<!-- Jquery and Js Plugins -->
	<script type="text/javascript" src="resources/css/js/jquery-3.2.1.slim.min.js"></script>
	<script type="text/javascript" src="resources/css/js/popper.min.js"></script>
	<script type="text/javascript" src="resources/css/js/bootstrap-material-design.min.js"></script>
	<script type="text/javascript" src="resources/css/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/css/js/menu.js"></script>
</body>

</html>

