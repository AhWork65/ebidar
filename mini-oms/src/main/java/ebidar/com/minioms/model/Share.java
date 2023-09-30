package ebidar.com.minioms.model;

import ebidar.com.minioms.model.enums.SettlementDateType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String shareCode;
    private SettlementDateType settlementDate;

    @OneToMany(mappedBy = "share", fetch = FetchType.EAGER)
    private Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Share share = (Share) o;
        return Objects.equals(Id, share.Id) && Objects.equals(shareCode, share.shareCode) && Objects.equals(settlementDate, share.settlementDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, shareCode, settlementDate);
    }
}
