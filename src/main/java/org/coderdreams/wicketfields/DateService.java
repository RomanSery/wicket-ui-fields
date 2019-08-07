package org.coderdreams.wicketfields;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateService {
	private static final Logger log = LoggerFactory.getLogger( DateService.class ) ;
	private static final String defaultTimeZone = "America/New_York";
	private static ZoneId displayTimeZone = null;
	
	private static final DateTimeFormatter f1 = DateTimeFormatter.ofPattern("h:mm a");
	private static final DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static final DateTimeFormatter f3 = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
	private static final DateTimeFormatter f8 = DateTimeFormatter.ofPattern("MM/dd");
	private static final DateTimeFormatter f9 = DateTimeFormatter.ofPattern("MM/yyyy");

    public String format(LocalDateTime date) { return format(date, true); }
	public String format(LocalDateTime date, boolean displayInLocalTimeZone) {
		if(date == null) {
		    return "";
        }
		if(displayInLocalTimeZone) {
			return f3.format(date.atZone(ZoneId.systemDefault()).withZoneSameInstant(getDisplayTimeZone()));
		} else {
			return f3.format(date);
		}
	}

	public String format(LocalTime d) {
		if(d == null) {
		    return "";
        }
	    return f1.format(d);
	}
	public String format(MonthDay d) {
		if(d == null) {
		    return "";
        }
	    return f8.format(d);
	}


	public String format(LocalDate date) {
		if(date == null) {
		    return "";
        }
	    return f2.format(date);
	}


    public LocalDateTime parse2(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return LocalDateTime.parse(str, f3);
        } catch (Exception e) {
            log.error("failed parse {}", str, e);
        }
        return null;
    }

    public LocalTime parseLocalTime(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return LocalTime.parse(str, f1);
        } catch (Exception e) {
            log.error("failed parseLocalTime {}", str, e);
        }
        return null;
    }

    public MonthDay parseMonthDay(String str) {
        if (StringUtils.isBlank(str)) return null;

        try {
            return MonthDay.parse(str, f8);
        } catch (Exception e) {
            log.error("failed parseMonthDay {}", str, e);
        }
        return null;
    }

	public static ZoneId getDisplayTimeZone() {

		if(displayTimeZone == null) {
			displayTimeZone = ZoneId.of(defaultTimeZone);
		}
		return displayTimeZone;
	}
}
