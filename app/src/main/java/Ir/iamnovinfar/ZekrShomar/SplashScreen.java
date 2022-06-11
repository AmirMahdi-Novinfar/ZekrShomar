package Ir.iamnovinfar.ZekrShomar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
        boolean islogin=sharedPreferences.getBoolean("isLogin",false);
        if (islogin){
            Intent intent=new Intent(SplashScreen.this,ZekrShomar.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}