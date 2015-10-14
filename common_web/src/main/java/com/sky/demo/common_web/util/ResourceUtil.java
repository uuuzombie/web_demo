package com.sky.demo.common_web.util;

import java.lang.reflect.Field;
import java.util.List;

import com.sky.demo.common_web.base.FieldChangeInfo;
import com.sky.demo.common_web.base.RelationColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


/**
 * Created by rg on 2015/7/16.
 */
public class ResourceUtil {

    private static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);


    public static <T> List<FieldChangeInfo> getResourceChangeInfo(T originalItem, T updatedItem, Class<T> clazz) {
        List<FieldChangeInfo> changeInfos = Lists.newArrayList();

        try {
            for (Field field : clazz.getDeclaredFields()) {
                RelationColumn revisionColumn = field.getAnnotation(RelationColumn.class);
                if (revisionColumn != null) {
                    field.setAccessible(true);

                    Object originalValue = field.get(originalItem);
                    Object updatedValue = field.get(updatedItem);
                    if (originalValue == null && updatedValue == null) {
                        continue;
                    }

                    if (originalValue == null || !originalValue.equals(updatedValue)) {
                        FieldChangeInfo info = new FieldChangeInfo();
                        info.setPropertyName(field.getName());
                        info.setPropertyHeader(revisionColumn.value());
                        if (revisionColumn.isShield()) {
                            info.setFrom("***");
                            info.setTo("***");
                        } else {
                            info.setFrom(originalValue);
                            info.setTo(updatedValue);
                        }

                        changeInfos.add(info);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("get resource change info error",e);
        }
        return changeInfos;
    }
}
