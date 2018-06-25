package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity2_ScheduleInputFormActivity extends Activity {

    EditText time_h;        // 시
    EditText time_m;        // 분
    EditText input_scd;    // 작성한 일정
    Button save_btn;       // 저장버튼
    Button cancel_btn;     // 취소버튼

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout2_schdule_input);

        time_h = (EditText) findViewById(R.id.time_h);              //시간 -시
        time_m = (EditText) findViewById(R.id.time_m);              //시간 -분
        input_scd = (EditText) findViewById(R.id.input_scd);        //일정
        save_btn = (Button) findViewById(R.id.save_btn);            //저장버튼
        cancel_btn = (Button) findViewById(R.id.cancle_btn);        //취소버튼


//저장 버튼 클릭
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HH = time_h.getText().toString();          //작성한 시를 get
                String MM = time_m.getText().toString();          //작성한 분을 get
                String Msg = input_scd.getText().toString();      //작성한 일정 get

                Intent intent = new Intent();
                intent.putExtra("hour", HH);                      //작성한 시를 인텐트에 담음
                intent.putExtra("min", MM);                       //작성한 분을 인텐트에 담음
                intent.putExtra("msg", Msg);                      //작성한 일정을 인텐트에 담음

                setResult(RESULT_OK, intent);                     //메인액티비티에게 응답

                finish();           //종료
            }
        });

//취소버튼 클릭
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();           //종료
            }
        });
    }
}
