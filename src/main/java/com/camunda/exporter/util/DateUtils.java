package com.camunda.exporter.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDateTime convertTimesStampTODate(long timestamp){
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        return dateTime;
    }
    public static LocalDateTime getCurrentDateTimeStamp(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime;
    }
}
