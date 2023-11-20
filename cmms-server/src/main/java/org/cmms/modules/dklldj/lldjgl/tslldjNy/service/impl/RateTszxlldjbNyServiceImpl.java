package org.cmms.modules.dklldj.lldjgl.tslldjNy.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.dto.RateKhAndTsZxLlNyDto;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.mapper.RateTszxlldjbNyMapper;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.service.IRateTszxlldjbNyService;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.service.IRateNyZbgzxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.swing.text.View;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: dwdw
 * @Author: jeecg-boot
 * @Date: 2022-09-14
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class RateTszxlldjbNyServiceImpl extends ServiceImpl<RateTszxlldjbNyMapper, RateTszxlldjb> implements IRateTszxlldjbNyService {

    @Autowired
    private IRateNyZbgzxxbService iRateZbgzxxbService;

    @Override
    public boolean selectSpStatusById(String id) {

        boolean flag = false;
        QueryWrapper<RateTszxlldjb> queryWrapper = new QueryWrapper();
        queryWrapper.eq("DJID", id);
        queryWrapper.and(wrapper -> {
            wrapper.eq("spzt", 0).or().eq("spzt", "0");
        });
        Long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public RateTszxlldjb selectRateRszxlldjbNyByDjId(Long djid) {
        LambdaQueryWrapper<RateTszxlldjb> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(RateTszxlldjb::getDjid, djid);
        RateTszxlldjb rateTszxlldjb = this.baseMapper.selectOne(queryWrapper);
        return rateTszxlldjb;
    }

    @Override
    public void AddRateTsNyLl(RateTszxlldjb rateTszxlldjb) {



    }

    @Override
    public JSONObject getComputeResultById(RateTszxlldjb rateTszxlldjb) {
        JSONObject view = null;
        QueryWrapper<RateTszxlldjb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DJID", rateTszxlldjb.getDjid());
        RateTszxlldjb rateTszxlldjb1 = this.baseMapper.selectOne(queryWrapper);
        if (rateTszxlldjb1 == null) {
            return view;
        } else {
            //查询基准利率
            Double jzll1 = 0d;
            Double jzll2 = 0d;
            Double jzll3 = 0d;
            Double LPRLv1 = 0d;
            Double LPRLv2 = 0d;
            String LPRDate = "";
            view = new JSONObject();
            QueryWrapper<RateZbgzxxb> QueryWrapper = new QueryWrapper<>();
            QueryWrapper.eq("zbid", "KH00011");
            List<RateZbgzxxb> rateZbgzxxbList = iRateZbgzxxbService.selectList(QueryWrapper);
            Iterator<RateZbgzxxb> zbgzxxb = rateZbgzxxbList.iterator();
            while (zbgzxxb.hasNext()) {
                String zbgzid = zbgzxxb.next().getZbgzid();
                String zbjg = zbgzxxb.next().getZbjg();
                if ("GZ00050".equalsIgnoreCase(zbgzid)) {
                    jzll1 = Double.valueOf(zbjg) / 100;
                } else if ("GZ00051".equalsIgnoreCase(zbgzid)) {
                    jzll2 = Double.valueOf(zbjg) / 100;
                } else if ("GZ00052".equalsIgnoreCase(zbgzid)) {
                    jzll3 = Double.valueOf(zbjg) / 100;
                }
            }
            view.put("jzll1", jzll1);
            view.put("jzll2", jzll2);
            view.put("jzll3", jzll3);
            // LPR利率查询
            QueryWrapper<RateZbgzxxb> QueryWrapper2 = new QueryWrapper<>();
            QueryWrapper.eq("zbid", "KH00013");
            List<RateZbgzxxb> rateZbgzxxbList2 = iRateZbgzxxbService.selectList(QueryWrapper2);
            /*  zbgzxxb1._qydm.andEqualTo(qydm);*/

            while (zbgzxxb.hasNext()) {
                String zbgzid = zbgzxxb.next().getZbgzid();
                String zbjg = zbgzxxb.next().getZbjg();
                if ("GZ00063".equalsIgnoreCase(zbgzid)) {
                    LPRLv1 = Double.valueOf(zbjg);
                } else if ("GZ00064".equalsIgnoreCase(zbgzid)) {
                    LPRLv2 = Double.valueOf(zbjg);
                } else if ("GZ00062".equalsIgnoreCase(zbgzid)) {
                    LPRDate = zbjg;
                }
            }
            view.put("bjrq", LPRDate);
            view.put("LPRLv1", LPRLv1);
            view.put("LPRLv2", LPRLv2);
            String qksm = rateTszxlldjb1.getQksm();
            if (qksm != null) {
                Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
                Matcher m = CRLF.matcher(qksm);
                if (m.find()) qksm = m.replaceAll("<br/>");
                rateTszxlldjb1.setQksm(qksm);
            }
            rateTszxlldjb1.getZxll();
        }

        view.put("table", rateTszxlldjb1);
        view.put("jsrq", DateUtil.formatDateTime("yyyy-MM-dd"));
        view.put("djid", rateTszxlldjb1.getDjid());
        return view;
    }

    @Override
    public void updateRateTsNyLl(RateTszxlldjb rateTszxlldjb) {

    }


}
