package bj.edem.softcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SoftcareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftcareApplication.class, args);
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setPrefix("/WEB-INF/views/");
        r.setSuffix(".jsp");
        return r;
    }

}
