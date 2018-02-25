package com.lzw.refactor.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

/**
 * @author binaryfork
 *         这个类的代码出自：https://github.com/binaryfork/Spanny
 *         这里做了一些翻译和注释
 */
public class Spanny extends SpannableStringBuilder {

    private int flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

    public Spanny() {
        super("");
    }

    public Spanny(CharSequence text) {
        super(text);
    }

    public Spanny(CharSequence text, Object span) {
        super(text);
        setSpan(span, 0, text.length());
    }

    public Spanny(CharSequence text, Object... spans) {
        super(text);
        for (Object span : spans) {
            setSpan(span, 0, length());
        }
    }


    /**
     * Append plain text.
     *
     * @return this {@code Spanny}.
     */
    @Override
    public Spanny append(CharSequence text) {
        super.append(text);
        return this;
    }


    /**
     * @deprecated use {@link #append(CharSequence text)}
     */
    @Deprecated
    public Spanny appendText(CharSequence text) {
        append(text);
        return this;
    }


    /**
     * Add the ImageSpan to the start of the text.
     *
     * @return this {@code Spanny}.
     */
    public Spanny append(CharSequence text, ImageSpan imageSpan) {
        text = "." + text;
        append(text);
        setSpan(imageSpan, length() - text.length(), length() - text.length() + 1);
        return this;
    }


    public Spanny append(CharSequence text, Object span) {
        append(text);
        setSpan(span, length() - text.length(), length());
        return this;
    }


    /**
     * Appends the character sequence {@code text} and spans {@code spans} over the appended part.
     *
     * @param text  the character sequence to append.
     * @param spans the object or objects to be spanned over the appended text.
     * @return this {@code Spanny}.
     */
    public Spanny append(CharSequence text, Object... spans) {
        append(text);
        for (Object span : spans) {
            setSpan(span, length() - text.length(), length());
        }
        return this;
    }


    /**
     * Change the flag. Default is SPAN_EXCLUSIVE_EXCLUSIVE.
     * The flags determine how the span will behave when text is
     * inserted at the start or end of the span's range
     *
     * @param flag see {@link Spanned}.
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }


    /**
     * Sets a span object to all appearances of specified text in the spannable.
     * A new instance of a span object must be provided for each iteration
     * because it can't be reused.
     *
     * @param textToSpan Case-sensitive text to span in the current spannable.
     * @param getSpan    Interface to get a span for each spanned string.
     * @return {@code Spanny}.
     */
    public Spanny findAndSpan(CharSequence textToSpan, GetSpanListener getSpan) {
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = toString().indexOf(textToSpan.toString(), lastIndex);
            if (lastIndex != -1) {
                setSpan(getSpan.getSpan(), lastIndex, lastIndex + textToSpan.length());
                lastIndex += textToSpan.length();
            }
        }
        return this;
    }

    /**
     * Mark the specified range of text with the specified object.
     * The flags determine how the span will behave when text is
     * inserted at the start or end of the span's range.
     */
    private void setSpan(Object span, int start, int end) {
        setSpan(span, start, end, flag);
    }


    /**
     * Interface to return a new span object when spanning multiple parts in the text.
     */
    public interface GetSpanListener {

        /**
         * @return A new span object should be returned.
         * for example: return new BackgroundColorSpan(Color.RED);
         * 其实这里可以返回的类可以使用android.text.style包下面的类
         * {@link android.text.style.AbsoluteSizeSpan}
         * {@link android.text.style.AlignmentSpan} 这是一个接口
         * {@link android.text.style.BackgroundColorSpan}
         * {@link android.text.style.BulletSpan}
         * {@link android.text.style.CharacterStyle}
         * {@link android.text.style.ClickableSpan}
         * {@link android.text.style.DrawableMarginSpan}
         * {@link android.text.style.DynamicDrawableSpan}
         * {@link android.text.style.EasyEditSpan}
         * {@link android.text.style.ForegroundColorSpan}
         * {@link android.text.style.IconMarginSpan}
         * {@link android.text.style.ImageSpan}
         * {@link android.text.style.LeadingMarginSpan} 这是一个接口
         * {@link android.text.style.LineBackgroundSpan} 这是一个接口
         * {@link android.text.style.LineHeightSpan} 这是一个接口
         * {@link android.text.style.LocaleSpan}
         * {@link android.text.style.MaskFilterSpan}
         * {@link android.text.style.MetricAffectingSpan}
         * {@link android.text.style.ParagraphStyle} 这是一个接口
         * {@link android.text.style.QuoteSpan}
         * {@link android.text.style.RasterizerSpan}
         * {@link android.text.style.RelativeSizeSpan}
         * {@link android.text.style.ReplacementSpan}
         * {@link android.text.style.ScaleXSpan}
         * {@link android.text.style.StrikethroughSpan}
         * {@link android.text.style.StyleSpan}
         * {@link android.text.style.SubscriptSpan}
         * {@link android.text.style.SuggestionSpan}
         * {@link android.text.style.SuperscriptSpan}
         * {@link android.text.style.TabStopSpan} 这是一个接口
         * {@link android.text.style.TextAppearanceSpan}
         * {@link android.text.style.TtsSpan}
         * {@link android.text.style.TypefaceSpan}
         * {@link android.text.style.UnderlineSpan}
         * {@link android.text.style.UpdateAppearance} 这是一个接口
         * {@link android.text.style.UpdateLayout} 这是一个接口
         * {@link android.text.style.URLSpan}
         * {@link android.text.style.WrapTogetherSpan} 这是一个接口
         */
        Object getSpan();
    }

    /**
     * Sets span objects to the text. This is more efficient than creating a new instance of Spanny
     * or SpannableStringBuilder.
     *
     * @return {@code SpannableString}.
     */
    public static SpannableString spanText(CharSequence text, Object... spans) {
        SpannableString spannableString = new SpannableString(text);
        for (Object span : spans) {
            spannableString.setSpan(span, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public static SpannableString spanText(CharSequence text, Object span) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(span, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
