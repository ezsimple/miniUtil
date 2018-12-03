package net.ion.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.LocalDateTime;
import org.springframework.util.ObjectUtils;

public class DateUtil {
	
	// javaScript의 경우 moments.js 를 사용하면 됩니다.
	public static long getDurTime(Date start, Date end, String timeUnit) {
        long ms = end.getTime() - start.getTime();
        if(timeUnit != null) {
			switch(timeUnit.toLowerCase()) {
				case "sec": return TimeUnit.MILLISECONDS.toSeconds(ms);
				case "min": return TimeUnit.MILLISECONDS.toMinutes(ms);
				case "hour":return TimeUnit.MILLISECONDS.toHours(ms);
				case "day":return TimeUnit.MILLISECONDS.toDays(ms);
			}
        }
        return ms;
    }
	
	// JAVA의 date(), calendar는 바로 사용하지 말 것 !!!
	public static Date getNow() {
		LocalDateTime datetime = LocalDateTime.now();
		return datetime.toDate();
	}
	
	public static Date getYesterday() {
		LocalDateTime datetime = LocalDateTime.now();
		return datetime.minusDays(1).toDate();
	}
	
	public static LocalDateTime getNowLocalDateTime() {
		return LocalDateTime.now();
	}
	
	private static String MIN_DATE = "0001-01-01";

	public static Date getDate(Date date) {
		if (date == null) {
			LocalDateTime datetime = LocalDateTime.parse(MIN_DATE); // MSSQL mininum, not allow null
			return datetime.toDate();
		}
		LocalDateTime datetime = LocalDateTime.fromDateFields(date);
		return datetime.toDate();
	}

	public static Date getDate(String date) {
		if (StringUtils.isEmpty(date)) {
			LocalDateTime datetime = LocalDateTime.parse(MIN_DATE); // MSSQL mininum, not allow null
			return datetime.toDate();
		}
		LocalDateTime datetime = LocalDateTime.fromDateFields(new Date(date));
		return datetime.toDate();
	}
	
	// SimpleDateFormat 형식의 date String 을 반환
	final static String DATE_FORMAT = "MM/dd/yyyy";
	public static String getFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String formated_date = sdf.format(date);
		return formated_date;
	}
	public static String getFormatDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String formated_date = sdf.format(getDate(date));
		return formated_date;
	}

	public static boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean isEmpty(String date) {
		if(StringUtils.isEmpty(date))
			return true;
		if(StringUtils.equals(date, MIN_DATE))
			return true;
		return false;
	}

	public static boolean isEmpty(Date date) {
		if(ObjectUtils.isEmpty(date))
			return true;
		
		LocalDateTime minDate = LocalDateTime.parse(MIN_DATE); // MSSQL mininum, not allow null
		Date nowDate = getDate(date);
		if(DateUtils.isSameDay(minDate.toDate(), nowDate))
			return true;

		return false;
	}
	
	// 최소 일자일 경우 공백(null)으로 출력
	public static Date getValidDate(Date date) {
		if(isEmpty(date)) return null;
		return getDate(date);
	}

	public static String getDateString(Map<String, Object> item, String key_dt) {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date _dt = getValidDate((Date) item.get(key_dt));
		String dt = "";
		if(_dt != null )
			dt = StringUtil.trimToEmpty(sdf.format(DateUtil.getValidDate((Date) item.get(key_dt))));
		return dt;
	}
	
	public static void main(String[] args) {
		getDate("");
	}

}
