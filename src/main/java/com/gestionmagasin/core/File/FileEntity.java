package com.gestionmagasin.core.File;

import com.gestionmagasin.core.Entree.Entree;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private byte[] filedata;
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "entreeId")
    private Entree entree;
}
