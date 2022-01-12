package br.com.packagingby.layer.beans;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ObjectMapper {
    public Object mapNonNullValues(Object objectSource, Object objectTarget){


        Class objectClass = objectSource.getClass();

        Field[] fields = objectClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object sourceValue = field.get(objectSource);


                if (sourceValue != null && !field.getName().equals("id")) {
                    field.set(objectTarget,sourceValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }

        return objectTarget;
    }
}