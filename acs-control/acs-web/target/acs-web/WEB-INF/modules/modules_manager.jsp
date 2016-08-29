<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<jsp:include page="../head.jsp"></jsp:include>
		<link href="<%=basePath %>resources/css/base.css" rel="stylesheet">
		<link href="<%=basePath %>resources/modules/css/modules.css" rel="stylesheet">

		<!-- Main content starts -->
		<div class="content">
			<!-- Sidebar -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
			<!-- Sidebar ends -->

			<!-- Main bar -->
			<div class="mainbar">

				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-list-alt"></i>Modules</h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Home</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">Modules</a>
					</div>

					<div class="clearfix"></div>

				</div>
				<!-- Page heading ends -->
				<!-- Matter -->
				<div class="matter">
					<div class="matter-head">
						<nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse">
							<ul class="nav navbar-nav">
								<!-- Sync to server link -->
								<li class="dropdown dropdown-big">
									<a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="addModules();return false;"><span class="label label-danger"><i class="icon-plus"></i></span> Add Modules</a>
								</li>
							</ul>
							<!-- Search form -->
							<div class="form-group">
								<form role="search" class="navbar-form navbar-right">
									<select class="selectpicker" data-width="150px" id="queryColumn" name="queryColumn" style="float:left">
										<option value="name">Name</option>
									</select>
									<input type="text" placeholder="Search" class="form-control" id="modules_search" style="width: 200px;">
									<span class="am-search-span">
										<input type="button" value="Search" class="am-search-span-btn" onclick="serarchModules()"/>
								    </span>
								</form>
							</div>
						</nav>
					</div>
					<div class="matter-table">
						<div class="widget">
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover group-table modules-table">
									<thead>
										<tr>
											<th>Serial no.</th>
											<th>Modules Name</th>
											<th>Product Class</th>
											<th>OUI</th>
											<th>Manufacturer</th>
											<th>Description</th>
											<th>Control</th>
										</tr>
									</thead>
									<tbody id="modules_tbody">
										<%-- <c:forEach var="item" items="${modulesList}" varStatus="status">
					                        <tr>
												<td>${status.index+1}</td>
												<td>${item.name}</td>
												<td>${item.productClass}</td>
												<td>${item.oui}</td>
												<td>${item.manufacturer}</td>
												<td>${item.description}</td>
												<td>
													<button class="btn btn-xs btn-success" onclick="editUser('${item.sid}')"><i class="icon-edit"></i> </button>
													<button class="btn btn-xs btn-danger" onclick="removeUser('${item.sid}','${item.name}')"><i class="icon-remove"></i> </button>
												</td>
											</tr>
					                    </c:forEach> --%>
									</tbody>
								</table>

								<div class="widget-foot">

									<ul class="pagination pull-right" id="modules_page">
<!-- 										<li><a href="#">Prev</a></li> -->
<!-- 										<li><a href="#">1</a></li> -->
<!-- 										<li><a href="#">2</a></li> -->
<!-- 										<li><a href="#">3</a></li> -->
<!-- 										<li><a href="#">4</a></li> -->
<!-- 										<li><a href="#">Next</a></li> -->
									</ul>

									<div class="clearfix"></div>

								</div>

							</div>

						</div>
					</div>
				</div>

				<!-- Matter ends -->

			</div>

			<!-- Mainbar ends -->
			<div class="clearfix"></div>

		</div>
		<!-- Content ends -->
		<!-- Modal -->
        <div id="modulesModal" class="modal fade" tabindex="-1" data-backdrop="static">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                 <h4 class="modal-title">New Modules</h4>
             </div>
             <div class="modal-body">
             	<form class="form-horizontal" id="modules_modal_form" role="form">
                        <div class="form-group" id="modules_name_group">
                          <label class="col-lg-4 control-label"><strong>Modules Name:</strong><em class="importance">*</em></label>
                          <div class="col-lg-8">
                            <input type="hidden" id="modules_sid">
                            <input type="text" class="form-control" id="modules_name" name="name" placeholder="Modules Name">
                          </div>
                        </div>
                        <div class="form-group" id="product_class_group">
                          <label class="col-lg-4 control-label"><strong>Product Class:</strong><em class="importance">*</em></label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" id="product_class" name="productClass" placeholder="Product Class">
                          </div>
                        </div>
                        <div class="form-group" id="oui_group">
                          <label class="col-lg-4 control-label"><strong>OUI:</strong><em class="importance">*</em></label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" id="oui" name="oui" placeholder="OUI">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-4 control-label"><strong>manufacturer:</strong></label>
                          <div class="col-lg-8">
                            <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="manufacturer">
                          </div>
                        </div>
                        <div class="form-group">
                           <label class="col-lg-4 control-label"><strong>description:</strong></label>
                           <div class="col-lg-8">
                             <textarea class="form-control" rows="3" id="description" placeholder="description"></textarea>
                           </div>
                         </div>
                    </form>
             </div>
             <div class="modal-footer">
<!--              	 <button type="button" class="btn btn-info" data-dismiss="modal" id="upload_firmware" onclick="uploadFirmware()" aria-hidden="true">Upload Firmware</button> -->
                 <button type="button" class="btn btn-primary" onclick="submitAddUpdataModules()">Save</button>
             </div>
		</div>
		<div id="deleteModulesModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<input type="hidden" id="deleteModulesId" >
		      <div class="modal-body">
		        <p style="font-size:18px;">Are you sure you want to delete the modules name <span id="deleteModulesName"></span>?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		        <button type="button" onclick="removeModules()" class="btn btn-primary">Continue</button>
		      </div>
		</div>
		<div id="uploadFirmwareModal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
			<form class="form-horizontal" id="file_modal_form" role="form" method="post" enctype="multipart/form-data" action="<%=basePath %>files/updateFile">
		      <div class="modal-body">
		        <p style="font-size:18px;">Are you sure you want to delete the modules name <span id="deleteModulesName"></span>?</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
		        <button type="button" onclick="removeModules()" class="btn btn-primary">Upload</button>
		      </div>
		     </form>
		</div>
	<jsp:include page="../foot.jsp"></jsp:include>
	<script src="<%=basePath %>resources/modules/js/modulesIndex.js"></script>