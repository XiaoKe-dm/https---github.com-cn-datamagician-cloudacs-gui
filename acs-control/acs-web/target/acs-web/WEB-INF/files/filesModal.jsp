<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="fileModal" class="modal fade" tabindex="-1" data-width="800" data-backdrop="static">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">Update File</h4>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="file_modal_form" role="form" method="post" enctype="multipart/form-data" action="<%=basePath %>files/updateFile">
			<div class="form-group" id="product_class_group">
				<label class="col-lg-4 control-label"><strong>File type:</strong></label>
				<div class="col-lg-8">
					<select class="selectpicker" name="fileType" id="type_select">
					  	<option value="1 Firmware Upgrade Image">1 Firmware Upgrade Image</option>
<!-- 					  	<option value="Vendor Configuration File">Vendor Configuration File</option> -->
<!-- 					  	<option value="Tone File">Tone File</option> -->
<!-- 					  	<option value="Ringer File">Ringer File</option> -->
					</select>
					<button type="button" class="btn btn-link" onclick="selectDevice()">Select Module</button>
				</div>
			</div>
			<div class="form-group">
               <label class="col-lg-4 control-label"><strong>OUI:</strong></label>
               <div class="col-lg-8">
                 <input type="text" class="form-control" id="OUI" name="oui" placeholder="OUI">
               </div>
            </div>
            <div class="form-group">
               <label class="col-lg-4 control-label"><strong>Product Class:</strong></label>
               <div class="col-lg-8">
                 <input type="text" class="form-control" id="product_class" name="productClass" placeholder="Product Class">
               </div>
            </div>
            <div class="form-group">
               <label class="col-lg-4 control-label"><strong>version:</strong></label>
               <div class="col-lg-8">
                 <input type="text" class="form-control" id="version" name="version" placeholder="version">
               </div>
            </div>
            <div class="form-group">
               <label class="col-lg-4 control-label"><strong>File:</strong></label>
               <div class="col-lg-8" >
				 <input id="file" type="file" class="file" name="file" data-show-preview="false" data-show-Remove="false" data-show-Upload="false" data-browse-class="btn btn-primary btn-custom-files">
               </div>
            </div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
		<button type="button" class="btn btn-primary" onclick="submitUpdateFile()">Add File</button>
	</div>
</div>
<div id="progressModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="progress progress-striped active">
	   <div class="progress-bar progress-bar-success" id="proBar"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
		  <span class="sr-only">80% Complete</span>
	   </div>
    </div>
</div>
<div id="deleteFilesModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" data-backdrop="static">
	<input type="hidden" id="deleteFilesId">
	<div class="modal-body">
		<p style="font-size:18px;">Are you sure you want to destroy the file name <span id="deleteFilesName"></span>?</p>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		<button type="button" onclick="submitRemoveFiles()" class="btn btn-primary">Continue</button>
	</div>
</div>
<div id="deviceListModal" class="modal fade" tabindex="-1" data-width="900" data-max-height="600" data-backdrop="static">
	<div class="modal-body">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th>name</th>
					<th>Product class</th>
					<th>OUI</th>
					<th>manufacturer</th>
				</tr>
			</thead>
			<tbody id="deviceList_tbody">
			</tbody>
		</table>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
			<button type="button" onclick="submitSelectDevice()" class="btn btn-primary">Submit</button>
		</div>
	</div>
</div>