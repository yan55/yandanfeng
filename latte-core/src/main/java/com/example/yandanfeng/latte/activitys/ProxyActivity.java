package com.example.yandanfeng.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.yandanfeng.latte.R;
import com.example.yandanfeng.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017-11-08
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {

        final ContentFrameLayout content = new ContentFrameLayout(this);

        content.setId(R.id.delegate_container);

        setContentView(content);

        if (savedInstanceState == null) {
            //如果是第一次加载的时候
            loadRootFragment(R.id.delegate_container, setRootDelegate());

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
