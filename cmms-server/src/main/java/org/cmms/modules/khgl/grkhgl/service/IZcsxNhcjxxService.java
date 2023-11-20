package org.cmms.modules.khgl.grkhgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.ZcsxNhcjxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 农户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
public interface IZcsxNhcjxxService extends IService<ZcsxNhcjxx> {

    public int updateCjzt(String id);

}
