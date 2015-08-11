package com.cowtowncoder.jsonperf.dzone;

import java.io.IOException;
import java.util.*;

/**
 * Main test item. Contains both fields and getters/setters, given that different
 * libraries access data in different ways: some require fields (Gson at least),
 * others getters/setters (Jackson jr); others work with either (Jackson)
 */
public class MeasurementRecord
{
    public String measurementId;
    public long duration;
    public long time;

    public MeasurementType type;

    // for deser
    protected MeasurementRecord() { }
    
    public MeasurementRecord(String measurementId, long duration, long time,
            MeasurementType type)
    {
        this.measurementId = measurementId;
        this.duration = duration;
        this.time = time;
        this.type = type;
    }

    public String getMeasurementId() { return measurementId; }
    public long getDuration() { return duration; }
    public long getTime() { return time; }
    public MeasurementType getType() { return type; }

    public void setMeasurementId(String s) { measurementId = s; }
    public void setDuration(Long l) { duration = l; }
    public void setTime(long l) { time = l; }
    public void setType(MeasurementType t) { type = t; }

    public static List<MeasurementRecord> construct(int count)
    {
        List<MeasurementRecord> result = new ArrayList<>(count);
        long now = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            result.add(new MeasurementRecord("/test.html",
                    10, now+i, MeasurementType.WEB_REQUEST));
        }
        return result;
    }

    // quick and dirty; needed just to avoid having to use any of impls being
    // tested
    public void appendAsJSON(Appendable target) throws IOException
    {
        target.append(String.format("{\"measurementId\":\"%s\","
                +"\"duration\":%d,"
                +"\"time\":%d,"
                +"\"type\":\"%s\"}",
                measurementId, duration, time, type.name()
                ));
    }
}
