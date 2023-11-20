package org.cmms.modules.khgl.xjjl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 聚合商户巡检记录
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
public interface AppJhshXjjlMapper extends BaseMapper<AppJhshXjjl> {
    List<TgacsTpsMchntInfo> getAllList(@Param("start")int start, @Param("end")int end, @Param("namecn")String namecn);

    List<AppJhshXjjl> getXjsj(@Param("mchntId")String mchntId);

    List<TgacsTpsMchntInfo> showXJ(@Param("start")int start, @Param("end")int end, @Param("namecn")String namecn);
}
