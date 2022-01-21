package web.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import web.entity.User;

import java.util.Arrays;
import java.util.List;

@Component
public class RestClient {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public RestClient(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie", String.join(";", restTemplate.headForHeaders(URL).get("Set-cookie")));
    }

    private final String URL = "http://91.241.64.178:7081/api/users";

    public void getUsersList() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        List<String> cookie = response.getHeaders().get("Cookie");
        System.out.println("All users cookie " + cookie);
        System.out.println("Get all users " + response.getBody() + "\n");
    }

    public void createUsers() {
        User user = new User(3L, "James", "Brown", (byte) 22);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, request, String.class);
        System.out.println("Created user " + user);
        System.out.println(response.getBody() + "\n");
    }

    public void updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 33);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("User updated to " + user);
        System.out.println(response.getBody() + "\n");
    }

    public void deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        List<String> cookie = response.getHeaders().get("Cookie");
        System.out.println("Deleted user " + cookie);
        System.out.println(response.getBody());
    }
}
