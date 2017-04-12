package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Id;
import com.epam.wklab.restsvc.beans.Lesson;
import com.epam.wklab.restsvc.server.LessonNotFoundException;

import java.util.Map;
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
        newLesson.setId(newId);
        lessonsMap.put(id, newLesson);
        return newId;
    }

    @Override
    public void delete(Id id) {
        lessonsMap.remove(id.getValue());
    }

    @Override
    public Lesson get(Id id) throws LessonNotFoundException {
        Lesson ret = lessonsMap.get(id.getValue());
        if(null == ret) {
            throw new LessonNotFoundException("Lesson with id " + id.getValue() + " not found");
        }
        return ret;
    }

    @Override
    public Lesson update(Lesson updatedLesson) {
        Lesson ret = lessonsMap.get(updatedLesson.getId().getValue());
        lessonsMap.put(updatedLesson.getId().getValue(),updatedLesson);
        return ret;
    }
}
