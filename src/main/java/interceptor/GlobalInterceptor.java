package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import pojo.User;

public class GlobalInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        User user= (User) request.getSession().getAttribute("user");
        if(user !=null) {
            return true;
        }else {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return false;
        }
        
   }
}
