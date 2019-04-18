package com.willkong.androidutils;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.willkong.androidutils.library.DownloadUtil;
import com.willkong.androidutils.library.VersionUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadUtil downloadUtil = new DownloadUtil(mActivity, "http://app.mi.com/download/25323");
                DownloadUtil.setPackageName(VersionUtil.getPackageName(MainActivity.this));
//                //下载显示名字，不能是中文
                downloadUtil.setDownloadFileName("weiyan" + System.currentTimeMillis() + ".apk");
                downloadUtil.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadUtil.start();
            }
        });
    }
}
