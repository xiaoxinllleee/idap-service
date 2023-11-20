package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;
import org.cmms.modules.khgl.dkkh.entity.HtlbVO;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgghdksjmx;
import org.cmms.modules.khgl.dkkh.mapper.TbDkYgghdksjmxMapper;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgghdksjmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 员工管户贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class TbDkYgghdksjmxServiceImpl extends ServiceImpl<TbDkYgghdksjmxMapper, TbDkYgghdksjmx> implements ITbDkYgghdksjmxService {




    @Override
    public List<String> getZjhms(int rownumStart, int rownumEnd, String yggh, String custType, String wjfl) {
        return null;
    }

    @Override
    public IPage<String> getZjhms2(Page page, String yggh, String custType, String wjfl, String zjhm) {
        return baseMapper.getZjhms2(page, yggh, custType, wjfl, zjhm);
    }


    @Override
    public List<String> getCustTypeByZjhm(String zjhm, String yggh) {
        return baseMapper.getCustTypeByZjhm(zjhm,yggh);
    }

    @Override
    public IPage<DkxtjcVO> getDkxtjcList(Page page, String yggh, String custType, String type, String zjhm,String qmTable,String jcTable,String zrTable) {
        if ("1".equals(type)){
            type = " jnc > 0";
        }else if ("2".equals(type)){
            type = " jnc < 0";
        }else if ("3".equals(type)){
            type = " jjc > 0";
        }else if ("4".equals(type)){
            type = " jjc < 0";
        }else if ("5".equals(type)){
            type = " jyc > 0";
        }else if ("6".equals(type)){
            type = " jjc < 0";
        }else if ("7".equals(type)){
            type = " jzr > 0";
        }else if ("8".equals(type)){
            type = " jzr < 0";
        }else {
            type = " jnc > 0";
        }
        return baseMapper.dkxtjc(page, yggh, custType, type, zjhm,qmTable,jcTable,zrTable);
    }

    @Override
    public List<HtlbVO> getHtlbsByZjhm(String zjhm) {
        return baseMapper.getHtlbsByZjhm(zjhm);
    }
}
