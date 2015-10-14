package com.sky.demo.common_web.util.json;

import java.io.IOException;
import java.util.Date;

import com.sky.demo.common_web.util.Constants;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by rg on 2015/7/14.
 */
public class JsonDateTimeSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        DateTimeFormatter format = DateTimeFormat.forPattern(Constants.DATETIME_PATTERN);
        gen.writeString(new DateTime(value).toString(format));
    }
}
