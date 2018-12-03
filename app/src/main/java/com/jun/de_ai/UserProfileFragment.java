package com.jun.de_ai;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel mViewModel;

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        // TODO: Use the ViewModel
        ImageView imageView = findViewById(R.id.profile);
        Button button = findViewById(R.id.button);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), android.R.drawable.homer);
        RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        bitmapDrawable.setCircular(true);
        bitmapDrawable.setColorFilter(ContextCompat.getColor(UserProfileFragment.this, R.color.colorAccent),PorterDuff.Mode.DST_OVER);

        imageView.setImageDrawable(bitmapDrawable);
    }

}
