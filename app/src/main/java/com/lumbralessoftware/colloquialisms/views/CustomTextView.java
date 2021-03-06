package com.lumbralessoftware.colloquialisms.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lumbralessoftware.colloquialisms.MainApplication;
import com.lumbralessoftware.colloquialisms.R;

/**
 * Created by javiergonzalezcabezas on 25/5/15.
 */
public class CustomTextView extends TextView {

        public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init(attrs);
        }

        public CustomTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(attrs);

        }
        public CustomTextView(Context context) {
            super(context);
            init(null);
        }

        private void init(AttributeSet attrs) {
            if (attrs!=null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextViewForm);
                //String fontName = a.getString(R.styleable.EditTextForm_fontName);
                //if (fontName!=null) {
                setTypeface(MainApplication.Fonts.PENCIL);
                //}
                a.recycle();
            }
        }
    }

