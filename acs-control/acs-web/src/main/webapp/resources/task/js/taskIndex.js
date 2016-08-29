$(document).ready(function() {
	reflushTasksList();
	$('#type_select').on("changed.bs.select",function(e){
		var type = $(this).val();
		if(type=="setParameterValues"){
			$('#configurations_text').html(type);
			$('#configurations_main').show();
			$('#configurations_add').show();
			$('#configurations_context').empty();
			appendParameterValues();
			//$('#configurations_context').append();
		}else if(type=="addObject"){
			$('#configurations_text').html(type);
			$('#configurations_main').show();
			$('#configurations_add').hide();
			$('#configurations_context').empty();
			appendAddObject();
		}else{
			$('#configurations_main').hide();
		}
	});
	$('#modules_select').on("changed.bs.select",function(e){
		
	});
	$('#configurations_add').on("click",function(){
		configuration_sub = 0;
		var type = $('#configurations_text').html();
		if(type=="setParameterValues"){
			appendParameterValues();
		}else if(type=="addObject"){
			appendAddObject();
		}
	});
});
var configuration_sub = 0;
function appendParameterValues(){
//	$.post(basePath+"modules/findParameterByModules",function(text){
	$.post(basePath+"device/getDeviceById",{id:'202BC1-BM632w-0000000'},function(text){
		var datas = $.parseJSON(text);
    	var data = null;
    	if(datas.length>0)data=datas[0];
		var parameterList = new Array();
		listJSON(data,parameterList);
		//console.log(parameterList);
		var parameter_html = '<div class="form-group">'
							+'		<label class="col-lg-3 control-label"><strong>Set:</strong></label>'
							+'	 	<input type="hidden" value="Set" name="configurations['+configuration_sub+'].type"/>'
							+'		<div class="col-lg-9">'
							+'			<select name="configurations['+configuration_sub+'].name" class="selectpicker inline-select" data-live-search="true" data-size="5">';
		for(var i=0;i<parameterList.length;i++){
			parameter_html= parameter_html	+ '<option value="'+parameterList[i].key+'">'+parameterList[i].key+'</option>'
		}
		parameter_html= parameter_html	+ '<option value="e">=</option>'
		parameter_html= parameter_html	+'</select>'
							+'			<strong class="to-group">to</strong>'
							+'			<input name="configurations['+configuration_sub+'].value" type="text" style="width: 45%;" class="form-control">'
							+'			<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>'
							+'		</div>'
							+'	</div>';
		$('#configurations_context').append(parameter_html);
		configuration_sub++;
		activateDeleteButton();
	});
}
function listJSON(json,parameterList,ukey){
    for(var key in json){
    	if(key!="_id"&&key!="_tags"&&key!="_type"&&key!="_writable"&&key!="_timestamp"&&key!="_object"){
    		var skey = "";
        	if(typeof(json[key])=="object"){
        		if(ukey==null||ukey=="")listJSON(json[key],parameterList,key);
        		else listJSON(json[key],parameterList,ukey + "."+key);
        	}else{
        		if(ukey==null||ukey=="") skey = key;
        		else skey = ukey+"."+key;
        		var realKey = skey;
        		if(key=="_value"){
        			skey = skey.replace("._value","").replace("_value","");
        		}
    			parameterList.push(createParameter(skey,json[key],realKey));
        	}
    	}
    }  
}
function createParameter(key,value,realKey) {
    var parameter = new Object();
    parameter.key = key;
    parameter.value = value;
    parameter.realKey = realKey;
    parameter.getKey = function () {
        return this.key;
    }
    return parameter;
}
function appendAddObject(){
	var object_html = '<div class="form-group">'
					+'			<label class="col-lg-3 control-label"><strong>Add object:</strong></label>'
					+'	 		<input type="hidden" value="Add object" name="configurations[0].type"/>'
					+'			<div class="col-lg-9">'
					+'				<input type="text" name="configurations[0].name" class="form-control">'
					+'			</div>'
					+'		</div>';
	$('#configurations_context').append(object_html);
	configuration_sub++;
	activateDeleteButton();
}
function reflushTasksList(currentPage,queryColumn,queryValue){
	$.post(basePath+"task/getAllTasksPage",{"currentPage":currentPage,"queryColumn":queryColumn,"queryValue":queryValue},function(text){
		var data = $.parseJSON(text);
		$('#tasks_tbody').empty();
		var jsonResults = $.parseJSON(data.jsonResults);
		for(var i=0;i<jsonResults.length;i++){
			var task_html = "<tr>"
							  +"	<td>"+(i+1)+"</td>"
							  +"	<td>"+jsonResults[i].device+"</td>"
							  +"	<td>"+jsonResults[i].name+"</td>"
							  +"	<td>"+jsonResults[i].timestamp.replace("T"," ").replace("Z","")+"</td>"
			if(jsonResults[i].retries!=undefined)	task_html = task_html	+"	<td>"+jsonResults[i].retries+"</td>";
			else task_html = task_html	+"	<td>0</td>";
			if(jsonResults[i].fault!=undefined)	{
				task_html = task_html	+"	<td><p><span class='font_blue'>FaultCode</span>:<span class='font_red'>"+jsonResults[i].fault.detail.Fault.FaultCode+"</span></p><p><span class='font_blue'>FaultString</span>:<span class='font_red'>"+jsonResults[i].fault.detail.Fault.FaultString+"</span></p></td>";
			}else task_html=task_html			  +"	<td></td>";
			task_html=task_html+"	<td>"
							  +"		<button class='btn btn-xs btn-warning' onclick=\"retryTasksShow('"+jsonResults[i]._id+"','"+jsonResults[i].name+"')\"><i class='icon-refresh'></i> </button>"
							  +"		<button class='btn btn-xs btn-danger' onclick=\"removeTasksShow('"+jsonResults[i]._id+"','"+jsonResults[i].name+"')\"><i class='icon-remove'></i> </button>"
							  +"	</td>"
							  +"</tr>";
			$('#tasks_tbody').append(task_html);
		}
		createPageList(data.currentPage,data.pageCount);
	});
}
function serarchTasks(){
	reflushTasksList(1,$('#queryColumn').val(),$('#task_search').val());
}
function createPageList(currentPage,pageCount){
	$('#task_page').empty();
	var page_html = '';
	if(currentPage<=1){
		page_html = '<li><a>Prev</a></li>';
	}else{
		page_html = '<li><a onclick="reflushTasksList('+(currentPage-1)+')" href="#">Prev</a></li>';
	}
	
	for(var j=1;j<=pageCount;j++){
		var clz = "";
		if(j==currentPage){
			page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
		}else{
			page_html = page_html + "<li><a onclick='reflushTasksList("+j+")' href='#'>"+j+"</a></li>";
		}
	}
	if(currentPage==pageCount){
		page_html = page_html + "<li><a>Next</a></li>";
	}else{
		page_html = page_html + "<li><a onclick='reflushTasksList("+(currentPage+1)+")' href='#'>Next</a></li>";
	}
	if(pageCount>0)$('#task_page').append(page_html);
}
function reflushModulesList(){
	$.post(basePath+"modules/getAllModules",function(text){
		$('#moduleNamediv').empty();
		var data = $.parseJSON(text);
		var modules_option ='<select class="selectpicker" name="moduleName" id="moduleName">'
		for(var i=0;i<data.length;i++){
			modules_option = modules_option + '<option value="'+data[i].name+'">'+data[i].name+'</option>';
		}
		modules_option = modules_option+'</select>';
		modules_option = modules_option+ '<button type="button" onclick="selectDeviceByModule();return false;" class="btn btn-link">Select Device</button>';
		$('#moduleNamediv').append(modules_option);
		$('#moduleName').selectpicker();
	});
}
function addTask(){
	reflushModulesList();
	$('#device_row').hide();
	$('#device_list').empty();
	$('#configurations_context').empty();
	appendParameterValues();
	configuration_sub = 0;
	$('#taskModal').modal('show');
}
function submitAddUpdataModules(){
	var msg = "";
	var modulesName = $('#modules_name').val();
	var productClass = $('#product_class').val();
	var oui = $('#oui').val();
	var manufacturer = $('#manufacturer').val();
	var description = $('#description').val();
	$('#modules_modal_form div').each(function(){
		$(this).removeClass('has-error');
	});
	if(stringTrim(modulesName)==""||modulesName==null){
		msg = msg + "&" +modules_name_notempty;
		$('#modules_name_group').addClass('has-error');
	}
	if(stringTrim(productClass)==""||productClass==null){
		msg = msg + "&" + modules_productClass_notempty;
		$('#product_class_group').addClass('has-error');
	}
	if(stringTrim(oui)==""||oui==null){
		msg = msg + "&" + modules_oui_notempty;
		$('#oui_group').addClass('has-error');
	}
	if(msg!=""){
		msg = msg.substr(1);
		alert(msg);
		return false;
	}else{
		$.post(basePath+"modules/addModules", { name: modulesName, productClass: productClass, oui: oui , manufacturer: manufacturer, description: description},function(text){
			reflushPresetsList();
			$('#modulesModal').modal('hide');
		});
	}
}
function stringTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function removeTasksShow(sid,name){
	$("#deleteTasksId").val(sid);
	$("#deleteTasksName").html(name);
	$("#deleteTasksModal").modal('show');
}
function submitRemoveTasks(){
	$("#deleteTasksModal").modal('hide');
	$.post(basePath+"task/destroyTaskById", { taskId: $("#deleteTasksId").val()},function(text){
		if(text=="success"){
			reflushTasksList();
		}
	});
}
function activateDeleteButton(){
	$('.deleteClick').on('click',deleteFatherGroup);
	$('.selectpicker').selectpicker();
}
function deleteFatherGroup(){
	$(this).parent().parent().remove();
}
function submitAddTasks(){
	$('#task_modal_form').ajaxSubmit(function(text){
		var data = $.parseJSON(text);
		var msg = "";
		for(var key in data){
			msg = msg + key+":"+data[key];
		}
		alert(msg);
		$('#taskModal').modal('hide');
		reflushTasksList();
	});
}
function selectDeviceByModule(){
	//$.post(basePath+"modules/findDeviceByModules", { modulesName: $('#moduleName').selectpicker('val')},function(text){
	$.post(basePath+"device/getAllDevice", function(text){
		var data = $.parseJSON(text);
		$('#deviceList_tbody').empty();
		for(var i=0;i<data.length;i++){
			var device_html = "<tr>"
						     +"  	<td><input type='checkbox' id='device_"+data[i].id+"' name='deviceSelectList_"+i+"' value='"+data[i].id+"'/></td>"
						     +"		<td>"+data[i].summary.serialNumber+"</td>"
						     +"		<td>"+data[i].summary.productClass+"</td>"
						     +"		<td>"+data[i].summary.oui+"</td>"
						     +"		<td>"+data[i].summary.softwareVersion+"</td>"
						     +"		<td>"+data[i].summary.mac+"</td>"
						     +"		<td>"+data[i].summary.ip+"</td>"
						     +"		<td>"+data[i].summary.wlanSsid+"</td>"
						     +"	</tr>";
			$('#deviceList_tbody').append(device_html);
		}
		$("input[name^='deviceList']").each(function(){
			$('#device_'+$(this).val()).prop("checked", true);
		});
		if($("input[name^='deviceList']").length!=0&&$("input[name^='deviceList']").length==data.length){
			$('#selectAll').prop("checked", true);
		}else{
			$('#selectAll').prop("checked", false);
		}
	});
	
	$('#deviceListModal').modal('show');
}
function selectAllDevice(){
	var isselect = $('#selectAll').is(':checked');
	if(isselect){
		$("input[name^='deviceSelectList']").prop("checked", true);
	}else{
		$("input[name^='deviceSelectList']").prop("checked", false);
	}
}
function submitSelectDeviceList(){
	var index = 0;
	$('#device_list').empty();
	$("input[name^='deviceSelectList']").each(function(){
		if($(this).is(':checked')){
			var device_html = '<a><input type="hidden" name="deviceList['+index+']" value="'+$(this).val()+'"><span>'+$(this).val()+'</span><em></em></a>';
			$('#device_list').append(device_html);
			index++;
		}
	});
	$('#deviceListModal').modal('hide');
	if(index!=0)$('#device_row').show();
	else $('#device_row').hide();
	$('#device_list em').on('click',removeDevice);
}
function removeDevice(){
	$(this).parent().remove();
	if($("input[name^='deviceList']").length==0){
		$('#device_row').hide();
	}
}
function retryTask(id){
	$.post(basePath+"task/retryTask",{id:id},function(text){
		alert(text);
	});
}
function retryTasksShow(id,name){
	$("#retryTasksId").val(id);
	$("#retryTasksName").html(name);
	$("#retryTasksModal").modal('show');
}
function submitRetryTasks(){
	$.post(basePath+"task/retryTask", { id: $("#retryTasksId").val()},function(text){
		if(text=="success"){
			alert(text);
			$("#retryTasksModal").modal('hide');
			reflushTasksList();
			
		}else{
			alert(text);
		}
	});
}