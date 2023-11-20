package org.cmms.modules.yxdygl.pqqxgl.entity;

import lombok.Data;

import java.util.List;

/**
 * @Date 2021/11/17
 * @Created by eran
 */
@Data
public class YxdyglPqqxglVO {
    private List<String> selectedRowKeys;
    private List<String> checkedKeys;
    private String khjl;
    private String sfzkhjl;

}
