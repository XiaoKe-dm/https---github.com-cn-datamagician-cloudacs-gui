var preParameterList = new Array();
var editParameterList = new Array();
var usernameList = null;
var passwordList = null;
var preTarget = "config_tab";
var tabId = "";
function initDeviceInfo(){
	$('#username_edit').val("");
	$('#password_edit').val("");
	$('#dns_servers_edit').val("");
	
	$('#wifi_ssid').val("");
	$('#wifi_password').val("");
	$('#wifi_channel').val("");
	$('#wifi_WPAEncryptionMode').val("");
	$('#wifi_WPAAuthenticationMode').val("");
	$('#wifi_standard').val("");
	$('#wifi_possible_channels').val("");
	
	preParameterList = new Array();
	editParameterList = new Array();
	preTarget = "config_tab";
	
	$('#hosts_tbody').empty();
	$('#task_tbody').empty();
	$('#device_parameters_search').val("");
	$('#device_parameters_context').empty();
	var i=0;
	$('.tabbable .nav').children("li").each(function(){
		if(i==0){
			$(this).addClass("active");
			i=1;
		}else $(this).removeClass("active");
	});
	$('#config_tab').addClass("active");
	$('#hosts_tab').removeClass("active");
	$('#wifi_tab').removeClass("active");
	$('#task_tab').removeClass("active");
	$('#parameters_tab').removeClass("active");
}
$(document).ready(function() {
	$('a[data-toggle="tab"]').click(function(e) {
			e.preventDefault();
	 		//tabId = this.href.split("#")[1];
	 		tabId = this.href.split("#")[1];
	 		editParameterList = new Array();
	 		if(preTarget=="config_tab"){
	 			var username = $('#username_edit').val();
	 			var password = $('#password_edit').val();
	 			var dnsServers = $('#dns_servers_edit').val();
	 			for(var i=0;i<preParameterList.length;i++){
	 				var parameterObj = preParameterList[i];
	 				if(parameterObj.name=="username"){
	 					if(parameterObj.value!=username){
	 						parameterObj.editValue = username;
	 						editParameterList.push(parameterObj);
	 					}
	 				}else if(parameterObj.name=="password"){
	 					if(parameterObj.value!=password){
	 						parameterObj.editValue = password;
	 						editParameterList.push(parameterObj);
	 					}
	 				}else if(parameterObj.name=="dns"){
	 					if(parameterObj.value!=dnsServers){
	 						parameterObj.editValue = dnsServers;
	 						editParameterList.push(parameterObj);
	 					}
	 				}
	 			}
	 		}else if(preTarget=="wifi_tab"){
	 			/*var wifi_ssid = $('#wifi_ssid').val();
	 			var wifi_password = $('#wifi_password').val();
	 			var wifi_channel = $('#wifi_channel').val();
	 			
	 			var wifi_WPAEncryptionMode = $('#wifi_WPAEncryptionMode').val();
	 			var wifi_WPAAuthenticationMode = $('#wifi_WPAAuthenticationMode').val();*/
	 			for(var i=0;i<preParameterList.length;i++){
	 				var parameterObj = preParameterList[i];
	 				if(parameterObj.name.indexOf("wifi_")==0){
		 				var parameterObjVal = $('#'+parameterObj.name).val();
		 				if(parameterObj.value!=parameterObjVal){
	 						parameterObj.editValue = parameterObjVal;
	 						editParameterList.push(parameterObj);
	 					}
	 				}
	 				
	 				/*if(parameterObj.name=="wifi_ssid"){
	 					if(parameterObj.value!=wifi_ssid){
	 						parameterObj.editValue = wifi_ssid;
	 						editParameterList.push(parameterObj);
	 					}
	 				}else if(parameterObj.name=="wifi_password"){
	 					if(parameterObj.value!=wifi_password){
	 						parameterObj.editValue = wifi_password;
	 						editParameterList.push(parameterObj);
	 					}
	 				}else if(parameterObj.name=="wifi_channel"){
	 					if(parameterObj.value!=wifi_channel){
	 						parameterObj.editValue = wifi_channel;
	 						editParameterList.push(parameterObj);
	 					}
	 				}else if(parameterObj.name=="wifi_WPAEncryptionMode"){
	 					if(parameterObj.value!=wifi_WPAEncryptionMode){
	 						parameterObj.editValue = wifi_WPAEncryptionMode;
	 						editParameterList.push(parameterObj);
	 					}	
	 				}else if(parameterObj.name=="wifi_WPAAuthenticationMode"){
	 					if(parameterObj.value!=wifi_WPAAuthenticationMode){
	 						parameterObj.editValue = wifi_WPAAuthenticationMode;
	 						editParameterList.push(parameterObj);
	 					}	
	 				}*/
	 			}
	 		}
	 		if(editParameterList.length>0){
	 			$('#device_edit_info').empty();
	 			for(var i=0;i<editParameterList.length;i++){
	 				var confirmHtml = "<p>change <span style='font-weight: bold;'>"+editParameterList[i].name+"</span>: <span style='color:blue'>"+editParameterList[i].value+"</span> to <span style='color:red'>"+editParameterList[i].editValue+"</span></p>";
	 				$('#device_edit_info').append(confirmHtml);
	 			}
	 			$('#deviceAllEditModal').modal('show');
	 			return false;
	 		}else{
	 			showTabById(tabId)
	 		}
  	});
	$('#device_parameters_search').bind('input propertychange', function(){
		reflushParameters($('#device_parameters_search').val()); 
	});
});
function showTabById(tabId){
	    liId = preTarget.split("_")[0]+"_li";
		$('#'+preTarget).removeClass('active');
		$('#'+liId).removeClass('active');
		preTarget = tabId;
		liId = preTarget.split("_")[0]+"_li";
		$('#'+preTarget).addClass('active');
		$('#'+liId).addClass('active');
		//$("#hosts_tab").tab('show');
		if(tabId=="config_tab"){
			
		}else if(tabId=="hosts_tab"){
			showHostsById();
		}else if(tabId=="wifi_tab"){	
			showWifiById();
		}else if(tabId=="task_tab"){
			showTaskById();
		}else if(tabId=="parameters_tab"){
			showParametersById();
		}
}
function cancelUpdataParameterValues(){
	for(var i=0;i<editParameterList.length;i++){
		var name = editParameterList[i].name;
		var value = editParameterList[i].value;
		var editValue = editParameterList[i].editValue;
		//console.log(name+"-"+value+"-"+editValue);
		if(name=="username"){
			$('#username_edit').val(value);
		}else if(name=="password"){
			$('#password_edit').val(value);
		}else if(name=="dns"){
			$('#dns_servers_edit').val(value);
		}else if(name=="wifi_ssid"){
			$('#wifi_ssid').val(value);
		}else if(name=="wifi_password"){
			$('#wifi_password').val(value);
		}else if(name=="wifi_channel"){
			$('#wifi_channel').val(value);
		}else if(name=="wifi_WPAEncryptionMode"){
			$('#wifi_WPAEncryptionMode').val(value);
		}else if(name=="wifi_WPAAuthenticationMode"){
			$('#wifi_WPAAuthenticationMode').val(value);
		}
	}
	$('#deviceAllEditModal').modal('hide');
}
function submitAllUpdataParameterValues(){
		var keyList = new Array();
		var valueList = new Array();
		var typeList = new Array();
		var usernameFlag = false;
		var usernameParameter = null;
		var passwordFlag = false;
		var passwordParameter = null;
		for(var i=0;i<editParameterList.length;i++){
			if(editParameterList[i].name=="username"){
				usernameFlag = true;
				usernameParameter = editParameterList[i];
			}else if(editParameterList[i].name=="password"){
				passwordFlag = true;
				passwordParameter = editParameterList[i];
			}else{
				keyList[i] = editParameterList[i].key;
				valueList[i] = editParameterList[i].editValue;
				typeList[i] = editParameterList[i].type;
			}
			
		}
		if(usernameFlag){
			for(var i=0;i<usernameList.length;i++){
				var n = keyList.length+i;
				keyList[n] = usernameList[i].key;
				valueList[n] = usernameParameter.editValue;
				typeList[n] = usernameParameter.type;
			}
		}
		if(passwordFlag){
			for(var i=0;i<passwordList.length;i++){
				var n = keyList.length+i;
				keyList[n] = passwordList[i].key;
				valueList[n] = passwordParameter.editValue;
				typeList[n] = passwordParameter.type;
			}
		}
		$.ajax({
	        type:"POST",
	        url: basePath+"task/updateParameterListValues",
	        data:{'id' : $('#device_id').html(),'keyList' : keyList,'valueList':valueList,'typeList':typeList},
	        datatype: "json",
	        success:function(text){
	        	if(text=="success"){
	        		alert(device_update_parameter_success);
	        		$('#deviceAllEditModal').modal('hide');
	        		showTabById(tabId)
	        	}else if(text=="queued"){
	        		alert(device_update_parameter_success);
	        		$('#deviceAllEditModal').modal('hide');
	        		showTabById(tabId)
	        	}else{
	        		alert(device_update_parameter_error);
	        	}
//	       	 	var datas = $.parseJSON(json);
	        },
	        error: function(){
	     		alert(msg_unsuccess);
	        }         
	     });
}
function showDeviceInfo1(id){
	$('#deviceModal').modal('show');
}
function showDeviceInfo(id){
	initDeviceInfo();
	$.ajax({
        type:"POST",
        url: basePath+"device/getDeviceById",
        data:{id : id},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('content');
        },
        success:function(json){
        	initDeviceInfo();
       	 	var data = $.parseJSON(json);
       	 	if(data!=null){
       	 	    usernameList = data.usernameList;
       	 	    passwordList = data.passwordList;
       	 		$('#device_id').html(data.id);
//       	 		var tags = data._tags;
//       	 		if(tags!=null||tags!=undefined)createTags(tags);
       	 		$('#serial_number').html(data.summary.serialNumber);
       	 		$('#product_class').html(data.summary.productClass);
       	 		$('#oui').html(data.summary.oui);
       	 		$('#hardware_version').html(data.summary.hardwareVersion);
       	 		$('#software_version').html(data.summary.softwareVersion);
		       	$('#manufacturer').html(data.summary.manufacturer);
		       	$('#mac').html(data.summary.mac);
			    $('#ip').html(data.summary.ip);
		       	var lastInform = data.lastInformStr;
		       	var date = new Date(lastInform.replace("T"," ").replace("Z",""));
		       	
		    	$('#last_inform').html(diffTime(date,new Date()));
		    	$('#inform_interval').html(data.informInterval);
		    	$('#connection_URL').html(data.connectionRequestURL);
		    	$('#up_time').html(countTime(data.upTime*1000));
		    	
		    	//set config
		    	if(data.username!=undefined){
			    	$('#username').html(data.username.value);
			    	$('#username_edit').val(data.username.value);
			    	preParameterList.push(createPreParameter("username",data.username.key,data.username.value,data.username.type));
		    	}
		    	if(data.password!=undefined){
			    	$('#password').html(data.password.value);
			    	$('#password_edit').val(data.password.value);
			    	preParameterList.push(createPreParameter("password",data.password.key,data.password.value,data.password.type));
		    	}
		    	$('#wlan_ssid').html(data.summary.wlanSsid);
		    	if(data.dns!=undefined){
			    	$('#dns_servers').html(data.dns.value);
			    	$('#dns_servers_edit').val(data.dns.value);
			    	preParameterList.push(createPreParameter("dns",data.dns.key,data.dns.value,data.dns.type));
       	 		}
		    	$('#xds_type').html(data.modulationType);
		    	$('#up_curr_Rate').html(data.upCurrRate/1000);
		    	$('#down_curr_Rate').html(data.downCurrRate/1000);
//		    	$('#up_max_Rate').html(data.upMaxRate);
//		    	$('#down_max_Rate').html(data.downMaxRate);
		    	
       	 		$('#deviceModal').modal('show');
       	 		hideMask();
       	 	}
        },
        error: function(){
     		alert(unsuccess);
     		hideMask();
        }         
     });
}
function diffTime(date1,date2){
	//北美时间，减4个小时
	var date3=date2.getTime()-date1.getTime()+4*60*60*1000  //时间差的毫秒数
	return countTime(date3);
}
function countTime(date3){
	date3 = Math.abs(date3);
	//计算出相差天数
	var days=Math.floor(date3/(24*3600*1000))
	//计算出小时数
	var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
	var hours=Math.floor(leave1/(3600*1000))
	//计算相差分钟数
	var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
	var minutes=Math.floor(leave2/(60*1000))

	//计算相差秒数
	var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
	var seconds=Math.round(leave3/1000);
	//console.log(days+"-"+hours+"-"+minutes+"-"+seconds);
	var diff="";
	if(days>0){
		diff = days+"d "+ hours +"h " + minutes +"m "+ seconds+"s";
	}else if(hours>0){
		diff = hours +"h " + minutes +"m "+ seconds+"s";
	}else if(minutes>0){
		diff = minutes +"m "+ seconds+"s";
	}else if(seconds>0){
		diff = seconds+"s";
	}
	return diff;
}
function createUserAndPass(userKey,userValue,passKey,passValue) {
    var userAndPass = new Object();
    userAndPass.userKey = userKey;
    userAndPass.userValue = userValue;
    userAndPass.passKey = passKey;
    userAndPass.passValue = passValue;
    return userAndPass;
}
function createTags(tags){
	var tagsHtml = "";
	for(var i=0;i<tags.length;i++){
		tagsHtml= tagsHtml+' <a><span>'+tags[i]+'</span><em onclick=\'removeTag("'+tags[i]+'")\'></em></a>';
	}
	$('#device_plus_tags').empty();
	$('#device_plus_tags').append(tagsHtml);
}
function editDeviceInfo2(key,value,type){
	$('#device-modal-type').html(key);
	$('#inputDeviceValue').val(value);
	$('#inputDeviceType').val(type);
	$('#deviceEditModal').modal();
}
function editDeviceInfo(key,value,type){
	$('#device-modal-type').html(key);
	$('#inputDeviceValue').val(value);
	$('#inputDeviceType').val(type);
	$('#deviceEditModal').modal();
}
function submitUpdataParameterValues(){
	var key = $('#device-modal-type').html()
	var type = $('#device-modal-type').html()
	if (confirm("Pending tasks "+key)){
		updateParameterValues($('#device_id').html(),key,$('#inputDeviceValue').val(),$('#inputDeviceType').val());
	}
}
function updateParameterValues(id,key,value,type){
	$.ajax({
        type:"POST",
        url: basePath+"task/updateParameterValues",
        data:{'id' : id,'key' : key,'value':value,'type':type},
        datatype: "json",
        success:function(text){
        	if(text=="success"){
        		alert(device_update_parameter_success);
        		$('#deviceEditModal').modal('hide');
        	}else if(text=="queued"){
        		alert(device_update_parameter_success);
        		$('#deviceEditModal').modal('hide');
        	}else{
        		alert(device_update_parameter_error);
        	}
//       	 	var datas = $.parseJSON(json);
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
}
function showWifiById(){
	$("#accordion").empty();
	$.ajax({
        type:"POST",
        url: basePath+"device/getDeviceWifiById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('deviceModal');
        },
        success:function(json){
        	var data = $.parseJSON(json);
        	for(var i=0;i<data.length;i++){
        		
        		preParameterList.push(createPreParameter("wifi_ssid_"+i,data[i].ssid.key,data[i].ssid.value,data[i].ssid.type));
        		preParameterList.push(createPreParameter("wifi_password_"+i,data[i].preSharedKey.key,data[i].preSharedKey.value,data[i].preSharedKey.type));
        		preParameterList.push(createPreParameter("wifi_channel_"+i,data[i].channel.key,data[i].channel.value,data[i].channel.type));
        		preParameterList.push(createPreParameter("wifi_WPAEncryptionMode_"+i,data[i].wPAEncryptionModes.key,data[i].wPAEncryptionModes.value,data[i].wPAEncryptionModes.type));
        		preParameterList.push(createPreParameter("wifi_WPAAuthenticationMode_"+i,data[i].wPAAuthenticationMode.key,data[i].wPAAuthenticationMode.value,data[i].wPAAuthenticationMode.type));
        		
        		var inclass="";
        		if(i==0)inclass = "in";
        		var html = "<div class='panel panel-default'>"
						  +"		<div class='panel-heading'>"
						  +"			<h4 class='panel-title'>"
						  +"							<a class='accordion-toggle' data-toggle='collapse' data-parent='#accordion' href='#collapse_"+i+"'>"
						  +"								<i class='icon-angle-down bigger-110' data-icon-hide='icon-angle-down' data-icon-show='icon-angle-right'></i>"
						  +"								"+(i+1)
						  +"							</a>"
						  +"						</h4>"
						  +"					</div>"
						  +"					<div class='panel-collapse collapse "+inclass+"' id='collapse_"+i+"'>"
						  +"						<div class='panel-body' style='padding:10px 5px'>"
						  +"                          <table class='table table-condensed table-hover'>"
						  +"							<tbody>"
						  +"		                        <tr>"
						  +"		                        	<td>SSID</td>"
						  +"									<td><input class='form-control' id='wifi_ssid_"+i+"' value='"+data[i].ssid.value+"'/></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>Password</td>"
						  +"									<td><input class='form-control' id='wifi_password_"+i+"' value='"+data[i].preSharedKey.value+"'/></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>Channel</td>"
						  +"									<td><input class='form-control' id='wifi_channel_"+i+"' value='"+data[i].channel.value+"'/></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>WPAEncryptionMode</td>"
						  +"									<td><input class='form-control' id='wifi_WPAEncryptionMode_"+i+"' value='"+data[i].wPAEncryptionModes.value+"'/></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>WPAAuthenticationMode</td>"
						  +"									<td><input class='form-control' id='wifi_WPAAuthenticationMode_"+i+"' value='"+data[i].wPAAuthenticationMode.value+"'/></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>Wifi Standard</td>"
						  +"									<td><span>"+data[i].standard.value+"</span></td>"
						  +"								</tr>"
						  +"								<tr>"
						  +"		                        	<td>PossibleChannels</td>"
						  +"									<td><span>"+data[i].possibleChannels.value+"</span></td>"
						  +"								</tr>"
						  +"							</tbody>"
						  +"						  </table>"
						  +"						</div>"
						  +"					</div>"
						  +"				</div>";
        		$("#accordion").append(html);
        	}
       	 	/*if(data!=null){
       	 		if(data.ssid!=undefined){
	       	 		$('#wifi_ssid').val(data.ssid);
	       	 		preParameterList.push(createPreParameter("wifi_ssid","summary.wlanSsid",data.ssid,data.ssidType));
       	 		}
       	 		if(data.password!=undefined){
	       	 		$('#wifi_password').val(data.password);
	       	 		preParameterList.push(createPreParameter("wifi_password","summary.wlanPassphrase",data.password,data.passwordType));
       	 		}
       	 		if(data.channel!=undefined){
		       	 	$('#wifi_channel').val(data.channel);
		       	 	preParameterList.push(createPreParameter("wifi_channel",data.channelKey,data.channel,data.channelType));
       	 		}
       	 		if(data.wpaEncryptionMode!=undefined){
		       	    $('#wifi_WPAEncryptionMode').val(data.wpaEncryptionMode);
		       	 	preParameterList.push(createPreParameter("wifi_WPAEncryptionMode",data.wpaEncryptionModeKey,data.wpaEncryptionMode,data.wpaEncryptionModeType));
       	 		}
       	 		if(data.wpaAuthenticationMode!=undefined){
		       	    $('#wifi_WPAAuthenticationMode').val(data.wpaAuthenticationMode);
		       	 	preParameterList.push(createPreParameter("wifi_WPAAuthenticationMode",data.wpaAuthenticationModeKey,data.wpaAuthenticationMode,data.wpaAuthenticationModeType));
       	 		}
	       	 	$('#wifi_standard').html(data.standardWlan);
	       	 	$('#wifi_possible_channels').html(data.possibleChannels);
       	 	}*/
        	hideMask();
        },
        error: function(){
     		alert(msg_unsuccess);
     		hideMask();
        }         
     });
}
function showHostsById(){
	$('#hosts_tbody').empty();
	$.ajax({
        type:"POST",
        url: basePath+"device/getDeviceHostById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('deviceModal');
        },
        success:function(json){
        	var data = $.parseJSON(json);
       	 	if(data!=null){
	       	 	$('#hosts_tbody').empty();
	       	 	for(var i=0;i<data.length;i++){
	       	 		var host = data[i];
		       	 	var host_html = "<tr>";
					host_html = host_html +"<td>"+(i+1)+"</td>";
					host_html = host_html +"<td>"+host.name+"</td>";
					host_html = host_html +"<td>"+host.ip+"</td>";
					host_html = host_html +"<td>"+host.mac+"</td>";
					if(host.type=="WIFI")host_html = host_html +"<td><div class='wifi'/></td>";
					else host_html = host_html +"<td><div class='network'/></td>";
					if(host.active)host_html = host_html +"<td><span class='label label-success'>"+device_online+"</span></td>";
					else host_html = host_html +"<td><span class='label label-danger'>"+device_not_online+"</span></td>";
					host_html = host_html + "</tr>";
					$('#hosts_tbody').append(host_html);
	       	 	}
		    	/*var hosts = data.InternetGatewayDevice.LANDevice["1"].Hosts.Host;
		    	for(var key in hosts){
					if(!isNaN(key)){
						var host = hosts[key];
						var active = host.Active._value;
						var layer2Interface = host.Layer2Interface._value;
						if(layer2Interface.indexOf("LANDevice")>=0){
							layer2Interface = "LAN";
						}else{
							layer2Interface = "WIFI";
						}
						var host_html = "<tr>";
						host_html = host_html +"<td>"+key+"</td>";
						host_html = host_html +"<td>"+host.HostName._value+"</td>";
						host_html = host_html +"<td>"+host.IPAddress._value+"</td>";
						host_html = host_html +"<td>"+host.MACAddress._value+"</td>";
						//host_html = host_html +"<td>"+layer2Interface+"</td>";
						if(active)host_html = host_html +"<td><span class='label label-success'>"+device_online+"</span></td>";
						else host_html = host_html +"<td><span class='label label-danger'>"+device_not_online+"</span></td>";
						host_html = host_html + "</tr>";
						$('#hosts_tbody').append(host_html);
					}
				}*/
       	 	}
        	hideMask();
        },
        error: function(){
     		alert(msg_unsuccess);
     		hideMask();
        }         
     });
}
function showTaskById(){
	$('#task_tbody').empty();
	$.ajax({
        type:"POST",
        url: basePath+"task/showTaskById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('deviceModal');
        },
        success:function(json){
        	var data = $.parseJSON(json);
       	 	if(data!=null){
       	 		$('#task_tbody').empty();
       	 		for(var i=0;i<data.length;i++){
	       	 		var task_html = "<tr>"
		                   +"     	<td>"+(i+1)+"</td>"
		                   +"     	<td>"+data[i].name+"</td>"
						   +"		<td>"+data[i].timestamp.replace("T"," ").replace("Z","")+"</td>"
						   +"		<td>0</td>"
						   +"		<td></td>"
						   +"		<td><button type='button' class='btn btn-link' onclick=\"destroyTaskById('"+data[i]._id+"','"+$('#device_id').html()+"')\">Destroy</button></td>"
						   +"	</tr>";
       	 			$('#task_tbody').append(task_html);
       	 		}
       	 	}
       	 	hideMask();
        },
        error: function(){
     		alert(msg_unsuccess);
     		hideMask();
        }         
     });
}
function destroyTaskById(id,device_id){
	if (confirm(task_destroy)){
		$.ajax({
	        type:"POST",
	        url: basePath+"task/destroyTaskById",
	        data:{'taskId' : id,'deviceId' : device_id},
	        datatype: "json",
	        success:function(text){
	        	if(text!="error"){
	        		alert(text)
	        		showTaskById();
	        	}
	        },
	        error: function(){
	     		alert(msg_unsuccess);
	        }
	     });
	}
}
function refreshObject(id,name){
	$.ajax({
        type:"POST",
        url: basePath+"task/refreshObject",
        data:{id : id,name : name},
        datatype: "json",
        success:function(text){
        	if(text!="error"){
        		alert(refresh_success)
        	}
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
}
function showParametersById(){
	var sipnner = null;
	$.ajax({
        type:"POST",
        url: basePath+"device/showParametersById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('deviceModal');
        },
        success:function(json){
        	var datas = $.parseJSON(json);
        	parameterList = datas;
        	reflushParameters("");
//        	var data = null;
//        	if(datas.length>0)data=datas[0];
       	 	/*if(datas!=null){
       	 		$('#device_parameters_context').empty();
       	 		//parameterList = new Array();
       	 		//listJSON(data);
       	 		for(var i=0;i<datas.length;i++){
	       	 		var	parameter = datas[i];
	       	 		var parameterHtml ='<tr>'
								  +'	<td>'+parameter.key+'</td>'
								  +'	<td class="param_value"><strong>'+parameter.value+'</strong></td>'
								  +'    <td class="param_control">';
		       	 	if(parameter.writable==true||parameter.key=="summary.ip"||parameter.key=="summary.wlanSsid")parameterHtml = parameterHtml +'<button type="button" class="btn btn-link btn-param btn-edit">Edit</button>';
			 			parameterHtml = parameterHtml +'<button type="button" class="btn btn-link btn-param btn-refresh">Refresh</button>'
			 			parameterHtml = parameterHtml +'</td>';
								  +'</tr>';
       	 			$('#device_parameters_context').append(parameterHtml);
       	 		}
       	 		activateButton();
       	 	}*/
        	hideMask();
        },
        error: function(){
     		alert(msg_unsuccess);
     		hideMask();
        }         
     });
}
var parameterList = new Array();
function reflushParameters(value){
	$('#device_parameters_context').empty();
	if(parameterList!=null){
		for(var i=0;i<parameterList.length;i++){
			if(parameterList[i].key.toLowerCase().indexOf(value.toLowerCase())>=0){
	       	 	var parameterHtml ='<tr>'
								  +'	<td>'+parameterList[i].key+'</td>'
								  +'	<td class="param_value"><strong>'+parameterList[i].value+'</strong></td>'
								  +'    <td class="param_control">';
	       	 	if(parameterList[i].writable==true||parameterList[i].key=="summary.ip"||parameterList[i].key=="summary.wlanSsid")parameterHtml = parameterHtml +'<button type="button" class="btn btn-link btn-param btn-edit">Edit</button>';
		 			//parameterHtml = parameterHtml +'<button type="button" class="btn btn-link btn-param btn-refresh">Refresh</button>'
		 			parameterHtml = parameterHtml +'</td>';
									  +'</tr>';
	       	 		$('#device_parameters_context').append(parameterHtml);
			}
		}
		activateButton();
 	}
}
function activateButton(){
	$('.btn-edit').on("click",function(){
		var parent = $(this).parent().parent().children().first().html();
		var parameter = getParameterByKey(parent);
		//alert(parameter.key+"="+parameter.value+"="+parameter.type);
		editDeviceInfo(parameter.key,parameter.value,parameter.type);
	});
	$('.btn-refresh').on("click",function(){
		if (confirm(task_refresh_parameter)){
			var parent = $(this).parent().parent().children().first().html();
			refreshObject($('#device_id').html(),parent);
		}
	});
}
function getParameterByKey(key){
	for(var i=0;i<parameterList.length;i++){
		if(key==parameterList[i].key)return parameterList[i];
	}
}
function listJSON(json,ukey){
	if(json["_writable"]!=undefined&&json["_type"]!=undefined&&json["_value"]!=undefined){
		parameterList.push(createParameter(ukey,json["_value"],json["_type"],json["_writable"]));
	}
    for(var key in json){
    	if(key.indexOf("X_")!=0&&typeof(json[key])=="object"){
    		if(ukey==null||ukey=="")listJSON(json[key],key);
    		else listJSON(json[key],ukey + "."+key);
    	}
    }  
}
function addtag() {
    var tag;
    tag = window.prompt('Enter new tag:').trim();
    if (tag) {
    	$.ajax({
            type:"POST",
            url: basePath+"device/addTagById",
            data:{'id' : $('#device_id').html(),'tagName':tag},
            datatype: "json",
            success:function(json){
            	var data = $.parseJSON(json);
           	 	if(data!=null){
	           	 	var tags = data[0]._tags;
	       	 		createTags(tags);
           	 	}
            },
            error: function(){
         		alert("unsuccess");
            }         
         });
    }
}
function removeTag(tagName){
	if (confirm("remove this tagName fro "+tagName)){
		$.ajax({
            type:"POST",
            url: basePath+"device/removeTagById",
            data:{'id' : $('#device_id').html(),'tagName':tagName},
            datatype: "json",
            success:function(json){
            	var data = $.parseJSON(json);
           	 	if(data!=null){
	           	 	var tags = data[0]._tags;
	       	 		createTags(tags);
           	 	}
            },
            error: function(){
         		alert("unsuccess");
            }         
         });
	}
}
function deviceRebootById(){
	$.ajax({
        type:"POST",
        url: basePath+"task/deviceRebootById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        success:function(json){
        	alert(json);
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
}
function deviceFactoryResetById(){
	$.ajax({
        type:"POST",
        url: basePath+"task/deviceFactoryResetById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        success:function(text){
        	if(text!="error"){
        		alert(task_factoryReset_success);
        	}
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
}
function deviceRefreshById(){
	$.ajax({
        type:"POST",
        url: basePath+"task/refreshDeviceById",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        success:function(text){
        	if(text!="error"){
        		alert(task_refresh_success);
        	}
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
}
function downloadById(){
	$('#device_id').html();
	$.ajax({
        type:"POST",
        url: basePath+"files/getFilesByDevice",
        data:{'id' : $('#device_id').html()},
        datatype: "json",
        beforeSend: function () {
            createLoadMask('deviceModal');
        },
        success:function(text){
        	var data = $.parseJSON(text);
        	$('#filesList_tbody').empty();
        	for(var i=0;i<data.length;i++){
    			var file_html = "<tr>"
    						     +"  	<td><input type='radio' id='device_"+data[i]._id+"' name='fileSelect' value='"+data[i]._id+"'/></td>"
    						     +"		<td>"+data[i].filename+"</td>"
    						     +"		<td>"+data[i].metadata.fileType+"</td>"
    						     +"	</tr>";
    			$('#filesList_tbody').append(file_html);
        	}
        	$('#filesListModal').modal('show');
        	hideMask();
        },
        error: function(){
     		alert(msg_unsuccess);
     		hideMask();
        }
     });
}
function submitSelectFiles(){
	var tdlist = $("input[name='fileSelect']:checked").parent().parent().children('td');
	$.ajax({
        type:"POST",
        url: basePath+"task/downloadFile",
        data:{'id':$('#device_id').html(),'fileId' : $("input[name='fileSelect']:checked").val(),'fileName' : $(tdlist[1]).html()},
        datatype: "json",
        success:function(text){
        	if(text!="error"){
        		$('#filesListModal').modal('hide');
        		alert(task_download_success);
        	}
        },
        error: function(){
     		alert(msg_unsuccess);
        }         
     });
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
function createPreParameter(name,key,value,type) {
    var parameter = new Object();
    parameter.name = name;
    parameter.key = key;
    parameter.value = value;
    parameter.type = type;
    return parameter;
}
function createParameter(key,value,type,writable) {
    var parameter = new Object();
    parameter.key = key;
    parameter.writable = writable;
    parameter.value = value;
    parameter.type = type;
    parameter.getKey = function () {
        return this.key;
    }
    parameter.getWritable = function () {
        return this.writable;
    }
    parameter.getType = function () {
        return this.type;
    }
    return parameter;
}