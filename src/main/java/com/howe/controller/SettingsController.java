package com.howe.controller;

import com.howe.dto.setting.SettingDTO;
import com.howe.utils.SettingUtils;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 10:53 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class SettingsController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDefaultValues();
    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pass = password.getText();

        SettingDTO settingDTO = SettingUtils.SETTING_CACHE;
        settingDTO.setUsername(uname);
        settingDTO.setPassword(pass);

        SettingUtils.writeSettingToFile(settingDTO);
    }

    private void initDefaultValues() {
        SettingDTO settingDTO = SettingUtils.SETTING_CACHE;
        username.setText(String.valueOf(settingDTO.getUsername()));
        String passHash = String.valueOf(settingDTO.getPassword());
        password.setText(passHash.substring(0, Math.min(passHash.length(), 10)));
    }
}
