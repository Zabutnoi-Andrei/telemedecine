package com.example.zveri.telemedicine.api;

import com.example.zveri.telemedicine.api.entities.DoctorProfile;
import com.example.zveri.telemedicine.api.entities.UserAuthBody;
import com.example.zveri.telemedicine.api.entities.UserAuthResult;
import com.example.zveri.telemedicine.api.entities.UserProfile;
import com.example.zveri.telemedicine.api.entities.UserRegBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by deDwarf on 12/16/2017.
 */

/**
 * Uses Retrofit2 framework (Thanks to <b>BORES VERIGOS</b>). Some useful links:
 * <ul>
 *      <li>https://zeroturnaround.com/rebellabs/getting-started-with-retrofit-2/</li>
 *      <li>https://futurestud.io/tutorials/retrofit-2-upgrade-guide-from-1-9</li>
 * </ul>
 */
public interface IRemoteApi {

    /**
     * Send registration request<br/><br/>
     * <b>Header: </b> Content-Type:application/x-www-form-urlencoded<br/>
     * <b>Path: </b> ~/api/Register/UserReg
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("api/Register/UserReg")
    Call<Class<Void>> userRegistration(@Body UserRegBody regBody);

    /**
     * Send login request<br/><br/>
     * <b>Header: </b> Content-Type:application/x-www-form-urlencoded<br/>
     * <b>Path: </b> ~/api/Login/UserAuth
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("api/Login/UserAuth")
    Call<UserAuthResult> userAuth(@Body UserAuthBody authBody);

    /**
     * Send request for current user`s profile information<br/><br/>
     * <b>Path:</b> ~/api/Profile/GetProfile<br/>
     * <b>Header</b> - Content-Type:application/x-www-form-urlencoded
     * @param token received from server on successful authorization
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("api/Profile/GetProfile")
    Call<UserProfile> getProfile(@Query("token") String token);

    /**
     * Send request for list of available doctors<br/><br/>
     * <b>Path:</b> ~/api/Doctor/GetDoctorList<br/>
     * <b>Header</b> - Content-Type:application/x-www-form-urlencoded
     * @param token received from server on successful authorization
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("api/Doctor/GetDoctorList")
    Call<List<DoctorProfile>> getDoctorList(@Query("token") String token);

    /**
     * Send request for information of one certain doctor defined by DocId<br/><br/>
     * <b>Path:</b> ~/api/Doctor/GetDoctor<br/>
     * <b>Header</b> - Content-Type:application/x-www-form-urlencoded
     * @param token received from server on successful authorization
     * @param docId requested doctor`s ID
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("api/Doctor/GetDoctor/{DocId}")
    Call<DoctorProfile> getDoctor(@Query("token") String token, @Path("DocId") Long docId);


    /**
     * Send requst for consultation<br/><br/>
     * <b>Path:</b> ~/api/Doctor/AddConsultation<br/>
     * <b>Header</b> - Content-Type:application/x-www-form-urlencoded
     * <br/><br/>
     * Available diseases:
     * "ochi", "ochiul", "ochelari","ochii", "vederea", "vedere", "vad", "copilul", "copil", "baiatul", "fetita",
     * "operatie", "operat", "interventie", "chirurgical", "chirurgicala",
     * "picior", "mina", "coloana", "vertebrala", "muschi", "ligamente", "fractura",
     * "fracturat","spinare", "vertebre", "dislocat", "dislocate"
     *
     * @param token received from server on successful authorization
     *
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @GET("api/Doctor/AddConsultation") //???
    void addConsultation(@Query("token") String token);

}
