package br.com.vitrina_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "whatsapps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumberContact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;
    private String agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}
