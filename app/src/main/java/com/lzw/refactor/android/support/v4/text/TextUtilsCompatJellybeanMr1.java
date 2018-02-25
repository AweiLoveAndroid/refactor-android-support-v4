package com.lzw.refactor.android.support.v4.text;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.Locale;

/**
 * Jellybean MR1 - specific TextUtils API access.
 *
 * 从v4包拷过来的类，为了好作对比就拷过来了
 */
@RequiresApi(17)
@TargetApi(17)
class TextUtilsCompatJellybeanMr1 {
    @NonNull
    public static String htmlEncode(@NonNull String s) {
        return TextUtils.htmlEncode(s);
    }

    public static int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }
}
