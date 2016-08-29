$(document).ready(function() {
	reflushPresetsList(1);
	$('#addPrecondition li>a').on('click',addPrecondition);
	$('#addConfigurations li>a').on('click',addConfigurations);
});
function reflushPresetsList(currentPage,queryColumn,queryValue){
	$.post(basePath+"presets/getPresetsPage",{"currentPage":currentPage,"queryColumn":queryColumn,"queryValue":queryValue},function(text){
		var data = $.parseJSON(text);
		$('#presets_tbody').empty();
		var jsonResults = $.parseJSON(data.jsonResults);
		for(var i=0;i<jsonResults.length;i++){
			var precondition_html = "";
			var precondition = $.parseJSON(jsonResults[i].precondition);
			for(var key in precondition){
				precondition_html = precondition_html + "<p><strong><span style='color:blue;'>"+key+"</span> : <span style='color:red;'>"+precondition[key]+"</span></strong></p>";
			}
			var configurations_html = "";
			var configurations_obj = jsonResults[i].configurations;
			for(var j=0;j<configurations_obj.length;j++){
				var configurations = configurations_obj[j];
				configurations_html = configurations_html + "<p><strong><span style='color:blue;'>"+configurations.name+"</span> : <span style='color:red;'>"+configurations.value+"</span></strong></p>";
			}
			
			var modules_html = "<tr>"
							  +"	<td>"+(i+1)+"</td>"
							  +"	<td>"+jsonResults[i]._id+"</td>"
							  +"	<td>"+precondition_html+"</td>"
							  +"	<td>"+configurations_html+"</td>"
//							  +"	<td>"+data[i].manufacturer+"</td>"
//							  +"	<td>"+data[i].description+"</td>"
							  +"	<td>"
//							  +"        <button class='btn btn-xs btn-success' onclick=\"editPreset('"+data[i]._id+"')\"><i class='icon-edit'></i> </button>"
							  +"		<button class='btn btn-xs btn-danger' onclick=\"removePresetsShow('"+jsonResults[i]._id+"')\"><i class='icon-remove'></i> </button>"
							  +"	</td>"
							  +"</tr>";
			$('#presets_tbody').append(modules_html);
		}
		createPageList(data.currentPage,data.pageCount);
	});
}
function serarchPreset(){
	reflushPresetsList(1,$('#queryColumn').val(),$('#preset_search').val());
}
function createPageList(currentPage,pageCount){
	$('#perset_page').empty();
	var page_html = '';
	if(currentPage<=1){
		page_html = '<li><a>Prev</a></li>';
	}else{
		page_html = '<li><a onclick="reflushPresetsList('+(currentPage-1)+')" href="#">Prev</a></li>';
	}
	
	for(var j=1;j<=pageCount;j++){
		var clz = "";
		if(j==currentPage){
			page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
		}else{
			page_html = page_html + "<li><a onclick='reflushPresetsList("+j+")' href='#'>"+j+"</a></li>";
		}
	}
	if(currentPage==pageCount){
		page_html = page_html + "<li><a>Next</a></li>";
	}else{
		page_html = page_html + "<li><a onclick='reflushPresetsList("+(currentPage+1)+")' href='#'>Next</a></li>";
	}
	if(pageCount>0)$('#perset_page').append(page_html);
}
function reflushModulesList(){
	$.post(basePath+"modules/getAllModules",function(text){
		$('#moduleNamediv').empty();
		var data = $.parseJSON(text);
		if(data!=null&&data!=undefined){
			var modules_option ='<select class="selectpicker" name="moduleName" id="moduleName">';
			for(var i=0;i<data.length;i++){
				var select = "";
				if(i==0) select = "selected='selected'";
				modules_option = modules_option + '<option value="'+data[i].name+'" '+select+' data-oui="'+data[i].oui+'" data-product-class="'+data[i].productClass+'">'+data[i].name+'</option>';
			}
			modules_option = modules_option+'</select>';
			$('#moduleNamediv').append(modules_option);
			$('#moduleName').selectpicker();
			var oui = data[0].oui;
			var productClass = data[0].productClass;
			$('#productClass').val(productClass);
			$('#oui').val(oui);
			initChangeModule();
		}
		
	});
}
function initChangeModule(){
	$('#moduleName').on('change',function(){
		var oui = $("#moduleName").find("option:selected").data('oui');
		var productClass = $("#moduleName").find("option:selected").data('product-class');
//		alert($(this).data('oui'));
//		alert($(this).data('product-class'));
		$('#productClass').val(productClass);
		$('#oui').val(oui);
	})
}
var precondition_sub = 2;
var configuration_sub = 0;
function addPresets(){
	precondition_sub = 2;
	configuration_sub = 0;
	$('#presets_name').removeAttr("readonly");
	$('#presets_name').val("");
	reflushModulesList();
	$('#precondition_context').empty();
	$('#configurations_context').empty();
	$('#presetsModal').modal('show');
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
		$('#modules_name_group').parent().parent().addClass('has-error');
	}
	if(stringTrim(productClass)==""||productClass==null){
		msg = msg + "&" + modules_productClass_notempty;
		$('#product_class_group').parent().parent().addClass('has-error');
	}
	if(stringTrim(oui)==""||oui==null){
		msg = msg + "&" + modules_oui_notempty;
		$('#oui_group').parent().parent().addClass('has-error');
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
function removePresetsShow(sid){
	$("#deletePresetsId").val(sid);
	$("#deletePresetsName").html(sid);
	$("#deletePresetsModal").modal('show');
}
function submitRemovePresets(){
	$("#deletePresetsModal").modal('hide');
	$.post(basePath+"presets/removePresetById", { id: $("#deletePresetsId").val()},function(text){
		reflushPresetsList();
	});
	
}
function addPrecondition(e,type){
	if(type==null||type==""||type==undefined){
		type = $(this).html();
	}
	var form_group_html = '<div class="form-group" style="margin-bottom: 5px;">'
						 +' <label class="col-lg-3 control-label"><strong style="font-size:12px;">'+type+':</strong></label>'
						 +' <input type="hidden" name="preconditionList['+precondition_sub+'].name" value="'+type+'"/>'
						 +' <div class="col-lg-9">'
						 +'		<select name="preconditionList['+precondition_sub+'].symbol" class="selectpicker inline-select inline-select-symbol">'
						 +'		  <option value="e">=</option>'
						 +'		  <option value="ne">≠</option>'
						 +'		  <option value="lt">&lt;</option>'
						 +'		  <option value="lte">≤</option>'
						 +'		  <option value="gt">&gt;</option>'
						 +'		  <option value="gte">≥</option>'
						 +'		</select>';
	if(type=='lastInform'){
		form_group_html = form_group_html + '<input type="text" name="preconditionList['+precondition_sub+'].value" style="width: 70%;" class="form-control form_datetime" name="'+type+'">';
	}else{
		form_group_html = form_group_html + '<input type="text" name="preconditionList['+precondition_sub+'].value" style="width: 70%;" class="form-control" name="'+type+'">';
	}
		form_group_html = form_group_html + '<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button></div></div>';
	$('#precondition_context').append(form_group_html);
	if(type=='lastInform'){
		$('.form_datetime').datetimepicker({
	    			format: 'yyyy-mm-dd hh:ii:ss',
	    			autoclose : true,
	    			todayBtn : true,
	    			minuteStep : 1,
	    			todayHighlight : true
	//				language : 'zh-CN'
		});
	}
	precondition_sub++;
	activateDeleteButton();
//	$(".form_datetime").datetimepicker({
//		format: "yyyy-mm-dd hh:ii:ss",//设置时间格式，默认值: 'mm/dd/yyyy'
//      weekStart : 0, //一周从哪一天开始。0（星期日）到6（星期六）,默认值0
//      startDate : "2013-02-14 10:00",//可以被选择的最早时间
//      endDate : "2016-02-14 10:00",//可以被选择的最晚时间
//      daysOfWeekDisabled : "0,6",//禁止选择一星期中的某些天，例子中这样是禁止选择周六和周日
//      autoclose : true,//当选择一个日期之后是否立即关闭此日期时间选择器
//      startView : 2,//点开插件后显示的界面。0、小时1、天2、月3、年4、十年，默认值2
//      minView : 0,//插件可以精确到那个时间，比如1的话就只能选择到天，不能选择小时了
//      maxView：4,//同理
//      todayBtn : true,//是否在底部显示“今天”按钮
//      todayHighlight : true,//是否高亮当前时间
//      keyboardNavigation : true,//是否允许键盘选择时间
//      language : 'zh-CN',//选择语言，前提是该语言已导入
//      forceParse : true,//当选择器关闭的时候，是否强制解析输入框中的值。也就是说，当用户在输入框中输入了不正确的日期，选择器将会尽量解析输入的值，并将解析后的正确值按照给定的格式format设置到输入框中
//      minuteStep : 5,//分钟的间隔
//      pickerPosition : "bottom-right",//显示的位置，还支持bottom-left
//      viewSelect : 0,//默认和minView相同
//      showMeridian : true,//是否加上网格
//      initialDate : "2015-02-14 10:00"//初始化的时间
//	});
}
function addConfigurations(e,type){
	if(type==null||type==""||type==undefined){
		type = $(this).html();
	}
	var form_group_html = "";
	if(type=="Set"){
//		$.post(basePath+"modules/findParameterByModules",function(text){
		$.post(basePath+"device/getDeviceById",{id:'202BC1-BM632w-0000000'},function(text){
			var datas = $.parseJSON(text);
	    	var data = null;
	    	if(datas.length>0)data=datas[0];
			var parameterList = new Array();
			listJSON(data,parameterList);
			form_group_html = '<div class="form-group" style="margin-bottom: 5px;">'
				+'	 <label class="col-lg-3 control-label"><strong>'+type+':</strong></label>'
				+'	 <input type="hidden" value="'+type+'" name="configurations['+configuration_sub+'].type"/>';
			form_group_html = form_group_html + '	 <div class="col-lg-9">'
								+'		<select name="configurations['+configuration_sub+'].name" class="selectpicker inline-select" data-live-search="true" data-size="5" style="margin-right:0;">'
			for(var i=0;i<parameterList.length;i++){
				form_group_html= form_group_html	+ '<option value="'+parameterList[i].key+'">'+parameterList[i].key+'</option>'
			}
			form_group_html= form_group_html	+'		</select>'
											+'		<strong class="to-group">To</strong>'
											+'		<input name="configurations['+configuration_sub+'].value" type="text" style="width: 42%;" class="form-control" name="setValue">'
											+'		<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>'
											+'	 </div>'
											+' </div>';
			configuration_sub++;
			$('#configurations_context').append(form_group_html);
			activateDeleteButton();
		});
	}else{
		form_group_html = '<div class="form-group" style="margin-bottom: 5px;">'
			+'	 <label class="col-lg-3 control-label"><strong>'+type+':</strong></label>'
			+'	 <input type="hidden" value="'+type+'" name="configurations['+configuration_sub+'].type"/>';
		if(type=="Remove Object"){
			form_group_html = form_group_html +'	 <div class="col-lg-9">'
												+'		<input name="configurations['+configuration_sub+'].name" type="text" style="width: 39%;" class="form-control" name="'+type+'">'
												+'		<strong class="to-group">From</strong>'
												+'		<input name="configurations['+configuration_sub+'].value" type="text" style="width: 42%;" class="form-control">'
												+'		<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>'
												+'	 </div>'
												+' </div>';
		}else{
			form_group_html = form_group_html + '	 <div class="col-lg-9">'
												+'		<input type="text" name="configurations['+configuration_sub+'].name" style="width: 39%;" class="form-control" name="'+type+'">'
												+'		<strong class="to-group">To</strong>'
												+'		<input type="text" style="width: 42%;" class="form-control" name="configurations['+configuration_sub+'].value">'
												+'		<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>'
												+'	 </div>'
												+' </div>';
		}
		configuration_sub++;
		$('#configurations_context').append(form_group_html);
		activateDeleteButton();
	}
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
function activateDeleteButton(){
	$('.deleteClick').on('click',deleteFatherGroup);
	$('.selectpicker').selectpicker();
}
function deleteFatherGroup(){
	$(this).parent().parent().remove();
}
function submitAddUpdataPresets(){
	$('#presets_modal_form').ajaxSubmit(function(data){
		if(data=="success"){
			$('#presetsModal').modal('hide');
			reflushPresetsList();
		}
	});
}
function editPreset(id){
	precondition_sub = 0;
	configuration_sub = 0;
	reflushModulesList();
	$.post(basePath+"presets/findPresetsById",{id:id},function(text){
		$('#precondition_context').empty();
		$('#configurations_context').empty();
		var datas = $.parseJSON(text);
		var data = null;
		if(datas.length>0)data = datas[0];
		if(data!=null){
			$('#presets_name').val(data._id);
			$('#presets_name').attr("readonly","readonly");
		}
		var predata = data.precondition;
		predata = predata.replace(/summary./g,"");
		var predatajson = $.parseJSON(predata);
		for(var key in predatajson){
			var type = key;
			if(key=="productClass"){
				$('#productClass').val(predatajson[key]);
			}else if(key=="oui"){
				$('#oui').val(predatajson[key]);
				$("#moduleName").find("option").each(function(){
					var oui = $(this).data('oui');
					var productClass = $(this).data('product-class');
					if(oui==predatajson['oui']&&productClass==predatajson[productClass]){
						$('.selectpicker').selectpicker($(this).val());
					}
				});
			}else{
				var type1 = type.replace(/[A-Z][a-z]*/,"");
				var type2 = type.replace(/[a-z]*/,"");
				type = type1+" "+type2;
				addPrecondition(null,type);
				if(typeof(predatajson[key])=="object"){
					for(var skey in predatajson[key]){
						$("select[name='preconditionList["+(precondition_sub-1)+"].symbol']").selectpicker('val', skey.replace("$",""));
						$("input[name='preconditionList["+(precondition_sub-1)+"].value']").val(predatajson[key][skey]);
					}
				}else{
					$("input[name='preconditionList["+(precondition_sub-1)+"].value']").val(predatajson[key])
				}
			}
		}
		var configdata = data.configurations;
		for(var i=0;i<configdata.length;i++){
			var type = configdata[i].type;
			var name = configdata[i].name;
			if(type=="value"){
				var value = configdata[i].value;
				var namestr = name;
				//addConfigurations(null,"Set");
				
				$.post(basePath+"modules/findParameterByModules",{modulesName:$('#moduleName').selectpicker('val')},function(text){
					//				$.post(basePath+"device/getDeviceById",{id:'202BC1-BM632w-0000000'},function(text){
					var datas = $.parseJSON(text);
			    	var data = null;
			    	if(datas.length>0)data=datas[0];
					var parameterList = new Array();
					listJSON(data,parameterList);
					form_group_html = '<div class="form-group" style="margin-bottom: 5px;">'
						+'	 <label class="col-lg-3 control-label"><strong>Set:</strong></label>'
						+'	 <input type="hidden" value="Set" name="configurations['+configuration_sub+'].type"/>';
					form_group_html = form_group_html + '	 <div class="col-lg-9">'
										+'		<select name="configurations['+configuration_sub+'].name" class="selectpicker inline-select" data-live-search="true" data-size="5" style="margin-right:0;">'
					for(var i=0;i<parameterList.length;i++){
						var selval = "";
						if(parameterList[i].key==namestr){
							selval="selected='selected'";
						}
						form_group_html= form_group_html	+ '<option value="'+parameterList[i].key+'" '+selval+'>'+parameterList[i].key+'</option>'
					}
					form_group_html= form_group_html	+'		</select>'
													+'		<strong class="to-group">To</strong>'
													+'		<input name="configurations['+configuration_sub+'].value" type="text" style="width: 42%;" class="form-control" value="'+value+'">'
													+'		<button type="button" class="btn btn-link deleteClick" style="float: right;">delete</button>'
													+'	 </div>'
													+' </div>';
					configuration_sub++;
					$('#configurations_context').append(form_group_html);
					activateDeleteButton();
					//$("select[name='configurations["+(configuration_sub-1)+"].name']").selectpicker('val',name);
				});
				
			}else if(type=="delete_object"){
				addConfigurations(null,"Remove Object");
				$("input[name='configurations["+(configuration_sub-1)+"].name']").val(configdata[i].name);
				$("input[name='configurations["+(configuration_sub-1)+"].value']").val(configdata[i].object);
			}else if(type=="add_object"){
				addConfigurations(null,"Add Object");
				$("input[name='configurations["+(configuration_sub-1)+"].name']").val(configdata[i].name);
				$("input[name='configurations["+(configuration_sub-1)+"].value']").val(configdata[i].object);
			}
		}
		reflushModulesList();
		$('#presetsModal').modal('show');
	});
}