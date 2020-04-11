package io.karrybee.covid19Tracker.services;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class Covid19DataService {

    private static  String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
@PostConstruct
//this method converts the string into the URI
    public void fetchData() throws IOException,InterruptedException {

        //create the request using builder pattern
HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL))
                .build();
        //synchronous send
        //take the body and return it as a a string
        HttpResponse<String> httpResponse =client.send(request, HttpResponse.BodyHandlers.ofString());

        //testing the string
        System.out.println(httpResponse.body());

    }

}
