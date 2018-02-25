package com.lzw.refactor.textutils;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.util.Locale;


/**
 * 兼容Android 17+ 版本
 * Jellybean MR1 - specific TextUtils API access.
 * @author lzw
 * @date 2018/2/18
 */
@RequiresApi(17)
@TargetApi(17)
class TextUtilsCompatJellybeanMr1 implements ITextUtilsCompat{

    @Override
    @NonNull
    public String htmlEncode(@NonNull String s) {
        return TextUtils.htmlEncode(s);
    }

    @Override
    public int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }
}
