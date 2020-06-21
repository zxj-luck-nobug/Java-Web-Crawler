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
    String value();
}
