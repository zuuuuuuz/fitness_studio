package by.it_academy.fitness.service;

import by.it_academy.fitness.core.audit.Audit;
import by.it_academy.fitness.core.dto.EssenceType;
import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.service.api.IAuditService;
import by.it_academy.fitness.service.api.IProductService;
import by.it_academy.fitness.web.utils.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Aspect
@Service
public class ProductAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;

    private final IProductService productService;

    public ProductAuditService(IAuditService auditService, UserHolder userHolder,
                               IProductService productService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.productService = productService;
    }

    @After("execution(* by.it_academy.fitness.service.ProductService.add(..))")
    public void add(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            Product arg = (Product) args[0];

            String uuid = productService.getProductEntity(arg.getTitle()).getUuid().toString();
            User user = userHolder.getUser();

            Audit audit = new Audit();
            audit.setUser(user);
            audit.setText("Создана запись в журнале продуктов");
            audit.setType(EssenceType.PRODUCT);
            audit.setId(uuid);
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* by.it_academy.fitness.service.ProductService.updateProduct(..))")
    public void AfterUpdateUser(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            UUID uuid = (UUID) args[0];

            Audit audit = new Audit();
            User user = userHolder.getUser();
            audit.setUser(user);
            audit.setText("Обновлена запись о продукте");
            audit.setType(EssenceType.PRODUCT);

            audit.setId(uuid.toString());
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
