package zhuazhu.gallery.decoration;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author zhuazhu
 **/
public class GalleryItemDecoration extends RecyclerView.ItemDecoration {
    private int mPageMargin = 0;
    private int mPageVisibleWidth = 50;
    private int mItemWidth;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        mItemWidth = parent.getWidth()-dpToPx(2*(mPageMargin+mPageVisibleWidth));
        final int position = parent.getChildAdapterPosition(view);
        final int itemCount = parent.getAdapter().getItemCount();
        int leftMargin = position==0?dpToPx(mPageMargin+mPageVisibleWidth):dpToPx(mPageMargin);
        int rightMargin = position==(itemCount-1)?dpToPx(mPageMargin+mPageVisibleWidth):dpToPx(mPageMargin);

        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = leftMargin;
        layoutParams.rightMargin = rightMargin;
        layoutParams.width = mItemWidth;
        view.setLayoutParams(layoutParams);
    }

    public int getItemWidth() {
        return mItemWidth;
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
