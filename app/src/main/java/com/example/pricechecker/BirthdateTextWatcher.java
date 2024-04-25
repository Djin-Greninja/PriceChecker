package com.example.pricechecker;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Calendar;

public class BirthdateTextWatcher implements TextWatcher {
    private EditText editText;
    private int currentYear;
    private boolean enforceYearLengthLimit;

    public BirthdateTextWatcher(EditText editText, boolean enforceYearLengthLimit) {
        this.editText = editText;
        this.currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.enforceYearLengthLimit = enforceYearLengthLimit;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Not used
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Not used
    }

    @Override
    public void afterTextChanged(Editable s) {
        String input = s.toString();
        if (input.length() == 2 || input.length() == 5) {
            // Add "/" when the user types the second or fifth character
            if (!input.endsWith("/")) {
                editText.setText(new StringBuilder(input).insert(input.length() - 0, "/").toString());
                editText.setSelection(editText.getText().length());
            }
        } else if (input.length() >= 10) {
            // Limit month to number 12
            int month = Integer.parseInt(input.substring(0, 2));
            if (month > 12) {
                editText.setText("12" + input.substring(2, 10));
                editText.setSelection(editText.getText().length());
            }
            // Limit date to number 31
            int date = Integer.parseInt(input.substring(3, 5));
            if (date > 31) {
                editText.setText(input.substring(0, 3) + "31" + input.substring(5, 10));
                editText.setSelection(editText.getText().length());
            }
            // Limit year to current year
            int year = Integer.parseInt(input.substring(6, 10));
            if (year > currentYear) {
                editText.setText(input.substring(0, 6) + currentYear);
                editText.setSelection(editText.getText().length());
            } else if (enforceYearLengthLimit && input.length() > 10) {
                editText.setText(input.substring(0, 10));
                editText.setSelection(editText.getText().length());
            }
        }
    }
}
