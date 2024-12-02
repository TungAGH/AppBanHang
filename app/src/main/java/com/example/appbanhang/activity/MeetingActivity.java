package com.example.appbanhang.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.fragment.ViewerFragment;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;
import com.google.protobuf.Api;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import live.videosdk.rtc.android.Meeting;
import live.videosdk.rtc.android.VideoSDK;
import live.videosdk.rtc.android.listeners.MeetingEventListener;

public class MeetingActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    private Meeting meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meeting);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        getMeetingIdFromServer();
    }

    private void getMeetingIdFromServer() {
        compositeDisposable.add(apiBanHang.getMeeting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   meetingModel -> {
                       if (meetingModel.isSuccess()){
                            String token = meetingModel.getResult().get(0).getToken();
                            String meetingId = meetingModel.getResult().get(0).getMeetingid();
                           String mode = "VIEWER";
                           boolean streamEnable = mode.equals("CONFERENCE");

                           // initialize VideoSDK
                           VideoSDK.initialize(getApplicationContext());

                           // Configuration VideoSDK with Token
                           VideoSDK.config(token);

                           // Initialize VideoSDK Meeting
                           meeting = VideoSDK.initMeeting(
                                   MeetingActivity.this, meetingId, "Sales App",
                                   streamEnable, streamEnable, null, mode, false, null, null);

                           // join Meeting
                           meeting.join();

                           meeting.addEventListener(new MeetingEventListener() {
                               @Override
                               public void onMeetingJoined() {
                                   if (meeting != null) {
                                        if (mode.equals("VIEWER")) {
                                           getSupportFragmentManager()
                                                   .beginTransaction()
                                                   .replace(R.id.mainLayout, new ViewerFragment(), "viewerFragment")
                                                   .commit();
                                       }
                                   }
                               }
                           });
                       }
                   },
                   throwable -> {
                       Log.d("logg", throwable.getMessage());
                   }
                ));
    }

    public Meeting getMeeting() {
        return meeting;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}