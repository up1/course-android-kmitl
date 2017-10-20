package com.project.demorecord;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTExtName)
    EditText editTExtName;

    @BindView(R.id.editTextAge)
    EditText editTextAge;

    private CommonSharePreference preference;
    private UserInfoList userInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        preference = new CommonSharePreference(this);
        userInfoList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);

    }

    private void validate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("Please Enter user info")
                .setPositiveButton("OK", null);
        builder.show();
    }

    @OnClick(R.id.buttonAdded)
    public void added() {
        String name = editTExtName.getText().toString();
        String age = editTextAge.getText().toString();

        if (name.isEmpty() || age.isEmpty()) {
            validate();
        } else {
            setUserInfo(name, age);
        }
    }

    @OnClick(R.id.buttonGotoList)
    public void gotoList() {
        Intent intent = new Intent(this, UserInfoListActivity.class);
        startActivity(intent);
    }

    private void setUserInfo(String name, String age) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(age);

        if (userInfoList == null) {
            userInfoList = new UserInfoList();
            List<UserInfo> suggests = new ArrayList<>();
            suggests.add(userInfo);
            userInfoList.setUserInfoList(suggests);
        } else {
            userInfoList.getUserInfoList().add(userInfo);
        }
        preference.save(UserInfoListActivity.EXTTRA_LIST, userInfoList);
    }

}
