package com.lzn;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * GenericServlet学习
 *
 * @author lin19 2019/09/21 23:07
 */
public class FirstGenericServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello genericServlet!!!");
    }
}
