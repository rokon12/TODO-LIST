package com.codexplo.todo.web;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.test.web.ModelAndViewAssert.assertAndReturnModelAttributeOfType;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 1/7/13
 * Time: 1:26 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml"})
public class TodoControllerTest {
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    @Autowired
    private TodoController controller;
    private RequestMappingHandlerAdapter handlerAdapter;
    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        handlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
    }

    @Test
    public void index() throws Exception {
        request.setRequestURI("/index");
        final String expectedMessage = "Hello, here were are going fetch all our todos";
        final String requestUri = "/api/todo/get";
        final String message;
        final Object handler;
        final HandlerMethod expectedHandlerMethod;
        final ModelAndView mav;
        final MockHttpServletRequest request;
        final MockHttpServletResponse response;

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        request.setRequestURI(requestUri);

        expectedHandlerMethod = new HandlerMethod(controller, "index", Model.class);
        handler = this.getHandler(request);
        // For the most part we will be expecting HandlerMethod objects to be returned for our controllers.
        // Calling the to string method will print the complete method signature.
        Assert.assertEquals("Correct handler found for request url: " + requestUri, expectedHandlerMethod.toString(), handler.toString());

        // Handle the actual request
        mav = handlerAdapter.handle(request, response, handler);

        // Ensure that the right view is returned
        assertViewName(mav, "index");
        // Ensure that the view will receive the message object and that it is
        // a string
        message = assertAndReturnModelAttributeOfType(mav, "message", String.class);
        // We can test the message in case
        Assert.assertEquals(("Message returned was " + expectedMessage), expectedMessage, message);
    }




    private Object getHandler(MockHttpServletRequest request) throws Exception {
        HandlerExecutionChain chain = null;

        Map<String, HandlerMapping> map = applicationContext.getBeansOfType(HandlerMapping.class);
        Iterator<HandlerMapping> itt = map.values().iterator();

        while (itt.hasNext()) {
            HandlerMapping mapping = itt.next();
            chain = mapping.getHandler(request);
            if (chain != null) {
                break;
            }

        }

        if (chain == null) {
            throw new InvalidParameterException("Unable to find handler for request URI: " + request.getRequestURI());
        }

        return chain.getHandler();
    }
}
