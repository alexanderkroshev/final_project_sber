package client.controller;

import client.dto.BalanceDTO;
import client.service.CardServiceApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;




@RestController
@AllArgsConstructor
public class ATMController {

    private static final String AUTHENTICATION_URL = "http://localhost:8083/api/v1/auth/login";
    private static final String HELLO_URL = "http://localhost:8083/card/hi";


    @GetMapping("/developers")
    public String getBalance() throws JsonProcessingException {
//                return  ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
//                        HttpMethod.POST, authenticationEntity, ResponseToken.class);getBody();
//
//
       /* RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3OCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNjQ4NDgxOTM1LCJleHAiOjE2NDkwODY3MzV9.abj55eFhl8IsE4pyOoppBBUwUOQIb3NA97g0ar5y6kY";
        headers.set("Authorization", token);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        // Use Token to get Response

        ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET,
                jwtEntity, String.class);
        System.out.println(helloResponse.getBody());
        ResponseEntity<String> res = ResponseEntity.status(HttpStatus.OK).headers(headers);

//        ResponseEntity<String> testResponse() {
//            return ResponseEntity.ok()
//                    .headers("Authorization", token);
//        }*/
        return null;
    }

    @GetMapping("/test")
    public String handle() throws JsonProcessingException {


/*  тут всё норм работает!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("test");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3OCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNjQ4NDgxOTM1LCJleHAiOjE2NDkwODY3MzV9.abj55eFhl8IsE4pyOoppBBUwUOQIb3NA97g0ar5y6kY";
        headers.set("Authorization", token);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
                String.class);
        return helloResponse.getBody();
    }*/

        String response =null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        Card authenticationCard = getAuthenticationCard();
        String authenticationBody = getBody(authenticationCard);
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> authenticationEntity = new HttpEntity<>(authenticationBody,
                authenticationHeaders);
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3OCIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNjQ4NDgxOTM1LCJleHAiOjE2NDkwODY3MzV9.abj55eFhl8IsE4pyOoppBBUwUOQIb3NA97g0ar5y6kY";
        ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, authenticationEntity, ResponseToken.class);

        String token = "Bearer " + authenticationResponse.getBody().getToken();
        headers.set("Authorization", token);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
                    String.class);
            response= helloResponse.getBody();
        }
        return response;
    }





  /*  @GetMapping("/test2")
    public String get() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String response = null;
        Card authenticationCard = getAuthenticationCard();
        String authenticationBody = getBody(authenticationCard);
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> authenticationEntity = new HttpEntity<>(authenticationBody,
                authenticationHeaders);
        ResponseEntity<ResponseToken> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, authenticationEntity, ResponseToken.class);

        System.out.println(authenticationResponse.getStatusCode().equals(HttpStatus.OK));

        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
            String token = "Bearer " + authenticationResponse.getBody().getToken();
            HttpHeaders headers = getHeaders();
            headers.set("Authorization", token);
            HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
            // Use Token to get Response
            ResponseEntity<String> helloResponse = restTemplate.exchange(HELLO_URL, HttpMethod.GET, jwtEntity,
                    String.class);

            System.out.println(helloResponse.getStatusCode());

            if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
                response = helloResponse.getBody();
            }
        }

        return response;
    }
*/

    private Card getAuthenticationCard() {
        Card card = new Card();
        card.setCardNumber("12345242");
        card.setCardPassword("1111");
        return card;
    }

    private String getBody(Card card) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(card);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

}

