package org.cmms.modules.dklldj.lldjgl.lldjjs.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateZxlldjxx;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@DS("rate") // rate
public interface IRateZxlldjxxService  extends IService<RateZxlldjxx> {

    /**
     * 获取最新定价编号
     *
     * @return
     */
    String getMaxDjidHive();

}
