package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.Audit;
import by.it_academy.fitness.core.dto.MessageAudit;
import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.exception.SingleErrorResponse;
import by.it_academy.fitness.core.entity.AuditEntity;
import by.it_academy.fitness.repositories.IAuditDao;
import by.it_academy.fitness.service.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {
    private final IAuditDao auditDao;
    private final ConversionService conversionService;

    public AuditService(IAuditDao auditDao, ConversionService conversionService) {
        this.auditDao = auditDao;
        this.conversionService = conversionService;
    }

    @Override
    public void save(MessageAudit messageAudit){
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUuid(UUID.randomUUID());
        auditEntity.setDtCreate(LocalDateTime.now());
        auditEntity.setUserUUID(messageAudit.getUuid());
        auditEntity.setUserMail(messageAudit.getMail());
        auditEntity.setUserFio(messageAudit.getFio());
        auditEntity.setUserRole(messageAudit.getRole());
        auditEntity.setText(messageAudit.getText());
        auditEntity.setType(messageAudit.getType());
        auditEntity.setId(messageAudit.getId());
        auditDao.save(auditEntity);
    }

    @Override
    public Audit getAudit(UUID uuid) {
        Optional<AuditEntity> auditEntityOptional = auditDao.findById(uuid);
        if(auditEntityOptional.isEmpty()){
            throw new SingleErrorResponse("Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз");
        }
        AuditEntity auditEntity = auditEntityOptional.get();
        return conversionService.convert(auditEntity, Audit.class);
    }

    @Override
    public Page<Audit> getPageAudits(int page, int size) {
        org.springframework.data.domain.Page<AuditEntity> auditEntityPage = auditDao.findAll(PageRequest.of(page, size));
        List <AuditEntity> auditEntityList = auditEntityPage.toList();
        List <Audit> auditList = new ArrayList<>();

        for (AuditEntity auditEntity : auditEntityList) {
            auditList.add(conversionService.convert(auditEntity, Audit.class));
        }
        Page <Audit> pageOfAudits = new Page<>(auditEntityPage.getNumber(),
                auditEntityPage.getSize(),
                auditEntityPage.getTotalPages(),
                auditEntityPage.getTotalElements(),
                auditEntityPage.isFirst(),
                auditEntityPage.getNumberOfElements(),
                auditEntityPage.isLast(),
                auditList);
        return pageOfAudits;
    }
}
