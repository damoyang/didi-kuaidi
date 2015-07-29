package ddhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
public class AppRun extends SpringBootServletInitializer{
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(AppRun.class);
	    }
	public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
