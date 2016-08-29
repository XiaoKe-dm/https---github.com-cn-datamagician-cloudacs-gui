<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
        <div id="deviceModal" class="modal fade" tabindex="-1" data-width="1200">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 class="modal-title" id="deviceModalLabel">Device: <span id="device_id"></span></h3>
                    </div>
                    <div class="modal-body">
                    	<div class="modal-operate-button">
                    		<button class="btn btn-primary" onclick="deviceRebootById()">Reboot</button> 
                      		<button class="btn btn-warning" onclick="deviceFactoryResetById()">Factory reset</button>
                      		<button class="btn btn-danger" onclick="deviceRefreshById()">Refresh</button>
                      		<button class="btn btn-info" onclick="downloadById()">Download</button>
                    	</div>
                    	<div class="device-modal-info">
                    		<div class="container-fluid">
                    			<!-- <div class="row">
								  <div class="col-sm-12" id="device_tags"><strong>Tags:  </strong>
								  	<div class="tagbtn plus-tag" id="device_plus_tags">
								  	</div>
								  	<button onclick="addtag()" type="button" class="btn btn-link">Add</button>
								  </div>
								</div> -->
	                    		<div class="row">
								  <div class="col-sm-4"><strong>Serial number: </strong> <span id="serial_number"></span></div>
								  <div class="col-sm-4"><strong>Product class: </strong> <span id="product_class"></span></div>
								   <div class="col-sm-4"><strong>OUI: </strong> <span id="oui"></span></div>
								</div>
								<div class="row">
								  <div class="col-sm-6"><strong>Hardware version: </strong> <span id="hardware_version"></span></div>
								  <div class="col-sm-6"><strong>Software version: </strong> <span id="software_version"></span></div>
								</div>
								<div class="row">
								  <div class="col-sm-6"><strong>Manufacturer:</strong> <span id="manufacturer"></span></div>
								  <div class="col-sm-6"><strong>MAC: </strong> <span id="mac"></span></div>
								</div>
								<div class="row">
								  <div class="col-sm-12"><strong>IP: </strong> <span id="ip"></span></div>
<!-- 								  <div class="col-sm-6"><strong>WLAN SSID: </strong> <span id="wlan_ssid"></span>  <button type="button" class="btn btn-link" onclick="editDeviceInfo('summary.wlanSsid','wlan_ssid','xsd:string')">Edit</button></div> -->
								</div>
								<div class="row">
								  <div class="col-sm-12"><strong>Last inform:  </strong> <span id="last_inform"></span> <button type="button" class="btn btn-link">Ping</button></div>
								</div>
							</div>
                    	</div>
                    	<div class="tabbable" style="margin-bottom: 18px;">
	                      <ul class="nav nav-pills">
	                       	<li class="active"><a href="#config_tab" data-toggle="tab">WAN Configuration</a></li>
	                        <li><a href="#hosts_tab" data-toggle="tab">Hosts</a></li>
	                        <li><a href="#task_tab" data-toggle="tab">Task queue</a></li>
	                        <li><a href="#parameters_tab" data-toggle="tab">Parameters</a></li>
	                      </ul>
	                      <div class="tab-content" style="padding-bottom: 9px; border-bottom: 1px solid #ddd;">
	                      	<div class="tab-pane active" id="config_tab">
	                          <h3>Configuration</h3>
	                          <div class="container-fluid">
		                          	<div class="row">
									  <div class="col-sm-4"><strong>Name: </strong> <span id="name"></span><button type="button" class="btn btn-link" id="name_button">Edit</button></div>
									  <div class="col-sm-4"><strong>Username: </strong> <span id="username"></span><button type="button" class="btn btn-link" id="username_button">Edit</button></div>
									   <div class="col-sm-4"><strong>Password: </strong> <span id="password"></span><button type="button" class="btn btn-link" id="password_button">Edit</button></div>
									</div>
									<div class="row">
									  <div class="col-sm-4"><strong>WLAN SSID: </strong> <span id="wlan_ssid"></span><button type="button" class="btn btn-link" id="wlan_ssid_button" onclick="editDeviceInfo('summary.wlanSsid','','xsd:string')">Edit</button></div>
									  <div class="col-sm-4"><strong>DNS Servers: </strong> <span id="dns_servers"></span><button type="button" class="btn btn-link" id="dns_button">Edit</button></div>
									  <div class="col-sm-4"><strong>XDSL Type:</strong> <span id="xds_type"></span></div>
									</div>
									<div class="row">
									  <div class="col-sm-6"><strong>Current Uplink Rate(K bps):</strong> <span id="up_curr_Rate"></span></div>
									  <div class="col-sm-6"><strong>Current Downlink Rate(K bps): </strong> <span id="down_curr_Rate"></span></div>
									</div>
									<div class="row">
									  <div class="col-sm-6"><strong>Max Uplink Rate(K bps):</strong> <span id="up_max_Rate"></span></div>
									  <div class="col-sm-6"><strong>Max Downlink Rate(K bps): </strong> <span id="down_max_Rate"></span></div>
									</div>
									
							  </div>
	                        </div>
	                        <div class="tab-pane" id="hosts_tab">
	                          <h3>Hosts</h3>
	                          <table class="table table-hover">
	                          	<thead>
									<tr>
										<th>No.</th>
										<th>Host name</th>
										<th>IP</th>
										<th>MAC</th>
