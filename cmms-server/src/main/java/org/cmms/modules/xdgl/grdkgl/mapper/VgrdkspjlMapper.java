package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.xdgl.grdkgl.entity.Vgrdkspjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人贷款审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
public interface VgrdkspjlMapper extends BaseMapper<Vgrdkspjl> {

    public List<Vgrdkspjl> queryDksp(@Param("userId")String userId);

    public List<Vgrdkspjl> queryByZjhm(@Param("userId")String userId,@Param("zjhm")String zjhm,@Param("khmc")String khmc);

    public List<Vgrdkspjl> selectByZjhm(@Param("userId")String userId,@Param("zjhm")String zjhm);

    public List<Vgrdkspjl> selectByKhmc(@Param("userId")String userId,@Param("khmc")String khmc);

}
