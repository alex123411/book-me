package com.alex123411.bookme.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/calendars")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

    @PostMapping
    public Calendar createCalendar(@RequestBody Calendar calendar) {
        return calendarService.createCalendar(calendar);
    }

    // Other CRUD endpoints and methods as needed
}

