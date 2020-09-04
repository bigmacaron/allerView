package ga.catcat.allerview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.catbox.allerview.R;

public class MyAllergyActivity extends AppCompatActivity {

    String pork,egg,chicken,beef,milk,mackerel,shrimp,squid,crap,buckwheat,soybean,
            wheat,clam,corn,peach,pinenut,tomato,onion,peanut,walnut,sa,pa;
    ImageButton btnBack;
    Button c_cancel;
    Button c_confirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_allergy);

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

        //확인 버튼과 취소버튼
        c_cancel = findViewById(R.id.c_cancel);
        c_confirm = findViewById(R.id.c_confirm);
        c_cancel.setClickable(true);


        // 체크박스 부르기
        final CheckBox c_pork = findViewById(R.id.c_pork);
        final CheckBox c_egg = findViewById(R.id.c_egg);
        final CheckBox c_chicken = findViewById(R.id.c_chicken);
        final CheckBox c_beef = findViewById(R.id.c_beef);
        final CheckBox c_milk = findViewById(R.id.c_milk);
        final CheckBox c_mackerel = findViewById(R.id.c_mackerel);
        final CheckBox c_shrimp = findViewById(R.id.c_shrimp);
        final CheckBox c_squid = findViewById(R.id.c_squid);
        final CheckBox c_crap = findViewById(R.id.c_crap);
        final CheckBox c_buckwheat = findViewById(R.id.c_buckwheat);
        final CheckBox c_soybean = findViewById(R.id.c_soybean);
        final CheckBox c_wheat = findViewById(R.id.c_wheat);
        final CheckBox c_corn = findViewById(R.id.c_corn);
        final CheckBox c_clam = findViewById(R.id.c_clam);
        final CheckBox c_pinenut = findViewById(R.id.c_pinenut);
        final CheckBox c_peach = findViewById(R.id.c_peach);
        final CheckBox c_tomato = findViewById(R.id.c_tomato);
        final CheckBox c_onion = findViewById(R.id.c_onion);
        final CheckBox c_peanut = findViewById(R.id.c_peanut);
        final CheckBox c_walnut = findViewById(R.id.c_walnut);
        final CheckBox c_sa = findViewById(R.id.c_sa);
        final CheckBox c_pa = findViewById(R.id.c_pa);






        //체크 박스 확인 여부후 값 담기
        c_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //체크박스 사용자 정보 저장
                SharedPreferences p = getSharedPreferences("check", MODE_PRIVATE);
                SharedPreferences.Editor editor = getEditor(p);

                if(c_pork.isChecked()){ pork =" 돼지고기"; }else{pork = "";}
                editor.putString("pork",pork);
                if(c_egg.isChecked()){egg = " 계란"; }else{egg = "";}
                editor.putString("egg",egg);
                if(c_chicken.isChecked()){ chicken = " 닭고기";}else{chicken = "";}
                editor.putString("chicken",chicken);
                if(c_beef.isChecked()){ beef = " 쇠고기";}else{beef = "";}
                editor.putString("beef",beef);
                if(c_milk.isChecked()){ milk = " 우유";}else{milk = "";}
                editor.putString("milk",milk);
                if(c_mackerel.isChecked()){ mackerel=" 고등어";}else{mackerel = "";}
                editor.putString("mackerel",mackerel);
                if(c_shrimp.isChecked() ){ shrimp=" 새우";}else{shrimp = "";}
                editor.putString("shrimp",shrimp);
                if(c_squid.isChecked()){ squid=" 오징어";}else{squid = "";}
                editor.putString("squid",squid);
                if(c_crap.isChecked()){ crap=" 게";}else{crap = "";}
                editor.putString("crap",crap);
                if(c_soybean.isChecked()){ soybean=" 대두";}else{soybean = "";}
                editor.putString("soybean",soybean);
                if(c_buckwheat.isChecked()){ buckwheat=" 메밀";}else{buckwheat = "";}
                editor.putString("buckwheat",buckwheat);
                if(c_wheat.isChecked()){ wheat=" 밀";}else{wheat = "";}
                editor.putString("wheat",wheat);
                if(c_clam.isChecked()){ clam=" 조개";}else{clam = "";}
                editor.putString("clam",clam);
                if(c_corn.isChecked()){ corn=" 옥수수";}else{corn = "";}
                editor.putString("corn",corn);
                if(c_peach.isChecked()){ peach=" 복숭아";}else{peach = "";}
                editor.putString("peach",peach);
                if(c_pinenut.isChecked()){ pinenut=" 잣";}else{pinenut = "";}
                editor.putString("pinenut",pinenut);
                if(c_tomato.isChecked()){ tomato=" 토마토";}else{tomato = "";}
                editor.putString("tomato",tomato);
                if(c_onion.isChecked()){ onion=" 양파";}else{onion = "";}
                editor.putString("onion",onion);
                if(c_peanut.isChecked()){ peanut=" 땅콩";}else{peanut = "";}
                editor.putString("peanut",peanut);
                if(c_walnut.isChecked()){ walnut=" 호두";}else{walnut = "";}
                editor.putString("walnut",walnut);
                if(c_sa.isChecked()){ sa=" 아황산";}else{sa = "";}
                editor.putString("sa",sa);
                if(c_pa.isChecked()){ pa=" 페닐알라닌";}else{pa = "";}
                editor.putString("pa",pa);

                editor.commit();


                onBackPressed();

            }

            private SharedPreferences.Editor getEditor(SharedPreferences p) {
                return p.edit();
            }
        });

        //취소 버튼
        c_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        //확인 취소 버튼 끝

        //체크박스 사용자 정보에 맞춰 미리 체크 하기

        







    }





}