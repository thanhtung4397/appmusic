package com.example.tung.appmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.example.tung.appmusic.Activity.PlayNhacActivity;
import com.example.tung.appmusic.Adapter.LyricAdapter;
import com.example.tung.appmusic.Model.Baihatduocthich;
import com.example.tung.appmusic.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;

public class Fragment_dia_nhac extends Fragment {

    View view;
  //  TextView txtlyric;
    RecyclerView recyclerViewLyric;
    LyricAdapter lyricAdapter;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        recyclerViewLyric = view.findViewById(R.id.recyclerviewLyric);
        circleImageView = view.findViewById(R.id.imageviewcircle);
        if(PlayNhacActivity.mangbaihat.size() >0 ){
            lyricAdapter = new LyricAdapter(getActivity(), PlayNhacActivity.mangbaihat);
            recyclerViewLyric.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewLyric.setAdapter(lyricAdapter);
        }

        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
    public void Playnhac(String hinhanh) {
        objectAnimator.start();
        Picasso.with(getContext()).load(hinhanh).into(circleImageView);
    }
}
