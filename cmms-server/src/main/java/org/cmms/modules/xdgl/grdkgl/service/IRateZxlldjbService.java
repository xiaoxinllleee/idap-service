package org.cmms.modules.xdgl.grdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.xdgl.grdkgl.entity.RateZxlldjb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 执行利率定价表
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IRateZxlldjbService extends IService<RateZxlldjb> {

    public  RateZxlldjb queryzxlldjb(String zjhm, Date djnf);

    int updateSpzt(String djid,String spzt,String note);

    List<RateZxlldjb> listByWrapper(QueryWrapper queryWrapper);

}
