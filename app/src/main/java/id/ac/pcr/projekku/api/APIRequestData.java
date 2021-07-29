package id.ac.pcr.projekku.api;

import id.ac.pcr.projekku.model.ResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveBerita();

    @FormUrlEncoded
    @POST("addevent.php")
    Call<ResponseBody> createEvent(@Field("judul") String judul,
                                   @Field("deskripsi") String deskripsi,
                                   @Field("lokasi") String lokasi,
                                   @Field("waktu_mulai") String mulai,
                                   @Field("waktu_selesai") String selesai);
}
