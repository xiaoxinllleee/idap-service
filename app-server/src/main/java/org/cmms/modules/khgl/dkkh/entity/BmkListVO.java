package org.cmms.modules.khgl.dkkh.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date 2022/4/12
 * @Created by eran
 */
@Data
public class BmkListVO {

    private BmkVO bmkVO;
    private List<BmkVO> bmkVOList;
}
