package br.com.vitrina_api.modules.store.model;

import br.com.vitrina_api.modules.store.dto.UpdateNumberContactDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "whatsapps",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_store_phone",
                columnNames = {"store_id", "countryCode", "areaCode", "number"}
        )},
        indexes = {
                @Index(name = "idx_whatsapp_store", columnList = "store_id")
        }

)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class NumberContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String areaCode;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String agent;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @JsonIgnore
    private Store store;

    public void updateFrom (UpdateNumberContactDTO dto){
        if(dto.countryCode() != null){
            this.countryCode = dto.countryCode();
        }
        if(dto.areaCode() != null){
            this.areaCode = dto.areaCode();
        }
        if(dto.number() != null){
            this.number = dto.number();
        }
        if(dto.agent() != null){
            this.agent = dto.agent();
        }
        if(dto.active() != null){
            this.active = dto.active();
        }
    }

}
