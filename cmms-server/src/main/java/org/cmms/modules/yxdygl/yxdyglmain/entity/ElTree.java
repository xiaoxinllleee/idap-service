package org.cmms.modules.yxdygl.yxdyglmain.entity;

import lombok.Data;

import java.util.List;

@Data
public class ElTree {
    private String id;
    private String name;
    private String parentId;
    private Boolean disabled = Boolean.TRUE;

    private List<ElTree> children;
}
