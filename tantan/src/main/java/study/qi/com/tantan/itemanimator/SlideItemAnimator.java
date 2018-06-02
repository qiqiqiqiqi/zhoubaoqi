package study.qi.com.tantan.itemanimator;

import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;



public class SlideItemAnimator extends BaseItemAnimator {

    @Override
    public void setRemoveAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.translationX(-holder.itemView.getWidth());
    }

    @Override
    public void removeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
    }

    @Override
    public void addAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getWidth());
    }

    @Override
    public void setAddAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.translationX(0);
    }

    @Override
    public void addAnimationCancel(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
    }

    @Override
    public void setOldChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        //   animator.translationX(-holder.itemView.getWidth());
        animator.translationX(holder.itemView.getWidth());
    }

    @Override
    public void oldChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
    }

    @Override
    public void newChangeAnimationInit(RecyclerView.ViewHolder holder) {
        // ViewCompat.setTranslationX(holder.itemView,holder.itemView.getWidth());

        ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getWidth());
    }

    @Override
    public void setNewChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.translationX(0);
    }

    @Override
    public void newChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);

    }
}
