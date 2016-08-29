<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="provisioningModal" class="modal fade" tabindex="-1" data-width="600" data-height="260" data-backdrop="static">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">New Account</h4>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="provisioning_modal_form" role="form" action="<%=basePath %>provisioning/addProvisioning" method="post">
<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-lg-4 control-label"><strong>Provisioning Name:</strong><em class="importance">*</em></label> -->
<!-- 				<div class="col-lg-8"> -->
<!-- 					<strong id="provisioning_name"></strong> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="form-group">
				<label class="col-lg-4 control-label"><strong>Mapping MAC:</strong><em class="importance">*</em></label>
				<div class="col-lg-8" id="moduleNamediv">
					<input type="text" class="form-control" data-toggle="tooltip" data-placement="bottom" title="Please use ':' to separate!" id="provisioning_mac" name="mac">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-4 control-label"><strong>Domain:</strong><em class="importance">*</em></label>
				<div class="col-lg-8" id="moduleNamediv">
                     <select class="selectpicker" name="domainsList" id="domainsList">
                     </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-4 control-label"><strong>Username:</strong><em class="importance">*</em></label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="provisioning_username" name="username">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-4 control-label"><strong>Password:</strong><em class="importance">*</em></label>
				<div class="col-lg-8">
					<input type="text" class="form-control" id="provisioning_password" name="password">
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
		<button type="button" class="btn btn-primary" onclick="submitAddUpdataProvisioning();return false;">Save</button>
	</div>
</div>
<div id="deleteProvisioningModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
	<div class="modal-body">
		<p style="font-size:18px;">Are you sure you want to destroy the account name <span id="deleteProvisioningName"></span>?</p>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" data-bb-handler="button" class="btn btn-sm">Cancel</button>
		<button type="button" onclick="submitRemoveProvisioning()" class="btn btn-primary">Continue</button>
	</div>
</div>

