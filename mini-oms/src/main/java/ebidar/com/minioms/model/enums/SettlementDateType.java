package ebidar.com.minioms.model.enums;

public enum SettlementDateType {
    T0(0),
    T1(1),
    T2(2),
    T3(3);

    private Integer value;

    SettlementDateType(Integer i) {
        this.value = i;
    }

    public Integer toValue() {
        return value;
    }
}
