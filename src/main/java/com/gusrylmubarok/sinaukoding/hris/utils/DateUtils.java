package com.gusrylmubarok.sinaukoding.hris.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static Date fromString(String param) {
        try {
            return new SimpleDateFormat("dd-MM-yyy", Locale.ENGLISH).parse(param);
        }catch(ParseException ignore) {

        }
        return null;
    }

}
