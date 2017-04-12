package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Lesson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sten on 12.04.17.
 */
public interface LessonsDAO {

    Integer create(Lesson newLesson);

    void delete(Integer id);

    Lesson get(Integer id);

    Lesson update(Lesson updatedLesson);
}
