package com.example.zveri.telemedicine;

import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        final ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.layout_root);
        final EditText email =(EditText)findViewById(R.id.email);
        final EditText password =(EditText)findViewById(R.id.pass);

        final float density=getResources().getDisplayMetrics().density;
        final Drawable drawable_email =getResources().getDrawable(R.drawable.login);
        final Drawable drawable_password =getResources().getDrawable(R.drawable.pass);

        final int width=Math.round(27 * density);
        final int height=Math.round(30 * density);

        drawable_email.setBounds(6,3,width,height);
        email.setCompoundDrawables(drawable_email,null,null,null);
        drawable_password.setBounds(6,3,width,height);
        password.setCompoundDrawables(drawable_password,null,null,null);



        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Toast.makeText(LoginActivity.this, "Input Validation Success", Toast.LENGTH_LONG).show();
                }

            }
        });

        /*btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });
    }

    public void openSignupActivity(){
    Intent intent = new Intent(this, SignupActivity.class);
    startActivity(intent);*/
    }

    //Return true if password is valid and false if password is invalid
    protected boolean validatePassword(String password) {
        if(password!=null && password.length()>9) {
            return true;
        } else {
            return false;
        }
    }

    //Return true if email is valid and false if email is invalid
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }
}
