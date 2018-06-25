package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SubActivity3_ListActivity  extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    SubActivity3_DBHandler handler;
    ArrayList<SubActivity3_MemoInfo> memoList;

    ListView view;
    SubActivity3_ListAdapter listAdapter;

    private static final int EDIT_ACTIVITY = 1;

    Button newMemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout3_list);

        newMemoBtn = (Button)findViewById( R.id.newMemoBtn );
        newMemoBtn.setOnClickListener( this );

        handler = new SubActivity3_DBHandler( this );
        memoList = new ArrayList<SubActivity3_MemoInfo>();

        listAdapter = new SubActivity3_ListAdapter( this, memoList );
        view = (ListView)findViewById( R.id.list);
        view.setAdapter( listAdapter );
        view.setOnItemClickListener( this );

        registerForContextMenu( view );
        displayMemoList();
    }

    protected void displayMemoList() {
        Cursor cursor = handler.selectAll();

        if( cursor == null ) return;
        removeAllList();

        do {
            SubActivity3_MemoInfo memo = new SubActivity3_MemoInfo();
            memo.id = cursor.getInt( 0 );
            memo.writeDate = cursor.getLong( 1 );
            memo.memo = cursor.getString( 2 );
            memoList.add( memo );
        } while( cursor.moveToNext() );
        cursor.close();
        listAdapter.notifyDataSetChanged();
    }
    protected void removeAllList() {
        memoList.removeAll( memoList );
    }

    protected void removeList( int position ) {
        SubActivity3_MemoInfo memo = memoList.get( position );
        handler.delete( memo.id );
        memoList.remove( position );
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.sub3_pop, menu );
        menu.setHeaderTitle( R.string.context_menu_title );
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if( info != null ) {
            int position = info.position;
            if( item.getItemId() == R.id.view_content ) {
                Intent intent = new Intent( this, MainActivity.class );
                intent.putExtra("id", memoList.get( position ).id );
                intent.putExtra("position",  position );
                intent.putExtra( "isCalled", true );
                startActivityForResult( intent, EDIT_ACTIVITY );
            }
            else if( item.getItemId() == R.id.remove_content ) removeList( position );
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra("id", memoList.get( arg2 ).id );
        intent.putExtra("position",  arg2 );
        intent.putExtra( "isCalled", true );
        startActivityForResult( intent, EDIT_ACTIVITY );
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        handler.close();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int edit_id, edit_position;
        boolean added;

        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == EDIT_ACTIVITY ) {
            edit_id = data.getIntExtra( "edit_id", 0 );
            edit_position = data.getIntExtra( "edit_position", -1 );
            added = data.getBooleanExtra("isadded",  false);
            if( edit_id != 0 && edit_position >= 0 ) {
                SubActivity3_MemoInfo edit_memo = handler.select( edit_id );
                memoList.set( edit_position,  edit_memo );
                listAdapter.notifyDataSetChanged();
            }
            else if( added ) {
                displayMemoList();
            }
        }
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra("id", 0);
        intent.putExtra("position",  -1 );
        intent.putExtra( "isCalled", true );
        startActivityForResult( intent, EDIT_ACTIVITY );
    }
}

