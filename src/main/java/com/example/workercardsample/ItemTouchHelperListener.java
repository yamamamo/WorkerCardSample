package com.example.workercardsample;

public interface ItemTouchHelperListener {
    boolean onItemMove(int from_position, int to_position);
//    void onItemSwipe(int position);
    void onRowSelected(DataCardAdapter.ViewHolder myViewHolder);
    void onRowClear(DataCardAdapter.ViewHolder myViewHolder);

}
