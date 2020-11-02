package ca.weather.historic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import ca.weather.historic.service.entity.HistoricWeather;
import ca.weather.historic.service.dao.HistoricWeatherDao;
import ca.weather.historic.service.model.SearchCriteria;
import ca.weather.historic.service.web.controllers.HistoricWeatherController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoricWeatherApplicationTests {

    @InjectMocks
    private HistoricWeatherController historicWeatherController;

    @Mock
    private HistoricWeatherDao historicWeatherDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearching() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2017-01-01");
        Date date2 = dateFormat.parse("2021-02-01");
        SearchCriteria s = new SearchCriteria();
        s.setStart(date1);
        s.setEnd(date2);

        Errors e = new BindException(new Object(), null);

        HistoricWeather historicWeather = new HistoricWeather("st1", "QC", new Date(), "10", "20", "5");
        List<HistoricWeather> weatherList = new ArrayList<>();
        weatherList.add(historicWeather);

        when(historicWeatherDao.findByRecordDate(s.getStart(), s.getEnd())).thenReturn(weatherList);
        ResponseEntity<?> r = historicWeatherController.search(s, e);
        assertEquals(200, r.getStatusCodeValue());
    }

}
