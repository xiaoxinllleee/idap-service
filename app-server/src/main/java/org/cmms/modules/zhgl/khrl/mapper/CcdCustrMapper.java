package org.cmms.modules.zhgl.khrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.khrl.entity.CcdCustr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.zhgl.khrl.entity.CcdCustrVO;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
public interface CcdCustrMapper extends BaseMapper<CcdCustr> {

    List<CcdCustrVO> getXykListByKhmc(@Param("khmc") String khmc, @Param("jgdm") String jgdm);
}
