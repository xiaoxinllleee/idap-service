package org.cmms.modules.system.entity;

import lombok.Data;

import java.util.List;

/**
 * @Date 2022/3/17
 * @Created by eran
 */
@Data
public class AppHrBasOrganization {
    private String text;
    private String value;
    private List<AppHrBasOrganization> children;
}
