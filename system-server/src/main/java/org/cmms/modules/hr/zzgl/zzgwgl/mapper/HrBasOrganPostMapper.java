package org.cmms.modules.hr.zzgl.zzgwgl.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.HrBasOrganPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Description: 组织岗位管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface HrBasOrganPostMapper extends BaseMapper<HrBasOrganPost> {

    List<String> existenceByGwbz(String gwbz);

    Integer ifExistByGwbzAndZzbz(@Param("gwbz") Integer gwbz,@Param("zzbz") String zzbz);
}
