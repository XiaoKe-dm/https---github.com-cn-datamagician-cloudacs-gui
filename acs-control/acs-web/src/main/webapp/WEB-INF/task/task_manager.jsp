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
		<link href="<%=basePath %>resources/task/css/task.css" rel="stylesheet">

		<!-- Main content starts -->
		<div class="content">
			<!-- Sidebar -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
			<!-- Sidebar ends -->

			<!-- Main bar -->
			<div class="mainbar">

				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-list-alt"></i>Tasks</h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Home</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">Tasks</a>
					</div>

					<div class="clearfix"></div>

				</div>
				<!-- Page heading ends -->
				<!-- Matter -->
				<div class="matter">
					<div class="matter-head">
						<nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse">
<!-- 							<ul class="nav navbar-nav"> -->
<!-- 								Sync to server link -->
<!-- 								<li class="dropdown dropdown-big"> -->
<!-- 									<a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="addTask();return false;"><span class="label label-danger"><i class="icon-plus"></i></span> Add Tasks</a> -->
<!-- 								</li> -->
<!-- 							</ul> -->
							<!-- Search form -->
							<div class="form-group">
								<form role="search" class="navbar-form navbar-right">
									<select class="selectpicker" data-width="150px" id="queryColumn" name="queryColumn" style="float:left">
										<option value="device">Device</option>
										<option value="name">Name</option>
									</select>
									<input type="text" placeholder="Search" class="form-control" id="task_search" style="width: 200px;">
									<span class="am-search-span">
										<input type="button" value="Search" class="am-search-span-btn" onclick="serarchTasks()"/>
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
											<th>No.</th>
											<th>device</th>
											<th>Name</th>
											<th>Time</th>
											<th>Retries</th>
											<th>Fault</th>
											<th>Control</th>
										</tr>
									</thead>
									<tbody id="tasks_tbody">
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

									<ul class="pagination pull-right" id="task_page">
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
        <jsp:include page="taskModal.jsp"></jsp:include>
	<jsp:include page="../foot.jsp"></jsp:include>
	<script src="<%=basePath %>resources/js/jquery.form.js"></script>
	<script src="<%=basePath %>resources/task/js/taskIndex.js"></script>