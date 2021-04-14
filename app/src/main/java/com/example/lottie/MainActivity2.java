package com.example.lottie;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.airbnb.lottie.LottieAnimationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.playAnimation();
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len = 0;
        try {
            while (true) {
                try {
                    if ((len = inputStream.read(buf)) == -1) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }

    public JSONObject changeTextFile(String newText){
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.test);
        String jsonString = readTextFile(XmlFileInputStream);
        JSONObject jsonObj = null;
        try {
             jsonObj = new JSONObject(jsonString);
            JSONArray ja_data = jsonObj.getJSONArray("layers");

            Log.e("layers","layers - "+ja_data);

            int count = ja_data.length();// get totalCount of all jsonObjects

            Log.e("count","count is - "+ count);

            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                JSONObject jsonObject = ja_data.getJSONObject(1);// get jsonObject @ i position
                String jsText = jsonObj.getString("nm");
                jsonObj.put("nm",newText);
                System.out.println("jsonObject " + i + ": " + jsonObject);
                jsonObj = jsonObject;

                // changed result
                Log.e("txt","iiiiii "+jsonObj.getString("nm"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }


}