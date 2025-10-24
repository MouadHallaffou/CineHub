package com.cinehub.util;

import com.cinehub.exception.LocalDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String toString(LocalDate date) {
        return date != null ? date.format(FORMATTER) : null;
    }

    public static LocalDate toLocalDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (Exception e) {
            throw new LocalDateException("Invalid date format. Expected: yyyy-MM-dd");
        }
    }
}