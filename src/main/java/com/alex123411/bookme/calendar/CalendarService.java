package com.alex123411.bookme.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

}

