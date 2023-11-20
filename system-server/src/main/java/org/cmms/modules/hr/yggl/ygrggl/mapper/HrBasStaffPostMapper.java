package org.cmms.modules.hr.yggl.ygrggl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostZhzgry;

/**
 * @Description: 员工入岗管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
public interface HrBasStaffPostMapper extends BaseMapper<HrBasStaffPost> {
    Integer ifExistByYgghAndRgrqAndLgrq(@Param("dao") HrBasStaffPost hrBasStaffPost);
    Long getId();
    public List<HrBasStaffPost> getByNowDate(String yggh);

    public List<HrBasStaffPostZhzgry> getZhzgry(String fpyf,String zzbz);

    public HrBasStaffPost getStaffPostInfoBySprq(String yggh,String sprq);
}
