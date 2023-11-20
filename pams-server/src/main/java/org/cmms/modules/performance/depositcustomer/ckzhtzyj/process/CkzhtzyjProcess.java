package org.cmms.modules.performance.depositcustomer.ckzhtzyj.process;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

import java.util.ArrayList;
import java.util.List;

public class CkzhtzyjProcess extends ActTaskHandler {
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
        String tableId = businessInfoVo.getTableId();

        List<Ckzhghlsb> ckzhghlsbInsertList = new ArrayList<>();
        LambdaQueryWrapper<Ckzhspxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ckzhspxx::getTableId, tableId);
        List<Ckzhspxx> ckzhspxxList = ckzhspxxService.list(queryWrapper);

        ckzhspxxList.forEach(spxx-> {
            //更新原管户人的结束日期
            UpdateWrapper<Ckzhghlsb> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", spxx.getId());
            Ckzhghlsb ckzhghlsb = new Ckzhghlsb();
            ckzhghlsb.setJsrq(DateUtil.addDays(spxx.getYjrq(), -1));
            ckzhghlsb.setLrbz(2);
            ckzhghlsb.setBz("移交给" + spxx.getTzr());
            ckzhghlsbService.update(ckzhghlsb, updateWrapper);

            Ckzhghlsb ckzhghlsbInsert = new Ckzhghlsb();
            ckzhghlsbInsert.setJgdm(spxx.getJgdm());
            ckzhghlsbInsert.setKhbh(spxx.getKhbh());
            ckzhghlsbInsert.setCkzh(spxx.getCkzh());
            ckzhghlsbInsert.setZhlx(spxx.getZhlx());
            ckzhghlsbInsert.setGhlx(1);
            ckzhghlsbInsert.setGhr(spxx.getTzr());
            ckzhghlsbInsert.setGhbl(spxx.getTzbl());
            ckzhghlsbInsert.setKsrq(spxx.getYjrq());
            ckzhghlsbInsert.setLrbz(1);
            ckzhghlsbInsert.setLrr(loginUser.getUsername());
            ckzhghlsbInsert.setBz("从" + spxx.getYtzr() + "移交");
            ckzhghlsbInsertList.add(ckzhghlsbInsert);
        });
        ckzhghlsbService.saveBatch(ckzhghlsbInsertList);
        LambdaUpdateWrapper<Ckzhspxx> ckzhspxxUpdateWrapper = new LambdaUpdateWrapper<>();
        ckzhspxxUpdateWrapper.eq(Ckzhspxx::getTableId, tableId);
        ckzhspxxUpdateWrapper.ne(Ckzhspxx::getProcessStatus, "3");
        Ckzhspxx ckzhspxx = new Ckzhspxx();
        ckzhspxx.setProcessStatus("2");
        ckzhspxx.setProcessInfo("流程完成");
        ckzhspxxService.update(ckzhspxx, ckzhspxxUpdateWrapper);

    }

}
