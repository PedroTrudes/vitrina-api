package br.com.vitrina_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NumberContact> contacts = new ArrayList<>();

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Integer plan;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
