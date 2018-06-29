package com.example.yang2;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yang
 * @ProjectName: yang2
 * @Package: com.example.yang2
 * @Description: URL拦截器
 * @Date: Created in 10:29 2018/6/12
 */
public class SessionInterceptor implements HandlerInterceptor {

    /**
     * 前置检查
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
        登录操作不做拦截
         */
        if ("/login/".equals(request.getRequestURI()) || "/login/login".equals(request.getRequestURI()) || "/login/ValiateCoding".equals(request.getRequestURI()) ||
                "/login/validateCode".equals(request.getRequestURI())){
            return true;
        }

        /*
         注册操作不做拦截
         */
        if ("/register/".equals(request.getRequestURI()) || "/register/add".equals(request.getRequestURI())){
            return true;
        }

        /*
         退出操作不做拦截
         */
        if ("/exit".equals(request.getRequestURI())){
            return true;
        }

        /*
         对用户请求拦截，未登录转向登录页
         */
        if ("/client/borrowlist".equals(request.getRequestURI()) || "/client/list".equals(request.getRequestURI()) ||
            "/client/search".equals(request.getRequestURI()) || ("/client/show").equals(request.getRequestURI()) ||
            "/client/showborrow".equals(request.getRequestURI()) || "/user/borrow".equals(request.getRequestURI()) || "/user/renew".equals(request.getRequestURI())) {
            if (request.getSession().getAttribute("username") == null){
                response.sendRedirect("/login/");
                return false;
            }
        }

        /*
         admin请求拦截器
         */
        if ("/admin/addbook".equals(request.getRequestURI()) || "/admin/back".equals(request.getRequestURI()) ||
            "/admin/borrowlist".equals(request.getRequestURI()) || "/admin/delete".equals(request.getRequestURI()) ||
            "/admin/list".equals(request.getRequestURI()) || "/admin/searchuserborrow".equals(request.getRequestURI()) ||
            "/admin/update".equals(request.getRequestURI()) || "admin/userborrow".equals(request.getRequestURI())){
            if (request.getSession().getAttribute("username") == null){
                response.sendRedirect("/login/");
                return false;
            }
            if ("general".equals(request.getSession().getAttribute("power"))){
                response.sendRedirect("/tip");
                return false;
            }
        }

        if ("/root/addadmin".equals(request.getRequestURI()) || "/root/adminlist".equals(request.getRequestURI()) ||
            "/root/adminmsglist".equals(request.getRequestURI()) || "/root/admin/msgupdata".equals(request.getRequestURI()) ||
            "/root/adminusermsglist".equals(request.getRequestURI()) || "/root/deleteadmin".equals(request.getRequestURI()) ||
            "/root/deleteusermsg".equals(request.getRequestURI()) || "/root/searchadmin".equals(request.getRequestURI()) ||
            "/root/setpassword".equals(request.getRequestURI()) || "/root/setuserpassword".equals(request.getRequestURI()) ||
            "/root/updatausermsg".equals(request.getRequestURI()) || "/root/userlist".equals(request.getRequestURI()) || "/root/usersearch".equals(request.getRequestURI())){

            if (request.getSession().getAttribute("username") == null){
                response.sendRedirect("/login/");
                return false;
            }

            if (!"root".equals(request.getSession().getAttribute("power"))){
                response.sendRedirect("/tip");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
