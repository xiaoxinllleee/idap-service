package org.cmms.modules.hr.zzgl.zzgwgl.entity;

import lombok.Data;

import java.util.List;

@Data
public class RelationDTO {
    private String radioValue;
    private List<String> checkedKeys;
}
