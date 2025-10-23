package com.cinehub.config;

import jakarta.servlet.Filter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
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

    @Override
    protected Filter[] getServletFilters() {
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        return new Filter[]{openEntityManagerInViewFilter};
    }


    /*
    * Map the DispatcherServlet to "/"
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
