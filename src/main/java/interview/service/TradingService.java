package interview.service;

import interview.dao.TradingRepository;
import interview.exception.InvalidTradeException;
import interview.model.Trade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TradingService {

    @Autowired

    private TradingRepository tradingRepository;

    public List<Trade> getAllTrades() {
        return tradingRepository.findAll();
    }

    public Optional<Trade> checkIfTradePresent(Trade trade) {
        return tradingRepository.findById(trade.getTradeId());
    }

    private boolean compareVersion(int oldVersion, int newVersion) {
        return oldVersion < newVersion ? true : false;
    }

    public boolean validateMaturityDate(Date maturityDate) {
        return !maturityDate.before(new Date()) ? true : false;
    }


    public void validateTradeAndSave(Trade trade) {
        Optional<Trade> tradeValue = checkIfTradePresent(trade);
        if (tradeValue.isPresent()) {
            log.info("tradeId : {} present in DB", trade.getTradeId());
            if (compareVersion(tradeValue.get().getVersion(), trade.getVersion())) {
                tradeValue.get().setVersion(trade.getVersion());
                updateOrSaveTrade(trade);
            } else {
                throw new InvalidTradeException("Lesser version not supported.Rejecting the trade. TradeId : {}", trade.getTradeId());
            }
        } else {
            log.info("tradeId : {} not present in DB. Adding new record to DB.", trade.getTradeId());
            updateOrSaveTrade(trade);
        }
    }

    private void updateOrSaveTrade(Trade trade) {
        if (validateMaturityDate(trade.getMaturityDate())) {
            trade.setCreatedDate(new Date());
            trade.setExpired("N");
            tradingRepository.save(trade);
        } else {
            throw new InvalidTradeException("Invalid maturity.Rejecting the trade.", trade.getTradeId());
        }
    }

    public void updateExpiryFlag() {
        tradingRepository.findAll().stream().forEach(trade -> {
            if (!validateMaturityDate(trade.getMaturityDate())) {
                trade.setExpired("Y");
                log.info("Updating TradeId : {} as expired", trade.getTradeId());
                tradingRepository.save(trade);
            }
        });
    }
}
