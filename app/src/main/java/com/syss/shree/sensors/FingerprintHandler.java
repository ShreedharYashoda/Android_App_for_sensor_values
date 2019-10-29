package com.syss.shree.sensors;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    public FingerprintHandler(Context context) {

        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal=new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);

        this.update("There was an auth error "+ errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        this.update("Authentication failed",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        this.update("Error "+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        this.update("Authentication succeeded",true);
    }

    private void update(String s, boolean b) {

        TextView tv_para= (TextView) ((Activity)context).findViewById(R.id.textView8);
        ImageView finger= (ImageView) ((Activity)context).findViewById(R.id.imageView);

        tv_para.setText(s);

        if (b==false){
            tv_para.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }else{
            tv_para.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            finger.setImageResource(R.mipmap.done);
            Intent i = new Intent(context, Activity_2.class);
            context.startActivity(i);
            ((Activity) context).finish();
        }

    }
}
