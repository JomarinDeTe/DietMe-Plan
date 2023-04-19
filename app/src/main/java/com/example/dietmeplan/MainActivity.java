package com.example.dietmeplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText mEmailEditText, mPasswordEditText;
    TextView mSignUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailEditText = findViewById(R.id.email_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);
        mSignUpTextView = findViewById(R.id.signup_text_view);

        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_signup.class);
                startActivity(intent);
            }
        });
    }

    public void login(View view) {
        // Get the user's email and password from the EditTexts
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        // Check if the email and password are valid (e.g. not empty)
        if (!email.isEmpty() && !password.isEmpty()) {
            // Get the saved email and password from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "");

            // Check if the email and password match the saved values
            if (email.equals(savedEmail) && password.equals(savedPassword)) {
                // If the email and password match, start the main activity
                Intent intent = new Intent(MainActivity.this, activity_bmi.class);
                startActivity(intent);
                finish(); // finish the current activity so the user cannot go back to it
            } else {
                // If the email and password do not match, display an error message
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        } else {
            // If either the email or password is empty, display an error message
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
        }
    }


    public void signUp(View view) {
        Intent intent = new Intent(this, activity_signup.class);
        startActivity(intent);
        finish();
    }
}
