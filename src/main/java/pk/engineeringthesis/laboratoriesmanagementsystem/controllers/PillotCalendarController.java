
package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.LaboratoryService;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.Timetable;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.TimetableService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class PillotCalendarController {

    @Autowired
    TimetableService timetableService;
    @Autowired
    LaboratoryService laboratoryService;
    @Autowired
    UserDetailsServiceImpl userService;

    @GetMapping("/kalendarz/terminy/{id}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Timetable> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end,@PathVariable(name = "id") Long laboratoryid) {
        return timetableService.findBetween(start, end,laboratoryService.get(laboratoryid));
    }
    @PostMapping("/termin/usun/{id}")
    String deleteEvent(@PathVariable(name = "id") Long eventId) {

            timetableService.delete(eventId);

        return "OK";
    }

    @PostMapping("/kalendarz/termin/stworz/{id}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Timetable createEvent(@RequestBody EventCreateParams params, @PathVariable(name = "id") Long laboratoryid, HttpServletRequest request) {


        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByUsername(principal.getName());
        Timetable timetable = new Timetable();
        timetable.setStart(params.start);
        timetable.setEnd(params.end);
        timetable.setText(params.text);
        timetable.setConfirmed(true);
        timetable.setLaboratory(laboratoryService.get(laboratoryid));
        timetable.setUser(user);
        timetableService.save(timetable);
        return timetable;
    }
    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }
}