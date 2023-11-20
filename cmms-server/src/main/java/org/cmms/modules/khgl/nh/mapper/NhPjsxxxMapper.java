package org.cmms.modules.khgl.nh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.NhPjsxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农户评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface NhPjsxxxMapper extends BaseMapper<NhPjsxxx> {

    public List<NhPjsxxx> selectByMainId(String zjhm);

    public boolean deleteByMainId(String zjhm);


}
