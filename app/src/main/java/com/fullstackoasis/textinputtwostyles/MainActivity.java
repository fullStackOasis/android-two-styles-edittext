package com.fullstackoasis.textinputtwostyles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

/**
 * Activity demos highlight a username that starts with "@".
 * It could be more complicated, but you get the idea.
 * It is totally possible to highlight text within an EditText in Android on a Samsung device.
 */
public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);

        editText.addTextChangedListener(textWatcher);
    }

    private void highlightUsername(Editable editable) {
        String text = editable.toString();

        // Create a SpannableStringBuilder to apply different styles to the text
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);

        // Find the index of the "@" symbol
        int atIndex = text.indexOf("@");

        if (atIndex != -1) {
            // Find the end index of the username by looking for the next space or end of the string
            int endIndex = text.indexOf(" ", atIndex);
            if (endIndex == -1) {
                endIndex = text.length();
            }

            // Apply the blue background color and white text color to the username
            spannable.setSpan(new BackgroundColorSpan(Color.BLUE), atIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(Color.WHITE), atIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            // Apply the default black text color on white background
            spannable.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(),
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Set the modified text back to the EditText without triggering TextWatcher
        editText.removeTextChangedListener(textWatcher);
        editText.setText(spannable);
        editText.setSelection(spannable.length());
        editText.addTextChangedListener(textWatcher);
    }

    // Add a TextWatcher to the EditText to handle text changes
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // NOOP
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // NOOP
        }

        @Override
        public void afterTextChanged(Editable s) {
            highlightUsername(s);
        }
    };
}