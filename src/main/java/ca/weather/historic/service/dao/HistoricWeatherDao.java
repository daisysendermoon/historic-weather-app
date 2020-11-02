package ca.weather.historic.service.dao;

import ca.weather.historic.service.entity.HistoricWeather;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoricWeatherDao extends JpaRepository<HistoricWeather, Long> {

    @Query("select T from HistoricWeather T where T.recordDate between :startDate and :endDate")
    List<HistoricWeather> findByRecordDate(@Param("startDate") Date startDate,
        @Param("endDate") Date endDate);

}
