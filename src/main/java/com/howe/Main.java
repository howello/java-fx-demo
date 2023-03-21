package com.howe;

import com.howe.enums.FxmlEnum;
import com.howe.utils.CommonUtils;
import com.howe.utils.SettingUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/15 11:05 星期三
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class Main extends Application {
    /**
     * 所有JavaFX应用程序的主要入口点。在init方法返回之后，在系统为应用程序开始运行做好准备之后，将调用start方法。
     *
     * <p>
     * NOTE: 此方法在JavaFX应用程序线程上调用。
     * </p>
     *
     * @param stage 此应用程序的主要阶段，可以在其上设置应用程序场景。
     *              如果应用程序是作为applet启动的，那么初级阶段将嵌入到浏览器中。
     *              如果需要，应用程序可以创建其他阶段，但它们不是主要阶段，也不会嵌入到浏览器中。
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FxmlEnum.MAIN.getFxml()));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(true);
        stage.setTitle(SettingUtils.SETTING_CACHE.getGlobal().getTitle());

        CommonUtils.setStageIcon(stage);
    }

    public static void main(String[] args) {
        SettingUtils.initConfig();
        launch(args);
    }
}
