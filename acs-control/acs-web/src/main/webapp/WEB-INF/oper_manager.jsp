<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.acs.utils.model.User" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
		<jsp:include page="head.jsp"></jsp:include>
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
					<h2 class="pull-left"><i class="icon-list-alt"></i> Oper Manager</h2>
					<!-- Breadcrumb -->
					<div class="bread-crumb pull-right">
						<a href="index.html"><i class="icon-home"></i>Home</a>
						<!-- Divider -->
						<span class="divider">/</span>
						<a href="#" class="bread-current">Oper Manager</a>
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
									<a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="showOperModal();return false;"><span class="label label-danger"><i class="icon-plus"></i></span> Add Oper</a>
								</li>
							</ul>
							<!-- Search form -->
							<div class="form-group">
								<form role="search" class="navbar-form navbar-right">
									<input type="text" placeholder="Search" class="form-control" id="oper_search" style="width: 200px;">
									<span class="am-search-span">
										<input type="button" value="Search" class="am-search-span-btn" onclick="serarchOper()"/>
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
											<th>OperName</th>
											<th>Modules List</th>
											<th>Domains</th>
											<th>Control</th>
										</tr>
									</thead>
									<tbody id="oper_tbody">
									</tbody>
								</table>
								<div class="widget-foot">
									<ul class="pagination pull-right" id="oper_page">
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
        <div id="operModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                 <h4 class="modal-title">New Oper</h4>
             </div>
             <div class="modal-body">
             	<form class="form-horizontal" role="form" id="oper_modal_form" method="post" action="<%=basePath %>oper/addOper">
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Oper Name</label>
                          <div class="col-lg-8">
                            <input type="hidden" id="oper_sid">
                            <input type="text" class="form-control" id="oper_name" name="name" placeholder="Input Box">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Domains</label>
                          <div class="col-lg-8">
                            <input type="text" data-toggle="tooltip" title="Please use a comma to separate!" class="form-control" id="domains" name="name" placeholder="Input Box">
                          </div>
