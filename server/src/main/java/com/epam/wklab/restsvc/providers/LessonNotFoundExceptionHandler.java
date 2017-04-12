package com.epam.wklab.restsvc.providers;

import com.epam.wklab.restsvc.beans.RESTException;
import com.epam.wklab.restsvc.server.LessonNotFoundException;
import com.epam.wklab.restsvc.server.TeacherNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by sten on 12.04.17.
 */
@Provider
public class LessonNotFoundExceptionHandler implements ExceptionMapper<LessonNotFoundException> {

    public Response toResponse(LessonNotFoundException e) {
        RESTException rex = new RESTException();
        rex.setWhat(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(rex).build();
    }
}
