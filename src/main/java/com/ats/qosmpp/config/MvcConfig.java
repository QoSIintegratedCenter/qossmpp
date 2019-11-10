package com.ats.qosmpp.config;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

  /*  @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/admin").setViewName("dashboard");
        registry.addViewController("/login").setViewName("login");
    }
*/
    /*public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }*/
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/**") // « /static/css/myStatic.css
                .addResourceLocations("classpath:/static/") // Default Static Loaction
                .setCachePeriod( 3600 )
                .resourceChain(true) // 4.1
                .addResolver(new GzipResourceResolver()) // 4.1
                .addResolver(new PathResourceResolver()); //4.1
        *//*registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);*//*
    }*/

    /* @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry
                 .addResourceHandler("/resources/**")
                 .addResourceLocations("/resources/");
     }*/
 /*   @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }*/


}
