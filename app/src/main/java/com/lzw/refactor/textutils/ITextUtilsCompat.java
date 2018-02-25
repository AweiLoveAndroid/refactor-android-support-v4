package com.lzw.refactor.textutils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * 抽取公用的接口
 */
public interface ITextUtilsCompat {

    /**
     * Html-encode the string.
     * @param s the string to be encoded
     * @return the encoded string
     */
    public String htmlEncode(@NonNull String s);


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
    public int getLayoutDirectionFromLocale(@Nullable Locale locale);
}
