package org.cmms.modules.home.entity;

import lombok.Data;

import java.util.List;

/**
 * @Date 2022/2/25
 * @Created by eran
 */
@Data
public class AppHomepageVO {
    List<AppHomepage> khgls;
    List<AppHomepage> ywgls;
    List<AppHomepage> zhgls;
}
