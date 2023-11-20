package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.fxd.entity.KhglIndexVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqBak;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Component
public interface NhxqBakMapper extends BaseMapper<NhxqBak> {
}
