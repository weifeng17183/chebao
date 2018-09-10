package com.justfind.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.justfind.annotation.PageQuery;

public class ParsePageQueryAnnotation {
    public static String parseMethod(Class<?> clazz,String queryMethod,Class<?> paramType) throws IllegalArgumentException,  
            IllegalAccessException, InvocationTargetException,  
            SecurityException, NoSuchMethodException, InstantiationException {  
		Method method=clazz.getMethod(queryMethod, paramType);
        PageQuery pageQuery = method.getAnnotation(PageQuery.class);  
        if (pageQuery != null && method.getName().contains(queryMethod)) {  
            String sql = pageQuery.sql();
            return sql;  
        }  
        return "";  
    }
    
}
