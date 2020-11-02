package ca.weather.historic.service.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "HISTORIC_WEATHER")
public class HistoricWeather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "RECORD_DATE")
    private Date recordDate;

    @Column(name = "MEAN_TEMP")
    private String meanTemp;

    @Column(name = "HIGHEST_MONTHLY_MAXI_TEMP")
    private String maxTempMonthlyTemp;

    @Column(name = "LOWEST_MONTHLY_MIN_TEMP")
    private String minTempMonthlyTemp;

    @Transient
    private String recordDateString;

    protected HistoricWeather() {
    }

    public HistoricWeather(String stationName, String province, Date recordDate,
        String meanTemp, String maxTempMonthlyTemp, String minTempMonthlyTemp) {
        super();
        this.stationName = stationName;
        this.province = province;
        this.recordDate = recordDate;
        this.meanTemp = meanTemp;
        this.maxTempMonthlyTemp = maxTempMonthlyTemp;
        this.minTempMonthlyTemp = minTempMonthlyTemp;
    }

    @Override
    public String toString() {
        return "HistoricWeather{" +
            "id=" + id +
            ", stationName='" + stationName + '\'' +
            ", province='" + province + '\'' +
            ", recordDate=" + recordDate +
            ", meanTemp='" + meanTemp + '\'' +
            ", maxTempMonthlyTemp='" + maxTempMonthlyTemp + '\'' +
            ", minTempMonthlyTemp='" + minTempMonthlyTemp + '\'' +
            '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(String meanTemp) {
        this.meanTemp = meanTemp;
    }

    public String getMaxTempMonthlyTemp() {
        return maxTempMonthlyTemp;
    }

    public void setMaxTempMonthlyTemp(String maxTempMonthlyTemp) {
        this.maxTempMonthlyTemp = maxTempMonthlyTemp;
    }

    public String getRecordDateString() {
        return recordDateString;
    }

    public void setRecordDateString(String recordDateString) {
        this.recordDateString = recordDateString;
    }

    public String getMinTempMonthlyTemp() {
        return minTempMonthlyTemp;
    }

    public void setMinTempMonthlyTemp(String minTempMonthlyTemp) {
        this.minTempMonthlyTemp = minTempMonthlyTemp;
    }
}