<!-- 										<th>Layer2Interface</th> -->
										<th>Active</th>
									</tr>
								</thead>
								<tbody id="hosts_tbody">
			                        <!-- <tr>
			                        	<td>1</td>
										<td>android-d87bf88d22e66acf</td>
										<td>192.168.1.2</td>
										<td>40:B0:FA:9C:4A:50</td>
									</tr> -->
								</tbody>
							  </table>
	                        </div>
	                        <div class="tab-pane" id="task_tab">
	                          <h3>Task queue</h3>
	                          <table class="table table-hover">
	                          	<thead>
									<tr>
										<th>No.</th>
										<th>Task</th>
										<th>Time</th>
										<th>Retries</th>
										<th>Fault</th>
										<th>Control</th>
									</tr>
								</thead>
								<tbody id="task_tbody">
			                        <!-- <tr>
			                        	<td>1</td>
			                        	<td>refreshObject</td>
										<td>2016-04-09T11:53:07.433Z</td>
										<td>0</td>
										<td></td>
										<td><button type="button" class="btn btn-link">Destroy</button></td>
									</tr> -->
								</tbody>
							  </table>
	                        </div>
	                        <div class="tab-pane" id="parameters_tab">
	                          <h3>Device parameters</h3>
	                          <div class="device_parameters_div">
	                           	<div class="device_parameters_search">
									<input type="text" class="form-control" id="device_parameters_search" placeholder="Search">
	                           	</div>
	                           	<div class="device_parameters_context">
									 <table class="table table-hover table-condensed" style="table-layout:fixed;">
			                          	<thead>
											<tr>
												<th width="60%">Name</th>
												<th width="30%">Value</th>
												<th width="10%">Control</th>
											</tr>
										</thead>
										<tbody id="device_parameters_context">
										</tbody>
									  </table>
	                           	</div>
	                          </div>
	                        </div>
	                      </div>
	                    </div>
                    </div>
                    <!-- <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitAddUpdataOper()">Add Save</button>
                    </div> -->
		</div>
		<div id="deviceEditModal" class="modal fade" tabindex="-1" data-width="1000">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                   <h4 class="modal-title" id="deviceEditModalLabel">Device Editing</h4>
               </div>
               <div class="modal-body">
               	<form class="form-inline">
				  <div class="form-group">
				    <label class="sr-only">Type</label>
				    <p class="form-control-static"><strong id="device-modal-type"></strong></p>
				    <input type="hidden" id="inputDeviceType">
				  </div>
				  <div class="form-group" style="margin-left:10px;">
				    <label for="inputDeviceValue" class="sr-only">Value</label>
				    <input type="text" class="form-control" id="inputDeviceValue">
				  </div>
				</form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
                   <button type="button" class="btn btn-primary" onclick="submitUpdataParameterValues()">Confirm</button>
               </div>
		</div>
<div id="filesListModal" class="modal fade" tabindex="-1" data-width="900" data-max-height="600">
	<div class="modal-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Type</th>
				</tr>
			</thead>
			<tbody id="filesList_tbody">
			</tbody>
		</table>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
			<button type="button" onclick="submitSelectFiles()" class="btn btn-primary">Submit</button>
		</div>
	</div>
</div>
