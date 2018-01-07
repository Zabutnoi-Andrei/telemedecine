package com.example.zveri.telemedicine;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zveri.telemedicine.api.RemoteApi;
import com.example.zveri.telemedicine.api.RemoteApiUtils;
import com.example.zveri.telemedicine.api.entities.UserAuthBody;
import com.example.zveri.telemedicine.api.entities.UserAuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        leftIconsInit();

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_signup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pass);

        switch (v.getId()){
            case R.id.btn_login:
                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    sendAndHandleLoginRequest(v.getContext(), email.getText().toString(), password.getText().toString());
                }
                break;

            case R.id.btn_signup:
                openSignupActivity(v.getContext());
                break;
        }
    }

    private void leftIconsInit(){
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pass);
        final float density = getResources().getDisplayMetrics().density;
        final Drawable drawable_email = getResources().getDrawable(R.drawable.login);
        final Drawable drawable_password = getResources().getDrawable(R.drawable.pass);

        final int width = Math.round(27 * density);
        final int height = Math.round(30 * density);

        drawable_email.setBounds(6, 3, width, height);
        email.setCompoundDrawables(drawable_email, null, null, null);
        drawable_password.setBounds(6, 3, width, height);
        password.setCompoundDrawables(drawable_password, null, null, null);
    }

    /**
     * Sends login request to the server.
     * <ul>
     *     <li>On success, save received token and open HomeScreen activity;</li>
     *     <li>On failure, make a toast with appropriate message.</li>
     * </ul>
     * @param context Context to make a Toast with error message
     * @param validatedEmail Email in valid format (according to {@link #validateEmail(String)})
     * @param validatedPassword Password in valid format (according to {@link #validatePassword(String)})
     */
    public static void sendAndHandleLoginRequest(final Context context, String validatedEmail, String validatedPassword){
        RemoteApi.getApi().userAuth(new UserAuthBody(validatedEmail, validatedPassword)
        ).enqueue(new Callback<UserAuthResult>() {
            @Override
            public void onResponse(Call<UserAuthResult> call, Response<UserAuthResult> response) {
                RemoteApiUtils.logResponse(this.getClass().toString(), response);
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Unable to send auth request", Toast.LENGTH_LONG).show();
                }
                else if (!response.body().getStatus().equalsIgnoreCase("SUCCESS")){
                    Toast.makeText(context, "Invalid username/password", Toast.LENGTH_LONG).show();
                }
                else if (response.body().getStatus().equalsIgnoreCase("SUCCESS")){
                    RemoteApi.setTOKEN(response.body().getMessage());
                    Log.d(context.toString(), "TOKEN - " + RemoteApi.getTOKEN());
                    LoginActivity.openHomeActivity(context);
                }
            }

            @Override
            public void onFailure(Call<UserAuthResult> call, Throwable t) {
                RemoteApiUtils.logResponse(context.getClass().toString(), t);
                Toast.makeText(context, "Unable to send auth request. IOException", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void openSignupActivity(Context context){
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    /**
     * Call on successful login only
     */
    public static void openHomeActivity(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    //Return true if password is valid and false if password is invalid
    public static boolean validatePassword(String password) {
        if (password == null){
            return false;
        }
        return password.length() > 6;
    }

    //Return true if email is valid and false if email is invalid
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()){
            return false;
        }
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }


}
