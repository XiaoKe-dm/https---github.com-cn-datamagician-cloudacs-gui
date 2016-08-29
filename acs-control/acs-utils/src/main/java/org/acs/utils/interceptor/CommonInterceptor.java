package org.acs.utils.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acs.utils.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter{
	private final Logger log = LogManager.getLogger(CommonInterceptor.class); 
    /* 
     * 利用正则映射到需要拦截的路径     
      
    private String mappingURL; 
     
    public void setMappingURL(String mappingURL) {     
               this.mappingURL = mappingURL;     
    }    
  */  
    /**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */    
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
        /*if ("GET".equalsIgnoreCase(request.getMethod())) {  
            RequestUtil.saveRequest();  
        }  */
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        
        if(("/user/login").equals(url)){
        	return true;
        }
        User user =  (User)request.getSession().getAttribute(User.SESSION_USERNAME);  
        if(user == null){
        	if(request.getHeader("x-requested-with") != null && 
        	    request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
        	    PrintWriter wirter =  response.getWriter();
        	    wirter.write("timeout");
        	    wirter.flush();
        	}else{
        		response.sendRedirect(contextPath+"?"+User.LOGIN_MEG+"="+User.NOSESSION_INFO);
        	}
        	return false;
        }else  
            return true;
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
        if(modelAndView != null &&!"redirect:/".equals(modelAndView.getViewName())){    
            modelAndView.addObject(User.LOGIN_MEG, User.NOSESSION_INFO);    
        }
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {
        //log.info("==============: 3、afterCompletion================");    
    }    
}
