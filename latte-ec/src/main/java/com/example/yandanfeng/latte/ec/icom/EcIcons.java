package com.example.yandanfeng.latte.ec.icom;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017-11-06
 */

public enum EcIcons implements Icon {
   icon_scan('\ue606'),
    icon_ali_pay('\ue606');


    private char character;

    EcIcons(char character ) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '_');
    }

    @Override
    public char character() {
        return character;
    }
}
