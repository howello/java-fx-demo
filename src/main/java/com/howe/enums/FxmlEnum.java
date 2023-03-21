package com.howe.enums;

import com.howe.constant.FxmlConstant;
import lombok.Getter;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/20 9:50 星期一
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Getter
public enum FxmlEnum {
    /**
     *
     */
    MAIN(0, FxmlConstant.MAIN, "Main"),
    TOOLBAR(1, FxmlConstant.TOOLBAR, "边栏"),
    SETTING(2, FxmlConstant.SETTING, "设置"),

    LOGIN(3, FxmlConstant.LOGIN, "登录"),
    MENU(4, FxmlConstant.MENU, "菜单"),
    ;

    private int id;

    private String fxml;
    private String desc;

    FxmlEnum(int id, String fxml, String desc) {
        this.id = id;
        this.fxml = fxml;
        this.desc = desc;
    }
}
