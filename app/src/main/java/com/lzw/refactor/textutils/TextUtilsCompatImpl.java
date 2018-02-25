package com.lzw.refactor.textutils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.text.ICUCompat;
import android.support.v4.view.ViewCompat;

import java.util.Locale;

/**
 * Android 17以下版本使用这个类
 * @author lzw
 * @date 2018/2/18
 */
class TextUtilsCompatImpl implements ITextUtilsCompat{

    public static final Locale ROOT = new Locale("", "");

    static String ARAB_SCRIPT_SUBTAG = "Arab";
    static String HEBR_SCRIPT_SUBTAG = "Hebr";


    @Override
    @NonNull
    public String htmlEncode(@NonNull String s) {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;"); //$NON-NLS-1$
                    break;
                case '>':
                    sb.append("&gt;"); //$NON-NLS-1$
                    break;
                case '&':
                    sb.append("&amp;"); //$NON-NLS-1$
                    break;
                case '\'':
                    //http://www.w3.org/TR/xhtml1
                    // The named character reference &apos; (the apostrophe, U+0027) was
                    // introduced in XML 1.0 but does not appear in HTML. Authors should
                    // therefore use &#39; instead of &apos; to work as expected in HTML 4
                    // user agents.
                    sb.append("&#39;"); //$NON-NLS-1$
                    break;
                case '"':
                    sb.append("&quot;"); //$NON-NLS-1$
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }


    @Override
    public int getLayoutDirectionFromLocale(@Nullable Locale locale) {
        if (locale != null && !locale.equals(ROOT)) {
            final String scriptSubtag = ICUCompat.maximizeAndGetScript(locale);
            if (scriptSubtag == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }

            // This is intentionally limited to Arabic and Hebrew scripts, since older
            // versions of Android platform only considered those scripts to be right-to-left.
            if (scriptSubtag.equalsIgnoreCase(ARAB_SCRIPT_SUBTAG) ||
                    scriptSubtag.equalsIgnoreCase(HEBR_SCRIPT_SUBTAG)) {
                return ViewCompat.LAYOUT_DIRECTION_RTL;
            }
        }
        return ViewCompat.LAYOUT_DIRECTION_LTR;
    }

    /**
     * Fallback algorithm to detect the locale direction. Rely on the first char of the
     * localized locale name. This will not work if the localized locale name is in English
     * (this is the case for ICU 4.4 and "Urdu" script)
     *
     * @param locale
     * @return the layout direction. This may be one of:
     * {@link ViewCompat#LAYOUT_DIRECTION_LTR} or
     * {@link ViewCompat#LAYOUT_DIRECTION_RTL}.
     *
     * Be careful: this code will need to be updated when vertical scripts will be supported
     */
    private static int getLayoutDirectionFromFirstChar(@NonNull Locale locale) {
        switch(Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            case Character.DIRECTIONALITY_RIGHT_TO_LEFT:
            case Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC:
                return ViewCompat.LAYOUT_DIRECTION_RTL;

            case Character.DIRECTIONALITY_LEFT_TO_RIGHT:
            default:
                return ViewCompat.LAYOUT_DIRECTION_LTR;
        }
    }
}
