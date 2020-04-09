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
         
        //����׳�����ϵͳ�Զ����쳣��ֱ��ת��
        if(ex instanceof GlobalException){
        	globalException = (GlobalException)ex;
        }else{
            //����׳��Ĳ���ϵͳ�Զ����쳣�����¹���һ��δ֪�����쳣��
        	globalException = new GlobalException("δ֪��������ϵͳ���� Ա��ϵ��");
        }
         
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", globalException.getMessage());
        modelAndView.setViewName("/jsp/error.jsp");
   
        return modelAndView;
}
}