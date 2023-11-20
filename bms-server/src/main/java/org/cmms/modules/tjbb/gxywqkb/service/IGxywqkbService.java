package org.cmms.modules.tjbb.gxywqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.gxywqkb.entity.Gxywqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 各项业务情况表
 * @Author: Penghr
 * @Date:   2022-12-12
 * @Version: V1.0
 */
@DS("tjbb")
public interface IGxywqkbService extends IService<Gxywqkb> {

    Gxywqkb queryByDatadateAndXh(String data_date, String xh);

}
