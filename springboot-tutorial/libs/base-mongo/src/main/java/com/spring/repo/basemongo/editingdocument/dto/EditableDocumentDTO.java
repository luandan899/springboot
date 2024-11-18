package com.spring.repo.basemongo.editingdocument.dto;

import com.spring.repo.basemongo.core.dto.BaseDTO;
import com.spring.repo.basemongo.editingdocument.DocumentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
public abstract class EditableDocumentDTO<D extends BaseDTO> extends BaseDTO {

    protected D editingDocument;
    protected DocumentStatus documentStatus;
}
