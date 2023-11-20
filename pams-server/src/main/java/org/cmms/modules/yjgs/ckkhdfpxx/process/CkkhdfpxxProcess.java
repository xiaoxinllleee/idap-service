package org.cmms.modules.yjgs.ckkhdfpxx.process;

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
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.entity.Ckkhghlsb;
import org.cmms.modules.performance.depositcustomer.ckkhghlsb.service.ICkkhghlsbService;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.entity.Ckkhspxx;
import org.cmms.modules.performance.depositcustomer.ckkhspxx.service.ICkkhspxxService;
import org.cmms.modules.yjgs.ckkhdfpxx.service.ICkkhdfpxxService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CkkhdfpxxProcess extends ActTaskHandler {
    @Override
    public void doSubmit(BusinessInfoVo businessInfoVo) {
        //提交审批之后的操作
        //更新审批信息表中的状态
        ICkkhspxxService ckkhspxxService = SpringContextUtils.getBean(ICkkhspxxService.class);
        UpdateWrapper<Ckkhspxx> ckkhspxxUpdateWrapper = new UpdateWrapper<>();
        ckkhspxxUpdateWrapper.eq("table_id", businessInfoVo.getTableId());
        Ckkhspxx ckkhspxx = new Ckkhspxx();
        ckkhspxx.setProcessStatus("1");
        ckkhspxxService.update(ckkhspxx, ckkhspxxUpdateWrapper);
    }

    @Override
    public void doFinalPass(BusinessInfoVo businessInfoVo) {
        //全部审批通过之后的操作
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ICkkhspxxService ckkhspxxService = SpringContextUtils.getBean(ICkkhspxxService.class);
        ICkkhghlsbService ckkhghlsbService = SpringContextUtils.getBean(ICkkhghlsbService.class);
        ICkkhdfpxxService ckkhdfpxxService = SpringContextUtils.getBean(ICkkhdfpxxService.class);
        String tableId = businessInfoVo.getTableId();

        List<Ckkhghlsb> ckkhghlsbInsertList = new ArrayList<>();
        LambdaQueryWrapper<Ckkhspxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ckkhspxx::getTableId, tableId);
        List<Ckkhspxx> ckkhspxxList = ckkhspxxService.list(queryWrapper);

        ckkhspxxList.forEach(spxx-> {
            Date ksrq = null;
            LambdaQueryWrapper<Ckkhghlsb> ckkhghlsbQueryWrapper = new LambdaQueryWrapper<>();
            ckkhghlsbQueryWrapper.eq(Ckkhghlsb::getJgdm, spxx.getJgdm());
            ckkhghlsbQueryWrapper.eq(Ckkhghlsb::getKhbh, spxx.getKhbh());
            List<Ckkhghlsb> ckkhghlsbList = ckkhghlsbService.list(ckkhghlsbQueryWrapper);
            if (ckkhghlsbList.isEmpty()) {
                ksrq = spxx.getZzkhrq();
            } else {
                ckkhghlsbQueryWrapper = new LambdaQueryWrapper<>();
                ckkhghlsbQueryWrapper.eq(Ckkhghlsb::getJgdm, spxx.getJgdm());
                ckkhghlsbQueryWrapper.eq(Ckkhghlsb::getKhbh, spxx.getKhbh());
                ckkhghlsbQueryWrapper.lt(Ckkhghlsb::getJsrq, new Date());
                ckkhghlsbQueryWrapper.orderByDesc(Ckkhghlsb::getJsrq);
                ckkhghlsbList = ckkhghlsbService.list(ckkhghlsbQueryWrapper);
                if (!ckkhghlsbList.isEmpty()) {
                    ksrq = DateUtil.addDays(ckkhghlsbList.get(0).getJsrq(), 1);
                }
            }
            //校验管户比例
            QueryWrapper<Ckkhghlsb> ckkhghlsbCheckQueryWrapper = new QueryWrapper<>();
            ckkhghlsbCheckQueryWrapper.select("nvl(sum(ghbl),0) as ghbl")
                    .eq("jgdm", spxx.getJgdm())
                    .eq("khbh", spxx.getKhbh())
                    .apply(" (jsrq is null or jsrq >= {0} )", ksrq);

            BigDecimal ghblSum = new BigDecimal(0);
            ghblSum.add(spxx.getGhbl());
            List<Map<String, Object>> listMap = ckkhghlsbService.listMaps(ckkhghlsbCheckQueryWrapper);
            if (!listMap.isEmpty()) {
                Object ghbl = listMap.get(0).get("ghbl");
                if (ghbl != null) {
                    ghblSum.add(new BigDecimal((Double)ghbl));
                }
            }
            if(ghblSum.compareTo(new BigDecimal(100)) > 0) {
                UpdateWrapper<Ckkhspxx> ckkhspxxUpdateWrapper = new UpdateWrapper<>();
                ckkhspxxUpdateWrapper.eq("id", spxx.getId());
                Ckkhspxx ckkhspxx = new Ckkhspxx();
                ckkhspxx.setProcessStatus("3");
                ckkhspxx.setProcessInfo("认领后有效管户比例总和超过100%");
                ckkhspxxService.update(ckkhspxx, ckkhspxxUpdateWrapper);
            } else {
                Ckkhghlsb ckkhghlsb = new Ckkhghlsb();
                ckkhghlsb.setJgdm(spxx.getJgdm());
                ckkhghlsb.setKhbh(spxx.getKhbh());
                ckkhghlsb.setGhlx(2);
                ckkhghlsb.setGhr(spxx.getGhr());
                ckkhghlsb.setGhbl(spxx.getGhbl());
                ckkhghlsb.setKsrq(ksrq);
                ckkhghlsb.setLrbz(1);
                ckkhghlsb.setLrr(loginUser.getUsername());
                ckkhghlsbInsertList.add(ckkhghlsb);
            }
        });
        ckkhghlsbService.saveBatch(ckkhghlsbInsertList);
        LambdaUpdateWrapper<Ckkhspxx> ckkhspxxUpdateWrapper = new LambdaUpdateWrapper<>();
        ckkhspxxUpdateWrapper.eq(Ckkhspxx::getTableId, tableId);
        ckkhspxxUpdateWrapper.ne(Ckkhspxx::getProcessStatus, "3");
        Ckkhspxx ckkhspxx = new Ckkhspxx();
        ckkhspxx.setProcessStatus("2");
        ckkhspxx.setProcessInfo("流程完成");
        ckkhspxxService.update(ckkhspxx, ckkhspxxUpdateWrapper);

        //删除待认领表中的数据
        ckkhdfpxxService.deleteSptgsj(tableId);
    }

}
