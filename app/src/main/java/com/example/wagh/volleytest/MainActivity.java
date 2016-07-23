package com.example.wagh.volleytest;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    Button getdata;
    TextView showdata;

//    ImageView imageView;
    Button getImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize fresco

        Fresco.initialize(this);


        setContentView(R.layout.activity_main);

        final String url_text="https://www.google.com";


        getdata=(Button)findViewById(R.id.bgtee);
        showdata=(TextView)findViewById(R.id.showdatatextview);

        getImage=(Button)findViewById(R.id.bgtimage);
       // imageView=(ImageView)findViewById(R.id.showdataimageview);


        final Cache cache=new DiskBasedCache(getCacheDir(),1024*9024);
        Network network=new BasicNetwork(new HurlStack());

        final RequestQueue requestQueue= new RequestQueue(cache,network);

        requestQueue.start();

        //facebook not working...

        final Uri uri=Uri.parse("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300");

        final SimpleDraweeView simpleDraweeView=(SimpleDraweeView)findViewById(R.id.showdataimageview);


        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                StringRequest stringRequest=new StringRequest(Request.Method.GET, url_text, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        showdata.setText(response);

                        Toast.makeText(MainActivity.this,"Done Fetching",Toast.LENGTH_LONG).show();

                        requestQueue.stop();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();

                        error.printStackTrace();

                        requestQueue.stop();
                    }
                });

                requestQueue.add(stringRequest);

                Toast.makeText(MainActivity.this,"Phase 1",Toast.LENGTH_LONG).show();
            }

        });


        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**

                //Volley style of getting any image but i got a better idea .... :P

                ImageRequest imageRequest=new ImageRequest(url_text, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        imageView.setImageBitmap(response);

                        Toast.makeText(MainActivity.this,"Done Image",Toast.LENGTH_LONG).show();


                    }
                }, 0, 0, ImageView.ScaleType.FIT_CENTER, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"Error in Image",Toast.LENGTH_LONG).show();


                    }
                });

                requestQueue.add(imageRequest);

                Toast.makeText(MainActivity.this,"Phase 2",Toast.LENGTH_LONG).show();

                **/
                // Applying the facebook way..


                simpleDraweeView.setImageURI(uri);



            }
        });


    }
}
