package interview.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Schedular {

    @Autowired
    private TradingService tradingService;

    @Scheduled(cron = "${trade.cronExpression}")
    public void updateExpiry() {
        log.info("Check and update expired trade flag.");
        tradingService.updateExpiryFlag();
    }
}
