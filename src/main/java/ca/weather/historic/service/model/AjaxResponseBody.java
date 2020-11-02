package ca.weather.historic.service.model;

import ca.weather.historic.service.entity.HistoricWeather;
import java.io.Serializable;
import java.util.List;

public class AjaxResponseBody implements Serializable {

    private String msg;
    private List<HistoricWeather> searchResult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<HistoricWeather> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<HistoricWeather> searchResult) {
        this.searchResult = searchResult;
    }


}
