package com.ssau.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "students", schema = "public")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(unique = true)
    private int number;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Group group;
}
