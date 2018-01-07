package com.example.zveri.telemedicine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zveri.telemedicine.api.RemoteApi;
import com.example.zveri.telemedicine.api.RemoteApiUtils;
import com.example.zveri.telemedicine.api.entities.UserRegBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteSignupActivity extends AppCompatActivity implements View.OnClickListener{

    private UserRegBody regBody;
    private EditText username;
    private EditText password;
    private String prevTitle;
    private final Context activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_signup);

        setViewClickListeners();
        initVariables();
    }

    @Override
    protected void onStart() {
        super.onStart();
        prevTitle = getTitle().toString();
        setTitle("Complete your registration");
    }

    @Override
    protected void onStop() {
        super.onStop();
        setTitle(prevTitle);
    }

    private void setViewClickListeners(){
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    private void initVariables(){
        username = (EditText) findViewById(R.id.txt_user_name);
        password = (EditText) findViewById(R.id.txt_password);
        regBody = (UserRegBody) this.getIntent()
                .getSerializableExtra(SignupActivity.REG_BODY_EXTRAS_KEY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                if (!validateFields()){
                    break;
                }
                regBody.setUsername(username.getText().toString());
                regBody.setPassword(password.getText().toString());

                registerAndLogin();
                break;
        }
    }

    private boolean validateFields(){
        boolean result = true;
        if (username.getText() == null || username.getText().toString().isEmpty()){
            result = false;
            username.setError("This field is required");
        }

        if (password.getText() == null ||
                !LoginActivity.validatePassword(password.getText().toString())){
            result = false;
            password.setError("This field is required");
        }

        return result;
    }

    /**
     * Sends registration request to the server. Uses <b>regBody</b> local variable to get necessary data<br/>
     * On registration success, sends login request too using {@link LoginActivity#sendAndHandleLoginRequest(Context, String, String)}.
     * <ul>
     *     <li>On success - registrates new user, saves received token and opens HomeScreen activity;</li>
     *     <li>On failure - makes a toast with appropriate message.</li>
     * </ul>
     */
    private void registerAndLogin(){
        RemoteApi.getApi().userRegistration(regBody).enqueue(new Callback<Class<Void>>() {
            @Override
            public void onResponse(Call<Class<Void>> call, Response<Class<Void>> response) {
                if (response.code() != 201){
                    RemoteApiUtils.logResponse(activity.toString(), response);
                    Toast.makeText(activity, "Registration failed. Please try again", Toast.LENGTH_LONG).show();
                }
                else {
                    LoginActivity.sendAndHandleLoginRequest(activity, regBody.getEmail(), regBody.getPassword());
                }
            }

            @Override
            public void onFailure(Call<Class<Void>> call, Throwable t) {
                RemoteApiUtils.logResponse(activity.toString(), t);
                Toast.makeText(activity, "Registration failed. Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

}
