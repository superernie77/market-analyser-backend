package se77.rmtcalc;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;


@SpringBootApplication
public class AppStarter implements CommandLineRunner{

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);

    }
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ObjectMapper getMapper() {
        ObjectMapper jackson = new ObjectMapper();
        jackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson.enable(SerializationFeature.INDENT_OUTPUT);
        jackson.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        jackson.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        jackson.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        return jackson;
    }

}
