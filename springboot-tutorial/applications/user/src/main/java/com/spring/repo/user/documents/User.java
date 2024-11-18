package com.spring.repo.user.documents;

import com.spring.repo.basemongo.editingdocument.document.EditableDocument;
import com.spring.repo.user.documents.subs.Image;
import com.spring.repo.user.documents.subs.Social;
import com.spring.repo.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends EditableDocument<User> {
    private String name;
    private String phone;
    private String email;
    private String password;
    private Set<Image> images = new HashSet<>();
    private Set<Social> socials = new HashSet<>();

    private UserRole role;
}

