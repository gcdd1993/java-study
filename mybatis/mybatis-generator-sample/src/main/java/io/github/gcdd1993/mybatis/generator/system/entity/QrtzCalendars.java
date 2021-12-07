package io.github.gcdd1993.mybatis.generator.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 日历信息表
 * </p>
 *
 * @author gcdd1993
 * @since 2021-12-07
 */
@TableName("qrtz_calendars")
public class QrtzCalendars implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度名称
     */
    private String schedName;

    /**
     * 日历名称
     */
    private String calendarName;

    /**
     * 存放持久化calendar对象
     */
    private Blob calendar;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    public Blob getCalendar() {
        return calendar;
    }

    public void setCalendar(Blob calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return "QrtzCalendars{" +
            "schedName=" + schedName +
            ", calendarName=" + calendarName +
            ", calendar=" + calendar +
        "}";
    }
}
