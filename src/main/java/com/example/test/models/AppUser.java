package com.example.test.models;

import com.example.test.Enum.AppUserRole;
import com.example.test.Enum.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class AppUser implements Serializable {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private int id;
    private String username;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Region region;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private Boolean enabled=false;
}
