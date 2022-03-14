package interview.controller;

import interview.model.Trade;
import interview.service.TradingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TradingController.class, TradingService.class})

public class TradingControllerTest {

    @Autowired
    private TradingController tradingController;

    @MockBean
    private TradingService tradingService;

    @Test
    public void getTrade() throws ParseException {
        List<Trade> trades = new ArrayList<>();
        trades.add(prepareTrade());
        Mockito.when(tradingService.getAllTrades()).thenReturn(trades);
        assertNotNull(tradingController.getAllTrades());

    }

    private Trade prepareTrade() throws ParseException {
        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setVersion(1);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022");
        trade.setMaturityDate(date);
        trade.setBookId("B-1");
        trade.setCounterPartyId("CP-1");
        return trade;
    }

    @Test
    public void addTrade() {
    }
}