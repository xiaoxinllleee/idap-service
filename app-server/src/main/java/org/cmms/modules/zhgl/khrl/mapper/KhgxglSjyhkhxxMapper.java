package org.cmms.modules.zhgl.khrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.khrl.entity.KhgxglSjyhkhxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 手机银行客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
public interface KhgxglSjyhkhxxMapper extends BaseMapper<KhgxglSjyhkhxx> {

    List<KhgxglSjyhkhxx> getListBykhmc(@Param("khmc") String khmc,@Param("jgdm")String jgdm);
}
