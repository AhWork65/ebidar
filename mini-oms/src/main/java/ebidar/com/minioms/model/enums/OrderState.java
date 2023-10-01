package ebidar.com.minioms.model.enums;

public enum OrderState {
    NON(0),
    REJECT(1),
    ACCEPT(2);

    private Integer value;

    OrderState(Integer i) {
        this.value = i;
    }

    public Integer toValue() {
        return value;
    }
}
