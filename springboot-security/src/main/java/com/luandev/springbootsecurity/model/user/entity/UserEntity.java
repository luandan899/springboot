package com.luandev.springbootsecurity.model.user.entity;

import com.luandev.springbootsecurity.model.common.entity.BaseEntity;
import com.luandev.springbootsecurity.model.user.enums.TokenClaims;
import com.luandev.springbootsecurity.model.user.enums.UserStatus;
import com.luandev.springbootsecurity.model.user.enums.UserType;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CASE_USER")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    public Map<String, Object> getClaims() {

        final Map<String, Object> claims = new HashMap<>();

        claims.put(TokenClaims.USER_ID.getValue(), this.id);
        claims.put(TokenClaims.USER_TYPE.getValue(), this.userType);
        claims.put(TokenClaims.USER_STATUS.getValue(), this.userStatus);
        claims.put(TokenClaims.USER_EMAIL.getValue(), this.email);

        return claims;

    }

}
