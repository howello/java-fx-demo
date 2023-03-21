package com.howe.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>@Author lu
 * <p>@Date 2023/3/20 10:58 星期一
 * <p>@Version 1.0
 * <p>@Description TODO
 */
@Data
public class MenuDTO {

    private String text;

    private String action;

    private String icon;

    private List<MenuDTO> children;
}
