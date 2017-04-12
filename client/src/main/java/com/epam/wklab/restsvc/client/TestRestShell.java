package com.epam.wklab.restsvc.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import java.util.Scanner;

/**
 * Created by sten on 12.04.17.
 */
public class TestRestShell {

    private static final Client CLIENT;
    static {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        CLIENT = Client.create(clientConfig);
    }


    private static class Shell {
        private static final Scanner SC = new Scanner(System.in);
        public void execute() {
            boolean exit = false;
            while(false == exit) {
                System.out.println(
                        "[t] - operate with teachers, [l] - operate with lessons, [q] - quit");
                String choice = SC.nextLine();
                if(choice.contains("t")) {
                    operateWithTeacher();
                } else if(choice.contains("r")) {
                    System.out.print("Teacher's id: ");
                    getTeacher(Integer.parseInt(SC.nextLine()));
                } else if(choice.contains("u")) {

                } else if(choice.contains("d")) {

                } else if(choice.contains("q")) {
                    exit = true;
                }
            }
        }
        private void operateWithTeacher() {
            boolean exit = false;
            while(false == exit) {
                System.out.println(
                    "[c] - create teacher, [r] - read teacher, [u] - update teacher, [d] - delete teacher, [q] - quit");
                String choice = SC.nextLine();
                if(choice.contains("c")) {

                } else if(choice.contains("r")) {
                    System.out.print("Teacher's id: ");
                    getTeacher(Integer.parseInt(SC.nextLine()));
                } else if(choice.contains("u")) {

                } else if(choice.contains("d")) {

                } else if(choice.contains("q")) {
                    exit = true;
                }
            }
        }
        
        private void operateWithLessons() {
            boolean exit = false;
            while(false == exit) {
                System.out.println(
                        "[c] - create lesson, [r] - read lesson, [u] - update lesson, [d] - delete lesson, [q] - quit");
                String choice = SC.nextLine();
                if(choice.contains("c")) {

                } else if(choice.contains("r")) {
                    System.out.print("Lesson's id: ");
                    getLesson(Integer.parseInt(SC.nextLine()));
                } else if(choice.contains("u")) {

                } else if(choice.contains("d")) {

                } else if(choice.contains("q")) {
                    exit = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        Shell sh = new Shell();
        sh.execute();
    }

    private static void getTeacher(int id) {
        try {

            WebResource webResource = CLIENT
                    .resource("http://localhost:9090/restservice/teachers/teacher/" + id);
            ClientResponse response = webResource.accept("application/xml")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                System.err.println("Failed : HTTP error code : "
                        + response.getStatus() + "; Server says: " + response.getEntity(String.class));
            } else {
                String output = response.getEntity(String.class);
                System.out.println("Output from Server .... \n");
                System.out.println(output);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getLesson(int id) {
        try {

            WebResource webResource = CLIENT
                    .resource("http://localhost:9090/restservice/lessons/lesson/" + id);
            ClientResponse response = webResource.accept("application/xml")
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                System.err.println("Failed : HTTP error code : "
                        + response.getStatus() + "; Server says: " + response.getEntity(String.class));
            } else {
                String output = response.getEntity(String.class);
                System.out.println("Output from Server .... \n");
                System.out.println(output);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
