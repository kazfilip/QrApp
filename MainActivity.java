package com.example.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends Activity {
    private Spinner title;
    private Spinner country;
    private Spinner language;
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText trainNumber;
    private ImageView imageView;
    private ViewGroup parentView;
    private final int maxUses = 5;

    private int uses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.le_marche_logo2);
        getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(),scaleBitmapToScreeSize(R.drawable.le_marche_logo2)));

        TitlePlaceholderAdapter titleAdapter= new TitlePlaceholderAdapter(this);
        titleAdapter.addAll("Mr.","Miss","Lady","Lord","Ms","Mrs.","Dr","Rev.","Mx");

        CountryPlaceholderAdapter countryAdapter = new CountryPlaceholderAdapter(this);
        countryAdapter.addAll("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina",
                "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
                "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
                "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon",
                "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo",
                "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
                "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
                "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
                "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary",
                "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
                "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
                "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar",
                "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
                "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
                "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
                "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama",
                "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
                "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
                "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
                "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
                "South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland",
                "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga",
                "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
                "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu",
                "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        LanguagePlaceholderAdapter languageAdapter = new LanguagePlaceholderAdapter(this);
        languageAdapter.addAll("English","Dutch","French");

        title = findViewById(R.id.Title);
        title.setAdapter(titleAdapter);
        country = findViewById(R.id.Country);
        country.setAdapter(countryAdapter);
        language = findViewById(R.id.Language);
        language.setAdapter(languageAdapter);

        name = findViewById(R.id.Name);
        name.setHint("Enter your name");
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String nameSurnamePattern = "^[a-zA-Z]{3,}(?:[-'\\s][a-zA-Z]+)*$";
                String check = name.getText().toString();
                if (!check.matches(nameSurnamePattern)){
                    name.setTextColor(Color.RED);
                }else name.setTextColor(Color.BLACK);
            }
        });
        surname = findViewById(R.id.Surname);
        surname.setHint("Enter your surname");
        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String nameSurnamePattern = "^[a-zA-Z]{3,}(?:[-'\\s][a-zA-Z]+)*$";
                String check = surname.getText().toString();
                if (!check.matches(nameSurnamePattern)){
                    surname.setTextColor(Color.RED);
                }else surname.setTextColor(Color.BLACK);
            }
        });
        email = findViewById(R.id.Email);
        email.setHint("Enter your email");
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";;
                String check = email.getText().toString();
                if (!check.matches(emailPattern)){
                    email.setTextColor(Color.RED);
                }else email.setTextColor(Color.BLACK);
            }
        });
        trainNumber = findViewById(R.id.TrainNumber);
        trainNumber.setHint("Enter your train number");
        trainNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String trainNumberPattern = "^([01]?[0-9]|2[0-3])[\\s.:]+([0-5]?[0-9])$";
                String check = trainNumber.getText().toString();
                if (!check.matches(trainNumberPattern)){
                    trainNumber.setTextColor(Color.RED);
                }else trainNumber.setTextColor(Color.BLACK);
            }
        });
        Button button = findViewById(R.id.generate);
        imageView = findViewById(R.id.qr_code);
        parentView = (ViewGroup) imageView.getParent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    if (isValidInput() && correctSelection() && uses<maxUses){
                        /*String data = title.getSelectedItem().toString()+"/"+language.getSelectedItem().toString()+"/"+country.getSelectedItem().toString()+"/"+
                                name.getText()+"/"+surname.getText()+"/"+email.getText()+"/"+trainNumber.getText();*/
                        String data = title.getSelectedItem().toString()+"|"+name.getText()+"|"+surname.getText();
                        System.out.println(data);
                        BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,300,300);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                        if (parentView !=null){
                            parentView.removeView(imageView);
                        }
                        parentView.addView(imageView);
                        uses++;
                    }else {
                        if (!isValidInput()){
                            showToast("Wrong data inputted");
                        }else if (!correctSelection()){
                            showToast("Wrong item selected");
                        }else showToast("Maximum uses reached");
                    }
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
        Button clearData = findViewById(R.id.clear_data);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
    }

    private void showToast(String invalidInputFormat) {
        Toast.makeText(this,invalidInputFormat,Toast.LENGTH_SHORT).show();
    }

    private void clearData() {
        //name.getText().clear();
        //surname.getText().clear();
        //email.getText().clear();
        trainNumber.getText().clear();
        //title.setSelection(0);
        //country.setSelection(0);
        //language.setSelection(0);
        if (parentView!=null){
            parentView.removeView(imageView);
        }
    }

    private Bitmap scaleBitmapToScreeSize(int drawableRes) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        return Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, true);
    }
    private boolean isValidInput(){
        String nameSurnamePattern = "^[a-zA-Z]{3,}(?:[-'\\s][a-zA-Z]+)*$";
        //String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String trainNumberPattern = "^([01]?[0-9]|2[0-3])[\\s.:]+([0-5]?[0-9])$";
        String nameCheck = name.getText().toString();
        String surnameCheck = surname.getText().toString();
        String emailCheck = email.getText().toString();
        String trainNumberCheck = trainNumber.getText().toString();
        return nameCheck.matches(nameCheck) && surnameCheck.matches(nameSurnamePattern)
                && trainNumberCheck.matches(trainNumberPattern);
    }
    private boolean correctSelection(){
        return !title.getSelectedItem().toString().equals("Select a title")
                && !country.getSelectedItem().toString().equals("Select a country")
                && !language.getSelectedItem().toString().equals("Select language");
    }
}
