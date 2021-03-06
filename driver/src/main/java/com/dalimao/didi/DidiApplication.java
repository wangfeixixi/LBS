package com.dalimao.didi;

import android.app.Application;

import com.dalimao.didi.common.CrashHandler;
import com.dalimao.didi.common.http.HttpConfig;

import c.b.BP;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobInstallation;

public class DidiApplication extends Application {
    private static DidiApplication instance;
    private String pushClientId;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this, "11111");

        HttpConfig.useTestConfig();
        initBmob();
        initPush();
        BP.init(HttpConfig.getCurrentAppID());
        instance = this;
    }

    /**
     * 初始化 bmob 配置
     */
    private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                .setApplicationId(HttpConfig.getCurrentAppID())
                .setConnectTimeout(30)
                .build();
        Bmob.initialize(config);
    }

    /**
     * 初始化推送服务
     */
    private void initPush() {


        // 使用推送服务时的初始化操作
        BmobInstallation installation = BmobInstallation.getCurrentInstallation();
        installation.save();
        pushClientId = installation.getInstallationId();

        // 启动推送服务
        BmobPush.setDebugMode(true);
        BmobPush.startWork(this);

    }

    public String getPushClientId() {
        return pushClientId;
    }

    public static DidiApplication getInstance() {
        return instance;
    }

    ;
}
