package exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
        ex.printStackTrace();
        
        GlobalException globalException = null;
         
        //如果抛出的是系统自定义异常则直接转换
        if(ex instanceof GlobalException){
        	globalException = (GlobalException)ex;
        }else{
            //如果抛出的不是系统自定义异常则重新构造一个未知错误异常。
        	globalException = new GlobalException("未知错误，请与系统管理 员联系！");
        }
         
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", globalException.getMessage());
        modelAndView.setViewName("/jsp/error.jsp");
   
        return modelAndView;
}
}