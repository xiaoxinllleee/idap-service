package org.cmms.common.aspect.annotation;

import java.lang.annotation.*;

/**
 *  组织机构权限（登录用户所拥有的组织机构权限）注解
 * @Author liuxiangqun
 * @Date 2020年12月31日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface PermissonSszh {

    String value() default "";


    /**
     * 配置菜单的组件路径,用于数据权限
     */
    String pageComponent() default "";
}
