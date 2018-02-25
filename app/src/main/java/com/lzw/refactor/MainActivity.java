package com.lzw.refactor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import com.lzw.refactor.textutils.TextUtilsCompat;
import com.lzw.refactor.util.Spanny;

/**
 * TextUtilsCompat使用示范
 * 这里使用的是htmlEncode方法 {@link TextUtilsCompat#htmlEncode}
 */
public class MainActivity extends AppCompatActivity {

    private TextView mRefactorTextView;
    private TextView mCompareTextView;

    private String mWords = "这句话很长很长，真的很长很长，长的不得了，长的不要不要的，长...长长...chang...chang................";
    private String mCode = "<html>\n<head>\n</head>\n<body>\n" + mWords + "\n</body>\n</html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        //普通用法
        mCompareTextView = (TextView) findViewById(R.id.tv_compare);
        //重构之后的用法
        mRefactorTextView = (TextView) findViewById(R.id.tv_refactor);


        //把“长”设置为红色背景
        Spanny spanny = new Spanny(mCode).findAndSpan("长", new Spanny.GetSpanListener() {
            @Override
            public Object getSpan() {
                return new BackgroundColorSpan(Color.RED);
            }
        });

        Spanny spanny2 = new Spanny(mCode).findAndSpan("长", new Spanny.GetSpanListener() {
            @Override
            public Object getSpan() {
                return new AlignmentSpan() {
                    @Override
                    public Layout.Alignment getAlignment() {
                        return null;
                    }
                };
            }
        });

        mCompareTextView.setText(spanny);
        mRefactorTextView.setText(TextUtilsCompat.htmlEncode(mCode));

    }
}
