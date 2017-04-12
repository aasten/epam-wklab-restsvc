package com.epam.wklab.restsvc.client;

import com.epam.wklab.restsvc.beans.Teacher;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by sten on 12.04.17.
 */
public class TestRestSimple {
    private static final Client CLIENT;
    static {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        CLIENT = Client.create(clientConfig);
    }

    public static void main(String[] args) {
        try {

            String resource;

            resource = "http://localhost:9090/restservice/lessons/lesson/";
            String req = "{ \"name\": \"Math\", \"durationMinutes\": 45 }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req),req);
            resource = "http://localhost:9090/restservice/lessons/lesson/";
            req = "{ \"name\": \"Physics\", \"durationMinutes\": 45 }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req),req);
            resource = "http://localhost:9090/restservice/lessons/lesson/";
            req = "{ \"name\": \"Informatics\", \"durationMinutes\": 45 }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req),req);

            resource = "http://localhost:9090/restservice/teachers/teacher/";
            req = "{ \"name\": \"Petr Petrovich\", \"birthDay\": \"1980-04-23T18:25:43.511Z\", \"lessons\": [1,2] }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req),req);
            resource = "http://localhost:9090/restservice/teachers/teacher/";
            req = "{ \"name\": \"Ivan Ivanovich\", \"birthDay\": \"1985-04-23T00:00:00.000Z\", \"lessons\": [3] }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, req),req);

            resource = "http://localhost:9090/restservice/teachers/teacher/1";
            WebResource webResource = CLIENT.resource(resource);
            viewResponse(CLIENT.resource(resource).accept("application/xml").get(ClientResponse.class),req);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewResponse(ClientResponse response, String request) {
        System.out.println("---------------");
        System.out.println("Sent:");
        System.out.println(request);
        if (response.getStatus() < 200 || response.getStatus() > 299) {
            System.err.println("Failed : HTTP error code : "
                    + response.getStatus() + "; Server says: " + response.getEntity(String.class));
        } else {
            String output = response.getEntity(String.class);
            System.out.println("Output from Server [HTTP " + response.getStatus() + "]:");
            System.out.println(output);
        }
    }

}
