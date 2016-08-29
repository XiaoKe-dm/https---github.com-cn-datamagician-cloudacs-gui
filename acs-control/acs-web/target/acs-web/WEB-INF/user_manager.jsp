<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<jsp:include page="head.jsp"></jsp:include>
		<jsp:include page="base/pre_deined.jsp"></jsp:include>
		<link href="<%=basePath %>resources/css/base.css" rel="stylesheet">

		<!-- Main content starts -->
		<div class="content">
			<!-- Sidebar -->
			<jsp:include page="sidebar.jsp"></jsp:include>
			<!-- Sidebar ends -->

			<!-- Main bar -->
			<div class="mainbar">

				<!-- Page heading -->
				<div class="page-head">
					<h2 class="pull-left"><i class="icon-list-alt"></i> User Manager</h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Home</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">User Manager</a>
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
									<a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="showModal();return false;"><span class="label label-danger"><i class="icon-plus"></i></span> Add User</a>
									<a data-toggle="modal" id="addModal" class="dropdown-toggle" href="#myModal" style="display:none;"></a>
								</li>
							</ul>
							<!-- Search form -->
							<div class="form-group">
								<form role="search" class="navbar-form navbar-right">
									<input type="text" placeholder="Search" class="form-control" id="user_search" style="width: 200px;">
									<span class="am-search-span">
										<input type="button" value="Search" class="am-search-span-btn" onclick="serarchUser()"/>
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
											<th>Serial no.</th>
											<th>UserName</th>
											<th>Oper</th>
											<th>Manager</th>
											<th>Control</th>
										</tr>
									</thead>
									<tbody id="user_tbody">
										<c:forEach var="item" items="${userList}" varStatus="status">
					                        <tr>
												<td>${status.index+1}</td>
												<td>${item.username}</td>
												<td>
													<c:if test="${empty item.oper}"><span class="label label-success">Manager</span></c:if>
													<c:if test="${!empty item.oper}"><span class="label label-danger">${item.oper}</span></c:if>
												</td>
												<td>
													<c:if test="${item.manager == true}"><span class="label label-success">Manager</span></c:if>
													<c:if test="${item.manager != true}"><span class="label label-danger">Oper</span></c:if>
												</td>
												<td>
													<button class="btn btn-xs btn-success" onclick="editUser('${item.sid}')"><i class="icon-edit"></i> </button>
													<button class="btn btn-xs btn-warning" onclick="refreshUser('${item.sid}')"><i class="icon-refresh"></i> </button>
													<button class="btn btn-xs btn-danger" onclick="removeUser('${item.sid}','${item.username}')"><i class="icon-remove"></i> </button>
												</td>
											</tr>
					                    </c:forEach>
									</tbody>
								</table>

								<div class="widget-foot">

									<ul class="pagination pull-right" id="user_page">
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
        <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">New User</h4>
                    </div>
                    <div class="modal-body">
                    	<form class="form-horizontal" role="form">
                               <div class="form-group">
                                 <label class="col-lg-4 control-label">Username</label>
                                 <div class="col-lg-8">
                                   <input type="hidden" id="user_sid">
                                   <input type="text" class="form-control" id="username" name="username" placeholder="Input Box">
                                 </div>
                               </div>
                               <div class="form-group">
                                 <label class="col-lg-4 control-label">Role</label>
                                 <div class="col-lg-8">
                                 	<div class="radio">
                                     <label>
                                       <input type="radio" name="manager" onclick="findAllOper()" value="0">
                                       Oper
                                     </label>
                                   </div>
                                   <div class="radio">
                                     <label>
                                       <input type="radio" name="manager" checked="checked" onclick="$('#operGroup').hide();" value="1" >
                                       Manager
                                     </label>
                                   </div>
                                 </div>
                               </div>
                               <div class="form-group" id="operGroup" style="display:none;">
                                 <label class="col-lg-4 control-label">Oper</label>
                                 <div class="col-lg-8">
                                   <select class="form-control" name="oper" id="operGroupInfo">
                                   </select>
                                 </div>
                               </div>
                           </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitAddUpdataUser()">Save</button>
                        
                    </div>
		</div>
	<jsp:include page="foot.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			findAllUser(1);
		});
		function showModal(){
			$('#user_sid').val('0');
			$('#username').val("");
			$("input[name='manager'][value='1']").prop("checked",'checked');
   		 	$('#operGroup').hide();
   		 	$('#addModal').trigger("click");
		}
		function findAllUser(currentPage,queryValue){
			$.ajax({
		         type:"POST",
		         url:"<%=basePath %>user/getAllUserPageJson",
		         data:{"currentPage":currentPage,"queryValue":queryValue},
		         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
		         success:function(json){
		        	 var data = $.parseJSON(json);
		        	 $('#user_tbody').empty();
		        	 var jsonResults = data.results;
		        	 for(var i=0;i<jsonResults.length;i++){
		        		 var manager = 'Manager';
		        		 var role = 'Manager';
		        		 var manager_classs = 'label-success';
		        		 if(!jsonResults[i].manager){
		        			 manager = jsonResults[i].oper;
		        			 manager_classs = 'label-danger';
		        			 role = 'Oper';
		        		 }
		        		 var html = "<tr>"
									+"	<td>"+(i+1)+"</td>"
									+"	<td>"+jsonResults[i].username+"</td>"
									+"	<td>"
									+"		<span class='label "+manager_classs+"'>"+manager+"</span>"
									+"	</td>"
									+"	<td>"
									+"		<span class='label "+manager_classs+"'>"+role+"</span>"
									+"	</td>"
									+"	<td>"
									+"		<button class='btn btn-xs btn-success' onclick='editUser(\""+jsonResults[i].sid+"\")'><i class='icon-edit'></i> </button>"
									+"      <button class='btn btn-xs btn-warning' onclick='refreshUser(\""+jsonResults[i].sid+"\")'><i class='icon-refresh'></i> </button>"
									+"		<button class='btn btn-xs btn-danger' onclick='removeUser(\""+jsonResults[i].sid+"\",\""+jsonResults[i].username+"\")'><i class='icon-remove'></i> </button>"
									+"	</td>"
									+"</tr>";
		        		 
		        		 $('#user_tbody').append(html);
		        	 }
		        	 createPageList(data.currentPage,data.pageCount);
		         },
		         error: function(){
		      		alert(msg_unsuccess);
		         }
		      });
		}
		function serarchUser(){
			findAllUser(1,$('#user_search').val());
		}
		function createPageList(currentPage,pageCount){
			$('#user_page').empty();
			var page_html = '';
			
			if(currentPage<=1){
				page_html = '<li><a>Prev</a></li>';
			}else{
				page_html = '<li><a onclick="findAllUser('+(currentPage-1)+')" href="#">Prev</a></li>';
			}
			
			for(var j=1;j<=pageCount;j++){
				var clz = "";
				if(j==currentPage){
					page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
				}else{
					page_html = page_html + "<li><a onclick='findAllUser("+j+")' href='#'>"+j+"</a></li>";
				}
			}
			if(currentPage==pageCount){
				page_html = page_html + "<li><a>Next</a></li>";
			}else{
				page_html = page_html + "<li><a onclick='findAllUser("+(currentPage+1)+")' href='#'>Next</a></li>";
			}
			if(pageCount>0)$('#user_page').append(page_html);
		}
		function findAllOper(){
			$.ajax({
		         type:"POST",
		         url:"<%=basePath %>oper/getAllOper",
		         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
		         success:function(json){
		        	 var data = $.parseJSON(json);
		        	 $('#operGroupInfo').empty();
		        	 for(var i=0;i<data.length;i++){
		        		 var html = "<option value='"+data[i].sid+"'>"+data[i].name+"</option>";
		        		 $('#operGroupInfo').append(html);
		        	 }
		        	 $('#operGroup').show();
		         },
		         error: function(){
		      		alert("unsuccess");
		         }         
		      });
		}
		function submitAddUpdataUser(){
			$.ajax({
		         type:"POST",
		         url:"<%=basePath %>user/addUser",
		         data:{sid : $('#user_sid').val(),username : $('#username').val(),manager:$("input[name='manager']:checked").val(),oper:$('#operGroupInfo').val()},
		         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
		         success:function(json){
		        	 $('#close_from').trigger("click"); 
		        	 //alert(json);
		        	 findAllUser();
		        	 $('#user_sid').val(0);
		         },
		         error: function(){
		      		alert(msg_unsuccess);
		         }         
		      });
		}
		function editUser(sid){
			$.ajax({
		         type:"POST",
		         url:"<%=basePath %>user/updateUserBySid",
		         data:{sid : sid},
		         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
		         success:function(json){
		        	 var data = $.parseJSON(json);
		        	 var msg = data.msg;
		        	 var operList = data.operList;
		        	 if(msg=="success"){
		        		 var user = data.user;
			        	 var operList = data.operList;
			        	 $('#user_sid').val(user.sid);
			        	 $('#username').val(user.username);
			        	 if(user.manager){
			        		 $("input[name='manager'][value='1']").prop("checked",'checked');
			        		 $('#operGroup').hide();
			        	 }else{
			        		 $("input[name='manager'][value='0']").prop("checked",'checked');
			        		 $('#operGroupInfo').empty();
				        	 for(var i=0;i<operList.length;i++){
				        		 var selected = "";
				        		 if(operList[i].name==user.oper){
				        			 selected = "selected=selected"
				        		 }
				        		 var html = "<option value='"+operList[i].sid+"' "+selected+">"+operList[i].name+"</option>";
				        		 $('#operGroupInfo').append(html);
				        	 }
				        	 $('#operGroup').show();
			        	 }
			        	 $('#addModal').trigger("click");
		        	 }else{
		        		 alert(user_edit_error_info);
		        		 findAllUser();
		        	 }
		        	 
		         },
		         error: function(){
		      		alert(msg_unsuccess);
		         }         
		      });
		}
		function refreshUser(sid){
			if(confirm(user_sure_reset_password)){
				 $.ajax({
			         type:"POST",
			         url:"<%=basePath %>user/refreshUser",
			         data:{sid : sid},
			         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
			         success:function(json){
			        	 var data = $.parseJSON(json);
			        	 var msg = data.msg;
			        	 if(msg=="success"){
			        		 alert(user_reset_password_success);
			        	 }
			         },
			         error: function(){
			      		alert(msg_unsuccess);
			         }         
			      });
			 }
		}
		function removeUser(sid,username){
			 if(confirm(oper_delete_info+" "+username)){
				 $.ajax({
			         type:"POST",
			         url:"<%=basePath %>user/deleteUser",
			         data:{sid : sid},
			         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
			         success:function(json){
			        	 if(json=="success"){
			        		 findAllUser();
			        	 }
			         },
			         error: function(){
			      		alert(msg_unsuccess);
			         }         
			      });
			 }
		}
	</script>