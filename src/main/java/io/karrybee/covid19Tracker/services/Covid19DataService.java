package io.karrybee.covid19Tracker.services;
import io.karrybee.covid19Tracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class Covid19DataService {

    private static  String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats=new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
/*
*/

//this method converts the string into the URI
/*
Tells the method to run after some time .
* denotes sec,min,hrs,day,week,year.
this methods runs each and every hr
 */
@Scheduled(cron="* 1 * * * *")
    public void fetchData() throws IOException,InterruptedException {
     List<LocationStats> newStats=new ArrayList<>();
        //create the request using builder pattern
HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL))
                .build();
        //synchronous send
        //take the body and return it as a a string
        HttpResponse<String> httpResponse =client.send(request, HttpResponse.BodyHandlers.ofString());

        //testing the string
    //Can be be converted to some objects(string)using commons library

    StringReader csvBodyReader=new StringReader(httpResponse.body());


    //Getting the headers automatically
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);


    for (CSVRecord record : records) {
       LocationStats locationStat=new LocationStats();
       locationStat.setState(record.get("Province/State"));
       locationStat.setCountry(record.get("Country/Region"));
       int latestCases=Integer.parseInt(record.get(record.size()-1));
       int previousCases=Integer.parseInt(record.get(record.size()-2));
       locationStat.setLatestTotalCases(latestCases);
       locationStat.setDiffFromPrevDay(latestCases-previousCases );
        System.out.println(locationStat);
        newStats.add(locationStat);
    }
  this.allStats=newStats;

    }

}
