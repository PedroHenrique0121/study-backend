package com.pedro.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "roleId")
    private Role role;
}
