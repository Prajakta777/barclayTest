package interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Trade")
@Getter
@Setter
public class Trade {

    @Id
    private String tradeId;

    private int version;

    private String counterPartyId;

    private String bookId;

    private Date maturityDate;

    private Date createdDate;

    private String expired;

}
