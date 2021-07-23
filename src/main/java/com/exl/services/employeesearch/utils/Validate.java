package com.exl.services.employeesearch.utils;

import com.library.common.StringHelper;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Validate {

    
    private Validate() {
        super();
    }

    
    
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void notBlank(String object, String message) {
        if (StringHelper.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void notBlank(StringBuilder object, String message) {
        Validate.notNull(object, message);
        if (object.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void notEmpty(Collection<?> object, String message) {
        if (object == null || object.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void notEmpty(Object[] object, String message) {
        if (object == null || object.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void containsNoNulls(Iterable<?> collection, String message) {
        for (Object object : collection) {
            notNull(object, message);
        }
    }

    
    public static void containsNoEmpties(Iterable<String> collection, String message) {
        for (String object : collection) {
            notBlank(object, message);
        }
    }

    
    public static void containsNoNulls(Object[] array, String message) {
        for (Object object : array) {
            notNull(object, message);
        }
    }

    
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static boolean isDate(String str) {
        Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}.*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}