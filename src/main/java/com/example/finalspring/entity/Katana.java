package com.example.finalspring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "katana")
public class Katana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @Column(name="swordman")
    private String bySwordsman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "samurai_id")
    private Samurai samurai;
}
