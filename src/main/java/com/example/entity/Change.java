package com.example.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Date;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Change {
    private Date changedOn;
    private String changedBy;
}
