<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	User user = (User)session.getAttribute(User.SESSION_USERNAME);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" style="height: 100%;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<!-- Title and other stuffs -->
		<title>Cloud ACS Administration Portal</title>
		<meta name="keywords" content="Cloud ACS Administration Portal" />
		<meta name="description" content="Cloud ACS Administration Portal" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Stylesheets -->
		<link href="<%=basePath %>resources/style/bootstrap.css" rel="stylesheet">
		<!-- Font awesome icon -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/font-awesome.css">
		<!-- jQuery UI -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/jquery-ui.css">
		<!-- Star rating -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/rateit.css">
		<!-- Date picker -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/bootstrap-datetimepicker.min.css">
		<!-- CLEditor -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/jquery.cleditor.css">
		<!-- Bootstrap toggle -->
		<link rel="stylesheet" href="<%=basePath %>resources/style/bootstrap-switch.css">
		<!-- Widgets stylesheet -->
		<link href="<%=basePath %>resources/style/widgets.css" rel="stylesheet">
		<!-- Main stylesheet -->
		<link href="<%=basePath %>resources/style/style.css" rel="stylesheet">
		<link href="<%=basePath %>resources/style/head.css" rel="stylesheet">
		<link href="<%=basePath %>resources/style/index.css" rel="stylesheet">
		<link href="<%=basePath %>resources/style/bootstrap-modal-bs3patch.css" rel="stylesheet" />
		<link href="<%=basePath %>resources/style/bootstrap-modal.css" rel="stylesheet" />
		<link href="<%=basePath %>resources/style/bootstrap-select.min.css" rel="stylesheet">
		<link href="<%=basePath %>resources/css/ace.min.css" rel="stylesheet">
<%-- 		<link href="<%=basePath %>resources/css/ace.css" rel="stylesheet"> --%>
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="<%=basePath %>resources/img/favicon.png">
	</head>
	<body style="height: 100%;">
		<header>
			<div class="container">
				<div class="row">
					<!-- Logo section -->
					<div class="logo_div">
						<div class="logo">
							<h1><a href="<%=basePath %>main/index">Cloud ACS<span class="bold"></span></a></h1>
							<p class="meta">Administration Portal</p>
						</div>
						<!-- Logo ends -->
					</div>
					<div class="user_div">
						<ul class="nav navbar-nav pull-right">
							<li class="dropdown pull-right user-ul">
								<a data-toggle="dropdown" class="dropdown-toggle" style="line-height: 46px;" href="#">
									<i class="icon-user"></i> <%=user.getUsername()%><b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a data-toggle="modal" href="#myPasswordModal"><i class="icon-cogs"></i> Setting</a></li>
									<li><a href="<%=basePath %>user/loginOut"><i class="icon-off"></i> Quit</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<div id="myPasswordModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">Password Change</h4>
                    </div>
                    <div class="modal-body">
                    	<form class="form-horizontal" role="form">
                               <div class="form-group">
                                 <label class="col-lg-4 control-label">Old Password <span class="important_red">*</span></label>
                                 <div class="col-lg-8">
                                   <input type="password" class="form-control" id="old_password" name="old_password" placeholder="Old Password">
                                 </div>
                               </div>
                               <div class="form-group">
                                 <label class="col-lg-4 control-label">New Password <span class="important_red">*</span></label>
                                 <div class="col-lg-8">
                                   <input type="password" class="form-control" id="new_password" name="new_password" placeholder="New Password">
                                 </div>
                               </div>
                               <div class="form-group">
                                 <label class="col-lg-4 control-label">Confirm Password <span class="important_red">*</span></label>
                                 <div class="col-lg-8">
                                   <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Confirm Password">
                                 </div>
                               </div>
                               <div class="ui-widget" id="warning-info" style="display:none">
				                  <div class="ui-state-error ui-corner-all">
				                    <p><span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
				                    <strong>Alert:</strong> <span id="warning-info-text"></span></p>
				                  </div>
				               </div>
<!--                                <div class="alert alert-warning"> -->
<!-- 			                   </div> -->
                           </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="close_change_password" aria-hidden="true">Close</button>
                        <button type="button" class="btn btn-primary" onclick="updataPassword()">Confirm Change</button>
                        
                    </div>
                </div>
			</div>
		</div>
		<!-- Header ends -->
		<script type="text/javascript">
			function updataPassword(){
				var newPassword = $("#new_password").val();
				var confirmPassword = $("#confirm_password").val();
				if(newPassword==""||confirmPassword==""){
					$('#warning-info').show();
					$('#warning-info-text').html('The new password can not be empty!');
					return false;
				}
				if(newPassword!=confirmPassword){
					$('#warning-info').show();
					$('#warning-info-text').html('Two passwords are not consistent!');
					return false;
				}else{
					$.ajax({
				         type:"POST",
				         url:"<%=basePath %>user/updataPassword",
				         data:{oldPassword : $("#old_password").val(),newPassword : $("#new_password").val(),confirmPassword : $("#confirm_password").val()},
				         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
				         success:function(json){
				        	 var data = $.parseJSON(json);
				        	 var msg = data.msg;
				        	 if(msg=="success"){
				        		 $('#close_change_password').trigger("click");
				        		 alert("Change Password successfully!");
				        		 $('#warning-info').hide();
				        		 resetPasswordFrom();
				        	 }else{
				        		 $('#warning-info-text').html(msg);
				        		 $('#warning-info').show();
				        	 }
				         },
				         error: function(){
				      		alert("unsuccess");
				         }         
				      });
				}
			}
			function resetPasswordFrom(){
				$("#old_password").val("");
				$("#new_password").val("");
				$("#confirm_password").val("")
			}
		</script>
		<jsp:include page="base/pre_deined.jsp"></jsp:include>