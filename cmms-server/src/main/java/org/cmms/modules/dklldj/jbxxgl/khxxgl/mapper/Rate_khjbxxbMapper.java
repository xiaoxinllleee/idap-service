package org.cmms.modules.dklldj.jbxxgl.khxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;


/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-03-04
 * @Version: V1.0
 */
@Component
public interface Rate_khjbxxbMapper extends BaseMapper<Rate_khjbxxb> {

    public void extract();

}
