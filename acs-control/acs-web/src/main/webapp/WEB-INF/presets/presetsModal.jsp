<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="presetsModal" class="modal fade" tabindex="-1" data-width="1000" data-height="600" data-backdrop="static">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">New Template</h4>
	</div>
	<div class="modal-body">
		<form class="form-horizontal" id="presets_modal_form" role="form" action="<%=basePath %>presets/addPresets" method="post">
			<div class="form-group" id="modules_name_group">
				<label class="col-lg-3 control-label"><strong>Template Name:</strong><em class="importance">*</em></label>
				<div class="col-lg-9">
					<input type="hidden" id="presets_sid">
					<input type="text" class="form-control" id="presets_name" name="presetName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"><strong>Modules:</strong><em class="importance">*</em></label>
				<div class="col-lg-9" id="moduleNamediv">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"><strong>productClass:</strong></label>
				<div class="col-lg-9">
					<input type="hidden" value="productClass" name="preconditionList[0].name">
					<input type="hidden" value="e" name="preconditionList[0].symbol">
					<input type="text" class="form-control" id="productClass" name="preconditionList[0].value" readonly>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"><strong>oui:</strong></label>
				<div class="col-lg-9">
					<input type="hidden" value="oui" name="preconditionList[1].name">
					<input type="hidden" value="e" name="preconditionList[1].symbol">
					<input type="text" class="form-control" id="oui" readonly name="preconditionList[1].value">
				</div>
			</div>
			<h3 style="float: left;">Precondition</h3>
			<div class="btn-group" style="margin:0 0 0 10px;">
				<button style="margin:5px 0 0 0px;padding: 1px 12px;" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Add <span class="caret"></span>
				</button>
				<ul id="addPrecondition" class="dropdown-menu">
					<li><a href="#">lastInform</a></li>
					<li><a href="#">serialNumber</a></li>
					<li><a href="#">manufacturer</a></li>
					<li><a href="#">hardwareVersion</a></li>
					<li><a href="#">softwareVersion</a></li>
					<li><a href="#">mac</a></li>
					<li><a href="#">ip</a></li>
					<li><a href="#">wlanSsid</a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
			<hr style="margin: 5px 0 10px;"/>
			<div id="precondition_context">
				<!--<div class="form-group">
					<label class="col-lg-3 control-label"><strong>Product Class:</strong></label>
					<div class="col-lg-9">
						<select class="selectpicker inline-select inline-select-symbol">
						  <option value="e">=</option>
						  <option value="ne">≠</option>
						  <option value="lt">&lt;</option>
						  <option value="lte">≤</option>
						  <option value="gt">&gt;</option>
						  <option value="gte">≥</option>
						</select>
						<input class="form_datetime">
						<button type="button" class="btn btn-link" style="float: right;">delete</button>
					</div>
				</div>-->
			</div>
			<h3 style="float: left;">Configurations</h3>
			<div class="btn-group" style="margin:0 0 0 10px;">
				<button style="margin:5px 0 0 0px;padding: 1px 12px;" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Add <span class="caret"></span>
				</button>
				<ul id="addConfigurations" class="dropdown-menu">
					<li><a href="#">Set</a></li>
					<li><a href="#">Add Object</a></li>
					<li><a href="#">Remove Object</a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
			<hr style="margin: 5px 0 10px;"/>
			<div id="configurations_context">
				<!-- <div class="form-group" id="product_class_group">
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
						<button type="button" class="btn btn-link" style="float: right;">delete</button>
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
				</div>
				<div class="form-group" id="product_class_group">
					<label class="col-lg-3 control-label"><strong>Remove object:</strong></label>
					<div class="col-lg-9">
						<input type="text" style="width: 39%;margin-right: 5px;" class="form-control" name="productClass">
						<strong class="to-group">to</strong>
						<input type="text" style="width: 45%;" class="form-control" name="productClass">
						<button type="button" class="btn btn-link" style="float: right;">delete</button>
					</div>
				</div> -->
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
		<button type="button" class="btn btn-primary" onclick="submitAddUpdataPresets();return false;">Save</button>
	</div>
</div>
<div id="deletePresetsModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
	<input type="hidden" id="deletePresetsId">
	<div class="modal-body">
		<p style="font-size:18px;">Are you sure you want to destroy the template name <span id="deletePresetsName"></span>?</p>
	</div>
	<div class="modal-footer">
		<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		<button type="button" onclick="submitRemovePresets()" class="btn btn-primary">Continue</button>
	</div>
</div>

