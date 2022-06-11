package Ir.iamnovinfar.ZekrShomar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adivery.sdk.Adivery;
import com.adivery.sdk.AdiveryAdListener;
import com.adivery.sdk.AdiveryBannerAdView;

import ir.Iamnovinfar.ZekrShomar.R;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.listener.OnCompleteListener;


public class ZekrShomar extends AppCompatActivity {

    TextView t1;
    Button b1, reset;
    SharedPreferences sharedPreferences, sh1;
    SharedPreferences.Editor editor;
    int zekercounterinit = 0, zekrcountersharedpredf = 0;
    String nameofuser,sex;
    ImageView tsbih;
    AdiveryBannerAdView bannerAd,bannerAd2;
    boolean iszekrlogin=false;


    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekr_shomar);
        Adivery.configure(getApplication(), "bcf5a84a-066a-46a6-a570-97715c5ede56");
        Adivery.prepareInterstitialAd(this, "f86883a2-d021-4230-a6cc-3bf3c6d4f46a");
        Adivery.showAd("f86883a2-d021-4230-a6cc-3bf3c6d4f46a");
        mediaPlayer = MediaPlayer.create(this, R.raw.alahomaeni);
        registerReceiver(broadcastReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        t1 = findViewById(R.id.zekrcounter);
        b1 = (Button) findViewById(R.id.btn_counter);
        reset = (Button) findViewById(R.id.btn_reset);
        tsbih =  findViewById(R.id.tsbih);
        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        sh1 = getSharedPreferences("zekrcounter", MODE_PRIVATE);
        editor = sh1.edit();
        nameofuser = sharedPreferences.getString("usr_name", "felani");
        sex = sharedPreferences.getString("sex", "felani");
        zekrcountersharedpredf = sh1.getInt("zekrcounterii", 0);
        if (sex.contains("آقا")){
            t1.setText(sex+"ی "+ nameofuser + " شما " + "\n" + zekrcountersharedpredf + " ذکر فرستادید قبول باشه ");
        }else {
            t1.setText(sex+" "+ nameofuser +" "+ "شما " + "\n" + zekrcountersharedpredf + " ذکر فرستادید قبول باشه ");

        }
        zekercounterinit = zekrcountersharedpredf;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zekercounterinit++;
                editor.putInt("zekrcounterii", zekercounterinit);
                editor.apply();

                if (sex.contains("آقا")){
                    t1.setText(sex+"ی "+ nameofuser + " شما " + "\n" + zekercounterinit + " ذکر فرستادید قبول باشه ");
                }else {
                    t1.setText(sex+" "+ nameofuser +" "+ "شما " + "\n" + zekercounterinit + " ذکر فرستادید قبول باشه ");

                }

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zekercounterinit = 0;
                editor.putInt("zekrcounterii", zekercounterinit);
                editor.apply();

                if (sex.contains("آقا")){
                    t1.setText(sex+"ی "+ nameofuser + " شما " + "\n" + zekercounterinit + " ذکر فرستادید قبول باشه ");
                }else {
                    t1.setText(sex+" "+ nameofuser +" "+ "شما " + "\n" + zekercounterinit + " ذکر فرستادید قبول باشه ");

                }
            }
        });


        tsbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
           }
        });


        tsbih.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mediaPlayer.pause();
                return true;
            }
        });


         iszekrlogin=sh1.getBoolean("iszekrlogin",false);
        if (!iszekrlogin){
            showinfo();
        }else {}

    }


    private void SetUpBannerAdivery(){
        bannerAd = findViewById(R.id.banner_ad_zekr1);
        bannerAd2 = findViewById(R.id.banner_ad_zekr2);

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
        RelativeLayout alllay=findViewById(R.id.alllay);

        FancyShowCaseView view1 = new FancyShowCaseView.Builder(this)
                .focusOn(t1)
                .title("و در نهایت اسم و شمارنده ذکر در این قسمت نمایش داده میشود")
                .titleStyle(R.style.Help, Gravity.BOTTOM)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .build();
        FancyShowCaseView view2 = new FancyShowCaseView.Builder(this)
                .focusOn(b1)
                .title("میتوانید با استفاده از این دکمه ذکر خود را بشمارید")
                .focusShape(FocusShape.ROUNDED_RECTANGLE)

                .titleStyle(R.style.Help, Gravity.CENTER_HORIZONTAL)
                .build();
        FancyShowCaseView view3 = new FancyShowCaseView.Builder(this)
                .focusOn(reset)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("از این دکمه میتوانید برای صفر کردن ذکر هایتان استفاده کنید ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();
      FancyShowCaseView view6 = new FancyShowCaseView.Builder(this)
                .focusOn(tsbih)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("با کلیک روی تصویر نوایی از دعای سحر را میتوانید بشونید ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();
      FancyShowCaseView view7 = new FancyShowCaseView.Builder(this)
                .focusOn(tsbih)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("با نگه داشتن بر روی تصویر میتوانید صدا را متوقف کنید ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();
      FancyShowCaseView view4 = new FancyShowCaseView.Builder(this)
                .focusOn(tsbih)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("این برنامه میتواند ذکر های شما را ذخیره کند در نتیجه نیازی به حفظ کردن عدد ذکر نیست ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();

      FancyShowCaseView view5 = new FancyShowCaseView.Builder(this)
                .focusOn(tsbih)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .title("ازاین برنامه لذت ببرید ")
                .titleStyle(R.style.Help, Gravity.START)
                .build();


        FancyShowCaseQueue queue = new FancyShowCaseQueue();
        queue.add(view2);
        queue.add(view3);
        queue.add(view1);
        queue.add(view6);
        queue.add(view7);

        queue.add(view4);
        queue.add(view5);
        queue.show();

        queue.setCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                iszekrlogin=!iszekrlogin;
                editor.putBoolean("iszekrlogin",iszekrlogin);
                editor.apply();
            }
        });

    }


    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {

            if (isNetworkConnected(ZekrShomar.this)){
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