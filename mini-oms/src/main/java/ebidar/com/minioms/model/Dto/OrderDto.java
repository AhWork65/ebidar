package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.OrderType;

import lombok.*;





@ToString
@Getter
@Setter
public class OrderDto {
    public OrderDto(String shareCode, String exchangeCode, OrderType orderType, Double amount, Integer count) {
        this.shareCode = shareCode;
        this.exchangeCode = exchangeCode;
        this.orderType = orderType;
        this.amount = amount;
        this.count = count;
    }

    public OrderDto() {
    }

    private String shareCode;
    private String exchangeCode;
    private OrderType orderType;
    private Double amount;
    private Integer count;

}
