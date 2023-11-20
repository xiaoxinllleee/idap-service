package org.cmms.modules.jx.dkkh.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.jx.common.entity.TGetListOfInstitutionalLoansResp;
import org.cmms.modules.jx.common.entity.TbJjfxJgdksj;
import org.cmms.modules.jx.common.entity.TbTjfxDkghhzxx;
import org.cmms.modules.jx.common.mapper.TbJjfxJgdksjMapper;
import org.cmms.modules.jx.common.mapper.TbTjfxDkghhzxxMapper;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxx;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxxBnblmxVO;
import org.cmms.modules.jx.dkkh.mapper.TbTjfxDkghmxxxxMapper;
import org.cmms.modules.jx.dkkh.service.ITbTjfxDkghmxxxxService;
import org.cmms.modules.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 贷款管户明细信息
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Service
public class TbTjfxDkghmxxxxServiceImpl extends ServiceImpl<TbTjfxDkghmxxxxMapper, TbTjfxDkghmxxxx> implements ITbTjfxDkghmxxxxService {

    @Autowired
    ITbTjfxCsszService iTbTjfxCsszService;
    @Autowired
    TbJjfxJgdksjMapper tbJjfxJgdksjMapper;
    @Autowired
    TbTjfxDkghhzxxMapper tbTjfxDkghhzxxMapper;
    @Autowired
    TbTjfxDkghmxxxxMapper tbTjfxDkghmxxxxMapper;
    @Override
    public Map<String, Object> getListOfInstitutionalLoans(Page page, String zzbz) {
        List<TGetListOfInstitutionalLoansResp> tGetListOfInstitutionalLoansResps = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNoneBlank(zzbz));
            queryWrapper.eq("zzbz",zzbz);
        Date date = DateUtil.stringToDate(iTbTjfxCsszService.getValue("P00001"), "yyyyMMdd");
        queryWrapper.eq("tjrq",date);
        IPage<TbJjfxJgdksj> iPage = tbJjfxJgdksjMapper.selectPage(page, queryWrapper);
        iPage.getRecords().forEach(data ->{
            TGetListOfInstitutionalLoansResp tGetListOfInstitutionalLoansResp = new TGetListOfInstitutionalLoansResp();
            BeanUtil.copyProperties(data,tGetListOfInstitutionalLoansResp);

            List<TGetListOfInstitutionalLoansResp.Item> items = new ArrayList<>();

            QueryWrapper queryWrapper1 = new QueryWrapper();
            if (StringUtils.isNoneBlank(data.getZzbz()))
                queryWrapper1.eq("zzbz",zzbz);
            queryWrapper1.eq("tjrq",date);

            List<TbTjfxDkghhzxx> list = tbTjfxDkghhzxxMapper.selectList(queryWrapper1);
            list.forEach(item ->{
                TGetListOfInstitutionalLoansResp.Item itemResp = new TGetListOfInstitutionalLoansResp.Item();
                BeanUtil.copyProperties(item,itemResp);
                items.add(itemResp);
            });
            tGetListOfInstitutionalLoansResp.setItems(items);
            tGetListOfInstitutionalLoansResps.add(tGetListOfInstitutionalLoansResp);
        });
        map.put("list", tGetListOfInstitutionalLoansResps);
        map.put("count", iPage.getTotal());
        return map;
    }

    @Override
    public IPage getPagMapAssist(Page page, Map<String, Object> map) {
        return null;
    }

    @Override
    public IPage<TbTjfxDkghmxxxxBnblmxVO> getPageByYggh(Page page, String yggh) {
        return tbTjfxDkghmxxxxMapper.getPageByYggh(page,yggh);
    }
}
