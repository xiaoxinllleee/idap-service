package org.cmms.modules.sjxf.qtxt.djkxt.khzlb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.entity.Khzlb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
public interface KhzlbMapper extends BaseMapper<Khzlb> {



    List<Khzlb> getXykListByKhmc(@Param("khmc")String khmc, @Param("jgdm")String jgdm);
}
