package org.shuai.boot.condition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;


public class ConditionalMissBean implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try {
            BeanDefinition userServiceBean = conditionContext.getRegistry().getBeanDefinition("userService");
            if (Objects.nonNull(userServiceBean)){
                System.out.println("userServiceBean is not null， 不为空，则不用再创建这个Bean");
                return false;
            }
            System.out.println("userServiceBean is null， 为空，则可以创建这个Bean");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("userServiceBean is null， 为空，则可以创建这个Bean");
            return true;

        }
    }
}
