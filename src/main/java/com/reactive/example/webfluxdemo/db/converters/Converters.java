package com.reactive.example.webfluxdemo.db.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Converters {
    @ReadingConverter
    public static class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

        @Override
        public Date convert(LocalDateTime localDateTime) {
            Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
            return Date.from(instant);
        }
    }
}
