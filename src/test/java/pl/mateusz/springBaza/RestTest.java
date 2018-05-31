package pl.mateusz.springBaza;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class RestTest {

    @Test
    public void randomQuotes(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://gturnquist-quoters.cfapps.io/api/random";
        QuoteResponse quoteResponse = restTemplate.getForObject(apiUrl, QuoteResponse.class);
        System.out.println("START");
        System.out.println(quoteResponse);
        System.out.println("STOP");
    }

}
