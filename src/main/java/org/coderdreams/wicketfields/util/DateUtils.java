package org.coderdreams.wicketfields.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtils {
	private static final Logger log = LoggerFactory.getLogger( DateUtils.class ) ;

	private static final DateTimeFormatter f1 = DateTimeFormatter.ofPattern("h:mm a");
	private static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static final DateTimeFormatter f3 = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
	private static final DateTimeFormatter f8 = DateTimeFormatter.ofPattern("MM/dd");

	public static String format(LocalDateTime date) {
		if(date == null) {
		    return "";
        }
		return f3.format(date);
	}

	public static String format(LocalTime d) {
		if(d == null) {
		    return "";
        }
	    return f1.format(d);
	}
	public static String format(MonthDay d) {
		if(d == null) {
		    return "";
        }
	    return f8.format(d);
	}


	public static String format(LocalDate date) {
		if(date == null) {
		    return "";
        }
	    return f2.format(date);
	}


    static LocalDateTime parse2(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return LocalDateTime.parse(str, f3);
        } catch (Exception e) {
            log.error("failed parse {}", str, e);
        }
        return null;
    }

    static LocalTime parseLocalTime(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return LocalTime.parse(str, f1);
        } catch (Exception e) {
            log.error("failed parseLocalTime {}", str, e);
        }
        return null;
    }

    static MonthDay parseMonthDay(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return MonthDay.parse(str, f8);
        } catch (Exception e) {
            log.error("failed parseMonthDay {}", str, e);
        }
        return null;
    }

}
