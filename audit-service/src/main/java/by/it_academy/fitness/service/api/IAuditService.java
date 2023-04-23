package by.it_academy.fitness.service.api;
import by.it_academy.fitness.core.dto.Audit;
import by.it_academy.fitness.core.dto.MessageAudit;
import by.it_academy.fitness.core.dto.Page;

import java.util.UUID;

public interface IAuditService {
    void save(MessageAudit messageAudit);
    Audit getAudit (UUID uuid);
    Page<Audit> getPageAudits (int page, int size);
}
