package org.example.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Slot {

    public static List<Integer> getSlots(LocalDateTime startTime, LocalDateTime endTime) {
        int startHour = startTime.getHour();
        int endHour = endTime.getHour();
        LocalDateTime endTimeTemp = endTime.withMinute(0);

        if(endTime.isAfter(endTimeTemp)) {
            endHour--;
        }
        List<Integer> result = new ArrayList<>();
        for(int i=startHour;i<=endHour;++i) {
            result.add(i);
        }
        return result;
    }

    public static int getSlot(LocalDateTime time) {
        return time.getHour();
    }
}
