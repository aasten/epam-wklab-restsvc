package com.epam.wklab.restsvc.server;

import com.epam.wklab.restsvc.beans.Lesson;
import com.epam.wklab.restsvc.beans.Teacher;
import com.epam.wklab.restsvc.dao.RAMLessonsDAO;
import com.epam.wklab.restsvc.dao.RAMTeachersDAO;
import com.epam.wklab.restsvc.dao.TeachersDAO;
import com.epam.wklab.restsvc.dao.LessonsDAO;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.wsdl.extensions.mime.MIMEContent;
import java.util.Date;

/**
 * Created by sten on 12.04.17.
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_XML)
public class TeacherService {

    // Teachers DAO singleton
    private enum TeachersDAOAccess {
        INSTANCE;
        private TeachersDAO teachersDAO = new RAMTeachersDAO();

        public TeachersDAO getTeachersDAO() { return teachersDAO; }
    }

    // Lessons DAO singleton
    private enum LessonsDAOAccess {
        INSTANCE;
        private LessonsDAO LessonsDAO = new RAMLessonsDAO();

        public LessonsDAO getLessonsDAO() { return LessonsDAO; }
    }

    @GET
    public Response getBusiestTeacher() {
        return null;
    }
    
    // teachers

    @POST
    @Path("/teachers/teacher")
    public Response createTeacher(Teacher newTeacher) {
        return Response.status(Response.Status.CREATED).entity(
                TeachersDAOAccess.INSTANCE.getTeachersDAO().create(newTeacher)).build();
    }

    @GET
    @Path("/teachers/teacher/{id}")
    public Response getTeacher(@PathParam("id")Integer id) throws TeacherNotFoundException {
        return Response.status(Response.Status.OK).entity(
                TeachersDAOAccess.INSTANCE.getTeachersDAO().get(id)).build();
    }

    @PUT
    @Path("/teachers/teacher")
    public Response updateTeacher(Teacher updatedTeacher) {
        try {
            Teacher existedTeacher = TeachersDAOAccess.INSTANCE.getTeachersDAO().get(updatedTeacher.getId());
            if(null != existedTeacher) {
                if(updatedTeacher.equals(existedTeacher)) {
                    return Response.status(Response.Status.NOT_MODIFIED).build();
                }
            }
        } catch(TeacherNotFoundException e) {
            // do nothing, this is exactly new teacher
        }

        return Response.status(Response.Status.NO_CONTENT).entity(
                TeachersDAOAccess.INSTANCE.getTeachersDAO().update(updatedTeacher)).build();
    }

    @DELETE
    @Path("/teachers/teacher/{id}")
    public Response deleteTeacher(@PathParam("id")Integer id) {
        TeachersDAOAccess.INSTANCE.getTeachersDAO().delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    // lessons

    @POST
    @Path("/lessons/lesson")
    public Response createLesson(Lesson newLesson) {
        return Response.status(Response.Status.CREATED).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().create(newLesson)).build();
    }

    @GET
    @Path("/lessons/lesson/{id}")
    public Response getLesson(@PathParam("id")Integer id) {
        return Response.status(Response.Status.OK).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().get(id)).build();
    }

    @PUT
    @Path("/lessons/lesson")
    public Response updateLesson(Lesson updatedLesson) {
        Lesson existedLesson = LessonsDAOAccess.INSTANCE.getLessonsDAO().get(updatedLesson.getId());
        if(null != existedLesson) {
            if(updatedLesson.equals(existedLesson)) {
                return Response.status(Response.Status.NOT_MODIFIED).build();
            }
        }
        return Response.status(Response.Status.NO_CONTENT).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().update(updatedLesson)).build();
    }

    @DELETE
    @Path("/lessons/lesson/{id}")
    public Response deleteLesson(@PathParam("id")Integer id) {
        LessonsDAOAccess.INSTANCE.getLessonsDAO().delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
