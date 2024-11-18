package com.spring.repo.basemongo.editingdocument.repository;


import com.spring.repo.basemongo.core.repository.MongoQueryDSLRepository;
import com.spring.repo.basemongo.editingdocument.DocumentStatus;
import com.spring.repo.basemongo.editingdocument.document.EditableDocument;
import com.spring.repo.basemongo.exception.bad_request.BadRequestException;
import com.spring.repo.basemongo.exception.not_found.ResourceNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public interface EditableDocumentRepository<D extends EditableDocument>
        extends MongoQueryDSLRepository<D> {

    default D saveDraftOrEditing(D document) {
        if (document.getId() == null) {
            document.setDocumentStatus(DocumentStatus.DRAFT);
            return save(document);
        } else {
            D existedDocument =
                    findById(document.getId())
                            .orElseThrow(
                                    () -> new ResourceNotFoundException(document.getClass(), document.getId()));
            if (DocumentStatus.DRAFT.equals(existedDocument.getDocumentStatus())) {
                return save(document);
            } else {
                // PUBLISHED || EDITING.
                document.setEditingDocument(null);
                document.setDocumentStatus(DocumentStatus.EDITING);
                existedDocument.setDocumentStatus(DocumentStatus.EDITING);
                existedDocument.setEditingDocument(document);
                return save(existedDocument);
            }
        }
    }

    default D partialUpdate(String id, Consumer<D> doUpdate) {
        D existedDocument =
                findById(id).orElseThrow(() -> new BadRequestException("Document not found"));

        if (existedDocument.getDocumentStatus().equals(DocumentStatus.DRAFT)) {
            doUpdate.accept(existedDocument);
            existedDocument.setEditingDocument(null);
            return save(existedDocument);
        }

        if (Objects.isNull(existedDocument.getEditingDocument())) {
            D existedDocument2 =
                    findById(id).orElseThrow(() -> new BadRequestException("Document not found"));
            existedDocument.setDocumentStatus(DocumentStatus.EDITING);
            existedDocument2.setEditingDocument(null);
            existedDocument.setEditingDocument(existedDocument2);
        }

        doUpdate.accept((D) existedDocument.getEditingDocument());
        existedDocument.setDocumentStatus(DocumentStatus.EDITING);
        return getPreviewedDocument(save(existedDocument));
    }

    /**
     * Publish the entity with provided information
     *
     * @param document
     * @return
     */
    default D publishDocument(String id) {
        D existedDocument =
                findById(id).orElseThrow(() -> new BadRequestException("Document not found: " + id));
        D editingDocument = getPreviewedDocument(existedDocument);
        editingDocument.setEditingDocument(null);
        editingDocument.setDocumentStatus(DocumentStatus.PUBLISHED);
        editingDocument.setVersion(existedDocument.getVersion()); // match with the newest version in db
        return save(editingDocument);
    }

    default D discardDraft(String id) {
        return null;
    }

    default D getPreviewedDocument(D document) {
        if (DocumentStatus.EDITING.equals(document.getDocumentStatus())) {
            return (D) document.getEditingDocument();
        }
        return document;
    }

    default List<D> getPreviewedDocuments(List<D> documents) {
        return documents.stream().map(this::getPreviewedDocument).collect(Collectors.toList());
    }

    default Optional<D> getPreviewDocumentById(String id) {
        Optional<D> optionalD = this.findById(id);
        if (optionalD.isPresent()) {
            return Optional.ofNullable(getPreviewedDocument(optionalD.get()));
        }
        return optionalD;
    }
}
