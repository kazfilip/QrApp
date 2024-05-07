package com.example.qrcode;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class CountryPlaceholderAdapter extends ArrayAdapter<String> {
    public CountryPlaceholderAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item);
        add("Select a country");
    }

}
