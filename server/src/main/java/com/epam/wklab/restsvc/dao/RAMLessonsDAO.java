package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Id;
import com.epam.wklab.restsvc.beans.Lesson;
import com.epam.wklab.restsvc.beans.Lessons;
import com.epam.wklab.restsvc.server.LessonNotFoundException;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sten on 12.04.17.
 */
public class RAMLessonsDAO implements LessonsDAO {
    private Map<Integer, Lesson> lessonsMap = new ConcurrentHashMap<Integer, Lesson>();
    private AtomicInteger idCounter = new AtomicInteger();

    @Override
    public Id create(Lesson newLesson) {
        Integer id = idCounter.addAndGet(1);
        Id newId = new Id();
        newId.setValue(id);
        newLesson.setId(id);
        lessonsMap.put(id, newLesson);
        return newId;
    }

    @Override
    public void delete(Integer id) {
        lessonsMap.remove(id);
    }

    @Override
    public Lesson get(Integer id) throws LessonNotFoundException {
        Lesson ret = lessonsMap.get(id);
        if(null == ret) {
            throw new LessonNotFoundException("Lesson with id " + id + " not found");
        }
        return ret;
    }

    @Override
    public Lesson update(Lesson updatedLesson) {
        Lesson ret = lessonsMap.get(updatedLesson.getId());
        lessonsMap.put(updatedLesson.getId(),updatedLesson);
        return ret;
    }

    @Override
    public Lessons getLessons() {
        Lessons lessons = new Lessons();
        lessons.setLessons(new ArrayList<>(lessonsMap.values()));
        return lessons;
    }
}
