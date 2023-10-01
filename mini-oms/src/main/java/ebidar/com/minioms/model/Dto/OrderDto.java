package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.OrderType;
import ebidar.com.minioms.model.enums.SettlementDateType;
import lombok.*;

import java.math.BigDecimal;


@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrderDto {

    private String shareCode;
    private String exchangeCode;
    private OrderType orderType;
    private Double amount;
    private Integer count;

}
