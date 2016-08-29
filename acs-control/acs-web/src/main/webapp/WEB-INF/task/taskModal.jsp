<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="taskModal" class="modal fade" tabindex="-1" data-width="1000" data-height="600" data-backdrop="static">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">New Task</h4>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="task_modal_form" role="form" method="post" action="<%=basePath %>task/addTask">
			<div class="form-group" id="product_class_group">
				<label class="col-lg-3 control-label"><strong>Name:</strong><em class="importance">*</em></label>
				<div class="col-lg-9">
					<select class="selectpicker" name="taskName" id="type_select">
						<option value="setParameterValues" selected="selected">setParameterValues</option>
<!-- 					  	<option value="refreshObject">refreshObject</option> -->
					  	<option value="addObject">addObject</option>
					  	<option value="reboot">reboot</option>
					  	<option value="FactoryReset">FactoryReset</option>
					</select>
				</div>
			</div>
			<div id="modules_context">
				<div class="form-group" id="product_class_group">
					<label class="col-lg-3 control-label"><strong>Module Name:</strong><em class="importance">*</em></label>
					<div class="col-lg-9" id="moduleNamediv">
						<!-- <select class="selectpicker" id="moduleName">
						  <option value="setParameterValues">Module1</option>
						  <option value="refreshObject">Module2</option>
						  <option value="addObject">Module3</option>
						  <option value="reboot">Module4</option>
						  <option value="FactoryReset">Module5</option>
						</select>
						<button type="button" class="btn btn-link">Select Device</button> -->
					</div>
				</div>
				<div class="row" id="device_row" style="display:none;">
				  <label class="col-lg-3 control-label"><strong>Device:</strong></label>
				  <div id="device_list" class="tagbtn plus-tag col-lg-9"> 
<!-- 				  	<a><span>a</span><em onclick="removeTag(&quot;a&quot;)"></em></a>  -->
				  </div>
				</div>
			</div>
			<div id="configurations_main">
				<h3 style="float: left;" id="configurations_text">setParameterValues</h3>
				<div class="btn-group" style="margin:0 0 0 10px;">
					<button style="margin:5px 0 0 0px;padding: 1px 12px;" type="button" id="configurations_add" class="btn btn-primary">Add</button>
				</div>
				<div class="clearfix"></div>
				<hr style="margin: 5px 0 10px;"/>
				<div id="configurations_context">
					<!--<div class="form-group">
						<label class="col-lg-3 control-label"><strong>Set:</strong></label>
						<div class="col-lg-9">
							<select class="selectpicker inline-select">
							  <option value="e">=</option>
							  <option value="ne">≠</option>
							  <option value="lt">&lt;</option>
							  <option value="lte">≤</option>
							  <option value="gt">&gt;</option>
							  <option value="gte">≥</option>
							</select>
							<strong class="to-group">to</strong>
							<input type="text" style="width: 45%;" class="form-control" name="productClass">
							<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>
						</div>
					</div>
					<div class="form-group" id="product_class_group">
						<label class="col-lg-3 control-label"><strong>Add object:</strong></label>
						<div class="col-lg-9">
							<input type="text" style="width: 39%;margin-right: 5px;" class="form-control" name="productClass">
							<strong class="to-group">to</strong>
							<input type="text" style="width: 45%;" class="form-control" name="productClass">
							<button type="button" class="btn btn-link" style="float: right;">delete</button>
						</div>
					</div>-->
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
		<button type="button" class="btn btn-primary" onclick="submitAddTasks()">Add Task</button>
	</div>
</div>
<div id="deleteTasksModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
	<input type="hidden" id="deleteTasksId">
	<div class="modal-body">
		<p style="font-size:18px;">Are you sure you want to destroy the task name <span id="deleteTasksName"></span>?</p>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		<button type="button" onclick="submitRemoveTasks()" class="btn btn-primary">Continue</button>
	</div>
</div>
<div id="retryTasksModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
	<input type="hidden" id="retryTasksId">
	<div class="modal-body">
		<p style="font-size:18px;">Are you sure you want to retry the task name <span id="retryTasksName"></span>?</p>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		<button type="button" onclick="submitRetryTasks()" class="btn btn-primary">Continue</button>
	</div>
</div>
<div id="deviceListModal" class="modal fade" tabindex="-1" data-width="900" data-backdrop="static">
	<div class="modal-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" onclick="selectAllDevice();" id="selectAll"></th>
					<th>Serial number</th>
					<th>Product class</th>
					<th>OUI</th>
					<th>Software version</th>
					<th>MAC</th>
					<th>IP</th>
					<th>WLAN SSID</th>
				</tr>
			</thead>
			<tbody id="deviceList_tbody">
			</tbody>
		</table>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
			<button type="button" onclick="submitSelectDeviceList()" class="btn btn-primary">Submit</button>
		</div>
	</div>
</div>
