package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Lesson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sten on 12.04.17.
 */
public class RAMLessonsDAO implements LessonsDAO {
    private Map<Integer, Lesson> LessonsMap = new ConcurrentHashMap<Integer, Lesson>();
    private AtomicInteger idCounter = new AtomicInteger();

    public Integer create(Lesson newLesson) {
        Integer id = idCounter.addAndGet(1);
        newLesson.setId(id);
        LessonsMap.put(id, newLesson);
        return id;
    }

    public void delete(Integer id) {
        LessonsMap.remove(id);
    }

    public Lesson get(Integer id) {
        return LessonsMap.get(id);
    }

    public Lesson update(Lesson updatedLesson) {
        Lesson ret = LessonsMap.get(updatedLesson.getId());
        LessonsMap.put(updatedLesson.getId(),updatedLesson);
        return ret;
    }
}
