package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;

import java.util.List;

/**
 * @Description: 指标工资信息表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@DS("eweb") // rate
public interface IRateNyZbgzxxbService extends IService<RateZbgzxxb> {

    public RateZbgzxxb queryzbgzxxb(String qydm, String zbid, String zbgzid);

    List<RateZbgzxxb> selectList(QueryWrapper<RateZbgzxxb> queryWrapper2);
}
