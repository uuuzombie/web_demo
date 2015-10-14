package com.sky.demo.common_web.util.json;

import java.util.Date;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * Created by rg on 2015/7/10.
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();

        SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, null, "com.skyguard.admin", "WebManager"));
        module.addSerializer(Date.class, new JsonDateTimeSerializer());
        module.addSerializer(String.class, new JsonHtmlSerializer());

        module.addDeserializer(Date.class, new JsonDateTimeDeserializer());
        this.registerModule(module);
    }

}
