package interview.controller;

import interview.exception.InvalidTradeException;
import interview.model.Trade;
import interview.service.TradingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trade")
@Log4j2
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @GetMapping(value = "/getAllTrades")
    public List<Trade> getAllTrades() {
        return tradingService.getAllTrades();
    }

    @PostMapping(value = "/newTrade")
    public ResponseEntity<String> addTrade(@RequestBody final Trade trade) {
        try {
            log.info("Received trading request");
            tradingService.validateTradeAndSave(trade);
        } catch (InvalidTradeException e) {
            log.error("Error during trading. Reason : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
