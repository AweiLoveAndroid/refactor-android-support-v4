package com.lzw.refactor.textutils;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * v4包下面的TextUtilsCompat的简单优化
 * 这里使用的是策略模式，根据不同api版本调用不同的接口实现类
 * 这样写更好维护。
 * @author lzw
 * @date 2018/2/18
 */
public final class TextUtilsCompat {

    private static final ITextUtilsCompat IMPL;

    private TextUtilsCompat() {}

    static {
        final int version = Build.VERSION.SDK_INT;
        // JellyBean MR1 大于等于17
        if (version >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            IMPL = new TextUtilsCompatJellybeanMr1();
        } else {
            IMPL = new TextUtilsCompatImpl();
        }
    }

    /**
     * Html-encode the string.
     * @param s the string to be encoded
     * @return the encoded string
     */
    @NonNull
    public static String htmlEncode(@NonNull String s) {
        return IMPL.htmlEncode(s);
    }

    /**
     * Return the layout direction for a given Locale
     *
     * @param locale the Locale for which we want the layout direction. Can be null.
     * @return the layout direction. This may be one of:
     * {@link android.support.v4.view.ViewCompat#LAYOUT_DIRECTION_LTR} or
     * {@link android.support.v4.view.ViewCompat#LAYOUT_DIRECTION_RTL}.
     *
     * Be careful: this code will need to be updated when vertical scripts will be supported
     */
    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        return IMPL.getLayoutDirectionFromLocale(locale);
    }

}

