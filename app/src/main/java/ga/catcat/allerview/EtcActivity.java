package ga.catcat.allerview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.catbox.allerview.R;

public class EtcActivity extends AppCompatActivity {

    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etc);


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


}