package com.example.appbanhang.retrofit;

import com.example.appbanhang.model.DonHangModel;
import com.example.appbanhang.model.KhuyenMaiModel;
import com.example.appbanhang.model.LoaiSpModel;
import com.example.appbanhang.model.MeetingModel;
import com.example.appbanhang.model.MessageModel;
import com.example.appbanhang.model.SanPhamMoiModel;
import com.example.appbanhang.model.Usermodel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    // GET DATA
    @GET("getloaisanpham.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("khuyenmai.php")
    Observable<KhuyenMaiModel> getKhuyenMai();

    @GET("getmeeting.php")
    Observable<MeetingModel> getMeeting();

    @GET("getsanphammoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    // POST DATA
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
      @Field("page") int page,
      @Field("loai") int loai
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<Usermodel> dangKi(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile,
            @Field("uid") String uid
    );

    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("updatemomo.php")
    @FormUrlEncoded
    Observable<MessageModel> updateMomo(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<Usermodel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass

    );

    @POST("reset.php")
    @FormUrlEncoded
    Observable<Usermodel> resetPass(
            @Field("email") String email
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Observable<MessageModel> createOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("userid") int userid,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );

    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("userid") int id
    );

    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );

    @POST("gettoken.php")
    @FormUrlEncoded
    Observable<Usermodel> gettoken(
            @Field("status") int status
    );

    @POST("deleteorder.php")
    @FormUrlEncoded
    Observable<MessageModel> deleteOrder(
            @Field("iddonhang") int id
    );
}
