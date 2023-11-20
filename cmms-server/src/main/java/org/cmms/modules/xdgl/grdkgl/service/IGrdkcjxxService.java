package org.cmms.modules.xdgl.grdkgl.service;

import org.cmms.modules.xdgl.grdkgl.entity.Grdkcjxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @Description: 个人贷款采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-17
 * @Version: V1.0
 */
public interface IGrdkcjxxService extends IService<Grdkcjxx> {
    public void updateSxed(String pddj, BigDecimal zzsxed, String yj, String zjhm);

    public void updateSpjled(String pddj, BigDecimal zzsxed, String yj, String spid);

}
