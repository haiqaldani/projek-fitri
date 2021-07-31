package id.ac.pcr.projekku.api;

import id.ac.pcr.projekku.model.IdModel;
import id.ac.pcr.projekku.model.ResponseModel;
import id.ac.pcr.projekku.model.RuanganModel;
import id.ac.pcr.projekku.model.ScheduleModel;
import id.ac.pcr.projekku.model.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("login.php")
    Call<UserModel> loginUser(@Field("username") String username,
                              @Field("password") String password);

    @GET("jadwalkuli.php")
    Call<ScheduleModel> getSchedule(@Query("hari") String hari);

    @GET("jadwaldosen.php")
    Call<ScheduleModel> getDosenSchedule(@Query("id_dosen") String id_dosen);

    @GET("getid.php")
    Call<IdModel> getListID(@Query("id_jadwal") String id_jadwal);

    @GET("getruangan.php")
    Call<RuanganModel> getEmptyRoom();

    @FormUrlEncoded
    @POST("change_schedule.php")
    Call<ResponseBody> changeSchedule(@Field("id_dosen") String id_dosen,
                                      @Field("id_jadwal") String id_jadwal,
                                      @Field("id_kelas") String id_kelas,
                                      @Field("id_matakuliah") String id_matakuliah,
                                      @Field("id_ruang_lama") String id_ruanglama,
                                      @Field("tanggal") String tanggal,
                                      @Field("jam") String jam,
                                      @Field("ruang_baru") String ruang_baru);

    @GET("accschedule.php")
    Call<ScheduleModel> getAccSchedule(@Query("id_dosen") String id_dosen);

    @GET("newschedule.php")
    Call<ScheduleModel> getNewSchedule();
}
