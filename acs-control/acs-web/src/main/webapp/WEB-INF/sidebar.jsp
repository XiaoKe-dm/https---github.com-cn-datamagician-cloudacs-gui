<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String requestUri = request.getRequestURI();  
    String contextPath = request.getContextPath();  
    String url = requestUri.substring(contextPath.length()); 
	//System.out.println("url:"+url);
	String domain = "main";
	if(url.indexOf("device")>=0)domain = "device";
	else if(url.indexOf("modules")>=0)domain = "modules";
	else if(url.indexOf("presets")>=0)domain = "presets";
	else if(url.indexOf("task")>=0)domain = "task";
	else if(url.indexOf("files")>=0)domain = "files";
	else if(url.indexOf("provisioning")>=0)domain = "provisioning";
	else if(url.indexOf("user")>=0||url.indexOf("oper")>=0)domain = "user";
	//System.out.println("domain:"+domain);
	request.setAttribute("domain",domain);  
%>
<!--- Sidebar navigation -->
<!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
<div class="sidebar">
	<ul id="nav">
		<!-- Main menu with font awesome icon -->
		<li><a href="<%=basePath %>main/index" <c:if test="${domain=='main'}"> class="open" </c:if>><i class="icon-home"></i>Main</a></li>
		<li><a href="<%=basePath %>device/deviceIndex?currentPage=1" <c:if test="${domain=='device'}"> class="open" </c:if>><i class="icon-magic"></i>Device</a></li>
		<li><a href="<%=basePath %>modules/modulesIndex" <c:if test="${domain=='modules'}"> class="open" </c:if>><i class="icon-table"></i>Modules</a></li>
		<li><a href="<%=basePath %>presets/presetsIndex" <c:if test="${domain=='presets'}"> class="open" </c:if>><i class="icon-bar-chart"></i>Template</a></li>
		<li><a href="<%=basePath %>task/taskIndex" <c:if test="${domain=='task'}"> class="open" </c:if>><i class="icon-tasks"></i>Tasks</a></li>
		<li><a href="<%=basePath %>files/filesIndex" <c:if test="${domain=='files'}"> class="open" </c:if>><i class="icon-file-alt"></i>Files</a></li>
		<li><a href="<%=basePath %>provisioning/provisioningIndex" <c:if test="${domain=='provisioning'}"> class="open" </c:if>><i class="icon-file-alt"></i>Account</a></li>
		<c:if test="${sessionScope.USER.manager==true}">
			<li><a href="#" <c:if test="${domain=='user'}"> class="open" </c:if>><i class="icon-list-alt"></i>User<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
				<ul>
					<li><a href="<%=basePath %>user/getAllUser">User Manager</a></li>
					<li><a href="<%=basePath %>oper/operIndex">Oper Manager</a></li>
				</ul>
			</li>
		</c:if>
	</ul>
</div>