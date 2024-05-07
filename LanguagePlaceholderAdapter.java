package com.example.qrcode;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.compose.ui.text.Placeholder;

public class LanguagePlaceholderAdapter extends ArrayAdapter<String> {
    LanguagePlaceholderAdapter(Context context){
        super(context, android.R.layout.simple_spinner_item);
        add("Select language");
    }
}
