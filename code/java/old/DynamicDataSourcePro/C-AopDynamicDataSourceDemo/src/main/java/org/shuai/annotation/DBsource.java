package org.shuai.annotation;
import org.shuai.constants.DataSourceConstant;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBsource {
    String value() default DataSourceConstant.MYSQL_MASTER;
}
