package cn.devspace.whynotteaming.Manager.Annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Commands {
    @AliasFor(attribute = "Command")
    String value() default "";

    @AliasFor(attribute = "value")
    String Command() default "";
}
