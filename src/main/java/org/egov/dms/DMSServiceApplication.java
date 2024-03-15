package org.egov.dms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@ComponentScan(basePackages = { "org.egov.gidc"})
@EnableAutoConfiguration
@ComponentScan
//@EnableScheduling
//@EnableSwagger2
@Configuration
public class DMSServiceApplication extends SpringBootServletInitializer{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DMSServiceApplication.class, args);
    }

	/*
	 * @Bean public Docket api() {
	 * 
	 * return new Docket(DocumentationType.SWAGGER_2) .select()
	 * .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()) .build();
	 * 
	 * }
	 */
    
}
