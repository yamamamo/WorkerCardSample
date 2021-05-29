package com.example.workercardsample;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class DataCardAdapter extends RecyclerView.Adapter<DataCardAdapter.ViewHolder>
//        implements ItemMoveCallback.ItemTouchHelperContract{
    implements ItemTouchHelperListener, Filterable {
    Context mContext;
    private ArrayList<DataCard> mCardListAll;
    private ArrayList<DataCard> mCardListFilter;
    private LayoutInflater mInflater;
//    private  ArrayList<DataCheckList> mCheckList;
//    private DataCheckListAdapter mAdapter;

    final String TAG = "DataCardAdapter";


    //    // Item의 클릭 상태를 저장할 array 객체
//    private SparseBooleanArray selectedItems = new SparseBooleanArray ();
//    // 직전에 클릭됐던 Item의 position
//    private int prePosition = -1;
    private int mExpandedPosition = -1;
//    private int previousExpandedPosition = -1;


    public int getmExpandedPosition() {
        return mExpandedPosition;
    }

    public void setmExpandedPosition(int mExpandedPosition) {
        this.mExpandedPosition = mExpandedPosition;
    }

    public DataCardAdapter(Context mContext, ArrayList<DataCard> list) {
        this.mContext = mContext;
        this.mCardListFilter = list;
        this.mCardListAll = list;
//        this.mInflater = LayoutInflater.from (mContext);
    }

   // 드래그앤드롭롭
   @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap (mCardListAll, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap (mCardListAll, i, i - 1);
            }
        }
        if(mExpandedPosition==fromPosition){
            mExpandedPosition=toPosition;
        }else{
            int expand = mExpandedPosition;
            if(expand>toPosition){

            }else if(expand<toPosition){

            }else if(expand==toPosition){

            }
        }
