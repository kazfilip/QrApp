package com.example.qrcode;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class TitlePlaceholderAdapter extends ArrayAdapter<String> {
    public TitlePlaceholderAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item);
        add("Select a title:");
    }

}
