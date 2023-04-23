package by.it_academy.fitness.service.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class StringToLocalDateTime implements Converter <String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String time) {
        Long longTime = Long.decode(time);

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longTime), ZoneOffset.UTC);
    }
}
