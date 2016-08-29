$(document).ready(function() {
	reflushModulesList(1);
});
function reflushModulesList(currentPage,queryColumn,queryValue){
	$.post(basePath+"modules/getModulesPage",{"currentPage":currentPage,"queryColumn":queryColumn,"queryValue":queryValue},function(text){
		var data = $.parseJSON(text);
		$('#modules_tbody').empty();
		var jsonResults = data.results;
		for(var i=0;i<jsonResults.length;i++){
			var modules_html = "<tr>"
							  +"	<td>"+(i+1)+"</td>"
							  +"	<td>"+jsonResults[i].name+"</td>"
							  +"	<td>"+jsonResults[i].productClass+"</td>"
							  +"	<td>"+jsonResults[i].oui+"</td>"
							  +"	<td>"+jsonResults[i].manufacturer+"</td>"
							  +"	<td>"+jsonResults[i].description+"</td>"
							  +"	<td>"
							  +"		<button class='btn btn-xs btn-danger' onclick=\"removeModulesShow('"+jsonResults[i].sid+"','"+jsonResults[i].name+"')\"><i class='icon-remove'></i> </button>"
							  +"	</td>"
							  +"</tr>";
			$('#modules_tbody').append(modules_html);
		}
		createPageList(data.currentPage,data.pageCount);
	});
}
function serarchModules(){
	reflushModulesList(1,$('#queryColumn').val(),$('#modules_search').val());
}
function createPageList(currentPage,pageCount){
	$('#modules_page').empty();
	var page_html = '';
	
	if(currentPage<=1){
		page_html = '<li><a>Prev</a></li>';
	}else{
		page_html = '<li><a onclick="reflushModulesList('+(currentPage-1)+')" href="#">Prev</a></li>';
	}
	
	for(var j=1;j<=pageCount;j++){
		var clz = "";
		if(j==currentPage){
			page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
		}else{
			page_html = page_html + "<li><a onclick='reflushModulesList("+j+")' href='#'>"+j+"</a></li>";
		}
	}
	if(currentPage==pageCount){
		page_html = page_html + "<li><a>Next</a></li>";
	}else{
		page_html = page_html + "<li><a onclick='reflushModulesList("+(currentPage+1)+")' href='#'>Next</a></li>";
	}
	if(pageCount>0)$('#modules_page').append(page_html);
}
function addModules(){
	$('#modules_name').val("");
	$('#product_class').val("");
	$('#oui').val("");
	$('#manufacturer').val("");
	$('#description').val("");
	$('#modulesModal').modal('show');
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
			if(text=="success"){
				reflushModulesList();
				$('#modulesModal').modal('hide');
			}else{
				
			}
			
		});
	}
}
function stringTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function removeModulesShow(sid,name){
	$("#deleteModulesId").val(sid);
	$("#deleteModulesName").html(name);
	$("#deleteModulesModal").modal('show');
}
function removeModules(){
	$("#deleteModulesModal").modal('hide');
	$.post(basePath+"modules/removeModulesById", { sid: $("#deleteModulesId").val(),name:$("#deleteModulesName").html()},function(text){
		if(text=="success"){
			reflushModulesList();
		}else{
			
		}
		
	});
}
function uploadFirmware(){
	
}