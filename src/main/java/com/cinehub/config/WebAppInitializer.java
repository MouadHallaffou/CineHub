package com.cinehub.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    * Root application context configuration classes
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /*
    * No servlet-specific configuration classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /*
    * Map the DispatcherServlet to "/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
