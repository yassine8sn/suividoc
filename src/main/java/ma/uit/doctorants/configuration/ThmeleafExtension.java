package ma.uit.doctorants.configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.thymeleaf.cache.StandardCacheManager;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.slf4j.Logger;

@Configuration
public class ThmeleafExtension {


    @Autowired
    private Environment env;

    @Bean
    public TemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/calendar/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("LEGACYHTML5");
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }

    @Bean
    public TemplateResolver templateResolver1() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/views/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addTemplateResolver(templateResolver1());
        engine.addTemplateResolver(templateResolver());
        engine.addDialect(new LayoutDialect());

        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }

    @Bean
    public StandardCacheManager thymeleafCacheManager() {
        StandardCacheManager cacheManager = new StandardCacheManager();
        cacheManager.setTemplateCacheMaxSize(500);
        cacheManager.setFragmentCacheMaxSize(1000);
        return cacheManager;
    }
}