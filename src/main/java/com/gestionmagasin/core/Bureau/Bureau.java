package com.gestionmagasin.core.Bureau;

import com.gestionmagasin.core.Fonctionnaire.Fonctionnaire;
import com.gestionmagasin.core.Service.ServiceClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bureau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "serviceClassId", nullable = false)
    private ServiceClass serviceClass;

    @OneToMany(mappedBy = "bureau",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Fonctionnaire> fonctionnares;
}
