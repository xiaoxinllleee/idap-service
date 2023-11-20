package org.cmms.modules.yjgs.ckzhdrlxx.process;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.activiti.controller.ActTaskHandler;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.entity.Ckzhghlsb;
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.service.ICkzhghlsbService;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.entity.Ckzhspxx;
import org.cmms.modules.performance.depositcustomer.ckzhspxx.service.ICkzhspxxService;
import org.cmms.modules.yjgs.ckzhdrlxx.service.ICkzhdrlxxService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CkzhdfpxxProcess extends ActTaskHandler {
    @Override
    public void doSubmit(BusinessInfoVo businessInfoVo) {
        //提交审批之后的操作
        //更新审批信息表中的状态
        ICkzhspxxService ckzhspxxService = SpringContextUtils.getBean(ICkzhspxxService.class);
        UpdateWrapper<Ckzhspxx> ckzhspxxUpdateWrapper = new UpdateWrapper<>();
        ckzhspxxUpdateWrapper.eq("table_id", businessInfoVo.getTableId());
        Ckzhspxx ckzhspxx = new Ckzhspxx();
        ckzhspxx.setProcessStatus("1");
        ckzhspxxService.update(ckzhspxx, ckzhspxxUpdateWrapper);
    }

    @Override
    public void doFinalPass(BusinessInfoVo businessInfoVo) {
        //全部审批通过之后的操作
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ICkzhspxxService ckzhspxxService = SpringContextUtils.getBean(ICkzhspxxService.class);
        ICkzhghlsbService ckzhghlsbService = SpringContextUtils.getBean(ICkzhghlsbService.class);
        ICkzhdrlxxService ckzhdrlxxService = SpringContextUtils.getBean(ICkzhdrlxxService.class);
        String tableId = businessInfoVo.getTableId();

        List<Ckzhghlsb> ckzhghlsbInsertList = new ArrayList<>();
        LambdaQueryWrapper<Ckzhspxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ckzhspxx::getTableId, tableId);
        List<Ckzhspxx> ckzhspxxList = ckzhspxxService.list(queryWrapper);

        ckzhspxxList.forEach(spxx-> {
            Date ksrq = null;
            LambdaQueryWrapper<Ckzhghlsb> ckzhghlsbQueryWrapper = new LambdaQueryWrapper<>();
            ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getJgdm, spxx.getJgdm());
            ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getKhbh, spxx.getKhbh());
            ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getCkzh, spxx.getCkzh());
            List<Ckzhghlsb> ckzhghlsbList = ckzhghlsbService.list(ckzhghlsbQueryWrapper);
            if (ckzhghlsbList.isEmpty()) {
                ksrq = spxx.getKhrq();
            } else {
                ckzhghlsbQueryWrapper = new LambdaQueryWrapper<>();
                ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getJgdm, spxx.getJgdm());
                ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getKhbh, spxx.getKhbh());
                ckzhghlsbQueryWrapper.eq(Ckzhghlsb::getCkzh, spxx.getCkzh());
                ckzhghlsbQueryWrapper.lt(Ckzhghlsb::getJsrq, new Date());
                ckzhghlsbQueryWrapper.orderByDesc(Ckzhghlsb::getJsrq);
                ckzhghlsbList = ckzhghlsbService.list(ckzhghlsbQueryWrapper);
                if (!ckzhghlsbList.isEmpty()) {
                    ksrq = DateUtil.addDays(ckzhghlsbList.get(0).getJsrq(), 1);
                }
            }
            //校验管户比例
            QueryWrapper<Ckzhghlsb> ckzhghlsbCheckQueryWrapper = new QueryWrapper<>();
            ckzhghlsbCheckQueryWrapper.select("nvl(sum(ghbl),0) as ghbl")
                    .eq("jgdm", spxx.getJgdm())
                    .eq("khbh", spxx.getKhbh())
                    .eq("ckzh", spxx.getCkzh())
                    .apply(" (jsrq is null or jsrq >= {0} )", ksrq);

            BigDecimal tzblSum = new BigDecimal(0);
            tzblSum.add(spxx.getTzbl());
            List<Map<String, Object>> listMap = ckzhghlsbService.listMaps(ckzhghlsbCheckQueryWrapper);
            if (!listMap.isEmpty()) {
                Object ghbl = listMap.get(0).get("ghbl");
                if (ghbl != null) {
                    tzblSum.add(new BigDecimal((Double)ghbl));
                }
            }
            if(tzblSum.compareTo(new BigDecimal(100)) > 0) {
                UpdateWrapper<Ckzhspxx> ckzhspxxUpdateWrapper = new UpdateWrapper<>();
                ckzhspxxUpdateWrapper.eq("id", spxx.getId());
                Ckzhspxx ckzhspxx = new Ckzhspxx();
                ckzhspxx.setProcessStatus("3");
                ckzhspxx.setProcessInfo("认领后有效拓展比例总和超过100%");
                ckzhspxxService.update(ckzhspxx, ckzhspxxUpdateWrapper);
            } else {
                Ckzhghlsb ckzhghlsb = new Ckzhghlsb();
                ckzhghlsb.setJgdm(spxx.getJgdm());
                ckzhghlsb.setKhbh(spxx.getKhbh());
                ckzhghlsb.setCkzh(spxx.getCkzh());
                ckzhghlsb.setZhlx(spxx.getZhlx());
                ckzhghlsb.setGhlx(2);
                ckzhghlsb.setGhr(spxx.getTzr());
                ckzhghlsb.setGhbl(spxx.getTzbl());
                ckzhghlsb.setKsrq(ksrq);
                ckzhghlsb.setLrbz(1);
                ckzhghlsb.setLrr(loginUser.getUsername());
                ckzhghlsbInsertList.add(ckzhghlsb);
            }
        });
        ckzhghlsbService.saveBatch(ckzhghlsbInsertList);
        LambdaUpdateWrapper<Ckzhspxx> ckkhspxxUpdateWrapper = new LambdaUpdateWrapper<>();
        ckkhspxxUpdateWrapper.eq(Ckzhspxx::getTableId, tableId);
        ckkhspxxUpdateWrapper.ne(Ckzhspxx::getProcessStatus, "3");
        Ckzhspxx ckzhspxx = new Ckzhspxx();
        ckzhspxx.setProcessStatus("2");
        ckzhspxx.setProcessInfo("流程完成");
        ckzhspxxService.update(ckzhspxx, ckkhspxxUpdateWrapper);

        //删除待认领表中的数据
        ckzhdrlxxService.deleteSptgsj(tableId);
    }

}
