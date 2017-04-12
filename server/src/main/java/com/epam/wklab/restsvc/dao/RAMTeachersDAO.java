package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Teacher;
import com.epam.wklab.restsvc.server.TeacherNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sten on 12.04.17.
 */
public class RAMTeachersDAO implements TeachersDAO {

    private Map<Integer, Teacher> teachersMap = new ConcurrentHashMap<Integer, Teacher>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Integer create(Teacher newTeacher) {
        Integer id = idCounter.addAndGet(1);
        newTeacher.setId(id);
        teachersMap.put(id, newTeacher);
        return id;
    }

    public void delete(Integer id) {
        teachersMap.remove(id);
    }

    @Override
    public Teacher get(Integer id) throws TeacherNotFoundException {
        Teacher ret = teachersMap.get(id);
        if(null == ret) {
            throw new TeacherNotFoundException("Teacher with id " + id + " not found");
        }
        return ret;
    }

    public Teacher update(Teacher updatedTeacher) {
        Teacher ret = teachersMap.get(updatedTeacher.getId());
        teachersMap.put(updatedTeacher.getId(),updatedTeacher);
        return ret;
    }
}
