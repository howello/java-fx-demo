package com.howe.dto.setting;


import cn.hutool.crypto.digest.DigestUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 10:21 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Data
@Slf4j
public class SettingDTO {

    private String username;
    private String password;

    private GlobalSettingDTO global;

    public void setPassword(String password) {
        if (password.length() < 16) {
            this.password = DigestUtil.sha1Hex(password);
        } else {
            this.password = password;
        }
    }
}
