package kataacademy.resttemplate;

import kataacademy.resttemplate.config.WebConfig;
import kataacademy.resttemplate.controllers.RestTemplateController;
import kataacademy.resttemplate.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class Application {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        RestTemplateController controller = context.getBean("restTemplateController", RestTemplateController.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers = controller.getSessionId(headers);
        System.out.println("Cookie после добавления:\n" + headers.get("Cookie"));

        HttpEntity<Object> entity = new HttpEntity<>(new User(3, "James", "Brown", 23), headers);

        controller.getFirstPhrase(entity);
        entity = new HttpEntity<>(new User(3, "Thomas", "Shelby", 23), headers);

        controller.getSecondPhrase(entity);

        controller.getThirdPhrase(entity);

        System.out.println("Ответ:\n"+controller.key.toString());
    }
}