package com.howe.controller;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONArray;
import com.howe.constant.ResourceConstant;
import com.howe.dto.MenuDTO;
import com.howe.utils.CommonUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/20 10:19 星期一
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Slf4j
public class MenuController implements Initializable {

    @FXML
    private MenuBar menuBar;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMenu();
    }

    private void initMenu() {
        List<MenuDTO> menuList = handleMenuTree();
        for (MenuDTO menuDTO : menuList) {
            Menu menu = createMenu(null, menuDTO, true);
            this.menuBar.getMenus().add(menu);
        }
    }

    private List<MenuDTO> handleMenuTree() {
        String menuStr = IoUtil.readUtf8(getClass().getResourceAsStream(ResourceConstant.MENU_JSON));
        return JSONArray.parseArray(menuStr, MenuDTO.class);
    }

    private Menu createMenu(Menu menu, MenuDTO menuDTO, boolean isTop) {
        if (isTop) {
            menu = new Menu(menuDTO.getText(), new ImageView("/img/" + menuDTO.getIcon()));
        }
        if (menuDTO.getChildren().isEmpty()) {
            MenuItem menuItem = createMenuItem(menuDTO);
            menu.getItems().add(menuItem);
        } else {
            for (MenuDTO childDTO : menuDTO.getChildren()) {
                createMenu(menu, childDTO, false);
            }
        }
        return menu;
    }

    private MenuItem createMenuItem(MenuDTO menuDTO) {
        MenuItem menuItem = new MenuItem();
        if (StringUtils.isNotBlank(menuDTO.getText())) {
            menuItem.setText(menuDTO.getText());
        }
        if (StringUtils.isNotBlank(menuDTO.getAction())) {
            // 如果该菜单项有操作，则添加一个事件处理器
            menuItem.setOnAction(event -> handleAction(menuDTO.getAction()));
        }
        if (StringUtils.isNotBlank(menuDTO.getIcon())) {
            ImageView icon = new ImageView("/img/" + menuDTO.getIcon());
            menuItem.setGraphic(icon);
        }
        return menuItem;
    }

    private void handleAction(String action) {
        log.info(action);
    }
}
