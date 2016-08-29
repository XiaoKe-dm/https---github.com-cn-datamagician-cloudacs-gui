$(document).ready(function() {
	reflushDeviceList();
	reflushTaskList();
	reflushModulesList();
});
function reflushDeviceList(){
	$.post(basePath+"main/getTopDevice",function(text){
		var data = $.parseJSON(text);
		var totalDevice = data.totalDevice;
		var activeDevice = data.activeDevice;
		$('#total_devices').html(totalDevice.length);
		$('#unknown_devices').html(data.unknownDeviceNum);
		$('#active_devices').html(activeDevice.length);
			var i=0;
			$('#device_online_tbody').empty();
			if(activeDevice!=null&&activeDevice!=undefined&&activeDevice.length>0){
				for(var j=0;j<activeDevice.length;j++){
					if(activeDevice[j].$ref==undefined){
						if(i<3)createDevice(activeDevice[j],true);
						i++;
					}
				}
			}
			if(i<3){
				for(var j=0;j<totalDevice.length;j++){
					if(totalDevice[j].$ref==undefined){
						if(i<3)createDevice(totalDevice[j],false);
						i++;
					}
				}
			}
			
	});
}
function createDevice(device,isonline){
		var html = "<tr>"
			+"		<td>"+device.summary.serialNumber+"</td>"
			+"		<td>"+device.id+"</td>"
			+"		<td>"+device.modules+"</td>"
			+"		<td>"+device.summary.oui+"</td>"
			+"		<td>"+device.summary.productClass+"</td>";
		if(isonline)html = html + "		<td><span class='label label-success'>Online</span></td>";
		else html = html + "		<td><span class='label label-danger'>Offline</span></td>";
				html = html +"	</tr>";
		$('#device_online_tbody').append(html);
}
function reflushTaskList(){
	$.post(basePath+"main/getTopTask",function(text){
		var data = $.parseJSON(text);
		$('#task_tbody').empty();
		for(var i=0;i<data.length;i++){
			var html = "<tr>"
				+"			<td>"+data[i].device+"</td>"
				+"			<td>"+data[i].taskName+"</td>"
				+"			<td>"+data[i].time+"</td>"
				+"      </tr>";
			$('#task_tbody').append(html);
		}
	});
}
function reflushModulesList(){
	$.post(basePath+"main/getTopModule",function(text){
		var data = $.parseJSON(text);
		$('#module_tbody').empty();
		for(var i=0;i<data.length;i++){
			var html = "<tr>"
				+"			<td>"+data[i].name+"</td>"
				+"			<td>"+data[i].deviceNum+"</td>"
				+"      </tr>";
			$('#module_tbody').append(html);
		}
	});
}