/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: HelloServletTest
 * Author:   Stephen.Liu
 * Date:     2019/3/15 下午 02:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package org.gradle.demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Stephen.Liu
 * @create 2019/3/15
 * @since 1.0.0
 */
public class HelloServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        new HelloServlet().doGet(httpServletRequest,httpServletResponse);

        assertEquals("Hello This is Stephen", stringWriter.toString());
    }

    @Test
    public void doPostWithoutName() throws Exception{
        when(httpServletRequest.getRequestDispatcher("response.jsp"))
                .thenReturn(requestDispatcher);

        new HelloServlet().doPost(httpServletRequest,httpServletResponse);

        verify(httpServletRequest).setAttribute("user","Stephen");
        verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
    }

    @Test
    public void doPostWithName() throws Exception{
        when(httpServletRequest.getParameter("name")).thenReturn("Stephen");
        when(httpServletRequest.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

        new HelloServlet().doPost(httpServletRequest,httpServletResponse);

        verify(httpServletRequest).setAttribute("user","Stephen");
        verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
    }


}
