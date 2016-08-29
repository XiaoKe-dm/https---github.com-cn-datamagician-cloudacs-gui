$(document).ready(function() {
	reflushProvisioningList(1);
	$('#provisioning_mac').tooltip();
});
function reflushProvisioningList(currentPage,queryColumn,queryValue){
	$.post(basePath+"provisioning/getProvisioningPage",{"currentPage":currentPage,"queryColumn":queryColumn,"queryValue":queryValue},function(text){
		var data = $.parseJSON(text);
		$('#provisioning_tbody').empty();
		var jsonResults = $.parseJSON(data.jsonResults);
		for(var i=0;i<jsonResults.length;i++){
			var modules_html = "<tr>"
							  +"	<td>"+jsonResults[i]._id+"</td>"
							  +"	<td>"+jsonResults[i].mac+"</td>"
							  +"	<td>"+jsonResults[i].username+"</td>"
							  +"	<td>"+jsonResults[i].pwd+"</td>"
//							  +"	<td>"+data[i].description+"</td>"
							  +"	<td>"
							  +"        <button class='btn btn-xs btn-success' onclick=\"editProvisioning('"+jsonResults[i]._id+"')\"><i class='icon-edit'></i> </button>"
							  +"		<button class='btn btn-xs btn-danger' onclick=\"removeProvisioningShow('"+jsonResults[i]._id+"')\"><i class='icon-remove'></i> </button>"
							  +"	</td>"
							  +"</tr>";
			$('#provisioning_tbody').append(modules_html);
		}
		createPageList(data.currentPage,data.pageCount);
	});
}
function serarchProvisioning(){
	reflushProvisioningList(1,$('#queryColumn').val(),$('#provisioning_search').val());
}
function createPageList(currentPage,pageCount){
	$('#provisioning_page').empty();
	var page_html = '';
	if(currentPage<=1){
		page_html = '<li><a>Prev</a></li>';
	}else{
		page_html = '<li><a onclick="reflushProvisioningList('+(currentPage-1)+')" href="#">Prev</a></li>';
	}
	
	for(var j=1;j<=pageCount;j++){
		var clz = "";
		if(j==currentPage){
			page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
		}else{
			page_html = page_html + "<li><a onclick='reflushProvisioningList("+j+")' href='#'>"+j+"</a></li>";
		}
	}
	if(currentPage==pageCount){
		page_html = page_html + "<li><a>Next</a></li>";
	}else{
		page_html = page_html + "<li><a onclick='reflushProvisioningList("+(currentPage+1)+")' href='#'>Next</a></li>";
	}
	if(pageCount>0)$('#provisioning_page').append(page_html);
}
function addProvisioning(){
	//$('#provisioning_name').html(ran(13));
	$('#provisioning_mac').val("");
	$('#provisioning_mac').removeAttr("readonly");
	$('#provisioning_username').val("");
	$('#provisioning_password').val("");
	
	$('#oper_sid').val("");
	$('#oper_name').val("");
	$.post(basePath+"oper/getDomainByUser",function(text){
	 	var data = $.parseJSON(text);
	 	$('#domainsList').empty();
	 	for(var i=0;i<data.length;i++){
	 		if(data[i]!=""&&data[i]!=null){
	 			var html = "<option value='"+data[i]+"'>"+data[i]+"</option>";
			 	$('#domainsList').append(html);
	 		}
	 	}
 		$('#provisioningModal').modal('show');
 		$('#domainsList').selectpicker('refresh');
	 });
	
}
function ran(m){
	m = m > 13 ? 13 : m;
	var num = new Date().getTime();
	return num.toString().substring(13 - m);
}
function submitAddUpdataProvisioning(){
	var msg = "";
	//var name = $('#provisioning_name').html();
	var mac = $('#provisioning_mac').val();
	var username = $('#provisioning_username').val();
	var password = $('#provisioning_password').val();
	var domain = $('#domainsList').selectpicker('val');
	$('#provisioning_modal_form div').each(function(){
		$(this).removeClass('has-error');
	});
	
	if(stringTrim(mac)==""||mac==null){
		msg = msg + "&" + modules_productClass_notempty;
		$('#provisioning_mac').parent().parent().addClass('has-error');
	}
	if(stringTrim(username)==""||username==null){
		msg = msg + "&" + modules_oui_notempty;
		$('#provisioning_username').parent().parent().addClass('has-error');
	}
	if(stringTrim(password)==""||password==null){
		msg = msg + "&" + modules_oui_notempty;
		$('#provisioning_password').parent().parent().addClass('has-error');
	}
	if(domain==null){
		msg = msg + "&" +"domains is not empty;" ;
	}
	if(msg!=""){
		msg = msg.substr(1);
		alert(msg);
		return false;
	}else{
		$.post(basePath+"provisioning/addProvisioning", { mac: mac, domain:domain,username: username , password: password},function(text){
			reflushProvisioningList(1);
			$('#provisioningModal').modal('hide');
		});
	}
}
function stringTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function removeProvisioningShow(name){
	$("#deleteProvisioningName").html(name);
	$("#deleteProvisioningModal").modal('show');
}
function submitRemoveProvisioning(){
	$("#deleteProvisioningModal").modal('hide');
	$.post(basePath+"provisioning/removeProvisioningById", { name: $("#deleteProvisioningName").html()},function(text){
		reflushProvisioningList(1);
	});
}
function editProvisioning(name){
	$.ajax({
        type:"POST",
        url:basePath+"oper/getDomainByUser",
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        success:function(text){
        	var data = $.parseJSON(text);
    	 	$('#domainsList').empty();
    	 	for(var i=0;i<data.length;i++){
    	 		if(data[i]!=""&&data[i]!=null){
    	 			var html = "<option value='"+data[i]+"'>"+data[i]+"</option>";
    			 	$('#domainsList').append(html);
    	 		}
    	 	}
     		$('#domainsList').selectpicker('refresh');
     		$.ajax({
     	        type:"POST",
     	        url:basePath+"provisioning/findProvisioningById",
     	        data:{"name":name},
     	        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
     	        success:function(json){
     	        	var datas = $.parseJSON(json);
     	        	if(datas.length>0){
     	        		var obj = datas[0];
         	        	$('#provisioning_mac').val(obj.mac);
         	        	$('#provisioning_mac').attr("readonly","readonly");
         	        	var usernameStr = obj.username.split("@");
         	        	if(usernameStr!=null&&usernameStr!=undefined)$('#provisioning_username').val(usernameStr[0]);
         	        	$('#provisioning_password').val(obj.pwd);
         	        	if(usernameStr!=null&&usernameStr!=undefined&&usernameStr.length>1)$('#domainsList').selectpicker('val',usernameStr[1]);
         	        	$('#provisioningModal').modal('show');
     	        	}
     	        },
     	        error: function(){
     	     		alert(msg_unsuccess);
     	        }
     	     });
        },
        error: function(){
     		alert(msg_unsuccess);
        }
     });
	
}