package ua.com.alevel;

import java.util.StringTokenizer;

public class MyCalendar {
    private final long YEAR = 31_536_000L;
    private final long DAY = 86_400L;
    public long sumSec;
    int hoursSec;
    private long daySec;
    private long monthSec;
    private long yearSec;
    private long milliseconds = 0;
    private long seconds = 0;
    private long minutes = 0;
    private int hours = 0;
    private long days = 1;
    private int monthNum = 1;
    private long years = 0;

    public MyCalendar() {

    }

    public MyCalendar(String date) {
        if (date.contains("/")) {
            StringTokenizer tokenizer = new StringTokenizer(date, "/ :");
            if (tokenizer.countTokens() == 3) {
                String[] strings = date.split("/");
                this.setYears(Long.parseLong(strings[2]));
                this.setMonthNum(Integer.parseInt((strings[1])));
                this.setDays(Long.parseLong(strings[0]));
            } else if (tokenizer.countTokens() == 6) {

                this.setMonthNum(Integer.parseInt(tokenizer.nextToken()));
                this.setYears(Integer.parseInt(tokenizer.nextToken()));
                this.setHours(Integer.parseInt(tokenizer.nextToken()));
                this.setMinutes(Integer.parseInt(tokenizer.nextToken()));
                this.setSeconds(Integer.parseInt(tokenizer.nextToken()));
                this.setMilliseconds(Integer.parseInt(tokenizer.nextToken()));
            } else if (tokenizer.countTokens() == 2) {
                this.setMonthNum(Integer.parseInt(tokenizer.nextToken()));
                this.setHours(Integer.parseInt(tokenizer.nextToken()));
            }
        } else if (!date.contains("/")) {
            StringTokenizer tokenizer = new StringTokenizer(date, "/ :");
            this.setYears(Integer.parseInt(tokenizer.nextToken()));
            this.setHours(Integer.parseInt(tokenizer.nextToken()));
            this.setMinutes(Integer.parseInt(tokenizer.nextToken()));
        }
    }

    public static MyCalendar[] sortDesc(MyCalendar... myCalendars) {
        MyCalendar temp;
        for (int i = 0; i < myCalendars.length; i++) {
            for (int j = i + 1; j < myCalendars.length; j++) {
                if (myCalendars[i].getMillisec() < myCalendars[j].getMillisec()) {
                    temp = myCalendars[i];
                    myCalendars[i] = myCalendars[j];
                    myCalendars[j] = temp;
                }
            }
        }
        return myCalendars;
    }

