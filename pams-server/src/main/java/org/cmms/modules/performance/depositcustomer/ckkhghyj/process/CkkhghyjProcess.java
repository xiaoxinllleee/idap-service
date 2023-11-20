package org.cmms.modules.performance.depositcustomer.ckkhghyj.process;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CkkhghyjProcess extends ActTaskHandler {
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
        String tableId = businessInfoVo.getTableId();

        List<Ckkhghlsb> ckkhghlsbInsertList = new ArrayList<>();
        LambdaQueryWrapper<Ckkhspxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ckkhspxx::getTableId, tableId);
        List<Ckkhspxx> ckkhspxxList = ckkhspxxService.list(queryWrapper);

        ckkhspxxList.forEach(spxx-> {
            //更新原管户人的结束日期
            UpdateWrapper<Ckkhghlsb> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", spxx.getId());
            Ckkhghlsb ckkhghlsb = new Ckkhghlsb();
            ckkhghlsb.setJsrq(DateUtil.addDays(spxx.getYjrq(), -1));
            ckkhghlsb.setLrbz(2);
            ckkhghlsb.setBz("移交给" + spxx.getGhr());
            ckkhghlsbService.update(ckkhghlsb, updateWrapper);

            Ckkhghlsb ckkhghlsbInsert = new Ckkhghlsb();
            ckkhghlsbInsert.setJgdm(spxx.getJgdm());
            ckkhghlsbInsert.setKhbh(spxx.getKhbh());
            ckkhghlsbInsert.setGhlx(2);
            ckkhghlsbInsert.setGhr(spxx.getGhr());
            ckkhghlsbInsert.setGhbl(spxx.getGhbl());
            ckkhghlsbInsert.setKsrq(spxx.getYjrq());
            ckkhghlsbInsert.setLrbz(1);
            ckkhghlsbInsert.setLrr(loginUser.getUsername());
            ckkhghlsbInsert.setBz("从" + spxx.getYghr() + "移交");
            ckkhghlsbInsertList.add(ckkhghlsbInsert);
        });
        ckkhghlsbService.saveBatch(ckkhghlsbInsertList);
        LambdaUpdateWrapper<Ckkhspxx> ckkhspxxUpdateWrapper = new LambdaUpdateWrapper<>();
        ckkhspxxUpdateWrapper.eq(Ckkhspxx::getTableId, tableId);
        ckkhspxxUpdateWrapper.ne(Ckkhspxx::getProcessStatus, "3");
        Ckkhspxx ckkhspxx = new Ckkhspxx();
        ckkhspxx.setProcessStatus("2");
        ckkhspxx.setProcessInfo("流程完成");
        ckkhspxxService.update(ckkhspxx, ckkhspxxUpdateWrapper);

    }

}
