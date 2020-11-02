package ca.weather.historic.service.web.controllers;

import ca.weather.historic.service.entity.HistoricWeather;
import ca.weather.historic.service.model.AjaxResponseBody;
import ca.weather.historic.service.model.SearchCriteria;
import ca.weather.historic.service.dao.HistoricWeatherDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/historic-weather")
public class HistoricWeatherController {

    private Logger logger = LoggerFactory.getLogger(HistoricWeatherController.class);

    @Autowired
    private HistoricWeatherDao historicWeatherDao;


    @RequestMapping("/home")
    public String basedPage(Map<String, Object> model) {
        logger.info("Entering into home page handler");
        List<HistoricWeather> weatherList = historicWeatherDao.findAll();

        weatherList
            .stream()
            .forEach(weather -> formatDate(weather));

        model.put("weatherinfo", weatherList);
        return "main";
    }

    private void formatDate(HistoricWeather weather) {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        weather.setRecordDateString(outputFormatter.format(weather.getRecordDate()));
    }

    @GetMapping("detail/{id}")
    public String detailPage(Map<String, Object> model,
        @PathVariable Long id) {
        logger.info("Entering into detail page handler");
        HistoricWeather historicWeather = historicWeatherDao.findById(id).orElse(null);
        if (historicWeather != null) {
            this.formatDate(historicWeather);
        }
        model.put("weatherdetail", historicWeather);
        return "detail";
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@Valid @RequestBody SearchCriteria searchCriteria, Errors errors) throws ParseException {

        logger.info("Entering into search handler");
        AjaxResponseBody result = new AjaxResponseBody();
        String errorMsg = null;
        if (Objects.isNull(searchCriteria.getStart()) && Objects.isNull(searchCriteria.getEnd())) {
            errorMsg = "Start date and End date can not both be null ";
        }
        if (errors.hasErrors() || Objects.nonNull(errorMsg)) {
            if (Objects.isNull(errorMsg)) {
                errorMsg = errors.getAllErrors().stream().
                    map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
            }

            logger.error("Error in searching criteria: {}", errorMsg);
            result.setMsg(errorMsg);
            return ResponseEntity.badRequest().body(result);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date oldestDate = dateFormat.parse("1900-01-01");
        Date futureDate = dateFormat.parse("2100-01-01");
        if (searchCriteria.getStart() == null) {
            searchCriteria.setStart(oldestDate);
        }
        if (searchCriteria.getEnd() == null) {
            searchCriteria.setEnd(futureDate);
        }
        List<HistoricWeather> weatherList = historicWeatherDao.findByRecordDate(
            searchCriteria.getStart(), searchCriteria.getEnd());

        logger.info("retrieved number of records :{}", weatherList.size());
        weatherList
            .stream()
            .forEach(weather -> formatDate(weather));
        result.setSearchResult(weatherList);
        return ResponseEntity.ok(result);
    }

        }
