package interview.exception;

public class InvalidTradeException extends RuntimeException {
    private String tradeId;

    public String getTradeId() {
        return tradeId;
    }

    public InvalidTradeException(String message, String tradeId) {
        super(message);
        this.tradeId = tradeId;
    }
}
