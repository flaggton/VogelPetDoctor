package io.github.flaggton.vogelpetdoctor.data;

import io.github.flaggton.vogelpetdoctor.enums.AnimalType;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pets")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "animal_type")
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

}