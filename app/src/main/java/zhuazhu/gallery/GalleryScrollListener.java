package zhuazhu.gallery;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import zhuazhu.gallery.decoration.GalleryItemDecoration;

/**
 * @author zhuazhu
 **/
public class GalleryScrollListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "GalleryScrollListener";
    private int mConstomX = 0;
    private int mSlideDirect = SLIDE_LEFT;
    /**
     * 缩放因子
     */
    private float mAnimFactor = 0.25f;
    private static final int SLIDE_LEFT = 1;
    private static final int SLIDE_RIGHT = 2;
    private GalleryItemDecoration mGalleryItemDecoration;

    public GalleryScrollListener(GalleryItemDecoration galleryItemDecoration) {
        mGalleryItemDecoration = galleryItemDecoration;
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mConstomX += dx;
        Log.e(TAG,String.format("dx->%d",dx));
        if(dx>0){
            mSlideDirect = SLIDE_LEFT;
        }
        if(dx<0){
            mSlideDirect = SLIDE_RIGHT;
        }
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                //item的宽度
                int itemWidth = mGalleryItemDecoration.getItemWidth();
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                float offset = (float) mConstomX/(float)itemWidth;
                float precent = offset-(int)offset;
                startAnimation(layoutManager,(int)offset,precent);
            }
        });
    }

    private void startAnimation(LinearLayoutManager layoutManager,int position,float precent){
        View leftView = layoutManager.findViewByPosition(position-1);
        View centerView = layoutManager.findViewByPosition(position);
        View rightView = layoutManager.findViewByPosition(position+1);
        View rightView1 = layoutManager.findViewByPosition(position+2);

            if (leftView!=null) {
                //放大
                leftView.setScaleX((1-mAnimFactor)+mAnimFactor*precent);
                leftView.setScaleY((1-mAnimFactor)+mAnimFactor*precent);
            }
            if (centerView!=null) {
                //缩小
                centerView.setScaleX(1-mAnimFactor*precent);
                centerView.setScaleY(1-mAnimFactor*precent);
            }
            if (rightView!=null) {
                //放大
                rightView.setScaleX((1-mAnimFactor)+mAnimFactor*precent);
                rightView.setScaleY((1-mAnimFactor)+mAnimFactor*precent);
            }
            if (rightView1!=null) {
                //缩小
                rightView1.setScaleX(1-mAnimFactor*precent);
                rightView1.setScaleY(1-mAnimFactor*precent);
            }
    }
}
