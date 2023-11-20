package org.cmms.modules.khgl.clkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量个人客户基本信息
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface ClgrkhjbxxMapper extends BaseMapper<Clgrkhjbxx> {
    int syncYesClgrxx();
}
