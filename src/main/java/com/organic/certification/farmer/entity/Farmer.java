package com.organic.certification.farmer.entity;

import com.organic.certification.farm.entity.Farm;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;


@Entity
@Data
@Table(name = "farmers")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String phone;

    private String email;

    private String county;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Farm> farms = new ArrayList<>();

}
