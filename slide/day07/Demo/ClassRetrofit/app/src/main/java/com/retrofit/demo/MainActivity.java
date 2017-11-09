package com.retrofit.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private UserProfile userProfile;

    //android
    //nature
    //cartoon

    private static final String USER = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFollow = findViewById(R.id.buttonFollow);
        buttonFollow.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserProfile(USER);
    }

    private void display(UserProfile userProfile) {
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@" + userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n" + userProfile.getPost());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n" + userProfile.getFollower());

        TextView textFollwing = findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n" + userProfile.getFollowing());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        ImageView imageProfile = findViewById(R.id.imageProfile);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);


        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 3));
        PostAdapter adapter = new PostAdapter(this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);

        updateFollowButton(userProfile);

    }

    private void updateFollowButton(UserProfile userProfile) {
        Button buttonFollow = findViewById(R.id.buttonFollow);
        buttonFollow.setText(userProfile.isFollow() ? "UnFollow" : "Follow");
        buttonFollow.setBackgroundColor(userProfile.isFollow() ? Color.parseColor("#99292929") : Color.parseColor("#33646464"));
    }

    private Api buildApi() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(makeOkHttpLog())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(Api.class);
    }

    private void displayError(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private AlertDialog createLoadingDialog() {
        ProgressBar bar = new ProgressBar(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bar)
                .create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private void getUserProfile(String name) {

        final AlertDialog loadingDialog = createLoadingDialog();
        loadingDialog.show();

        Api api = buildApi();
        api.getProfile(name).enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()) {
                    userProfile = response.body();
                    display(userProfile);
                } else {
                    displayError("Error!", "There is some thing wrong!");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserProfile> call, Throwable t) {
                loadingDialog.dismiss();
                displayError("Error!", "There is some thing wrong!");
            }
        });

    }


    private void clickFollow() {
        final AlertDialog loadingDialog = createLoadingDialog();
        loadingDialog.show();

        FollowRequest request = new FollowRequest();
        request.setUser(USER);
        request.setFollow(!userProfile.isFollow());

        Api api = buildApi();
        api.follow(request).enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                loadingDialog.dismiss();
                if (response.isSuccessful()) {
                    userProfile.setFollow(!userProfile.isFollow());
                    updateFollowButton(userProfile);
                } else {
                    displayError("Error!", "There is some thing wrong!");
                }
            }

            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                loadingDialog.dismiss();
                displayError("Error!", "There is some thing wrong!");
            }
        });
    }

    private HttpLoggingInterceptor makeOkHttpLog() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Override
    public void onClick(View view) {
        clickFollow();
    }
}
