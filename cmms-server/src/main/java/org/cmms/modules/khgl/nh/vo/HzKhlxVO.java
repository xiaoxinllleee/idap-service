package org.cmms.modules.khgl.nh.vo;

import lombok.Data;

/**
 * 可评议户数
 * @Date 2022/4/29
 * @Created by eran
 */
@Data
public class HzKhlxVO {
    //灰名单
    private Integer hui;
    //白名单
    private Integer bai;
    //黑名单
    private Integer hei;
    private String wgbh;
}