<!--                           <div class="col-lg-1"> -->
<!--                             <span title="" data-content="More details." data-placement="auto left" data-trigger="hover" data-rel="popover" class="help-button">?</span> -->
<!--                           </div> -->
                        </div>
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Modules List</label>
                          <div class="col-lg-8">
                          	<select class="selectpicker" name="modulesList" id="modulesListInfo" multiple data-live-search="true" data-actions-box="true">
                            </select>
                          </div>
                        </div>
                    </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-default" data-dismiss="modal" id="close_from" aria-hidden="true">Close</button>
                 <button type="button" class="btn btn-primary" onclick="submitAddUpdataOper()">Save</button>
             </div>
		</div>
	<jsp:include page="foot.jsp"></jsp:include>
	<script src="<%=basePath %>resources/js/jquery.form.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			refreshOperList(1);
			//$('.help-button').popover();
   		 	$('#domains').tooltip();
		});
		function refreshOperList(currentPage,queryValue){
			$.ajax({
		         type:"POST",
		         url:"<%=basePath %>oper/getAllOperAndGroup",
		         data:{"currentPage":currentPage,"queryValue":queryValue},
		         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
		         success:function(json){
		        	 var data = $.parseJSON(json);
		        	 $('#oper_tbody').empty();
		        	 var jsonResults = data.results;
		        	 for(var i=0;i<jsonResults.length;i++){
		        		 var html = "<tr>"
									+"	<td>"+(i+1)+"</td>"
									+"	<td>"+jsonResults[i].name+"</td>"
									+"	<td>";
									for(var j=0;j<jsonResults[i].deviceGroupList.length;j++){
										html = html	+"		<span class='label label-success'>"+jsonResults[i].deviceGroupList[j].name+"</span>";
									}
							html = html	+"	</td>";
							html = html	+"<td>";
									var domainList = null;
									if(jsonResults[i].domains!=null){
										domainList = jsonResults[i].domains.split(",");
									}
									if(domainList!=null){
										for(j=0;j<domainList.length;j++){
											html = html	+"		<span class='label label-info'>"+domainList[j]+"</span>";
										}
									}
							html = html	+"	</td>"
									+"	<td>"
									+"		<button class='btn btn-xs btn-success' onclick='editOper(\""+jsonResults[i].sid+"\")'><i class='icon-edit'></i> </button>"
									+"		<button class='btn btn-xs btn-danger' onclick='removeOper(\""+jsonResults[i].sid+"\",\""+jsonResults[i].name+"\")'><i class='icon-remove'></i> </button>"
									+"	</td>"
									+"</tr>";
		        		 
		        		 $('#oper_tbody').append(html);
		        	 }
		        	 createPageList(data.currentPage,data.pageCount);
		         },
		         error: function(){
		      		alert(msg_unsuccess);
		         }
		      });
		}
		function serarchOper(){
			refreshOperList(1,$('#oper_search').val());
		}
		function createPageList(currentPage,pageCount){
			$('#oper_page').empty();
			var page_html = '';
			
			if(currentPage<=1){
				page_html = '<li><a>Prev</a></li>';
			}else{
				page_html = '<li><a onclick="refreshOperList('+(currentPage-1)+')" href="#">Prev</a></li>';
			}
			
			for(var j=1;j<=pageCount;j++){
				var clz = "";
				if(j==currentPage){
					page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
				}else{
					page_html = page_html + "<li><a onclick='refreshOperList("+j+")' href='#'>"+j+"</a></li>";
				}
			}
			if(currentPage==pageCount){
				page_html = page_html + "<li><a>Next</a></li>";
			}else{
				page_html = page_html + "<li><a onclick='refreshOperList("+(currentPage+1)+")' href='#'>Next</a></li>";
			}
			if(pageCount>0)$('#oper_page').append(page_html);
		}
		function showOperModal(){
			$('#oper_sid').val("");
			$('#oper_name').val("");
			$.post(basePath+"modules/getAllModules",function(text){
	   		 	var data = $.parseJSON(text);
	   			$('#modulesListInfo').empty();
	   		 	for(var i=0;i<data.length;i++){
        		 	var html = "<option value='"+data[i].sid+"'>"+data[i].name+"</option>";
        		 	$('#modulesListInfo').append(html);
        	 	}
   		 		$('#operModal').modal('show');
   		 		$('#modulesListInfo').selectpicker('refresh');
   		 	});
		}
		function submitAddUpdataOper(){
			var msg = "";
			if($('#oper_name').val()==null||$('#oper_name').val()==""){
				msg = ",name is not empty;";
			}
			if($('#domains').val()==null||$('#domains').val()==""){
				msg = msg + "," +"domains is not empty;" ;
			}
			if($('#modulesListInfo').selectpicker('val')==null){
				msg = msg + "," +"modules is not empty;" ;
			}
			if(msg==""){
				$.post(basePath+"oper/addOper",{sid:$('#oper_sid').val(),name:$('#oper_name').val(),domains:$('#domains').val(),modulesList:$('#modulesListInfo').selectpicker('val').join(",")},function(text){
					if(text=="success"){
						$('#operModal').modal('hide');
						refreshOperList();
					}else{
		        		 alert("Add Operation Error!");
		        	 }
				});
			}else alert(msg.substr(1));
		}
		function removeOper(sid,name){
			 if(confirm(oper_delete_info+" "+name)){
				 $.ajax({
			         type:"POST",
			         url:"<%=basePath %>oper/deleteOper",
			         data:{sid : sid},
			         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
			         success:function(json){
			        	 if(json=="success"){
			        		 refreshOperList();
			        	 }else{
			        		 alert("delete Operation Error!");
			        	 }
			        	 
			         },
			         error: function(){
			      		alert(msg_unsuccess);
			         }         
			      });
			 }
		}
		function editOper(sid){
			$.post(basePath+"oper/findOperById",{sid:sid},function(text){
				//alert(text);
				var operdata = $.parseJSON(text)[0];
				$('#oper_sid').val(operdata.sid);
				$('#oper_name').val(operdata.name);
				$('#domains').val(operdata.domains);
				$.post(basePath+"modules/getAllModules",function(text){
		   		 	var data = $.parseJSON(text);
		   			$('#modulesListInfo').empty();
		   		 	for(var i=0;i<data.length;i++){
	        		 	var html = "<option value='"+data[i].sid+"'>"+data[i].name+"</option>";
	        		 	$('#modulesListInfo').append(html);
	        	 	}
	   		 		$('#operModal').modal('show');
	   		 		$('#modulesListInfo').selectpicker('refresh');
	   		 		
		   		 	var valList = new Array();
					for(var i=0;i<operdata.deviceGroupList.length;i++){
						valList.push(operdata.deviceGroupList[i].sid);
					}
					$('#modulesListInfo').selectpicker('val', valList);
					$('#operModal').modal('show');
	   		 	});
			});
		}
	</script>