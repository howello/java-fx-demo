package com.howe.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.howe.constant.ResourceConstant;
import com.howe.dto.setting.SettingDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/21 10:19 星期二
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class SettingUtils {
    public static SettingDTO SETTING_CACHE;

    public static void initConfig() {
        File config = getConfigFile();
        if (!FileUtil.exist(config)) {
            FileUtil.touch(config);
            String defaultSetting = IoUtil.readUtf8(SettingUtils.class.getResourceAsStream("/json/config.json"));
            FileUtil.writeUtf8String(defaultSetting, config);
        }
        SETTING_CACHE = getSettingDTO();

    }

    private static SettingDTO getSettingDTO() {
        if (SETTING_CACHE == null) {
            try {
                File config = getConfigFile();
                String setting = FileUtil.readUtf8String(config);
                SETTING_CACHE = JSONObject.parseObject(setting, SettingDTO.class);
            } catch (IORuntimeException e) {
                e.printStackTrace();
            }
        }
        return SETTING_CACHE;
    }

    public static void writeSettingToFile(SettingDTO settingDTO) {
        try {
            File config = getConfigFile();
            FileUtil.writeUtf8String(JSONObject.toJSONString(settingDTO), config);
            AlertMaker.showSimpleAlert("成功", "设置更新成功");
        } catch (IORuntimeException e) {
            log.error("配置文件保存失败", e);
            AlertMaker.showErrorMessage(e, "失败", "无法保存配置文件");
        }
    }

    private static File getConfigFile() {
        return new File(ResourceConstant.CONFIG_FILE_NAME);
    }
}
