package com.example.workercardsample;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataCheckListAdapter extends RecyclerView.Adapter<DataCheckListAdapter.CustomViewHolder> {
    private Context mContext;
    private ArrayList<DataCheckList> mArrayList;
    LayoutInflater mInflater;
//    static String checkN;

    public DataCheckListAdapter(Context mContext, ArrayList<DataCheckList> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        this.mInflater = LayoutInflater.from(mContext);
    }
    private OnItemClickListener mListener = null;

    //클릭이벤트
    interface OnItemClickListener{
        void onItemClick(View v, int position);
        void onEditClick(View v, int position);
        void onCheckNameSave(View v, int position, String name);
        void onCheckBoxOk(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public ArrayList<DataCheckList> getmArrayList() {
        return mArrayList;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CheckBox checkBox;
        public ImageButton imageButton;
        public Button checkNameSave;
        public EditText checkName;
        public TextView test;

        public CustomViewHolder(View itemView) {
            super(itemView);
            checkName = itemView.findViewById (R.id.edt_checkName);
            checkNameSave = itemView.findViewById (R.id.btn_checkNameSave);
            checkBox =itemView.findViewById (R.id.cb_checkList);
            imageButton = itemView.findViewById (R.id.ib_editCheck);
//            test = itemView.findViewById (R.id.tv_test);

            checkBox.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition ();
                    if (position!=RecyclerView.NO_POSITION){
                        if (mListener!=null){
                            mListener.onCheckBoxOk (v,position);
                        }
                    }
                }
            });
//            //체크리스트이름저장
//            checkNameSave.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition ();
//                    if (position != RecyclerView.NO_POSITION) {
//                        if(mListener!=null){
//                            mListener.onCheckNameSave (v, position, checkName.getText ().toString ());
//                        }
//                    }
//                }
//            });
//            //에딧버튼
//            imageButton.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition ();
//                    if (position != RecyclerView.NO_POSITION) {
//                        if(mListener!=null){
//                            mListener.onEditClick (v, position);
//                        }
//                    }
//                }
//            });
//
//            //아이템뷰
//            itemView.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition ();
//                    if (position != RecyclerView.NO_POSITION) {
//                        if(mListener!=null){
//                            mListener.onItemClick (v, position);
//                        }
//                    }
//                }
//            });
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d ("Tag", "자식 온크리에잇뷰홀더");
        View view;
        view = mInflater.inflate(R.layout.item_check_list, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataCheckListAdapter.CustomViewHolder holder, final int position) {
        Log.d ("Tag", "자식 온바인드 뷰홀더 테스트용 이름: "+mArrayList.get (position).getCheckName ());
//        holder.test.setText (String.valueOf (position)); //테스트용
        //스크롤시 체크박스 풀림 방지용
//        holder.checkBox.setOnCheckedChangeListener (null);
//        holder.checkBox.setText (mArrayList.get (position).getCheckName ());
        if(mArrayList.get (position).isCheckFlag ()){
            holder.checkBox.setChecked (mArrayList.get (position).isCheckFlag ());
            holder.checkBox.setPaintFlags (holder.checkBox.getPaintFlags ()| Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.checkBox.setPaintFlags (0);
        }
        if(mArrayList.get (position).getCheckName ()!=null){
            holder.checkBox.setVisibility (View.VISIBLE);
            holder.imageButton.setVisibility (View.VISIBLE);
            holder.checkName.setVisibility (View.GONE);
            holder.checkNameSave.setVisibility (View.GONE);

            holder.checkBox.setText (mArrayList.get (position).getCheckName ());
            holder.checkName.setText (mArrayList.get (position).getCheckName ());
        }else{
            holder.checkBox.setVisibility (View.GONE);
            holder.imageButton.setVisibility (View.GONE);
            holder.checkName.setVisibility (View.VISIBLE);
            holder.checkNameSave.setVisibility (View.VISIBLE);
        }

        //Log.d ("Tag", )
//        holder.checkNameSave.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//        String checkN = holder.checkName.getText ().toString ();
//                    Log.d ("Tag", "체크박스이름"+checkN);
//                if(!checkN.equals ("")){
//                    mArrayList.get (position).setCheckName (checkN);
//                    notifyDataSetChanged ();
//
//                    holder.checkNameSave.setVisibility (View.GONE);
//                    holder.checkName.setVisibility (View.GONE);
//                    holder.checkBox.setVisibility (View.VISIBLE);
//                    holder.imageButton.setVisibility (View.VISIBLE);
//
//                    holder.checkBox.setText (position+checkN);
//                }
//            }
//        });

        //에딧버튼클릭
//        holder.imageButton.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//                //팝업메뉴생성
//                final PopupMenu popupMenu = new PopupMenu (mContext,v);
//                popupMenu.getMenuInflater ().inflate (R.menu.check_list, popupMenu.getMenu ());
//                //팝업메뉴 클릭이벤트
//                popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        Log.d ("Tag", "클릭포지션값+"+position);
//                        switch (item.getItemId ()){
//                            case R.id.m1://수정
//                                holder.checkNameSave.setVisibility (View.VISIBLE);
//                                holder.checkName.setVisibility (View.VISIBLE);
//                                holder.checkBox.setVisibility (View.GONE);
//                                holder.imageButton.setVisibility (View.GONE);
//                                break;
//                            case R.id.m2://삭제
////                                for(int i =0 ; i<mArrayList.size ();i++){
////                                    Log.d ("Tag","이름"+mArrayList.get (i).getCheckName ());
////                                }
//                                Log.d ("Tag", position+"번째 삭제"+mArrayList.size ()+"크기");
//                                mArrayList.remove (position);
////                                for(int i =0 ; i<mArrayList.size ();i++){
////                                    Log.d ("Tag","이름"+mArrayList.get (i).getCheckName ());
////                                }
////                                Log.d ("Tag", position+"번째 삭제"+mArrayList.size ()+"크기");
////                                notifyItemRemoved (position);
//                                notifyDataSetChanged ();
//                                break;
//                        }
//                        return false;
//                    }
//                });
//                popupMenu.show ();
//            }
//        });
    }

    @Override
    public int getItemCount() {
//        Log.d ("Tag", "자식겟아이템카운트 어레이사이즈 : "+mArrayList.size ());
//        return (null != mAskList ? mAskList.size () : 0);
//        return mArrayList.size ();
        return (null !=mArrayList ? mArrayList.size ():0);
    }


}
