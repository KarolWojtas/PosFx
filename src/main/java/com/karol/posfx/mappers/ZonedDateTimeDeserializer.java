package com.karol.posfx.mappers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    public ZonedDateTimeDeserializer(){
        super(ZonedDateTime.class);
    }
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return ZonedDateTime.parse(jp.getText(), formatter);
    }
}
