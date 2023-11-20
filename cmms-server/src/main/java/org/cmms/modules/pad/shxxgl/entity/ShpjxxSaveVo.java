package org.cmms.modules.pad.shxxgl.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 龚辉
 * @date 2023/9/15 16:51 周五
 */
@Data
public class ShpjxxSaveVo {

    private String shid;
    private List<CamsZcsxShpjsxxxZcqk> zcqkList;
    private List<CamsZcsxShpjsxxxFzqk> fzqkList;
    private CamsZcsxShpjsxxx camsZcsxShpjsxxx;
}
