package sample;


import java.util.Date;

/**
 * Created by Tom on 5/1/2016.
 */
//patients
public class PATIENT {
    String last_name;
    String first_name;
    int base;
    int index;
    int phn;
    int day;
    int month;
    int year;

    public PATIENT(String last, String first, int base, int index, int phn, int day, int month, int year) {

        this.last_name = last;
        this.first_name = first;
        this.base = base;
        this.index = index;
        this.phn = phn;
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public String getLast_name() {
        return last_name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getBase() {
        return base;
    }

    public int getIndex() {
        return index;
    }

    public int getPhn() {
        return phn;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPhn(int phn) {
        this.phn = phn;
    }

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return false;
        if (!(o instanceof PATIENT)) return false;
        PATIENT temp = (PATIENT) o;

        if (this.phn == temp.phn) return true;

        return false;
    }

}
