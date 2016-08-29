$(document).ready(function() {
	reflushFilesList(1);
});
function reflushFilesList(currentPage,queryColumn,queryValue){
	$.post(basePath+"files/getAllFilesPage",{"currentPage":currentPage,"queryColumn":queryColumn,"queryValue":queryValue},function(text){
		var data = $.parseJSON(text);
		$('#files_tbody').empty();
		var jsonResults = $.parseJSON(data.jsonResults);
		for(var i=0;i<jsonResults.length;i++){
			var file_html = "<tr>"
							  +"	<td>"+(i+1)+"</td>"
							  +"	<td>"+jsonResults[i].filename+"</td>"
							  +"	<td>"+jsonResults[i].metadata.fileType+"</td>"
							  +"	<td>"+jsonResults[i].metadata.oui+"</td>"
							  +"	<td>"+jsonResults[i].metadata.productClass+"</td>"
							  +"	<td>"+jsonResults[i].metadata.version+"</td>"
							  +"	<td>"
							  +"		<button class='btn btn-xs btn-danger' onclick=\"removeFilesShow('"+jsonResults[i]._id+"','"+jsonResults[i].filename+"')\"><i class='icon-remove'></i> </button>"
							  +"	</td>"
							  +"</tr>";
			$('#files_tbody').append(file_html);
		}
		createPageList(data.currentPage,data.pageCount);
	});
}
function serarchFiles(){
	reflushFilesList(1,$('#queryColumn').val(),$('#files_search').val());
}
function createPageList(currentPage,pageCount){
	$('#files_page').empty();
	var page_html = '';
	
	if(currentPage<=1){
		page_html = '<li><a>Prev</a></li>';
	}else{
		page_html = '<li><a onclick="reflushFilesList('+(currentPage-1)+')" href="#">Prev</a></li>';
	}
	
	for(var j=1;j<=pageCount;j++){
		var clz = "";
		if(j==currentPage){
			page_html = page_html + "<li class='active'><a href='#'>"+j+"</a></li>";
		}else{
			page_html = page_html + "<li><a onclick='reflushFilesList("+j+")' href='#'>"+j+"</a></li>";
		}
	}
	if(currentPage==pageCount){
		page_html = page_html + "<li><a>Next</a></li>";
	}else{
		page_html = page_html + "<li><a onclick='reflushFilesList("+(currentPage+1)+")' href='#'>Next</a></li>";
	}
	if(pageCount>0)$('#files_page').append(page_html);
}
function addFile(){
	$('#OUI').val("");
	$('#product_class').val("");
	$('#version').val("");
	$('#file').val("");
	$('#fileModal').modal('show');
}
var intId;
function submitUpdateFile(){
	createLoadMask('content');
	$('#file_modal_form').ajaxSubmit(function(data){
		if(data=="success"){
			hideMask();
			$('#fileModal').modal('hide');
			reflushFilesList();
		}
	});
	/*$("#progressModal").modal('show');
	$('#proBar').css({width:'0%'});
	$('#proBar').empty();
	eventFun();
	intId = window.setInterval(eventFun,500);*/
}
function eventFun(){
	$.ajax({  
        type: 'GET',  
        url: basePath+"files/process",  
        data: {},  
        dataType: 'json',  
        success : function(data){
	                $('#proBar').animate({ width: data }, 500).html(data + "% ");
	                if(data == 100){
	                    window.clearInterval(intId);  
	                    $("#progressModal").modal('hide');
	                }
	                /*$('#proBar').empty();
	                $('#proBar').append(data.show);
	                */
        		}
        });
}
function stringTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function removeFilesShow(sid,name){
	$("#deleteFilesId").val(sid);
	$("#deleteFilesName").html(name);
	$("#deleteFilesModal").modal('show');
}
function submitRemoveFiles(){
	
	$.post(basePath+"files/removeFilesByName", { name: $("#deleteFilesName").html()},function(text){
		if(text!="error"){
			reflushFilesList();
			$("#deleteFilesModal").modal('hide');
			
		}
	});
}
function selectDevice(){
	$.post(basePath+"modules/getAllModules", function(text){
		var data = $.parseJSON(text);
		$('#deviceList_tbody').empty();
		for(var i=0;i<data.length;i++){
			var device_html = "<tr>"
						     +"  	<td><input type='radio' id='device_"+data[i].id+"' name='deviceSelect' value='"+data[i].id+"'/></td>"
						     +"		<td>"+data[i].name+"</td>"
						     +"		<td>"+data[i].productClass+"</td>"
						     +"		<td>"+data[i].oui+"</td>";
			if(data[i].manufacturer!=undefined)device_html = device_html  + "		<td>"+data[i].manufacturer+"</td>";
			device_html = device_html  + "	</tr>";
			$('#deviceList_tbody').append(device_html);
		}
		/*$("input[name^='deviceList']").each(function(){
			$('#device_'+$(this).val()).prop("checked", true);
		});*/
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
function submitSelectDevice(){
	var tdlist = $("input[name='deviceSelect']:checked").parent().parent().children('td');
	$('#product_class').val($(tdlist[2]).html().replace(/\s+/g,""));
	$('#OUI').val($(tdlist[3]).html().replace(/\s+/g,""));
	$('#deviceListModal').modal('hide');
}
var spinner = null;
function createLoadMask(targetId){
	//var target = document.getElementById("deviceModal");
	var maskHtml = '<div id="mask" class="mask"></div>';
	$("#"+targetId).append(maskHtml);
	var height = $('#mask').height()/2-30;
	var width = $('#mask').width()/2-30;
	height = height+"px";
	width = width+"px";
	var opts = {
			lines: 13, // 花瓣数目
            length: 20, // 花瓣长度
            width: 10, // 花瓣宽度
            radius: 30, // 花瓣距中心半径
            corners: 1, // 花瓣圆滑度 (0-1)
            rotate: 0, // 花瓣旋转角度
            direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
            color: '#FFF', // 花瓣颜色
            speed: 1, // 花瓣旋转速度
            trail: 90, // 花瓣旋转时的拖影(百分比)
            shadow: false, // 花瓣是否显示阴影
            hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
            className: 'spinner', // spinner css 样式名称
            zIndex: 2e9, // spinner的z轴 (默认是2000000000)
            top: height, // spinner 相对父容器Top定位 单位 px
            left: width// spinner 相对父容器Left定位 单位 px
	    };
	spinner = new Spinner(opts);
	var target = document.getElementById("mask")
	$("#mask").show();
	spinner.spin(target);
//	alert($('#mask').height()+":"+$('#mask').width());
}
function hideMask(sipnner){
	$("#mask").remove();
	spinner.spin();
	spinner = null;
}