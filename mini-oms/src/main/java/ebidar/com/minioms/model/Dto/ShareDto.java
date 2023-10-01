package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.SettlementDateType;
import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ShareDto {
    private String shareCode;
    private SettlementDateType settlementDate;

}
