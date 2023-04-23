package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.Audit;
import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditConvertor implements Converter<AuditEntity, Audit> {
    @Override
    public Audit convert(AuditEntity auditEntity) {
        Audit audit = new Audit(auditEntity.getUuid(),
                auditEntity.getDtCreate(),
                new User(auditEntity.getUserUUID(),
                        auditEntity.getUserMail(),
                        auditEntity.getUserFio(),
                        auditEntity.getUserRole()),
                auditEntity.getText(),
                auditEntity.getType(),
                auditEntity.getId());

        return audit;
    }
}
