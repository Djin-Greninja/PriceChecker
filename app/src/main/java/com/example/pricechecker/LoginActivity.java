package com.example.pricechecker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private Button  cont_btn;
    private EditText editTextPhone;

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        button = findViewById(R.id.back_btn);
        cont_btn = findViewById(R.id.continue_btn);
        editTextPhone = findViewById(R.id.editTextPhone);
        TextView textView = findViewById(R.id.textView6);

        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToNextActivity();
            }
        });

        String clickableString = getString(R.string.clickable_string);

        // Convert HTML string to Spanned
        Spanned spannedText = Html.fromHtml(clickableString);

        // Create a SpannableString from the Spanned
        SpannableString spannableString = new SpannableString(spannedText);

        // Find the index of the clickable part in the full text
        int startIndex = clickableString.indexOf("terms of use");
        int endIndex = startIndex + "terms of use".length();

        // Set a ClickableSpan on the clickable part
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle click action for terms of use
                openTermsOfUse(); // You can define this method to open the terms of use page
            }
        };

        // Set the ForegroundColorSpan to make the text appear in blue color
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue));

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text to the TextView
        textView.setText(spannableString);

        // Make the TextView clickable and handle link clicks
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {navigateToPreviousActivity();}
        });
    }

    private void navigateToNextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void openTermsOfUse() {
        String url = "https://google.com";
        // Open the terms of use page in a web browser
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void navigateToPreviousActivity() {
        Intent intent = new Intent(this, IntroPage.class);
        startActivity(intent);
    }

}
