package org.cmms.modules.khgl.wyxkhxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.wyxkhxx.entity.Wyxkhxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 未用信客户信息
 * @Author: jeecg-boot
 * @Date:   2019-09-29
 * @Version: V1.0
 */
public interface WyxkhxxMapper extends BaseMapper<Wyxkhxx> {
    public void initWyxkhxx();

    public List<Wyxkhxx> queryByHzcustid(String hzcustid);
}
