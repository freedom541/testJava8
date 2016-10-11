package com.ccl.tools;

import java.lang.annotation.*;

/**
 * Created by ccl on 16/10/11.
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Info {
    String value();
}
