package com.example.saber.bookchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Saber on 2015/4/21.
 */
public class RegisterActivity extends Activity {

    /*
 * View Component object
 */
    private EditText userText, passwordText, passwordText2;
    private Button registerButton;

    /*
     * event happened handler
     */
    private View.OnClickListener registerOCL;

    /*
     * value for states
     */
    private boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_register);

        initOCL();
        initComponent();
    }


    private void initOCL(){

        registerOCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid)
                {
                    Intent mIntent = getIntent();
                    Bundle mBundle = new Bundle();
                    mBundle.putString("userName", userText.getText().toString());
                    mBundle.putString("password", passwordText.getText().toString());
                    mIntent.putExtras(mBundle);

                   /* mIntent.putExtra("userName", userText.getText());
                    mIntent.putExtra("password", passwordText.getText());*/

                    Log.i("userText", String.valueOf(userText.getText()));
                    Log.i("password", String.valueOf(passwordText.getText()));

                    RegisterActivity.this.setResult(LoginActivity.RESULT_FOR_NEW_USER, mIntent);
                    RegisterActivity.this.finish();
                }
            }
        };
    }

    private void initComponent(){
        userText = (EditText) findViewById(R.id.user);
        passwordText = (EditText) findViewById(R.id.password);
        passwordText2 = (EditText) findViewById(R.id.password2);
        registerButton = (Button) findViewById(R.id.registerButton);


        registerButton.setOnClickListener(registerOCL);
    }

}
