package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxZhsj;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.PjsxZhsjMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IPjsxZhsjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 评级授信支行数据
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
@Service
public class PjsxZhsjServiceImpl extends ServiceImpl<PjsxZhsjMapper, PjsxZhsj> implements IPjsxZhsjService {

    @Override
    public void gxsj(String zzbz) {
        try {
            if (StringUtils.isNotBlank(zzbz)){
                baseMapper.gxsjByZzbh(zzbz);
            }else {
                baseMapper.gxsj();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
