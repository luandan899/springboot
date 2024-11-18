package com.luandev.springbootsecurity.model.user.entity;

import com.luandev.springbootsecurity.model.common.entity.BaseEntity;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "INVALID_TOKEN")
public class InvalidTokenEntity extends BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "TOKEN_ID")
    private String tokenId;

}
