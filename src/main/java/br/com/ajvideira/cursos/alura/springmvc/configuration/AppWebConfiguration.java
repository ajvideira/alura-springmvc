package br.com.ajvideira.cursos.alura.springmvc.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackages = "br.com.ajvideira.cursos.alura.springmvc")
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");

		//Descomentar para ter todos os beans como atributos, pode ser arriscado
		// resolver.setExposeContextBeansAsAttributes(true);

		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService conversionService = new FormattingConversionService();

		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);

		return conversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/arquivos-imagem/**").addResourceLocations("/arquivos-imagem/");
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }
 
    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }
    
    @Bean
    public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
    	List<ViewResolver> viewResolvers = new ArrayList<>();
    	viewResolvers.add(internalResourceViewResolver());
    	viewResolvers.add(new JsonViewResolver());
    	
    	ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    	resolver.setContentNegotiationManager(manager);
    	resolver.setViewResolvers(viewResolvers);
    	
    	return resolver;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	configurer.enable();
    	//super.configureDefaultServletHandling(configurer);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	 
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("language");
    	
    	
    	registry.addInterceptor(localeChangeInterceptor);
    }
    
    @Bean
    public LocaleResolver localeResolver() {
    	CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    	
    	return localeResolver;
    }
    
    @Bean
    public MailSender mailSender() {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	
    	Properties mailProperties = System.getProperties();
    	mailProperties.put( "mail.smtp.host", "prosmtp.procempa.com.br" );
    	//mailProperties.put("mail.smtp.auth", true);
    	//mailProperties.put("mail.smtp.starttls.enable", true);
    	
    	mailSender.setJavaMailProperties(mailProperties);
    	
    	return mailSender;
    }

}
