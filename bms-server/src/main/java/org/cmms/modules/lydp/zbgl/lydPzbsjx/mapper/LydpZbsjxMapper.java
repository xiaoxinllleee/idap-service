package org.cmms.modules.lydp.zbgl.lydPzbsjx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;

/**
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
public interface LydpZbsjxMapper extends BaseMapper<LydpZbsjx> {
    List<LydpZbsjx> getListByQydm(String qydm, String zblx, String zbwd, String zbid);
    List<LydpZbsjx> getList(String qydm, String zblx);
}
