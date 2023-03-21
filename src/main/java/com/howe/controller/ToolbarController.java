package com.howe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cn.hutool.core.util.StrUtil;
import com.howe.enums.FxmlEnum;
import com.howe.utils.AlertMaker;
import com.howe.utils.CommonUtils;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 11:11 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class ToolbarController implements Initializable {

    private AnchorPane basePane;

    private JFXDrawer drawer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void loadSettings(ActionEvent event) {
        changeMainWindow(FxmlEnum.SETTING);
    }

    private void changeMainWindow(FxmlEnum fxml) {
        basePane.getChildren().clear();
        try {
            URL resource = getClass().getResource(fxml.getFxml());
            if (resource == null) {
                log.error("未获取到fxml文件，文件名{}", fxml.getFxml());
                AlertMaker.showErrorAlert(StrUtil.format("未获取到fxml文件，文件名{}", fxml.getFxml()));
                return;
            }
            Parent root = FXMLLoader.load(resource);
            basePane.getChildren().add(root);
            StackPane.setMargin(root, new Insets(0, 0, 0, 0));
            StackPane.setAlignment(root, Pos.CENTER);
            drawer.toggle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBasePane(AnchorPane baseAnchorPane, JFXDrawer drawer) throws IOException {
        this.basePane = baseAnchorPane;
        this.drawer = drawer;
    }
}
