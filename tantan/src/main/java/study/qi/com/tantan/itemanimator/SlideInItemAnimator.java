package study.qi.com.tantan.itemanimator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;

import study.qi.com.tantan.manager.CardConfig;


public class SlideInItemAnimator extends BaseItemAnimator {

    @Override
    public void setRemoveAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(0);


    }

    @Override
    public void removeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 1);

    }

    @Override
    public void addAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getWidth());
        ViewCompat.setTranslationY(holder.itemView, -holder.itemView.getWidth() / CardConfig.DEFAULT_TRANSLATE_Y);
        ViewCompat.setAlpha(holder.itemView, 0);

    }

    @Override
    public void setAddAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.translationX(0);
        animator.translationY(0);
        animator.alpha(1);


    }

    @Override
    public void addAnimationCancel(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, 0);
    }

    @Override
    public void setOldChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        //   animator.translationX(-holder.itemView.getWidth());

    }

    @Override
    public void oldChangeAnimationEnd(RecyclerView.ViewHolder holder) {


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

    @Override
    public void onRemoveStarting(RecyclerView.ViewHolder item) {
        super.onRemoveStarting(item);
    }

    @Override
    public void onRemoveFinished(RecyclerView.ViewHolder item) {
        super.onRemoveFinished(item);
    }


}
