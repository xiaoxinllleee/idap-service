package org.cmms.modules.zhgl.khrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.khrl.entity.KhgxglEtckhxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ETC客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
public interface KhgxglEtckhxxMapper extends BaseMapper<KhgxglEtckhxx> {

    List<KhgxglEtckhxx> getEtcListByKhmc(@Param("khmc")String khmc,@Param("jgdm") String jgdm);
}
