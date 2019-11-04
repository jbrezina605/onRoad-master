package com.bdp.onroad;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bdp.onroad.R;
import com.bdp.onroad.UserTypeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    // member variables
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;
    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String DISPLAY_NAME_KEY = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, UserTypeActivity.class));
            finish();
        }
        mEmailView = (AutoCompleteTextView) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.integer.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //  FirebaseAuth instance
        mAuth= FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)
    {

        attemptLogin();

    }

    // Executed when Register button pressed
    public void registerNewUser(View v)
    {
        Intent intent = new Intent(this, com.bdp.onroad.RegisterActivity.class);
        startActivity(intent);
        onBackPressed();
        finish();
    }


    private void attemptLogin()
    {
        String email=mEmailView.getText().toString();
        String pass=mPasswordView.getText().toString();

        if(email.equals("")|| pass.equals(""))
            return;


        Toast.makeText(this,"SignIn in Progress",Toast.LENGTH_SHORT).show();




        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                Log.d("hey","SignIn via. Email n Password was:"+task.isSuccessful());
                if(!task.isSuccessful())
                {
                    Log.d("hey","SignIn not successful"+task.getException());
                    showDialogue("SignIn not Successful");
                }
                else
                {
                    Log.d("hey","SignedIn Successfully");
                    Intent intnt = new Intent(LoginActivity.this, UserTypeActivity.class);
                    startActivity(intnt);

                    onBackPressed();
                    finish();
                    showDialogueSignedUp();
                }
            }
        });

    }



    private void showDialogue(String message)
    {
        new AlertDialog.Builder(this)
                .setTitle("OOPS!")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showDialogueSignedUp()
    {
        Toast.makeText(this,"Signed in successfully",Toast.LENGTH_SHORT).show();
        //koment 
    }

}