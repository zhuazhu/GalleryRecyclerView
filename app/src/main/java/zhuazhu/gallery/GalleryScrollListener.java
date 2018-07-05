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
    private float mAnimFactor = 0.13f;
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
                //当前第一个显示item的position
                int itemPosition = layoutManager.findFirstVisibleItemPosition();
                Log.d(TAG,String.format("第一个显示的position->%d",itemPosition));
                float offset = (float) mConstomX/(float)itemWidth;
                float precent = offset-(int)offset;
                int position = Math.round(offset);
                if(position!=0){
                    position = itemPosition+1;
                }
                if(offset>=(itemPosition+1)&&mSlideDirect==SLIDE_LEFT){
                    return;
                }
                startAnimation(layoutManager,position,precent);
            }
        });
    }

    private void startAnimation(LinearLayoutManager layoutManager,int position,float precent){
        View leftView = layoutManager.findViewByPosition(position-1);
        View centerView = layoutManager.findViewByPosition(position);
        View rightView = layoutManager.findViewByPosition(position+1);

        if(precent<=0.5f){
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
        }else{
            if (leftView!=null) {
                //缩小
                leftView.setScaleX(1-mAnimFactor*precent);
                leftView.setScaleY(1-mAnimFactor*precent);
            }
            if (centerView!=null) {
                //方法
                centerView.setScaleX((1-mAnimFactor)+mAnimFactor*precent);
                centerView.setScaleY((1-mAnimFactor)+mAnimFactor*precent);
            }
            if (rightView!=null) {
                //缩小
                rightView.setScaleX(1-mAnimFactor*precent);
                rightView.setScaleY(1-mAnimFactor*precent);
            }
        }
    }
}
