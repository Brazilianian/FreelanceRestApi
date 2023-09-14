package com.mitit.util;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateUtil {
    public static LocalDateTime parseDateByPattern(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date - " + date);
            return LocalDateTime.now();
        }
    }

}
