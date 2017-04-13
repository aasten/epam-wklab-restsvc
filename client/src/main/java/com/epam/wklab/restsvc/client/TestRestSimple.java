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

            // test for not-modified
            resource = "http://localhost:9090/restservice/teachers/teacher";
            req = "{ \"id\":1, \"name\": \"Petr Petrovich\", \"birthDay\": \"1980-04-23T18:25:43.511Z\", \"lessons\": [1,2] }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, req),req);
            resource = "http://localhost:9090/restservice/teachers/teacher";
            req = "{ \"id\":1, \"name\": \"Petr Petrovich\", \"birthDay\": \"1980-04-23T18:25:43.511Z\", \"lessons\": [1,3] }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, req),req);

            resource = "http://localhost:9090/restservice/teachers/teacher";
            req = "{ \"id\":2, \"name\": \"Ivan Ivanovich\", \"birthDay\": \"1985-04-23T00:00:00.000Z\", \"lessons\": [2] }";
            viewResponse(CLIENT.resource(resource).accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, req),req);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewResponse(ClientResponse response, String request) {
        System.out.println("---------------");
        System.out.println("Sent:");
        System.out.println(request);
        if (response.getStatus() < 200 || response.getStatus() > 299) {
            if (response.getStatus() >= 300 && response.getStatus() < 400) {
                System.out.println("Redirection, no output from Server [HTTP " + response.getStatus() + "]");    
            } else {
                System.err.println("Failed : HTTP error code : "
                    + response.getStatus() + "; Server says: " + response.getEntity(String.class));
            }
        } else if (response.getStatus() == 204) {
            System.out.println("No output from Server [HTTP " + response.getStatus() + "]");
        } else {
            String output = response.getEntity(String.class);
            System.out.println("Output from Server [HTTP " + response.getStatus() + "]:");
            System.out.println(output);
        }
    }

}
