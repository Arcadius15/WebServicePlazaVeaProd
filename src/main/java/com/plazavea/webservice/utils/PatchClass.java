package com.plazavea.webservice.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class PatchClass {
    public Object patch(Class<?> class1,Map<@NotNull Object, @NotNull Object> item,Object obj){
        try {
            item.forEach((key,value)->{
                Field field = ReflectionUtils.findField(class1, (String)key);
                field.setAccessible(true);
                var valuePatch = new Object();
                if (field.getType().equals(LocalDate.class)) {
                    valuePatch = LocalDate.parse(value.toString());
                }else{
                    valuePatch = value;
                }
                ReflectionUtils.setField(field, obj, valuePatch);
            });
            return obj;
        } catch (Exception e) {
            return null;
        }
    }
}
