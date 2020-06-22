package com.crawler.config.anno;

import java.lang.annotation.*;

/**
 * Injection param
 *
 * @author wencai.xu
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface InjectionParam {
    /**
     * Value string
     *
     * @return the string
     */
    String value();
}
