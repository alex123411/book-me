package com.alex123411.bookme.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

    @PostMapping
    public Calendar createCalendar(@RequestBody Calendar calendar) {
        return calendarService.createCalendar(calendar);
    }

}

