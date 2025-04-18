package com.sociedade.catalogoback.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;


import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class GenericEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Boolean deleted;

    @Column()
    private Boolean ativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;


    @PrePersist
    private void fillDate() {
        this.setDateCreate(LocalDateTime.now());
    }

    @PreUpdate
    private void updateDate() {
        this.setDateUpdate(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "EntidadeGenerica{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", ativo=" + ativo +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}
