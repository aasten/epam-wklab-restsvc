package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Teacher;
import com.epam.wklab.restsvc.server.TeacherNotFoundException;

/**
 * Created by sten on 12.04.17.
 */
public interface TeachersDAO {

    /**
     * @return id of newly created teacher
     */
    Integer create(Teacher newTeacher);

    void delete(Integer id);
    Teacher get(Integer id) throws TeacherNotFoundException;

    /**
     * @return previous Teacher object with same id as in updatedTeacher
     */
    Teacher update(Teacher updatedTeacher);
}
