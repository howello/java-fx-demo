package com.howe.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.howe.enums.FxmlEnum;
import com.howe.utils.CommonUtils;
import com.howe.utils.SettingUtils;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 11:14 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = StringUtils.trimToEmpty(username.getText());
        String pword = DigestUtil.sha1Hex(password.getText());

        if (uname.equals(SettingUtils.SETTING_CACHE.getUsername()) && pword.equals(SettingUtils.SETTING_CACHE.getPassword())) {
            closeStage();
            loadMain();
            log.info("用户登录成功 {}", uname);
        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(FxmlEnum.MAIN.getFxml()));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(SettingUtils.SETTING_CACHE.getGlobal().getTitle());
            stage.setScene(new Scene(parent));
            stage.show();
            CommonUtils.setStageIcon(stage);
        } catch (IOException ex) {
            log.error("加载主窗口失败", ex);
        }
    }

}
