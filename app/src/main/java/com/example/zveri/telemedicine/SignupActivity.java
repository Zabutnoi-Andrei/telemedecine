package com.example.zveri.telemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.zveri.telemedicine.api.entities.UserRegBody;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Vita on 1/7/2018.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    public static String REG_BODY_EXTRAS_KEY = "REG_BODY";
    private UserRegBody regBody;
    private EditText name;
    private EditText email;
    private EditText bday;
    private Date dt_bday;
    private EditText phone;
    private EditText address;
    private String prevTitle;

    @Override
    protected void onStart() {
        super.onStart();
        prevTitle = getTitle().toString();
        setTitle("Registration");
    }

    @Override
    protected void onStop() {
        super.onStop();
        setTitle(prevTitle);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initVariables();
        setViewClickListeners();
        fillInFields();
    }

    private void setViewClickListeners(){
        findViewById(R.id.userPhoto).setOnClickListener(this);
        findViewById(R.id.btn_reg_next).setOnClickListener(this);
    }

    private void initVariables(){
        name    = (EditText) findViewById(R.id.txt_name);
        email   = (EditText) findViewById(R.id.txt_email);
        bday    = (EditText) findViewById(R.id.txt_bday);
        phone   = (EditText) findViewById(R.id.txt_phone);
        address = (EditText) findViewById(R.id.txt_address);
    }

    // FOR TEST ONLY
    private void fillInFields(){
        name.setText("Vasya Pupkin");
        email.setText("vasya.p@gmail.com");
        bday.setText("11/11/11");
        phone.setText("733744");
        address.setText("Some address 13/1");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userPhoto:
                break;
            case R.id.btn_reg_next:
                if (!validateFieldsAndSetBDate()){
                    break;
                }
                regBody = new UserRegBody();
                regBody.setAddress(address.getText().toString());
                regBody.setEmail(email.getText().toString());
                regBody.setFullName(name.getText().toString());
                regBody.setBirthday(dt_bday);
                regBody.setPhone(phone.getText().toString());
                /**
                 * TODO
                 */
                regBody.setBase64photo(null);

                // start new activity and send completed fields to it
                Intent i = new Intent(this, CompleteSignupActivity.class);
                i.putExtra(REG_BODY_EXTRAS_KEY, regBody);
                startActivity(i);
                break;
        }
    }

    // setting dt_bdate here to avoid handling ParseException twice
    private boolean validateFieldsAndSetBDate(){
        boolean result = true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yy");

        if (address.getText() == null || address.getText().toString().isEmpty()){
            result = false;
            address.setError("This field is required");
        }
        if (phone.getText() == null || phone.getText().toString().isEmpty()){
            result = false;
            phone.setError("This field is required");
        }
        if (name.getText() == null || name.getText().toString().isEmpty()){
            result = false;
            name.setError("This field is required");
        }
        if (!LoginActivity.validateEmail(email.getText().toString())){
            result = false;
            email.setError("Wrong email format");
        }
        try {
            dt_bday = formatter.parse(bday.getText().toString());
        } catch (ParseException | NullPointerException e) {
            result = false;
            bday.setError("Date should be in dd/mm/yy format");
        }
        return result;
    }
}
