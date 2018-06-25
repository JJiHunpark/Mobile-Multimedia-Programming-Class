package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//메모장
public class SubActivity3 extends Activity implements View.OnClickListener {
    SubActivity3_DBHandler handler;

    int edit_id = 0, edit_position = 0;
    boolean isCalled = false, isadded = false;

    Button resetBtn, submitBtn, listBtn;
    EditText memo;

    SubActivity3_MemoInfo memoinfo;

    Intent result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout3);

        handler = new SubActivity3_DBHandler( this );

        resetBtn = (Button)findViewById( R.id.resetBtn );
        resetBtn.setOnClickListener( this );
        submitBtn = (Button)findViewById( R.id.submitBtn );
        submitBtn.setOnClickListener( this );
        listBtn = (Button)findViewById( R.id.listBtn );
        listBtn.setOnClickListener( this );
        memo = (EditText)findViewById( R.id.memo );

        Intent intent = getIntent();
        edit_id = intent.getIntExtra( "id", 0 );
        edit_position = intent.getIntExtra("position",  -1 );
        isCalled = intent.getBooleanExtra("isCalled", false );

        if( edit_id > 0 && edit_position >= 0 ) {
            memoinfo = handler.select( edit_id );
            memo.setText( memoinfo.memo );
        }

        result = new Intent();
    }

    @Override
    public void onClick(View v ) {
        // TODO Auto-generated method stub
        if( v == resetBtn ) {
            resetMemo();
        }
        else if( v == submitBtn ) {
            if( !inputCheck() ) {
                Toast.makeText(this,  "메모를 입력해주세요.", Toast.LENGTH_LONG).show();
                return;
            }

            if( edit_id > 0 &&  memo.getText().toString() != memoinfo.memo ) {
                if( handler.update( edit_id,  memo.getText().toString() ) == 0 ) Toast.makeText( this, "������ �� ����ϴ�.",  Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText( this, "�����Ǿ����.",  Toast.LENGTH_LONG).show();
                    result.putExtra( "edit_id", edit_id );
                    result.putExtra( "edit_position", edit_position );
                    result.putExtra( "isadded", isadded );
                    setResult( RESULT_OK, result );
                    finish();
                }
            }
            else {
                if( handler.insert( memo.getText().toString() ) == 0 ) Toast.makeText( this, "����� �� ����ϴ�.",  Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText( this, "메모 등록 완료.",  Toast.LENGTH_LONG).show();
                    resetMemo();
                    isadded = true;
                }
            }
        }
        else if( v == listBtn ) {
            if( !isCalled ) {
                Intent intent = new Intent( this, SubActivity3_ListActivity.class );
                startActivity( intent );
                finish();
            }
            else {
                result.putExtra( "edit_id", 0 );
                result.putExtra( "edit_position", -1 );
                result.putExtra( "isadded", isadded );
                setResult( RESULT_OK, result );
                finish();
            }
        }
    }

    protected boolean inputCheck() {
        if( memo.getText().toString().length() == 0 ) return false;
        else return true;
    }

    private void resetMemo() {
        edit_id = 0;
        memo.setText( "" );
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if( isCalled ) {
            result.putExtra( "edit_id", 0 );
            result.putExtra( "edit_position", -1 );
            result.putExtra( "isadded", isadded );
            setResult( RESULT_OK, result );
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        handler.close();
    }
}