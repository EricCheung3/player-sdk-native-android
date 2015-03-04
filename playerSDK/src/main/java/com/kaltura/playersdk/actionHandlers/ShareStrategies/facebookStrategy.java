package com.kaltura.playersdk.actionHandlers.ShareStrategies;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.kplayersdk.R;
import com.kaltura.playersdk.BrowserActivity;
import com.kaltura.playersdk.actionHandlers.ShareManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nissopa on 2/24/15.
 */
public class facebookStrategy implements ShareManager.KPShareStrategy {


    @Override
    public void share(JSONObject shareParams, Activity activity) {
        String sharePrefix = "";
        String videoLink = "";
        String videoName = "";
        String barColor = "";
        try {
            sharePrefix = ((String)((JSONObject)shareParams.get("shareNetwork")).get("template")).replace("{share.shareURL}", "");
            videoLink = (String)shareParams.get("sharedLink");
            videoName = (String)shareParams.get("videoName");
            barColor = (String)((JSONObject)shareParams.get("shareNetwork")).get("barColor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String shareScheme = sharePrefix + videoLink;
        Intent shareIntent = new Intent(activity, BrowserActivity.class);
        shareIntent.putExtra("ShareLink", shareScheme);
        shareIntent.putExtra("VideoName", videoName);
        shareIntent.putExtra("BarColor", barColor);

        activity.startActivity(shareIntent);
        activity.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}

