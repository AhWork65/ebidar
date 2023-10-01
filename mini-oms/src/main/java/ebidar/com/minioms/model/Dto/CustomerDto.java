package ebidar.com.minioms.model.Dto;

import lombok.*;


@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CustomerDto {

    private String name;
    private String lastName;
    private String exchangeCode;

}
