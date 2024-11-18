package com.luandev.springbootpostgre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luandev.springbootpostgre.common.entity.BaseEntity;
import com.luandev.springbootpostgre.enums.Gender;
import com.luandev.springbootpostgre.enums.UserStatus;
import com.luandev.springbootpostgre.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type")
    private UserType type;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status")
    private UserStatus status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    public void saveAddresses(Set<Address> addresses) {
        if (addresses != null) {
            if (this.addresses == null) {
                this.addresses = new HashSet<>();
            }
            for (Address address : addresses) {
                if (address != null) {
                    this.addresses.add(address);
                    address.setUser(this);
                }
            }
        }
    }
}
