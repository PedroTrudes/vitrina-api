package br.com.vitrina_api.modules.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    @JsonIgnore
    private Store store;

}
