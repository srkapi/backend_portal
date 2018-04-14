package com.srkapi.auth.api.config;

import com.srkapi.common.async.AsyncConfig;
import com.srkapi.common.cache.CacheConfig;
import com.srkapi.common.sse.SseConfig;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({AsyncConfig.class, SseConfig.class,  CacheConfig.class})
public class AppConfiguration {


	
	@Bean
    public PasswordEncoder loadPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * allow all origins - only for development mode
	 * @return
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**")
            	.allowedMethods("*");
            }
        };
	}
	
	@Bean
    public CacheManager cacheManager() {
        return CacheConfig.createCacheManager(
        		"UserDaoImpl"
        		, "RoleDaoImpl"
        		, "PermissionDaoImpl");
    }
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
