package com.nhsoft.module.demo.data.util;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class CopyUtil {

    public static String[] getNullPropertyNames(Object object){
        BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] descriptors = wrapper.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor descriptor : descriptors){
            Object propertyValue = wrapper.getPropertyValue(descriptor.getName());
            if(propertyValue == null){
                emptyNames.add(descriptor.getName());
            }
        }
        String[] results = new String[emptyNames.size()];
        return emptyNames.toArray(results);
    }
}
