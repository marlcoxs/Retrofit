package com.example.xkcd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ImageView idImg;
    TextView idTextView;
    Button idBtn;
    XkcdService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idImg=findViewById(R.id.idImg);
        idTextView=findViewById(R.id.idTextView);
        idBtn=findViewById(R.id.idBtn);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(XkcdService.class);

        idBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Call<Comic> call= service.getImg(new Random().nextInt(1000));
                call.enqueue(new Callback<Comic>(){
                                 public void onResponse(Call<Comic> call, Response<Comic> response ){
                        Comic comic=response.body();
                        try {
                            if (comic != null) {
                                idTextView.setText(comic.getTitle());
                                Picasso.with(MainActivity.this)
                                        .load(comic.getImg())
                                        .into(idImg);
                            }
                        }catch (Exception e){
                            Log.e("MainActivity", e.toString());
                        }
                    }
                    public void onFailure(Call<Comic> call, Throwable t){
                        Toast.makeText(MainActivity.this, "Ocurrio un error con la API ", Toast.LENGTH_LONG);
                    }

                }

                    );
                }


            });
        };


}