    public static MyCalendar[] sortAsc(MyCalendar... myCalendars) {
        MyCalendar temp;
        for (int i = 0; i < myCalendars.length; i++) {
            for (int j = i + 1; j < myCalendars.length; j++) {
                if (myCalendars[i].getMillisec() > myCalendars[j].getMillisec()) {
                    temp = myCalendars[i];
                    myCalendars[i] = myCalendars[j];
                    myCalendars[j] = temp;
                }
            }
        }
        return myCalendars;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours >= 0 && hours < 24)
            this.hours = hours;
        else {
            try {
                throw new HoursException("Invalid hours number, under 0 or over 23", hours);
            } catch (HoursException e) {
                e.printStackTrace();
            }
        }

    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        if (milliseconds >= 0 && milliseconds < 1000)
            this.milliseconds = milliseconds;
        else
            try {
                throw new MillisecondException("Milliseconds is under 0 or over 999", milliseconds);
            } catch (MillisecondException e) {
                e.printStackTrace();
            }
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        if (seconds >= 0 && seconds < 60)
            this.seconds = seconds;
        else
            try {
                throw new SecondException("Seconds is under 0 or over 60", seconds);
            } catch (SecondException e) {
                e.printStackTrace();
            }
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        if (minutes >= 0 && minutes < 60)
            this.minutes = minutes;
        else
            try {
                throw new MinuteException("Seconds is under 0 or over 60", (int) minutes);
            } catch (MinuteException e) {
                e.printStackTrace();
            }
    }

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        if (monthNum > 0 && monthNum < 13)
            this.monthNum = monthNum;
        else
            try {
                throw new MonthException("Invalid month number is under 0 or over 12", monthNum);
            } catch (MonthException e) {
                e.printStackTrace();
            }

    }

    public long getYears() {
        return years;
    }

    public void setYears(long years) {
        if (years >= 0)
            this.years = years;
        else
            try {
                throw new YearException("Invalid year number, under 0", (int) years);
            } catch (YearException e) {
                e.printStackTrace();
            }
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        switch (this.monthNum) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if (days > 31 || days < 1) {
                    try {
                        throw new DayException("Days is under 0 or over 31", (int) days);
                    } catch (DayException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.days = days;
                }
            }
            case 4, 6, 9, 11 -> {
                if (days > 30 || days < 1) {
                    try {
                        throw new DayException("Days is under 0 or over 30", (int) days);
                    } catch (DayException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.days = days;
                }
            }
            case 2 -> {
                if (this.isLeap())
                    if ((days <= 29 && days >= 1)) {
                        this.days = days;
                    } else {
                        try {
                            throw new DayException("Days is under 0 or over 29", (int) days);
                        } catch (DayException e) {
                            e.printStackTrace();
                        }
                    }
                if (!this.isLeap())
                    if ((days <= 28 && days >= 1)) {
                        this.days = days;
                    } else {
                        try {
                            throw new DayException("Days is under 0 or over 28", (int) days);
                        } catch (DayException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }
    }

    public void difference(MyCalendar myCalendar) {
        long yearDif = (Math.abs(this.getYears() - myCalendar.getYears()));
        long monthDif = (Math.abs(this.getMonthNum() - myCalendar.getMonthNum()));
        long dayDif = (Math.abs(this.getDays() - myCalendar.getDays()));
        int hoursDif = (Math.abs(this.getHours() - myCalendar.getHours()));
        long secDif = (Math.abs(this.getSeconds() - myCalendar.getSeconds()));
        long millisecDif = (Math.abs(this.getMilliseconds() - myCalendar.getMilliseconds()));
        System.out.println("Years: " + yearDif + "\nMonth: " + monthDif + "\nDay: " + dayDif + "\nHours: " + hoursDif + "\nSec: " +
                secDif + "\nMilliseconds: " + millisecDif);
    }

    public void addYears(int year) {
        this.setYears(this.getYears() + year);
    }

    public void addMonths(int month) {
        int months = this.getMonthNum() + month;
        if (months <= 12) {
            this.setMonthNum(months);
        } else if (months > 12) {
            if (months % 12 == 0) {
                addYears((months - 1) / 12);
                setMonthNum(getMonthNum() + (month % 12));
            } else {
                addYears((months - 1) / 12);
                setMonthNum(months % 12);
            }
        }
    }

    public void addDays(int day) {
        int days = (int) (getDays() + day);
        if (days <= monthDays()) {
            setDays(days);
        }
        if (days > monthDays()) {
            days = days - monthDays();
            addMonths(1);
            setDays(1);
            addDays(days - 1);
        }
    }

    public void addHours(int hour) {
        int hours = getHours() + hour;
        if (hours < 24) {
            setHours(hours);
        } else {
            addDays(hours / 24);
            setHours(hours % 24);
        }
    }

    public void addMinutes(int minute) {
        int minutes = (int) (getMinutes() + minute);
        if (minutes < 60) {
            setMinutes(minutes);
        } else {
            addHours(minutes / 60);
            setMinutes(minutes % 60);
        }
    }

    public void addSeconds(int second) {
        int seconds = (int) (getSeconds() + second);
        if (seconds < 60) {
            setSeconds(seconds);
        } else {
            addHours(seconds / 60);
            setSeconds(seconds % 60);
        }
    }

    public void addMilliseconds(int millisecond) {
        int milliseconds = (int) (getMilliseconds() + millisecond);
        if (milliseconds < 1000) {
            setMilliseconds(milliseconds);
        } else {
            addSeconds(milliseconds / 1000);
            setMilliseconds(milliseconds % 1000);
        }
    }

    public void removeYear(int year) {
        if (getYears() - year < 0) {
            setYears(0);
        } else {
            setYears(getYears() - year);
        }
    }

    public void removeMonth(int month) {
        if (getMonthNum() - month >= 1) {
            setMonthNum(getMonthNum() - month);
        } else {
            if (month < 12) {
                removeYear(month / getMonthNum());
                int rs = month % getMonthNum();
                setMonthNum(12 - rs);
            } else if (month >= 12) {
                removeYear(month / 12);
                removeMonth(month % 12);
            }
        }
    }

    public void removeDays(int day) {
        if (getDays() - day >= 1) {
            setDays(getDays() - day);
        } else {
            day = (int) (day - getDays() + 1);
            removeMonth(1);
            setDays(monthDays());
            removeDays(day - 1);
        }
    }

    public void removeHours(int hour) {
        if (getHours() - hour >= 0) {
            setHours(getHours() - hour);
        } else {
            hour = hour - getHours() - 1;
            removeDays(1);
            setHours(23);
            removeHours(hour);
        }
    }

    public void removeMinutes(int minute) {
        if (getMinutes() - minute >= 0) {
            setMinutes(getMinutes() - minute);
        } else {
            minute = (int) (minute - getMinutes() - 1);
            removeHours(1);
            setMinutes(59);
            removeMinutes(minute);
        }
    }

    public void removeSeconds(int second) {
        if (getSeconds() - second >= 0) {
            setSeconds(getSeconds() - second);
        } else {
            second = (int) (second - getSeconds() - 1);
            removeMinutes(1 + second / 60);
            setSeconds(59);
            removeSeconds(second);
        }
    }

    public void removeMilliseconds(int millisecond) {
        if (getMilliseconds() - millisecond >= 0) {
            setMilliseconds(getMilliseconds() - millisecond);
        } else {
            millisecond = (int) (millisecond - getMilliseconds() - 1);
            removeSeconds(1);
            setMilliseconds(999);
            removeMilliseconds(millisecond);
        }
    }

    public int monthDays() {
        int days = 0;
        int month = this.getMonthNum();
        if (month == 2) {
            if (this.isLeap()) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (month != 2) {
            days = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                default -> throw new IllegalStateException("Unexpected value: " + monthNum);
            };
        }
        return days;
    }

    public long getSec() {
        if (isLeap()) {
            yearSec = this.getYears() * YEAR + DAY * countLeap(this.getYears());
            monthSec = getMonthSecLeap(getMonthNum());
        } else {
            yearSec = getYears() * YEAR;
            monthSec = getMonthSec(getMonthNum());
        }
        hoursSec = this.getHours() * 60 * 60;
        daySec = getDays() * DAY;
        sumSec = daySec + monthSec + yearSec + hoursSec + getSeconds() - DAY;
        return sumSec;
    }

    public long getMillisec() {
        long sec = getSec();
        long millisec = sec * 1000 + getMilliseconds();
        return millisec;
    }

    public long getMonthSec(int monthNum) {
        long monthSec = switch (monthNum) {
            case 1 -> 0;
            case 2 -> 31 * DAY;
            case 3 -> 28 + 31 * DAY;
            case 4 -> 28 + 31 * 2 * DAY;
            case 5 -> 28 + 30 + 31 * 2 * DAY;
            case 6 -> 28 + 30 + 31 * 3 * DAY;
            case 7 -> 28 + 30 * 2 + 31 * 3 * DAY;
            case 8 -> 28 + 30 * 2 + 31 * 4 * DAY;
            case 9 -> 28 + 30 * 2 + 31 * 5 * DAY;
            case 10 -> 28 + 30 * 3 + 31 * 5 * DAY;
            case 11 -> 28 + 30 * 3 + 31 * 6 * DAY;
            case 12 -> 28 + 30 * 4 + 31 * 6 * DAY;
            default -> throw new IllegalStateException("Unexpected value: " + monthNum);
        };
        return monthSec;
    }

    public long getMonthSecLeap(int monthNum) {
        long monthSec = switch (monthNum) {
            case 1 -> 0;
            case 2 -> 31 * DAY;
            case 3 -> 29 + 31 * DAY;
            case 4 -> 29 + 31 * 2 * DAY;
            case 5 -> 29 + 30 + 31 * 2 * DAY;
            case 6 -> 29 + 30 + 31 * 3 * DAY;
            case 7 -> 29 + 30 * 2 + 31 * 3 * DAY;
            case 8 -> 29 + 30 * 2 + 31 * 4 * DAY;
            case 9 -> 29 + 30 * 2 + 31 * 5 * DAY;
            case 10 -> 29 + 30 * 3 + 31 * 5 * DAY;
            case 11 -> 29 + 30 * 3 + 31 * 6 * DAY;
            case 12 -> 29 + 30 * 4 + 31 * 6 * DAY;
            default -> throw new IllegalStateException("Unexpected value: " + monthNum);
        };
        return monthSec;
    }

    public boolean isLeap() {
        boolean leap;
        if (years == 0) {
            leap = false;
        } else if (years % 4 == 0 && years % 100 != 0) {
            leap = true;
        } else if (years % 400 == 0) {
            leap = true;
        } else {
            leap = false;
        }
        return leap;
    }

    public int countLeap(long years) {
        int i = 0;
        for (int j = 0; j < years; j++) {
            if (j % 4 == 0 && j % 100 != 0) {
                i++;
            } else if (j % 400 == 0) {
                i++;
            }
        }
        return i;
    }

    public void printDDMMYY() {
        System.out.println(getDays() + "/" + getMonthNum() + "/" + (getYears()));
    }

    public void printMDYYYY() {
        System.out.println(getMonthNum() + "/" + getDays() + "/" + getYears());
    }

    public void printMMMDYY() {
        System.out.println(printRusNameOfMonth(getMonthNum()) + " " + getDays() + " " + (getYears()));
    }

    public void printDDMMMYYYYTime() {
        System.out.println(getDays() + " " + printRusNameOfMonth(getMonthNum()) + " " + getYears() + " " + getHours() + ":" + getMinutes());
    }

    public String printRusNameOfMonth(int monthNum) {
        String name = switch (monthNum) {
            case 1 -> "Январь";
            case 2 -> "Февраль";
            case 3 -> "Март";
            case 4 -> "Апрель";
            case 5 -> "Май";
            case 6 -> "Июнь";
            case 7 -> "Июль";
            case 8 -> "Август";
            case 9 -> "Сентябрь";
            case 10 -> "Октябрь";
            case 11 -> "Ноябрь";
            case 12 -> "Декабрь";
            default -> throw new IllegalStateException("Unexpected value: " + monthNum);
        };
        return name;
    }

    @Override
    public String toString() {
        return "MyCalendar{" +
                ", milliseconds=" + milliseconds +
                ", seconds=" + seconds +
                ", minutes=" + minutes +
                ", hours=" + hours +
                ", days=" + days +
                ", monthNum=" + monthNum +
                ", years=" + years +
                '}';
    }
}
