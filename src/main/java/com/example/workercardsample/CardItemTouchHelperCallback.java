package com.example.workercardsample;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class CardItemTouchHelperCallback extends ItemTouchHelper.Callback {
    ItemTouchHelperListener listener;

    public CardItemTouchHelperCallback(ItemTouchHelperListener listener) {
        this.listener = listener;
    }
//    public interface OnItemMoveListener{
//        boolean onItemMove(int fromPosition, int toPosition);
//    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags (dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        mItemMoveListener.onItemMove (viewHolder.getAdapterPosition (),target.getAdapterPosition ());
        return  listener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        Log.d ("TAG", "onSwiped: "+viewHolder.getAdapterPosition ());
//        listener.onItemSwipe (viewHolder.getAdapterPosition ());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        Log.d ("TAG","롱클릭");
        return true;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof DataCardAdapter.ViewHolder) {
                DataCardAdapter.ViewHolder myViewHolder= (DataCardAdapter.ViewHolder) viewHolder;
                listener.onRowSelected(myViewHolder);
            }

        }
        super.onSelectedChanged (viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView (recyclerView, viewHolder);

        if (viewHolder instanceof DataCardAdapter.ViewHolder) {
            DataCardAdapter.ViewHolder myViewHolder= (DataCardAdapter.ViewHolder) viewHolder;
            listener.onRowClear(myViewHolder);
        }
    }
    //    private final OnItemMoveListener mItemMoveListener;
//
//    public CardItemTouchHelperCallback(OnItemMoveListener listener){
//        mItemMoveListener=listener;
//    }
}
