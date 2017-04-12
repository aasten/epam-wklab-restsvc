package com.epam.wklab.restsvc.client;

import com.epam.wklab.restsvc.beans.Teacher;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by sten on 12.04.17.
 */
public class TestRestSimple {
    private static final Client CLIENT;
    static {
        ClientConfig clientConfig = new DefaultClientConfig();
//        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        CLIENT = Client.create(clientConfig);
    }

    public static void main(String[] args) {
        try {

            String resource;
            resource = "http://localhost:9090/restservice/teachers/teacher/";
            Teacher t = new Teacher();
            t.setBirthDay(new Date());
            t.setName("A");
            viewResponse(CLIENT.resource(resource).accept("application/xml").post(ClientResponse.class, t));

            resource = "http://localhost:9090/restservice/teachers/teacher/1";
            WebResource webResource = CLIENT.resource(resource);
            viewResponse(CLIENT.resource(resource).accept("application/xml").get(ClientResponse.class));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewResponse(ClientResponse response) {
        if (response.getStatus() < 200 || response.getStatus() > 299) {
            System.err.println("Failed : HTTP error code : "
                    + response.getStatus() + "; Server says: " + response.getEntity(String.class));
        } else {
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n");
            System.out.println(output);
        }
    }

}