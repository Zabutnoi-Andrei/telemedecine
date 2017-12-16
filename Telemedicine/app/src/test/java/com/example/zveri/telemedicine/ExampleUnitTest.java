package com.example.zveri.telemedicine;

import com.example.zveri.telemedicine.api.RemoteApi;
import com.example.zveri.telemedicine.api.entities.UserAuthBody;
import com.example.zveri.telemedicine.api.entities.UserAuthResult;
import com.example.zveri.telemedicine.api.entities.UserRegBody;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Response;

public class ExampleUnitTest {

    private final String testUserEmail = "ti155@mail.ru";
    private final String testUserPwd = "12345";

    private static void initProxy(){
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");

        System.out.println("http.Host: " + System.getProperty("http.proxyHost"));
        System.out.println("http.port: " + System.getProperty("http.proxyPort"));
        System.out.println("https.Host: " + System.getProperty("https.proxyHost"));
        System.out.println("https.port: " + System.getProperty("https.proxyPort"));
    }
    // send wrong auth request
    private static void testDestination() throws IOException {
        Call<UserAuthResult> res = RemoteApi.getApi().userAuth(new UserAuthBody("NON_EXISTING", "NON_EXISTING"));

        Response<UserAuthResult> response = res.execute();
        // need to find out what does server send in case failed login attempt is handled
        Assert.assertNotEquals(response.code(), 417, "Cannot reach destination server");
    }

    @BeforeSuite
    public static void init() throws Exception {
        // for fiddler capturing
        initProxy();

        // make sure destination server is up and running
        testDestination();
    }


    @Test(priority = 1)
    public void registrationTest() throws IOException, ParseException {
        UserRegBody body = new UserRegBody(
                "Vasya Pupkin",
                new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1996"),
                testUserEmail,
                "37379111222",
                "Some address 12",
                "VASYANZVEZDA",
                testUserPwd,
                ""
        );
        Call<Class<Void>> call = RemoteApi.getApi().userRegistration(body);
        Response<Class<Void>> response = call.execute();

        Assert.assertEquals(response.code(), 201, "Registration failed");
    }


    @Test(priority = 2)
    public void authTest() throws IOException {
        UserAuthBody body = new UserAuthBody(testUserEmail, testUserPwd);
        Call<UserAuthResult> call = RemoteApi.getApi().userAuth(body);
        Response<UserAuthResult> response = call.execute();

        Assert.assertEquals(response.body().getStatus(), "SUCCESS");
    }
}