<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<jsp:include page="head.jsp"></jsp:include>
		<!-- Main content starts -->
		<div class="content">
			<!-- Sidebar -->
			<jsp:include page="sidebar.jsp"></jsp:include>
			<!-- Sidebar ends -->
			<!-- Main bar -->
			<div class="mainbar">
				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-home"></i></h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Main</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">console</a>
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- Page heading ends -->
				<!-- Matter -->
				<div class="matter">
					<div class="container">
						<!-- Today status. jQuery Sparkline plugin used. -->
						<div class="row">
							<div class="col-md-12">
								<div class="widget">
									<div class="widget-content">
										<div class="padd statement">
											<div class="row">
												<div class="col-md-4">
													<div class="well total_devices" onclick="window.location.href='<%=basePath %>device/deviceIndex'">
														<h3>Total devices</h3>
														<p id="total_devices">0</p>
													</div>
												</div>

												<div class="col-md-4">
													<div class="well unknown_devices" onclick="window.location.href='<%=basePath %>device/deviceIndex?queryType=unknownModel'">
														<h3>Unknown devices</h3>
														<p id="unknown_devices">0</p>
													</div>
												</div>

												<div class="col-md-4">
													<div class="well active_devices" onclick="window.location.href='<%=basePath %>device/deviceIndex?queryType=actice'">
														<h3>Active devices</h3>
														<p id="active_devices">0</p>
													</div>
												</div>

											</div>

											<div class="row">

												<div class="col-md-12">
													<hr />
													<div class="widget">
														<div class="widget-content">
															<table class="table table-striped table-bordered table-hover">
																<thead>
																	<tr>
																		<th>Serial no.</th>
																		<th>Device Name</th>
																		<th>Device Model</th>
																		<th>OUI</th>
																		<th>Product Class</th>
																		<th>Status</th>
																	</tr>
																</thead>
																<tbody id="device_online_tbody">
																	<!-- <tr>
																		<td>0B5E358726</td>
																		<td>DLink-DSL-2158B-0B5E358726</td>
																		<td>001C45</td>
																		<td>DLink DSL 2158</td>
																		<td><span class="label label-success">Normal</span></td>
																		<td>
																			<button class="btn btn-xs btn-default"><i class="icon-pencil"></i> </button>
																			<button class="btn btn-xs btn-default"><i class="icon-remove"></i> </button>
																		</td>
																	</tr> -->
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<!-- File Upload widget -->
							<div class="col-md-6">
								<div class="widget">
									<!-- Widget title -->
									<div class="widget-head">
										<div class="pull-left">Task List</div>
										<div class="widget-icons pull-right">
											<a class="wminimize" href="#"><i class="icon-chevron-up"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">
										<!-- Widget content -->

										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>Task Device</th>
													<th>Task Name</th>
													<th>Time</th>
												</tr>
											</thead>
											<tbody id="task_tbody">
<!-- 												<tr> -->
<!-- 													<td>DLink DSL 2158</td> -->
<!-- 													<td>3,005</td> -->
<!-- 												</tr> -->
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="widget">
									<!-- Widget title -->
									<div class="widget-head">
										<div class="pull-left">Module List</div>
										<div class="widget-icons pull-right">
											<a class="wminimize" href="#"><i class="icon-chevron-up"></i></a>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="widget-content referrer">
										<!-- Widget content -->

										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>Model Name</th>
													<th>Match Num</th>
												</tr>
											</thead>
											<tbody id="module_tbody">
<!-- 												<tr> -->
<!-- 													<td>DLink DSL 2158</td> -->
<!-- 													<td>3,005</td> -->
<!-- 												</tr> -->
											</tbody>
										</table>
									</div>
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

	<jsp:include page="foot.jsp"></jsp:include>
	<script src="<%=basePath %>resources/js/main.js"></script>