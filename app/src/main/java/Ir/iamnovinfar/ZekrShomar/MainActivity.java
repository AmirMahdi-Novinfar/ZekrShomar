package Ir.iamnovinfar.ZekrShomar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adivery.sdk.Adivery;
import com.adivery.sdk.AdiveryAdListener;
import com.adivery.sdk.AdiveryBannerAdView;
import com.rejowan.cutetoast.CuteToast;

import ir.Iamnovinfar.ZekrShomar.R;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;

public class MainActivity extends AppCompatActivity {


    EditText e1;
    Button b1;
    boolean isLogin = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Spinner sex;
    AdiveryBannerAdView bannerAd,bannerAd2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registerReceiver(broadcastReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));



        e1 = (EditText) findViewById(R.id.edt_name);
        b1 = (Button) findViewById(R.id.btn_login);
        sex = (Spinner) findViewById(R.id.spinner);
        String[] ali = {"جنسیت خود را انتخاب کنید", "آقا","خانم"};
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<>
                (MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ali);
        sex.setAdapter(sp_adapter);
        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr_name = e1.getText().toString().trim();
                validlogin(usr_name);
            }
        });

    showinfo();



    }



    private void validlogin(String name) {
        String sex_sp = sex.getSelectedItem().toString();
        if (e1.length() != 0 && sex.getSelectedItemPosition() != 0) {
            isLogin = !isLogin;
            editor.putString("usr_name", name);
            editor.putString("sex", sex_sp);
            editor.putBoolean("isLogin", isLogin);
            editor.apply();
            startActivity(new Intent(MainActivity.this, ZekrShomar.class));
            finish();
        } else if (e1.length() == 0) {

            CuteToast.ct(MainActivity.this, "لطفا نام خود را وارد کنید.", Toast.LENGTH_LONG, CuteToast.ERROR).show();

        } else {

            if (sex.getSelectedItemPosition() == 0) {
                CuteToast.ct(MainActivity.this, "لطفا جنسیت خود را وارد کنید.", Toast.LENGTH_LONG, CuteToast.ERROR).show();
            }


        }

    }


    private void SetUpBannerAdivery(){

        Adivery.configure(getApplication(), "bcf5a84a-066a-46a6-a570-97715c5ede56");
        Adivery.setLoggingEnabled(true);
        Adivery.prepareInterstitialAd(this, "f86883a2-d021-4230-a6cc-3bf3c6d4f46a");
        Adivery.showAd("f86883a2-d021-4230-a6cc-3bf3c6d4f46a");
         bannerAd = findViewById(R.id.banner_ad);
         bannerAd2 = findViewById(R.id.banner_ad2);

        bannerAd.setBannerAdListener(new AdiveryAdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onError(String reason){

            }

            @Override
            public void onAdClicked(){
                // کاربر روی بنر کلیک کرده
            }
        });
        bannerAd2.setBannerAdListener(new AdiveryAdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onError(String reason){

            }

            @Override
            public void onAdClicked(){
                // کاربر روی بنر کلیک کرده
            }
        });

        bannerAd.loadAd();
        bannerAd2.loadAd();
    }



   private void showinfo() {
        FancyShowCaseView view1 = new FancyShowCaseView.Builder(this)
                .focusOn(e1)
                .title("نام خود را جهت استفاده در برنامه وارد کنید")
                .titleStyle(R.style.Help, Gravity.CENTER_HORIZONTAL)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .build();
        FancyShowCaseView view2 = new FancyShowCaseView.Builder(this)
                .focusOn(sex)
                .title("جنسیت خود را وارد کنید")
                .focusShape(FocusShape.ROUNDED_RECTANGLE)

                .titleStyle(R.style.Help, Gravity.CENTER_HORIZONTAL)
                .build();
        FancyShowCaseView view3 = new FancyShowCaseView.Builder(this)
                .focusOn(b1)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("بعد از وارد کردن اطلاعات دکمه ورود به برنامه را بزنید ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();


        FancyShowCaseQueue queue = new FancyShowCaseQueue();
       queue.add(view1);
       queue.add(view2);
       queue.add(view3);
        queue.show();

    }


    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {

            if (isNetworkConnected(MainActivity.this)){
                SetUpBannerAdivery();
            }

        }
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}