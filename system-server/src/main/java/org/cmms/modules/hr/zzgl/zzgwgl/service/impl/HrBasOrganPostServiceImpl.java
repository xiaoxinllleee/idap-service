package org.cmms.modules.hr.zzgl.zzgwgl.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.HrBasOrganPost;
import org.cmms.modules.hr.zzgl.zzgwgl.entity.RelationDTO;
import org.cmms.modules.hr.zzgl.zzgwgl.mapper.HrBasOrganPostMapper;
import org.cmms.modules.hr.zzgl.zzgwgl.service.IHrBasOrganPostService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 组织岗位管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Service
@Slf4j
public class HrBasOrganPostServiceImpl extends ServiceImpl<HrBasOrganPostMapper, HrBasOrganPost> implements IHrBasOrganPostService {

    @Override
    public int relationByRolesAndBank(RelationDTO relationDTO) {
        List<String> list = baseMapper.existenceByGwbz(relationDTO.getRadioValue());
        List<String> checkedKeys = relationDTO.getCheckedKeys();
        checkedKeys.removeAll(list);

        int count = 0;

        if (CollUtil.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                HrBasOrganPost hrBasOrganPost = new HrBasOrganPost();
                hrBasOrganPost.setGwbz(Integer.valueOf(relationDTO.getRadioValue()));
                hrBasOrganPost.setZzbz(list.get(i));
                if (baseMapper.insert(hrBasOrganPost) == 1)
                    count++;
            }
        }
        return count;
    }

    @Override
    public Boolean ifExistByGwbzAndZzbz(Integer gwbz, String zzbz) {
        return baseMapper.ifExistByGwbzAndZzbz(gwbz,zzbz)>0?Boolean.TRUE:Boolean.FALSE;
    }
}
