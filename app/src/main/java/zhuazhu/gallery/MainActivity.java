package zhuazhu.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import zhuazhu.gallery.decoration.GalleryItemDecoration;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        GalleryItemDecoration gralleryItemDecoration = new GalleryItemDecoration();
        mRecyclerView.addItemDecoration(gralleryItemDecoration);

        RecyclerAdapter adapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new GalleryScrollListener(gralleryItemDecoration));
    }
}
