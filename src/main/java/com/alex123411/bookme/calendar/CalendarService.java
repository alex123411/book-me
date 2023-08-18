package com.alex123411.bookme.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    // Other CRUD operations and business logic as needed
}

