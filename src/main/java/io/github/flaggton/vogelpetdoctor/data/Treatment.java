package io.github.flaggton.vogelpetdoctor.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "treatments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long treatmentId;

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "comment")
    private String comment;

}