package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.OrderState;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UpdateOrderDto {
    private Long id;
    private OrderState orderState;
}
