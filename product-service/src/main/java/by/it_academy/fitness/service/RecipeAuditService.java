package by.it_academy.fitness.service;

import by.it_academy.fitness.core.audit.Audit;
import by.it_academy.fitness.core.dto.EssenceType;
import by.it_academy.fitness.core.dto.RecipeForCU;
import by.it_academy.fitness.service.api.IAuditService;
import by.it_academy.fitness.service.api.IRecipeService;
import by.it_academy.fitness.web.utils.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Aspect
@Service
public class RecipeAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;

    private final IRecipeService recipeService;

    public RecipeAuditService(IAuditService auditService, UserHolder userHolder,
                              IRecipeService recipeService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.recipeService = recipeService;
    }

    @After("execution(* by.it_academy.fitness.service.RecipeService.add(..))")
    public void add(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            RecipeForCU arg = (RecipeForCU) args[0];

            String uuid = recipeService.getRecipeEntity(arg.getTitle()).getUuid().toString();
            User user = userHolder.getUser();

            Audit audit = new Audit();
            audit.setUser(user);
            audit.setText("Создана запись в журнале рецептов");
            audit.setType(EssenceType.RECIPE);
            audit.setId(uuid);
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* by.it_academy.fitness.service.RecipeService.updateRecipe(..))")
    public void AfterUpdateUser(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            UUID uuid = (UUID) args[0];

            Audit audit = new Audit();
            User user = userHolder.getUser();
            audit.setUser(user);
            audit.setText("Обновлена запись о рецепте");
            audit.setType(EssenceType.RECIPE);

            audit.setId(uuid.toString());
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
