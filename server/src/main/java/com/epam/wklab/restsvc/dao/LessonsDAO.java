package com.epam.wklab.restsvc.dao;

import com.epam.wklab.restsvc.beans.Id;
import com.epam.wklab.restsvc.beans.Lesson;
import com.epam.wklab.restsvc.beans.Lessons;
import com.epam.wklab.restsvc.server.LessonNotFoundException;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sten on 12.04.17.
 */
public interface LessonsDAO {

    Id create(Lesson newLesson);

    void delete(Integer id);

    Lesson get(Integer id) throws LessonNotFoundException;

    Lesson update(Lesson updatedLesson);

    Lessons getLessons();
}
