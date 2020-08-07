package ir.housework.rest.webserives.restfulwebserives.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class SpringConfiguration {

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver localeResolver =
                new AcceptHeaderLocaleResolver();

        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessage(){
        ResourceBundleMessageSource resourceMessage =
                new ResourceBundleMessageSource();
//        resourceMessage.setBasename("messages");
        return resourceMessage;
    }
}
