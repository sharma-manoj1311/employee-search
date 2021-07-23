

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html dir="ltr" lang="en" class="no-outlines">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>EMPLOYEE SEARCH</title>
<meta name="author" content="">
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="icon" href="favicon12.png" type="image/png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700%7CMontserrat:400,500">
<link rel="stylesheet" href="<c:url value="/resources/Admin/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/Admin/assets/css/datatables.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/Admin/assets/css/jquery-ui.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/Admin/assets/css/style.css"/>">




<script src="<c:url value="/resources/Admin/assets/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/Admin/assets/js/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/resources/Admin/assets/js/datatables.min.js"/>"></script>
<script src="<c:url value="/resources/Admin/assets/js/main.js"/>"></script>
	
</head>
<body>
	<div class="wrapper">
		
		
		<main class="main--container">


			<tiles:insertAttribute name="body" />


		</main>
	</div>
	
	
	
	
	
	

 
</body>
</html>


