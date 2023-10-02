package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.OrderState;
import lombok.*;


@ToString
@Getter
@Setter
public class UpdateOrderDto {
    private Long id;
    private OrderState orderState;

    public UpdateOrderDto() {
    }

    public UpdateOrderDto(Long id, OrderState orderState) {
        this.id = id;
        this.orderState = orderState;
    }
}
