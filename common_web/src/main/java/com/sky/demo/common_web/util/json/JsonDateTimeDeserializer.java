package com.sky.demo.common_web.util.json;

import java.io.IOException;
import java.util.Date;

import com.sky.demo.common_web.util.Constants;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Created by rg on 2015/7/14.
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.DATETIME_PATTERN);
        return DateTime.parse(p.getText(),formatter).toDate();
    }
}
