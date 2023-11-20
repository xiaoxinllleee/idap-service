package org.cmms.modules.pad.lsdxxcx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.pad.lsdxxcx.entity.Lsdxxcx;

/**
 * @Description: 流水贷信息查询
 * @Author: jeecg-boot
 * @Date:   2023-05-24
 * @Version: V1.0
 */
public interface LsdxxcxMapper extends BaseMapper<Lsdxxcx> {


    IPage<Lsdxxcx> getMaxData(Page page,@Param("khmc") String khmc,@Param("zjhm") String zjhm);

}