//        mExpandedPosition = -1;
        if(mExpandedPosition == -1){
            notifyItemMoved (fromPosition, toPosition);
        }else{
            //mExpandedPosition = -1;
            notifyDataSetChanged ();
        }
        return true;
    }

    @Override
    public void onRowSelected(ViewHolder myViewHolder) {
        Log.d (TAG, "드래그시작");
    }

    @Override
    public void onRowClear(ViewHolder myViewHolder) {
        Log.d (TAG, "드래그끝");
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter () {

        //automatic background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<DataCard> filteredList = new ArrayList<>();
            String charString = constraint.toString ();
            if (charString.isEmpty ()) {
                filteredList.addAll(mCardListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DataCard dataCard : mCardListAll) {
                    //TODO filter 대상 setting
                    String cardName = dataCard.getCardName ();
                    if (cardName.toLowerCase().contains(filterPattern)) {
                        filteredList.add (dataCard);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        //automatic ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.d (TAG, "mCardListAll.size:"+mCardListAll.size ());
//            mCardListFilter.clear();
//            mCardListFilter.addAll((ArrayList) results.values);
            mCardListFilter=(ArrayList<DataCard>) results.values;
            notifyDataSetChanged();
        }
    };

//    @Override
//    public void onRowMoved(int fromPosition, int toPosition) {
//
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(mCardList, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(mCardList, i, i - 1);
//            }
//        }
//        Log.d (TAG, "시작포지션-"+fromPosition+" 끝포지션-"+toPosition);
//        notifyItemMoved(fromPosition, toPosition);
//    }
//
//
//    @Override
//    public void onRowSelected(ViewHolder myViewHolder) {
//        Log.d (TAG, "드롭시작");
//    }
//
//    @Override
//    public void onRowClear(ViewHolder myViewHolder) {
//        Log.d (TAG, "드롭끝");
//    }
    //스와이프
//    @Override
//    public void onItemSwipe(int position) {
//        Log.d (TAG, "스와이프 포지션"+position);
//        mCardList.remove (position);
//        notifyItemRemoved (position);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardName, checkCount, checkAll;
        public ImageButton edit;
        public ImageView profile;
        public RecyclerView recyclerView;
        public LinearLayout linear_checkCount;
        //        public ArrayList<DataCheckList> mCheckList = mCardList.get (getAdapterPosition ()).getCheckLists () ;
        public DataCheckListAdapter dataCheckListAdapter;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            cardName = itemView.findViewById (R.id.tv_cardName);
            checkCount = itemView.findViewById (R.id.tv_checkCount);
            checkAll = itemView.findViewById (R.id.tv_checkAll);
            edit = itemView.findViewById (R.id.ib_edit);
            profile = itemView.findViewById (R.id.iv_profile);
            linear_checkCount = itemView.findViewById (R.id.linear_checkCount);

            recyclerView = itemView.findViewById (R.id.recycle_checkList);
//            LinearLayoutManager layoutManager = new LinearLayoutManager (mContext, LinearLayoutManager.VERTICAL, false);
//            recyclerView.setLayoutManager (layoutManager);
////            mCheckList = new ArrayList<> ();
//            DataCard dataCard = mCardList.get (getAdapterPosition ());
////            mCheckList = mCardList.get (getAdapterPosition ()).getCheckLists ();
//            dataCheckListAdapter = new DataCheckListAdapter (mContext, dataCard.getCheckLists ());
//            recyclerView.setAdapter (dataCheckListAdapter);

//            dataCheckListAdapter.setOnItemClickListener (new DataCheckListAdapter.OnItemClickListener () {
//                @Override
//                public void onItemClick(View v, int position) {
//                    Toast.makeText (mContext, "안녕친구들" + position, Toast.LENGTH_SHORT).show ();
//                }
//
//                @Override//에딧버튼 클릭
//                public void onEditClick(View v, final int position) {
//                    //팝업메뉴 생성
//                    final PopupMenu menu = new PopupMenu (mContext, v);
//                    menu.getMenuInflater ().inflate (R.menu.check_list, menu.getMenu ());
//                    menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch (item.getItemId ()) {
//                                case R.id.m1://수정
//                                    break;
//                                case R.id.m2://삭제
//                                    mCardList.get (getAdapterPosition ()).getCheckLists ().remove (position);
////                                    mCheckList.remove (position);
////                                    dataCheckListAdapter.notifyItemRemoved (position);
//                                    notifyDataSetChanged ();
//                                    checkAll.setText (String.valueOf (mCardList.get (getAdapterPosition ()).getCheckLists ().size ()));
//                                    if (mCardList.get (getAdapterPosition ()).getCheckLists ().size () == 0) {
//                                        linear_checkCount.setVisibility (View.GONE);
//                                    }
//                                    break;
//                            }
//                            return false;
//                        }
//                    });
//                    menu.show ();
//
//                }
//            });


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        mExpandedPosition = -1;
        View view;
        mInflater = (LayoutInflater) mContext.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate (R.layout.item_worker_card, parent, false);
        return new ViewHolder (view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final DataCard dataCard = mCardListFilter.get (position);
        holder.dataCheckListAdapter = new DataCheckListAdapter (mContext, dataCard.getCheckLists ());
        LinearLayoutManager layoutManager = new LinearLayoutManager (mContext, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setHasFixedSize (true);
        holder.recyclerView.setLayoutManager (layoutManager);
        holder.recyclerView.setAdapter (holder.dataCheckListAdapter);

        dataCard.setCheckLists (new ArrayList<DataCheckList> ());
//        Log.d (TAG, "onBindViewHolder 카드이름: " + mCardListAll.get (position).getCardName ());
        holder.cardName.setText (dataCard.getCardName ());
        int checkAll = 0; //체크리스트갯수
        int checkBoxCount = 0; //체크박스 체크여부
        try {
            checkAll = dataCard.getCheckLists ().size ();
        } catch (NullPointerException e) {
            Log.d (TAG, "체크리스트갯수 null : " + e);
            checkAll = 0;
        }

        //체크리스트가 있을때
        if (checkAll > 0) {
            for(int i=0; i<dataCard.getCheckLists ().size ();i++){
                if(dataCard.getCheckLists ().get (i).isCheckFlag ()){
                    checkBoxCount++;
                }
            }
            Log.d (TAG, "체크리스트있다 갯수 : " + checkAll);
            holder.linear_checkCount.setVisibility (View.VISIBLE);
            holder.checkAll.setText (String.valueOf (checkAll));//안그럼 터진다
            holder.checkCount.setText (String.valueOf (checkBoxCount));

            if(checkAll==checkBoxCount&&checkAll!=0){
                holder.linear_checkCount.setBackgroundColor (ContextCompat.getColor(mContext,R.color.colorComplete));
                holder.checkCount.setTextColor (ContextCompat.getColor (mContext,R.color.colorWhite));
                holder.checkAll.setTextColor (ContextCompat.getColor (mContext,R.color.colorWhite));
            }else{
                holder.linear_checkCount.setBackgroundColor (ContextCompat.getColor(mContext,R.color.colorNotCom));
            }
        } else if (checkAll == 0) {
            holder.linear_checkCount.setVisibility (View.GONE);
        }

        //edit클릭이벤트
       holder.edit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Log.d (TAG, "edit누름");
                final AlertDialog.Builder ad = new AlertDialog.Builder (mContext);
                //팝업메뉴 생성
                final PopupMenu popup = new PopupMenu (mContext, v);//v는 클릭된 뷰를 의미
                popup.getMenuInflater ().inflate (R.menu.edit, popup.getMenu ());
                //팝업메뉴 클릭이벤트
                popup.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId ()) {
                            case R.id.m1://체크리스트추가
//                                Toast.makeText(mContext,"체크리스트추가",Toast.LENGTH_SHORT).show();
                                DataCheckList data = new DataCheckList ();
//                              ((ViewHolder) holder).mCheckList.add (data);
//                                mCardList.get (position).getCheckLists ().add (data);
                                dataCard.getCheckLists ().add (data);
//                                mCardList.get (position).setCheckLists ( ((ViewHolder)holder).mCheckList);
//                                holder.dataCheckListAdapter.notifyDataSetChanged ();
                                notifyDataSetChanged ();
                                Log.d (TAG, position + " 체크박스생성 크기 : " + mCardListAll.get (position).getCheckLists ().size ());
//                                int ListAll = mCardList.get (position).getCheckLists ().size ();
//                                holder.linear_checkCount.setVisibility (View.VISIBLE);
//                                holder.checkAll.setText (String.valueOf (ListAll));//안그럼 터진다
//                                ((ViewHolder)holder).checkCount.setText (String.valueOf (checkCount));
                                break;
                            case R.id.m2://제목수정
                                //Toast.makeText(mContext,"제목수정",Toast.LENGTH_SHORT).show();
                                ad.setTitle ("업무카드이름변경");
                                final EditText cardName = new EditText (mContext);
                                cardName.setText (mCardListAll.get (position).getCardName ());
                                ad.setView (cardName);
                                ad.setPositiveButton ("수정", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mCardListAll.get (position).setCardName (cardName.getText ().toString ());
                                        notifyDataSetChanged ();
                                        dialog.dismiss ();
                                    }
                                });
                                ad.setNegativeButton ("취소", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss ();
                                    }
                                });
                                ad.show ();
                                break;
                            case R.id.m3://카드삭제
                                mExpandedPosition = -1;
                                //Toast.makeText(mContext,"카드삭제", Toast.LENGTH_SHORT).show();
                                ad.setTitle ("업무카드삭제");
                                ad.setMessage ("삭제하시겠습니까?");
                                ad.setPositiveButton ("삭제", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mCardListAll.get (position).getCheckLists ().clear ();
                                        mCardListAll.remove (position);
//                                        notifyDataSetChanged ();
                                        notifyItemRemoved (position);
//                                        notifyItemRangeChanged (position,mCardList.size ());
                                        dialog.dismiss ();
                                    }
                                });

                                ad.setNegativeButton ("취소", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss ();
                                    }
                                });
                                ad.show ();
                                break;
                            default:
                                break;
                        }

                        return false;
                    }
                });
                popup.show ();

            }

        });

        //itemView 클릭시 익스펜더블 하나만
        final boolean isExpanded = position == mExpandedPosition;
        ((ViewHolder) holder).recyclerView.setVisibility (isExpanded ? View.VISIBLE : View.GONE);
        ((ViewHolder) holder).edit.setVisibility (isExpanded ? View.VISIBLE : View.GONE);
        ((ViewHolder) holder).itemView.setActivated (isExpanded);
        ((ViewHolder) holder).itemView.setOnClickListener (new View.OnClickListener () {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d (TAG, "익스펜더블포지션 : " + position);
                mExpandedPosition = isExpanded ? -1 : position;
//                TransitionManager.beginDelayedTransition(recyclerView);
//                notifyItemChanged (mExpandedPosition);
                notifyDataSetChanged ();
            }
        });


        //자식리사이클러뷰 클릭이벤트
        holder.dataCheckListAdapter.setOnItemClickListener (new DataCheckListAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(View v, int position) {
//                Toast.makeText (mContext, "안녕친구들" + position, Toast.LENGTH_SHORT).show ();
            }

            @Override//에딧버튼 클릭
            public void onEditClick(View v, final int pos) {
                Log.d (TAG, "클릭 포지션값 "+pos);
                //팝업메뉴 생성
                final PopupMenu menu = new PopupMenu (mContext, v);
                menu.getMenuInflater ().inflate (R.menu.check_list, menu.getMenu ());
                menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener () {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId ()) {
                            case R.id.m1://수정
                                dataCard.getCheckLists ().get (pos).setCheckName (null);
                                holder.dataCheckListAdapter.notifyItemChanged (pos);
                                break;
                            case R.id.m2://삭제
                                dataCard.getCheckLists ().remove (pos);
                                notifyDataSetChanged ();
                                break;
                        }
                        return false;
                    }
                });
                menu.show ();

            }

            @Override//체크리스트이름저장
            public void onCheckNameSave(View v, int pos, String name) {
                if(name.equals ("")){
                    Toast.makeText (mContext, "체크리스트 이름을 입력하시오.", Toast.LENGTH_SHORT).show ();
                }else{
                    dataCard.getCheckLists ().get (pos).setCheckName (name);
                    notifyDataSetChanged ();
                }
            }

            @Override//체크박스
            public void onCheckBoxOk(View v, int pos) {
                if(dataCard.getCheckLists ().get (pos).isCheckFlag ()){//체크 돼 있을때때
                    dataCard.getCheckLists ().get (pos).setCheckFlag (false);
                    notifyDataSetChanged ();
                }else{//체크가 안돼있을 때
                    dataCard.getCheckLists ().get (pos).setCheckFlag (true);
                    notifyDataSetChanged ();
                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return (null!=mCardListFilter ? mCardListFilter.size ():0);
    }

    //    public interface onItemClickListener{
//        public void onItemClick(DataCardAdapter.ViewHolder holder, View view, int position);
//    }
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
