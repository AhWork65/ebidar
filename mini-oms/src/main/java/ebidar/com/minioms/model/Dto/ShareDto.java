package ebidar.com.minioms.model.Dto;

import ebidar.com.minioms.model.enums.SettlementDateType;
import lombok.*;



@ToString
@Getter
@Setter
public class ShareDto {

    private String shareCode;
    private SettlementDateType settlementDate;

    public ShareDto(String shareCode, SettlementDateType settlementDate) {
        this.shareCode = shareCode;
        this.settlementDate = settlementDate;
    }

    public ShareDto() {
    }
}
