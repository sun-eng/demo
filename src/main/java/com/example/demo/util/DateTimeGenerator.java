package com.example.demo.util;

import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.util.Calendar;
import java.util.Date;

public class DateTimeGenerator implements ValueGenerator<Date> {

    @Override
    public Date generateValue(Session session, Object owner) {
        return Calendar.getInstance().getTime();
    }
}
