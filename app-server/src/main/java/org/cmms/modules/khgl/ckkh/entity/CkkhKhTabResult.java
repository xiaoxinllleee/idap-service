package org.cmms.modules.khgl.ckkh.entity;

import lombok.Data;

import java.util.List;

/**
 * @Date 2022/3/8
 * @Created by eran
 */
@Data
public class CkkhKhTabResult {
    List<CkkhCardVO> cardVOList;
    Integer ghs;
}
