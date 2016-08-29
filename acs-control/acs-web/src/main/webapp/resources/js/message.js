var msg_unsuccess;
var refresh_success;
var device_online;
var device_not_online;
var device_update_parameter_success;
var device_update_parameter_error;
var task_destroy;
var task_factoryReset_success;
var task_refresh_success;
var task_refresh_parameter;
var task_download_success;
var modules_name_notempty;
var modules_productClass_notempty;
var modules_oui_notempty;
var oper_delete_info;
var user_edit_error_info;
var user_sure_reset_password;
$(function(){
    jQuery.i18n.properties({
        name : 'strings', //资源文件名称
        path : '/acs-web/resources/i18n/', //资源文件路径
        mode : 'map', //用Map的方式使用资源文件中的值
//        language : 'zh',
        callback : function() {//加载成功后设置显示内容
        	msg_unsuccess = $.i18n.prop('unsuccess');
        	refresh_success = $.i18n.prop('refresh.success');
        	device_online = $.i18n.prop('device.online');
        	device_not_online = $.i18n.prop('device.not.online');
        	device_update_parameter_success = $.i18n.prop('device.update.parameter.success');
        	device_update_parameter_error = $.i18n.prop('device.update.parameter.error');
        	task_destroy = $.i18n.prop('task.destroy');
        	task_factoryReset_success = $.i18n.prop('task.factoryReset.success');
        	task_refresh_success = $.i18n.prop('task.refresh.success');
        	task_refresh_parameter = $.i18n.prop('task.refresh.parameter');
        	task_download_success = $.i18n.prop('task.download.success');
        	
        	modules_name_notempty = $.i18n.prop('modules.name.notempty');
        	modules_productClass_notempty = $.i18n.prop('modules.productClass.notempty');
        	modules_oui_notempty = $.i18n.prop('modules.oui.notempty');
        	
        	
        	oper_delete_info = $.i18n.prop('oper.delete.info');
        	
        	user_edit_error_info = $.i18n.prop('user.edit.error.info');
        	user_sure_reset_password = $.i18n.prop('user.sure.reset.password');
        	user_reset_password_success = $.i18n.prop('user.reset.password.success');
        	
        	
        }
    });
});
