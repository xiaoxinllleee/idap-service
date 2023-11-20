package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

/**
 * @Description: 一键准入报告 / 一 基本信息 / 关联人信息
 * @Author: penghr
 * @Date:   2023.04.16
 * @Version: V1.0
 */
@Data
public class JbxxGlrxx {

    /**姓名*/
    private String khmc;
    /**证件类型*/
    private String zjlx;
    /**证件号码*/
    private String zjhm;
    /**与户主关系*/
    @Dict(dicCode = "yhzgx")
    private String yhzgx;

}
