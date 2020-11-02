package ca.weather.historic.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchCriteria implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        return "SearchCriteria{" +
            "start=" + sdt.format(start) +
            ", end=" + sdt.format(end) +
            '}';
    }
}
