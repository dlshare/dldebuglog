package com.dlshare.debuglog;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by zhangbeibei on 17/7/5.
 * 注解
 */

@Retention(CLASS)
@Target({TYPE, METHOD, CONSTRUCTOR})
public @interface DlLog {
}

