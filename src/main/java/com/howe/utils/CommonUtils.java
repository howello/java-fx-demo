package com.howe.utils;


import cn.hutool.core.io.FileUtil;
import com.howe.constant.ResourceConstant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 10:18 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class CommonUtils {

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(CommonUtils.class.getResourceAsStream(ResourceConstant.ICON)));
    }

    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            setStageIcon(stage);
        } catch (IOException ex) {
            log.error("loadWindow失败", ex);
        }
        return controller;
    }
}
