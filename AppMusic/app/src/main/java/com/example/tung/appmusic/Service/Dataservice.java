package com.example.tung.appmusic.Service;

import com.example.tung.appmusic.Model.Account;
import com.example.tung.appmusic.Model.Albumhot;
import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.Model.ChuDe;
import com.example.tung.appmusic.Model.Playlist;
import com.example.tung.appmusic.Model.Quangcao;
import com.example.tung.appmusic.Model.TheLoai;
import com.example.tung.appmusic.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {


    @GET("quangcao.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistngay.php")
    Call<List<Playlist>> GetPlaylistNgay();

    @GET("chudevatheloai.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Albumhot>> GetAlbumhot();

    @GET("baihatduocthich.php")
    Call<List<Baihatduocthich>> GetBaihatduocthich();

    @FormUrlEncoded
    @POST("Danh_sach_bai_hat.php")
    Call<List<Baihatduocthich>> GetDanhsachbaihattheoquangcao(@Field("idquangcao")String idquangcao );

    @FormUrlEncoded
    @POST("Danh_sach_bai_hat.php")
    Call<List<Baihatduocthich>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist );

    @GET("Danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacplaylist();

    @FormUrlEncoded
    @POST("Danh_sach_bai_hat.php")
    Call<List<Baihatduocthich>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai );

    @GET("Tatcachude.php")
    Call<List<ChuDe>> GetTatcachude();

    @FormUrlEncoded
    @POST("Theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Albumhot>> GettatcaAlbum();

    @FormUrlEncoded
    @POST("Danh_sach_bai_hat.php")
    Call<List<Baihatduocthich>> GetDanhsachcacbaihattheoALbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotthich(@Field("luotthich") String luotthich , @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("Timkiembaihat.php")
    Call<List<Baihatduocthich>> GetSearchBaihat (@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("register.php")
    Call<String> InsertData(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("IdDongnhac") String IdDongnhac);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Account>> Logindata(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("dongnhac.php")
    Call<List<Baihatduocthich>> GetSearchDongnhac (@Field("tukhoa") String tukhoa);
}
