package com.spring.repo.basemongo.editingdocument.document;

import com.spring.repo.basemongo.core.document.BaseDocument;
import com.spring.repo.basemongo.editingdocument.DocumentStatus;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public abstract class EditableDocument<D extends BaseDocument> extends BaseDocument {
    @Nullable
    protected D editingDocument;
    @Nullable
    protected DocumentStatus documentStatus;
}
