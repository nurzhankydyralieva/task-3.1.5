package web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import web.client.RestClient;

@SpringBootApplication
public class RestTemplateTask315Application {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestTemplateTask315Application.class);
        RestClient client = context.getBean("restClient", RestClient.class);
        client.getUsersList();
        client.createUsers();
        client.updateUser();
        client.deleteUser(3L);
    }
}
