package com.evendas.evendas.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false)
    @Getter
    @Setter
    protected UUID id;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Getter
    @Setter
    protected LocalDateTime dataCadastro;

    @Column
    @Getter
    @Setter
    @UpdateTimestamp
    protected LocalDateTime dataAlteracao;

    @Column
    @Getter
    @Setter
    protected LocalDateTime dataExcluido;

    public void changeExcludedDate(){
        this.dataExcluido = LocalDateTime.now();
    }


}