package com.spring.repo.user.repository;

import com.spring.repo.basemongo.editingdocument.DocumentStatus;
import com.spring.repo.basemongo.editingdocument.repository.EditableDocumentRepository;
import com.spring.repo.basemongo.exception.bad_request.BadRequestException;
import com.spring.repo.user.documents.User;
import com.spring.repo.user.predicate.UserPredicateBuilder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
public interface UserRepository extends EditableDocumentRepository<User> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);


    default User partialUpdatePassword(String email, Consumer<User> doUpdate) {
        User existedDocument =
                findOne(UserPredicateBuilder.getUserByEmail(email)).orElseThrow(() -> new BadRequestException("Document not found"));

        if (DocumentStatus.DRAFT.equals(existedDocument.getDocumentStatus())) {
            doUpdate.accept(existedDocument);
            existedDocument.setEditingDocument(null);
            return save(existedDocument);
        }

        if (Objects.isNull(existedDocument.getEditingDocument())) {
            User existedDocument2 =
                    findOne(UserPredicateBuilder.getUserByEmail(email)).orElseThrow(() -> new BadRequestException("Document not found"));
            existedDocument.setDocumentStatus(DocumentStatus.EDITING);
            existedDocument2.setEditingDocument(null);
            existedDocument.setEditingDocument(existedDocument2);
        }

        doUpdate.accept(existedDocument.getEditingDocument());
        existedDocument.setDocumentStatus(DocumentStatus.EDITING);
        return getPreviewedDocument(save(existedDocument));
    }

}
