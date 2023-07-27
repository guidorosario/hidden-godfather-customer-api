package com.hidden.godfather.customer.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {

    private static final String ZONE_ID = "America/Sao_Paulo";



    private static Long getDefaultZoneOffsetInSeconds() {
        var zoneId = ZoneId.of(ZONE_ID);
        var defaultDateTime = ZonedDateTime.now(zoneId);
        return (long) defaultDateTime.getOffset().getTotalSeconds();
    }


    public static LocalDateTime localDateTimeDefaultZone() {
        return LocalDateTime.now().atZone(ZoneId.of(ZONE_ID)).toLocalDateTime();

    }
}
