package org.acs.core.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acs.core.model.Device;
import org.acs.core.model.Modules;
import org.acs.core.model.Preset;
import org.acs.core.model.Task;
import org.acs.core.service.DeviceService;
import org.acs.core.service.FilesService;
import org.acs.core.service.ModulesService;
import org.acs.utils.tools.HttpUtil;
import org.acs.utils.base.PageResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("files")
public class FilesController {

	@Autowired
	private FilesService filesService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ModulesService modulesService;
	
	@RequestMapping("/filesIndex")
	public String filesIndex(){
		//List<Device> deviceList = modulesService.getAllDevice();
		//modelMap.addAttribute("tasksList", deviceList);
		return "files/files_manager";
	}
	
	@RequestMapping("/getAllFiles")
	public void getAllFiles(HttpServletResponse response){
		String resultJson = filesService.getAllFiles();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getAllFilesPage")
	public void getAllFilesPage(String currentPage,String queryColumn,String queryValue,HttpServletResponse response){
		int cPage = 1;
		if(currentPage!=null)cPage=Integer.parseInt(currentPage);
		String query = "";
		if(queryValue!=null&&!"".equals(queryValue.trim())){
			query = "query={\""+queryColumn+"\":\"/"+queryValue+"/i\"}";
		}
		PageResults<String> presetPage= filesService.getAllFilesPage(query,cPage);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(presetPage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/removeFilesByName")
	public void removeFilesByName(String name,HttpServletResponse response){
		String state = filesService.removeFilesByName(name);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/updateFile")
	public void updateFile(@RequestParam("file")CommonsMultipartFile file,String fileType,String oui,String productClass,String version,HttpSession session,HttpServletResponse response){
		session.setAttribute("file", file);
		/*try{  
	        //文件类型:btnFile[0].getContentType()  
	        //文件名称:btnFile[0].getName()  
//	        if(btnFile[0].getSize()>Integer.MAX_VALUE){//文件长度  
//	            OutputUtil.jsonArrOutPut(response, JSONArray.fromObject("上传文件过大!"));  
//	        }  
	        InputStream is = file.getInputStream();//多文件也适用,我这里就一个文件  
	        //String fileName = request.getParameter("fileName");  
//	        String guid = request.getParameter("guid");  
	        byte[] b = new byte[(int)file.getSize()];  
	        int read = 0;  
	        int i = 0;  
	        while((read=is.read())!=-1){  
	            b[i] = (byte) read;  
	            i++;  
	        }  
	        is.close();  
	       // OutputStream os = new FileOutputStream(new File("D://"+guid+"."+btnFile[0].getOriginalFilename()));//文件原名,如a.txt  
	        //os.write(b);  
	       // os.flush();  
	        //os.close();  
	       // OutputUtil.jsonOutPut(response, null);  
	    }catch (Exception e) {  
	    	e.printStackTrace();
	    }  */
		System.out.println("fileName:"+file.getOriginalFilename());
		File f = new File(file.getOriginalFilename());
		String state = "";
		try {
			file.transferTo(f);
			/*File targetFile = new File("D:\\", file.getOriginalFilename());
			file.transferTo(targetFile);
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }*/  
	        
			state = filesService.updateFile(f,fileType,oui,productClass,version);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
     * 获取上传进度
     * 
     * @param file
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "process")
    public void process(HttpSession session, HttpServletResponse response) {
        // 上传进度百分比
        long processPercent = 0;
        CommonsMultipartFile file = (CommonsMultipartFile) session
                .getAttribute("file");
        if (file == null) {
            return;
        }
        long totalFileSize = file.getSize();
        long readedFileSize = file.getFileItem().getSize();
 
        System.out.println("totalFileSize:" + totalFileSize
                + ",readedFileSize:" + readedFileSize);
 
        if (totalFileSize != 0) {
            processPercent = Math.round(readedFileSize / totalFileSize) * 100;
        }
        System.out.println("processPercent:"+processPercent);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(processPercent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@RequestMapping("/getFilesByDevice")
	public void getFilesByDevice(String id,HttpServletResponse response){
		String moduleName = deviceService.getDeviceModuleById(id);
		if(moduleName!=null){
			Map<String,Object> params= new HashMap<String,Object>();
			params.put("name", moduleName);
			List<Modules> moduleList = modulesService.findByParams(Modules.class, params);
			if(moduleList!=null&&moduleList.size()>0){
				Modules modules = moduleList.get(0);
				String jsonResult = filesService.getFilesByDevice(modules.getProductClass(),modules.getOui());
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print(jsonResult);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
