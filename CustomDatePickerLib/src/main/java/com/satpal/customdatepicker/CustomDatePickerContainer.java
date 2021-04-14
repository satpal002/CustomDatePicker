package com.satpal.customdatepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomDatePickerContainer extends LinearLayout {
    public CustomDatePickerContainer(Context context) {
        super(context);
        initLayout();
    }

    public CustomDatePickerContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDatePickerContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomDatePickerContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initLayout() {
        inflate(getContext(), R.layout.date_picker_parent, this);
    }
}
