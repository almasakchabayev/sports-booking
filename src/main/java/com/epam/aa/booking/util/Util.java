package com.epam.aa.booking.util;

import java.sql.Date;
import java.time.LocalDate;

public class Util {
    public static Date getSQLDateFromLocalDate(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }
}
