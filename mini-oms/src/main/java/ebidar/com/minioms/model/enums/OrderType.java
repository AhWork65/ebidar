package ebidar.com.minioms.model.enums;

public enum OrderType {
    SALE(1),
    BUY(2);

    private Integer value;

    OrderType(Integer i) {
        this.value = i;
    }

    public Integer toValue() {
        return value;
    }
}
