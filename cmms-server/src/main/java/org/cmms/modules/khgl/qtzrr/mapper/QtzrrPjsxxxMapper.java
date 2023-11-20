package org.cmms.modules.khgl.qtzrr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.qtzrr.entity.QtzrrPjsxxx;

/**
 * @Description: 其他自然人评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface QtzrrPjsxxxMapper extends BaseMapper<QtzrrPjsxxx> {

    public List<QtzrrPjsxxx> selectByMainId(String zjhm);

    public boolean deleteByMainId(String zjhm);


}
