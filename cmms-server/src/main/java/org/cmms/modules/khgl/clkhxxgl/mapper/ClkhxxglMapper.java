package org.cmms.modules.khgl.clkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量客户信息管理
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface ClkhxxglMapper extends BaseMapper<Clkhxxgl> {
    public void initClkhxx();
    public void initClkhxxRC();
    public void initKhxx();
    public void initKhxxRC(String zxrkrq);
    public int checkTableExist(@Param("tableName") String tableName);
    public String getCsz(@Param("csbm") String csbm);
    public int syncYesYwhywxxwl();
    public int syncYesYwhywxxwlZh();
    public int delYwhxxwlDayBySjrq(String sjrq);
    public int delYwhxxwlDayZhBySjrq(String sjrq);
}
