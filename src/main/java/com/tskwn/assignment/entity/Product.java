package com.tskwn.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long price;
}
