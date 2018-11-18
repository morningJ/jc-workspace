package com.java.smart.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    /**
     * 创建token
     * @Title: createKey 
     * @Project Froad Cbank Coremodule Framework Expand Token
     * @Package com.froad.cbank.coremodule.framework.expand.token.annotation
     * @Description 是否向Cookie中添加Token，默认false
     * @return
     */
    boolean createKey() default false;
}
