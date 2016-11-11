package com.wenhao.netshop.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.security.AccessController;

/**
 * Created by lw on 2016/11/11.
 */
public class RoleFilter extends AccessControlFilter {

    private String logingUrl = "localhost:8080";
    private String unauthorizedUrl = "localhost:8080/unauthorized.jsp";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        String[] arra = (String[]) o;
        Subject subject = getSubject(request, response);
        for (String role : arra) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            saveRequest(request);
            WebUtils.issueRedirect(request, response, logingUrl);
        }else {
            WebUtils.issueRedirect(request, response, unauthorizedUrl);
        }
        return false;
    }
}
