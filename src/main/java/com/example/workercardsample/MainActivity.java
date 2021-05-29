package com.example.workercardsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private static final String TAG = MainActivity.class.getName ();
    SwipeRefreshLayout swipeRefreshLayout;
    Button addCard;
    EditText edt_filter;
    final String email = "admin@naver.com";
    final String profile = null;
    RecyclerView mRecyclerView;
    ArrayList<DataCard> mCardList;
//    ArrayList<DataCheckList> mCheckList;
    DataCardAdapter mAdapter;
    Context mContext;

    ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        mContext = MainActivity.this;
        addCard = findViewById (R.id.btn_addCard);
        edt_filter = findViewById (R.id.edt_filter);
        edt_filter.addTextChangedListener (this);


        swipeRefreshLayout = findViewById (R.id.swipeRefreshLayout);
        mRecyclerView = findViewById (R.id.recycle_workerCard);
        LinearLayoutManager layoutManager = new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager (layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration (mContext, 1));


//        CardItemTouchHelperCallback callback = new CardItemTouchHelperCallback (mAdapter);
//        mItemTouchHelper = new ItemTouchHelper (callback);

        mCardList = new ArrayList<> ();
        mAdapter =  new DataCardAdapter (mContext, mCardList);
        mRecyclerView.setAdapter ((RecyclerView.Adapter) mAdapter);

//        ItemTouchHelper.Callback callback = new ItemMoveCallback(mAdapter);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(mRecyclerView);

        mItemTouchHelper = new ItemTouchHelper (new CardItemTouchHelperCallback (mAdapter));
        mItemTouchHelper.attachToRecyclerView (mRecyclerView);
//        mAdapter.setOnItemClickListener (new DataCardAdapter.OnItemClickListener () {
//            @Override
//            public void onItemClick(View itemView, int position) {
//
//
//            }
//        });
        addCard.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle ("업무카드생성");
                final EditText cardName = new EditText (mContext);
                ab.setView (cardName);

                ab.setPositiveButton ("생성", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String value = cardName.getText ().toString ();
                        ArrayList<DataCheckList> checkLists = new ArrayList<> ();
                        DataCard data = new DataCard (value, email, profile, checkLists);
//                        DataCard data = new DataCard ();
                        mCardList.add (0,data);
//                        mAdapter.notifyItemInserted (0);
                        mAdapter.setmExpandedPosition (mAdapter.getmExpandedPosition ()==-1?-1:mAdapter.getmExpandedPosition ()+1);
                        mAdapter.notifyDataSetChanged ();
//                        ((RecyclerView.Adapter) mAdapter).notifyItemInserted (0);
                        dialog.dismiss ();
                    }
                });

                ab.setNegativeButton ("취소", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss ();
                    }
                });
                ab.show ();
            }
        });


        //스와이프리프레쉬
        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing (false);//종료
            }
        });
//        mRecyclerView.addOnItemTouchListener (new RecyclerView.OnItemTouchListener () {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

    //다른곳 터치시
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

//    @Override
//    protected void onUserLeaveHint() {
//        super.onUserLeaveHint ();
//        Log.d (TAG, "onUserLeaveHint: ");
//    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        mAdapter.getFilter ().filter (s);
        Log.d (TAG, "list.size"+mCardList.size());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch(keyCode){

            case KeyEvent.KEYCODE_BACK :

// 여기에 뒤로가기 버튼을 눌렀을 때 행동 입력

                return true;

            case KeyEvent.KEYCODE_VOLUME_DOWN :

// 여기에 볼륨 ↓ 버튼을 눌렀을 때 행동 입력

                break;

            case KeyEvent.KEYCODE_VOLUME_UP :

// 여기에 볼륨 ↑ 버튼을 눌렀을 때 행동 입력

                break;

            case KeyEvent.KEYCODE_HOME :

// 여기에 홈 버튼을 눌렀을 때 행동 입력

                break;

        }

        return super.onKeyDown(keyCode, event);

    }



}
