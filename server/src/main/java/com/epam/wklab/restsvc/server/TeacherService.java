package com.epam.wklab.restsvc.server;

import com.epam.wklab.restsvc.beans.Id;
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
    @Path("/teachers/busiest-teacher")
    public Response getBusiestTeacher() throws TeacherNotFoundException {
        Integer idBusiest = null;
        Integer busiestMinutes = new Integer(0);
        for(Teacher t : TeachersDAOAccess.INSTANCE.getTeachersDAO().getTeachers().getTeachers()) {
            int teachersMinutes = 0;
            for(Integer lessonId : t.getLessons()) {
                try {
                    teachersMinutes += LessonsDAOAccess.INSTANCE.getLessonsDAO().get(lessonId).getDurationMinutes();
                } catch(LessonNotFoundException e) {
                    continue;
                }
            }
            if(teachersMinutes > busiestMinutes) {
                idBusiest = t.getId();
                busiestMinutes = teachersMinutes;
            }
        }
        if(null == idBusiest) {
            throw new TeacherNotFoundException("There is no busiest teacher");
        }
        return Response.status(Response.Status.OK).entity(
                TeachersDAOAccess.INSTANCE.getTeachersDAO().get(idBusiest)).build();
    }
    
    // teachers

    @GET
    @Path("/teachers")
    public Response getTeachers() {
        return Response.status(Response.Status.OK).entity(
                TeachersDAOAccess.INSTANCE.getTeachersDAO().getTeachers()).build();
    }

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
                if(updatedTeacher.toXml().equals(existedTeacher.toXml())) {         
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

    @GET
    @Path("/lessons")
    public Response getLessons() {
        return Response.status(Response.Status.OK).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().getLessons()).build();
    }

    @POST
    @Path("/lessons/lesson")
    public Response createLesson(Lesson newLesson) {
        return Response.status(Response.Status.CREATED).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().create(newLesson)).build();
    }

    @GET
    @Path("/lessons/lesson/{id}")
    public Response getLesson(@PathParam("id")Integer id) throws LessonNotFoundException {
        return Response.status(Response.Status.OK).entity(
                LessonsDAOAccess.INSTANCE.getLessonsDAO().get(id)).build();
    }

    @PUT
    @Path("/lessons/lesson")
    public Response updateLesson(Lesson updatedLesson) {
        try {
            Lesson existedLesson = LessonsDAOAccess.INSTANCE.getLessonsDAO().get(updatedLesson.getId());
            if (null != existedLesson) {
                if (updatedLesson.toXml().equals(existedLesson.toXml())) {
                    return Response.status(Response.Status.NOT_MODIFIED).build();
                }
            }
        } catch(LessonNotFoundException e) {
            // this is a new lesson
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
