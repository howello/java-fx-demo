package com.howe.controller;

import cn.hutool.core.util.StrUtil;
import com.howe.enums.FxmlEnum;
import com.howe.utils.AlertMaker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * <p>@Author lu
 * <p>@Date 2023/3/17 10:59 星期五
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class MainController implements Initializable {

    @FXML
    private AnchorPane baseAnchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane rootAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMenu();
        initDrawer();
        this.menuBar.prefWidthProperty().bind(rootPane.widthProperty());
        this.baseAnchorPane.prefWidthProperty().bind(rootPane.widthProperty());
        this.baseAnchorPane.prefHeightProperty().bind(rootAnchorPane.heightProperty());
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    private void initDrawer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlEnum.TOOLBAR.getFxml()));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            ToolbarController controller = loader.getController();
            controller.initBasePane(baseAnchorPane, drawer);
        } catch (IOException e) {
            log.error("未找到Toolbar", e);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }

    private void initMenu() {
        try {
            URL resource = getClass().getResource(FxmlEnum.MENU.getFxml());
            if (resource == null) {
                AlertMaker.showErrorAlert(StrUtil.format("未获取到fxml文件，文件名{}", FxmlEnum.MENU.getFxml()));
                return;
            }
            MenuBar menuBar = FXMLLoader.load(resource);
            this.menuBar.getMenus().addAll(menuBar.getMenus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
