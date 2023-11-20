package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NsImportVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.VNhPjsxMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IVNhPjsxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 农户评级授信视图
 * @Author: jeecg-boot
 * @Date:   2023-02-05
 * @Version: V1.0
 */
@Service
public class VNhPjsxServiceImpl extends ServiceImpl<VNhPjsxMapper, VNhPjsx> implements IVNhPjsxService {

    @Override
    public void updatePjsx(NsImportVO nsImportVO) {
        baseMapper.updatePjsx(nsImportVO);
    }

    @Override
    public void updateCpzlll() {
        baseMapper.updateCpzlll();
    }

    @Override
    public void updateNh(NsImportVO nsImportVO) {
        baseMapper.updateNh(nsImportVO);
    }

    @Override
    public String getWorkNoByRole(String role, String sszh) {
        return baseMapper.getWorkNoByRole(role, sszh);
    }
}
