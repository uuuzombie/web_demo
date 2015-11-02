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

        //自定义将null输出为""
        getSerializerProvider().setNullValueSerializer(new JsonNullSerializer());


        //方式一，采用SimpleModule方法，构造函数不带参数
        SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, null, "com.skyguard.admin", "WebManager"));
        module.addSerializer(Date.class, new JsonDateTimeSerializer());
        module.addSerializer(String.class, new JsonHtmlSerializer());

        module.addDeserializer(Date.class, new JsonDateTimeDeserializer());
        this.registerModule(module);

        //方式二，采用SimpleModule方法，注意构造函数带参数，则还需handledType()函数
//        SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "com.qunar.survey"));
//        module.addSerializer(new JsonHtmlXssSerializer(String.class));
//        module.addSerializer(new JsonDateSerializer(Timestamp.class));
//        this.registerModule(module);

        //方式三，采用CustomSerializerFactory方式
//        CustomSerializerFactory factory = new CustomSerializerFactory();
//        factory.addGenericMapping(Timestamp.class,new JsonDateSerializerNoParam());
//        factory.addGenericMapping(String.class,new JsonHtmlXssSerializerNoParam());
//        this.setSerializerFactory(factory);

    }

}
