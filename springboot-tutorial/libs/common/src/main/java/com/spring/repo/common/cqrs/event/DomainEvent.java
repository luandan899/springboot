package com.spring.repo.common.cqrs.event;

import com.spring.repo.common.cqrs.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class DomainEvent<E, C extends Command> extends BaseExternalEvent {

    private String app;
    private String eventType;
    private String correlationId;
    private C requestData;
    private E payloadData;

    public DomainEvent(Class<?> eventTypeClass, String byUserId) {
        this.eventType = eventTypeClass.getName();
        this.correlationId = fetchCorrelationId();
    }

    public DomainEvent(E payloadData, String byUserId) {
        this(payloadData.getClass(), byUserId);
        this.payloadData = payloadData;
    }

    public DomainEvent(E payloadData) {
        this(payloadData, null);
    }

    public Version getVersion() {
        return Version.VERSION_1_0;
    }

    private String fetchCorrelationId() {
        String correlationId = MDC.get("correlationId");
        return (correlationId != null && !correlationId.isBlank()) ? correlationId : UUID.randomUUID().toString();
    }

    public enum Version {
        VERSION_1_0, VERSION_1_1, VERSION_1_2
    }
}
