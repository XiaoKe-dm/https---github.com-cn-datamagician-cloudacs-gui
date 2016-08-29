<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<jsp:include page="../head.jsp"></jsp:include>
		<link href="<%=basePath %>resources/device/css/device.css" rel="stylesheet">

		<!-- Main content starts -->
		<div id="content" class="content">
			<!-- Sidebar -->
			<jsp:include page="../sidebar.jsp"></jsp:include>
			<!-- Sidebar ends -->

			<!-- Main bar -->
			<div class="mainbar">

				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-list-alt"></i>Device</h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Home</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">Device</a>
					</div>

					<div class="clearfix"></div>

				</div>
				<!-- Page heading ends -->
				<!-- Matter -->
				<div class="matter">
					<div class="matter-head">
						<nav role="navigation" class="collapse navbar-collapse bs-navbar-collapse">
							<!-- <ul class="nav navbar-nav">
								Sync to server link
								<li class="dropdown dropdown-big">
									<a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="showDeviceInfo();return false;"><span class="label label-danger"><i class="icon-plus"></i></span> Add Oper</a>
								</li>
							</ul> -->
							<!-- Search form -->
							<div style="float:left;margin-top: 10px;">
								<a class="btn btn-success <c:if test="${empty queryType}">btn-info</c:if>" href="<%=basePath %>device/deviceIndex">
									<i class="icon-laptop"></i> All
								</a>
								<a class="btn btn-success <c:if test="${queryType=='actice'}">btn-info</c:if>" href="<%=basePath %>device/deviceIndex?queryType=actice">
									<i class="icon-eye-open"></i> Active
								</a>
								<a class="btn btn-success <c:if test="${queryType=='unknownModel'}">btn-info</c:if>" href="<%=basePath %>device/deviceIndex?queryType=unknownModel">
									<i class="icon-exclamation-sign"></i> Unknown
								</a>
							</div>
							<div class="form-group">
								<form role="search" id="searchDevice" class="navbar-form navbar-right" action="<%=basePath %>device/deviceIndex">
									<select class="selectpicker" data-width="150px" name="queryColumn" style="float:left">
										<option value="mac">MAC</option>
									  	<option value="serialNumber">Serial number</option>
									  	<option value="productClass">Product class</option>
									</select>
									<input data-rel="tooltip" type="text" placeholder="Search" name="queryValue" title="Hello Tooltip!" class="form-control" data-placement="bottom">
									<span class="am-search-span">
										<input type="button" value="Search" class="am-search-span-btn" onclick="$('#searchDevice').submit();"/>
								    </span>
								</form>
							</div>
						</nav>
					</div>
					<div class="matter-table">
						<div class="widget">
							<div class="widget-content">
								<table class="table table-striped table-bordered table-hover group-table user-table">
									<thead>
										<tr>
											<th>No.</th>
											<th>Serial number</th>
											<th>Modules</th>
											<th>Product class</th>
											<th>Software version</th>
											<th>MAC</th>
											<th>IP</th>
<!-- 											<th>WLAN SSID</th> -->
											<th>Last inform</th>
											<th>Control</th>
										</tr>
									</thead>
									<tbody id="device_tbody">
										<!-- <tr>
					                        	<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>1</td>
												<td>
													<button class="btn btn-xs btn-success" onclick="showDeviceInfo('1')"><i class="icon-edit"></i>Show</button>
												</td>
											</tr> -->
<%-- 										<c:if test="${devicePage.results.size()<=0}"> --%>
<!-- 											<tr><td colSpan=10>no result</td></tr> -->
<%-- 										</c:if> --%>
										<c:forEach var="item" items="${devicePage.results}" varStatus="status">
					                        <tr>
					                        	<td>${status.index+1}</td>
												<td>${item.summary.serialNumber}</td>
												<td>${item.modules}</td>
												<td>${item.summary.productClass}</td>
												<td>${item.summary.softwareVersion}</td>
												<td>${item.summary.mac}</td>
												<td>${item.summary.ip}</td>
<%-- 												<td>${item.summary.wlanSsid}</td> --%>
												<td><fmt:formatDate value="${item.lastInform}" type="both" pattern="EE MM dd yyyy HH:mm:ss zz"/></td>
												<td>
												<c:if test="${item.modules!=''&&item.modules!='Unknown'&&!empty item.modules}">
													<button class="btn btn-xs btn-success" onclick="showDeviceInfo('${item.id}')"><i class="icon-edit"></i>Show</button>
												</c:if>
												</td>
											</tr>
					                    </c:forEach>
									</tbody>
								</table>
								<div class="widget-foot">
									<ul class="pagination pull-right">
										<c:if test="${devicePage.results.size()>0}">
											<li><a <c:if test="${devicePage.currentPage>1}"> href="<%=basePath %>device/deviceIndex?currentPage=${devicePage.currentPage-1}"</c:if>>Prev</a></li>
										</c:if>
										<c:forEach var="item" begin="1" end="${devicePage.pageCount}">
											<li <c:if test="${devicePage.currentPage==item}"> class="active"</c:if> ><a href="<%=basePath %>device/deviceIndex?currentPage=${item}">${item}</a></li>
										</c:forEach>
										<c:if test="${devicePage.results.size()>0}">
											<li><a <c:if test="${devicePage.currentPage<devicePage.pageCount}"> href="<%=basePath %>device/deviceIndex?currentPage=${devicePage.currentPage+1}" </c:if>>Next</a></li> 
										</c:if>
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
		<jsp:include page="deviceModal.jsp"></jsp:include>
	<jsp:include page="../foot.jsp"></jsp:include>
<%-- 	<script src="<%=basePath %>resources/js/tooltip.js"></script> --%>
	<script src="<%=basePath %>resources/js/spin.min.js"></script>
	<script src="<%=basePath %>resources/device/js/deviceIndex.js"></script>