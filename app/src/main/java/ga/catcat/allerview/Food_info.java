package ga.catcat.allerview;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.catbox.allerview.R;

import java.io.IOException;
import java.io.InputStream;

public class Food_info extends AppCompatActivity {
    ImageButton btnBack;
    TextView t_company;
    TextView t_name;
    TextView t_allergy;
    TextView t_material;
    ImageView f_img;
    String data;
    String img_url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        t_company = findViewById(R.id.company);
        t_name = findViewById(R.id.name);
        t_allergy= findViewById(R.id.allergy);
        t_material= findViewById(R.id.material);
        f_img = findViewById(R.id.img);


        Intent intent = getIntent();
        data = intent.getExtras().getString("barcode");















        //뒤로가기 버튼 시작
        btnBack = findViewById(R.id.btnBack);
        btnBack.setClickable(true);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        // 뒤로가기 버튼 끝
        // URL 설정.
        String url = "http://catcat.ga:8080/JDBC/connectDb.jsp";

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;


        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {


            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            if (data == null){
                result = requestHttpURLConnection.request(url, values ,"1234");

            }else{
                result = requestHttpURLConnection.request(url, values , data); // 해당 URL로 부터 결과물을 얻어온다.
            }
            return result;


        }





        @Override
        protected void onPostExecute(String s) {
            Log.i("파싱하기전",s);



            String target = "회사명 :";
            int target_num = s.indexOf(target);
            String step =  s.substring(target_num,(s.substring(target_num).indexOf("<")+target_num));
            String company = step.substring(step.lastIndexOf(":")+1);

            target = "제품명 :";
            target_num = s.indexOf(target);
            step =  s.substring(target_num,(s.substring(target_num).indexOf("<")+target_num));
            String name = step.substring(step.lastIndexOf(":")+1);

            target = "원재료 :";
            target_num = s.indexOf(target);
            step =  s.substring(target_num,(s.substring(target_num).indexOf("<")+target_num));
            String material = step.substring(step.lastIndexOf(":")+1);

            target = "알러지 :";
            target_num = s.indexOf(target);
            step =  s.substring(target_num,(s.substring(target_num).indexOf("<")+target_num));
            String allergy = step.substring(step.lastIndexOf(":")+1);

            target = "이미지 :";
            target_num = s.indexOf(target);
            step =  s.substring(target_num,(s.substring(target_num).indexOf("<")+target_num));
            img_url = step.substring(step.lastIndexOf(":")+1);

//            Log.e("주소",img_url);
            //체크박스 값 가져오기
            SharedPreferences p =getSharedPreferences("check", MODE_PRIVATE);
            String[] c_allergy =new String[22];
            //배열에 넣기
            c_allergy[0] = p.getString("pork", "0");
            c_allergy[1] = p.getString("egg", "0");
            c_allergy[2] = p.getString("chicken", "0");
            c_allergy[3] = p.getString("beef", "0");
            c_allergy[4] = p.getString("milk", "0");
            c_allergy[5] = p.getString("mackerel", "0");
            c_allergy[6] = p.getString("shrimp", "0");
            c_allergy[7] = p.getString("squid", "0");
            c_allergy[8] = p.getString("crap", "0");
            c_allergy[9] = p.getString("buckwheat", "0");
            c_allergy[10] = p.getString("soybean", "0");
            c_allergy[11] = p.getString("wheat", "0");
            c_allergy[12] = p.getString("clam", "0");
            c_allergy[13] = p.getString("corn", "0");
            c_allergy[14] = p.getString("peach", "0");
            c_allergy[15] = p.getString("pinenut", "0");
            c_allergy[16] = p.getString("tomato", "0");
            c_allergy[17] = p.getString("onion", "0");
            c_allergy[18] = p.getString("peanut", "0");
            c_allergy[19] = p.getString("walnut", "0");
            c_allergy[20] = p.getString("sa", "0");
            c_allergy[21] = p.getString("pa", "0");


            //allergy 가 한군데에 담겨 있음으로 나눠담음
            String[] allergy_split = allergy.split(",");

            t_allergy.setText("");



            if(allergy_split[0].equals(" 없음") ){
                t_allergy.append(allergy);
                //Log.i("나눈것",allergy_split[0]);
            }else {
                for (int i = 0; i < allergy_split.length; i++) {
                    String view = allergy_split[i];
                    boolean bool = false;
                    for (int j = 0; j < c_allergy.length; j++) {
                        if(view.equals(c_allergy[j])){
                            bool = true;
                        }
                    }
                    if( bool ) {
                        t_allergy.append(Html.fromHtml(
                                "<font color=\"#FF0000\">&nbsp;"+ view + "</font>"));
                    }else{
                        t_allergy.append(" "+view);
                    }
                }
            }


















            //이미지 표기 부분
            if(img_url.length() >5){ //없음 이라고 들어가 있고, 주소의 길이가 5글자 이하일 경우는 없으니 5로 설정
                new DrawUrlImageTask((ImageView) findViewById(R.id.img))
                        .execute("http://"+img_url);
            }else {
                img_url = "https://cdn.pixabay.com/photo/2016/01/02/04/59/coffee-1117933_960_720.jpg";
                new DrawUrlImageTask((ImageView) findViewById(R.id.img))
                        .execute(img_url);
            }

            //페이지에 표기
            if (company != null & name != null & allergy != null & allergy != null){
                t_company.setText(company);
                t_name.setText(name);
//                t_allergy.setText(allergy);
                t_material.setText(material);

            }

        }

    }
    private class DrawUrlImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView ivSample;

        public DrawUrlImageTask(ImageView ivSample) {
            this.ivSample = ivSample;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            InputStream in = null;

            try {
                in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            ivSample.setImageBitmap(bitmap);
        }
    }
}

