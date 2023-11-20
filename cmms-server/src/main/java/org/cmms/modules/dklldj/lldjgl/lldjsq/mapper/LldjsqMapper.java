package org.cmms.modules.dklldj.lldjgl.lldjsq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.Lldjsq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
public interface LldjsqMapper extends BaseMapper<Lldjsq> {

    void ckrp(String djnf, String zjhm);

}
