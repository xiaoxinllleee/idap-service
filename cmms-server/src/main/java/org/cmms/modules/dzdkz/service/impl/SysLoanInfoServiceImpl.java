package org.cmms.modules.dzdkz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.dzdkz.entity.Sequence;
import org.cmms.modules.dzdkz.entity.SysLoanInfo;
import org.cmms.modules.dzdkz.mapper.SequenceMapper;
import org.cmms.modules.dzdkz.mapper.SysLoanInfoMapper;
import org.cmms.modules.dzdkz.service.SysLoanInfoService;
import org.cmms.modules.util.sm4.Sm4Utils;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 */
@Service
@DS("dzdkz")
@Slf4j
public class SysLoanInfoServiceImpl implements SysLoanInfoService {

    @Autowired
    SysLoanInfoMapper sysLoanInfoMapper;

    @Autowired
    SequenceMapper sequenceMapper;

    @Override
    public void updateDzdkzData(List<Grpjsxspjl> list) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < list.size(); i++) {
            Grpjsxspjl grpjsxspjl = list.get(i);
            String sm4Idn = Sm4Utils.encryptData_CBC(grpjsxspjl.getZjhm());
            SysLoanInfo sysLoanInfo = new SysLoanInfo();
            sysLoanInfo.setName(Sm4Utils.encryptData_CBC(grpjsxspjl.getKhmc()));
            sysLoanInfo.setIdcard(sm4Idn);
            sysLoanInfo.setRank(getPddjVal(grpjsxspjl.getZzpddj()));
            if (grpjsxspjl.getZzsxed() == null) {
                sysLoanInfo.setQuota("0");
            } else {
                int i1 = grpjsxspjl.getZzsxed().multiply(new BigDecimal(10000)).intValue();
                sysLoanInfo.setQuota(i1 + "");
            }

            sysLoanInfo.setLoanType("1");
            if (StringUtils.isNotBlank(grpjsxspjl.getBz())) {
                sysLoanInfo.setOrgan(grpjsxspjl.getBz());

                if (grpjsxspjl.getBz().contains("淮川") || grpjsxspjl.getBz().contains("营业部") || grpjsxspjl.getBz().contains("城关") || grpjsxspjl.getBz().contains("百园"))
                    ;
                sysLoanInfo.setLoanType("0");
            }

            if (StringUtils.isNotBlank(grpjsxspjl.getSqr())) {
                sysLoanInfo.setManager(grpjsxspjl.getSqr());
            }

            if (StringUtils.isNotBlank(grpjsxspjl.getSjhm())) {
                sysLoanInfo.setTel(Sm4Utils.encryptData_CBC(grpjsxspjl.getSjhm()));
            }

            sysLoanInfo.setTmSmp(DateUtil.format(new Date(), "yyyyMMddHHmmss"));


            SysLoanInfo byZjhm = sysLoanInfoMapper.getByZjhm(sm4Idn);


            if (byZjhm != null) {
                //更新数据
                sysLoanInfo.setId(byZjhm.getId());
                sysLoanInfo.setOpenid(byZjhm.getOpenid());
                int update = sysLoanInfoMapper.updateById(sysLoanInfo);
                if (update == 1) {
                    a++;
                }
            } else {
                //新增数据

                Sequence sequence = sequenceMapper.selectById("SEQ_NO");
                Long currentValue = sequence.getCurrentValue() + 1l;
                sysLoanInfo.setId(currentValue.toString());
                int insert = sysLoanInfoMapper.insert(sysLoanInfo);


                if (insert == 1) {
                    sequence.setCurrentValue(currentValue);
                    sequenceMapper.updateById(sequence);
                    b++;
                }
            }

        }
        log.info("本次电子贷款证数据 更新{}条，新增{}条", a, b);
    }


    @Override
    public void comDzdkzUpdate(String khmc, String zjhm, String fpdj, BigDecimal je, String type,
                               String khjl, String khjlsjhm,String zzjc) {
        SysLoanInfo sysLoanInfo = new SysLoanInfo();
        if (StringUtils.isNotBlank(khmc))
            sysLoanInfo.setName(Sm4Utils.encryptData_CBC(khmc));
        String sm4Idn = Sm4Utils.encryptData_CBC(zjhm);
        sysLoanInfo.setIdcard(sm4Idn);
        if (StringUtils.isNotBlank(fpdj))
            sysLoanInfo.setRank(fpdj);
        if (je != null) {
            BigDecimal multiply = je.multiply(new BigDecimal("10000"));
            sysLoanInfo.setQuota(multiply.toString());
        }
        if (StringUtils.isNotBlank(type))
            sysLoanInfo.setLoanType(type);
        if (StringUtils.isNotBlank(khjl))
            sysLoanInfo.setManager(khjl);
        if (StringUtils.isNotBlank(khjlsjhm))
            sysLoanInfo.setTel(Sm4Utils.encryptData_CBC(khjlsjhm));
        if (StringUtils.isNotBlank(zzjc))
            sysLoanInfo.setOrgan(zzjc);
        sysLoanInfo.setTmSmp(DateUtil.format(new Date(), "yyyyMMddHHmmss"));

        SysLoanInfo byZjhm = sysLoanInfoMapper.getByZjhm(sm4Idn);


        if (byZjhm != null) {
            //更新数据
            sysLoanInfo.setId(byZjhm.getId());
            sysLoanInfo.setOpenid(byZjhm.getOpenid());
            int update = sysLoanInfoMapper.updateById(sysLoanInfo);

        } else {
            //新增数据

            Sequence sequence = sequenceMapper.selectById("SEQ_NO");
            Long currentValue = sequence.getCurrentValue() + 1l;
            sysLoanInfo.setId(currentValue.toString());
            int insert = sysLoanInfoMapper.insert(sysLoanInfo);

            if (insert == 1) {
                sequence.setCurrentValue(currentValue);
                sequenceMapper.updateById(sequence);
            }
        }
    }

    public String getPddjVal(String zzpddj) {
        if ("A".equals(zzpddj))
            return "特级";
        if ("B".equals(zzpddj))
            return "优秀";
        if ("C".equals(zzpddj))
            return "较好";
        if ("D".equals(zzpddj))
            return "一般";
        if ("E".equals(zzpddj))
            return "级外";
        return "特级";
    }

}
