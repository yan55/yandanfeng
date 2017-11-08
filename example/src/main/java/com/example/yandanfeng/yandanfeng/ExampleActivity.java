package com.example.yandanfeng.yandanfeng;

import com.example.yandanfeng.latte.activitys.ProxyActivity;
import com.example.yandanfeng.latte.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }


}