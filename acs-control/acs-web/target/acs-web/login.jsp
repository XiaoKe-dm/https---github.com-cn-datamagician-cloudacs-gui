<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String msg = request.getParameter(User.LOGIN_MEG);
	if(User.UNSUCCESS_INFO.equals(msg)){
		msg = "User name or password error";
	}else if(User.NOSESSION_INFO.equals(msg)){
		msg = "Login has timed out,please re login";
	}else{
		msg = "";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Cloud ACS Administration Portal</title>
		<meta name="keywords" content="Cloud ACS Administration Portal" />
		<meta name="description" content="Cloud ACS Administration Portal" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="author" content="">
		<!-- Stylesheets -->
		<link rel="shortcut icon" href="<%=basePath %>resources/img/favicon.png">
		<link href="<%=basePath %>resources/style/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=basePath %>resources/style/font-awesome.css">
		<link href="<%=basePath %>resources/style/style.css" rel="stylesheet">
		<link href="<%=basePath %>resources/style/login.css" rel="stylesheet" />
	</head>
	<body>
		<div class="display_name">
			<span>Cloud ACS Administration Portal</span>
		</div>
		<!-- Form area -->
		<div class="admin-form">
			<div class="container">

				<div class="row">
					<div class="col-md-12">
						<!-- Widget starts -->
						<div class="widget worange">
							<!-- Widget head -->
							<div class="widget-head">
								<i class="icon-lock"></i> Login
							</div>

							<div class="widget-content">
								<div class="padd">
									<!-- Login form -->
									<form class="form-horizontal" action="<%=basePath %>user/login" method="post">
										<!-- Email -->
										<div class="form-group">
											<label class="control-label col-lg-3" for="inputEmail">Username</label>
											<div class="col-lg-9">
												<input type="text" name="username" class="form-control" id="inputEmail" placeholder="Username">
											</div>
										</div>
										<!-- Password -->
										<div class="form-group">
											<label class="control-label col-lg-3" for="inputPassword">Password</label>
											<div class="col-lg-9">
												<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
											</div>
										</div>
										<div class="form-group">
                    						<span class="message-info"><%=msg%></span>
										</div>
										<div class="col-lg-9 col-lg-offset-2">
											<button type="submit" class="btn btn-danger">Sign in</button>
											<button type="reset" class="btn btn-default">Reset</button>
										</div>
										<br />
									</form>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- JS -->
		<script src="<%=basePath %>resources/js/jquery.js"></script>
	</body>
</html>