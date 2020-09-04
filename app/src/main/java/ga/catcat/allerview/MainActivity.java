package ga.catcat.allerview;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.catbox.allerview.R;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    LinearLayout my_allergy_info, etc; // 메인 버튼 두개
    ImageButton btnBack; // 타이틀 뒤로가기 버튼




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //바코드 부분
        barcodeScannerView = (DecoratedBarcodeView) findViewById(R.id.dbvBarcode);

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(this.getIntent(), savedInstanceState);
        capture.decode();
        barcodeScannerView.decodeContinuous(new BarcodeCallback(){
            @Override
            public void barcodeResult(BarcodeResult result) {
                readBarcode(result.toString());
                String barcode = result.toString();

                Intent intent = new Intent(MainActivity.this, Food_info.class);
                intent.putExtra("barcode", barcode);
                startActivity(intent);
//                Log.e("왜오류? intent",barcode);



            }
            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
            }

        });



        // 버튼 부분

        my_allergy_info = findViewById(R.id.my_allergy_info);
        etc = findViewById(R.id.etc);

        my_allergy_info.setClickable(true);
        etc.setClickable(true);
        my_allergy_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyAllergyActivity.class);
                startActivity(intent);

            }
        });


        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EtcActivity.class);
                startActivity(intent);
            }
        });


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














    }



    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    public void readBarcode(String barcode) {
        Toast.makeText(getApplicationContext(), barcode, Toast.LENGTH_SHORT).show();
    }

    public void titleBack(View v){
        backPress();
    }
    private void backPress(){
        finish();
    }
    public static class MyApplication extends Application
    {
        public String g_barcode;

    }

}
