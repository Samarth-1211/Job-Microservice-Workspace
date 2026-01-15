package com.JobApplication.reviewms.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.JobApplication.reviewms.exceptions.ResourceNotFoundException;

@Configuration
public class RestConfig {

    @Value("${companyms.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("companymsRestClient")
    RestClient getCompanyObject(){
        return RestClient.builder()
                            .baseUrl(BASE_URL)
                            .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                            .defaultStatusHandler(HttpStatusCode :: is5xxServerError ,(req,res)->{
                                throw new ResourceNotFoundException("Server Side Error Occured");

                            })
                            // 404, 400, etc.
                            .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                                    (req, res) -> {
                                        throw new ResourceNotFoundException(
                                                "Company with given ID does not exist");
                                    })
                            .build();
        
    }
    
}
