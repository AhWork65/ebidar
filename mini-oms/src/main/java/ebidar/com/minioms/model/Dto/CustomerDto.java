package ebidar.com.minioms.model.Dto;

import lombok.*;



@ToString
@Getter
@Setter
public class CustomerDto {

    private String name;
    private String lastName;
    private String exchangeCode;

    public CustomerDto() {
    }

    public CustomerDto(String name, String lastName, String exchangeCode) {
        this.name = name;
        this.lastName = lastName;
        this.exchangeCode = exchangeCode;
    }
}
