package org.cmms.modules.hr.xsgl.grkhxs.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.hr.xsgl.grkhxs.entity.ErpPersonalKhxs;
import org.cmms.modules.hr.xsgl.grkhxs.mapper.ErpPersonalKhxsMapper;
import org.cmms.modules.hr.xsgl.grkhxs.service.IErpPersonalKhxsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 个人考核系数
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Service
public class ErpPersonalKhxsServiceImpl extends ServiceImpl<ErpPersonalKhxsMapper, ErpPersonalKhxs> implements IErpPersonalKhxsService {

    @Override
    public Boolean isHaveByDate(String yggh, Date kssj, Date jssj) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("yggh",yggh);
        List<ErpPersonalKhxs> list = baseMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {

                if (!compareDate(kssj,jssj,list.get(i).getKssj(),list.get(i).getJssj())){
                    return false;
                }
            }
            return true;

        }else {
            return true;
        }
    }

    /**
     *
     * 新开始时间 新结束时间 旧开始时间 旧结束时间
     */
    public Boolean compareDate(Date nks,Date njs,Date oks,Date ojs){
        //时间段不重叠 要么都大于 要么都小于就满足条件
        return (nks.compareTo(oks) == -1 && njs.compareTo(oks) == -1) || (nks.compareTo(ojs) == 1 && njs.compareTo(ojs) == 1);
    }
}
