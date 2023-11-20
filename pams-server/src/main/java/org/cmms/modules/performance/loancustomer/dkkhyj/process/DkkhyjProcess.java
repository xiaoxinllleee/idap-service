package org.cmms.modules.performance.loancustomer.dkkhyj.process;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.activiti.controller.ActTaskHandler;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.performance.loancustomer.dkkhghlsb.entity.Dkkhghlsb;
import org.cmms.modules.performance.loancustomer.dkkhghlsb.service.IDkkhghlsbService;
import org.cmms.modules.performance.loancustomer.dkkhspxx.entity.Dkkhspxx;
import org.cmms.modules.performance.loancustomer.dkkhspxx.service.IDkkhspxxService;

import java.util.ArrayList;
import java.util.List;

public class DkkhyjProcess extends ActTaskHandler {
    @Override
    public void doSubmit(BusinessInfoVo businessInfoVo) {
        //提交审批之后的操作
        //更新审批信息表中的状态
        IDkkhspxxService dkkhspxxService = SpringContextUtils.getBean(IDkkhspxxService.class);
        UpdateWrapper<Dkkhspxx> dkkhspxxUpdateWrapper = new UpdateWrapper<>();
        dkkhspxxUpdateWrapper.eq("table_id", businessInfoVo.getTableId());
        Dkkhspxx dkkhspxx = new Dkkhspxx();
        dkkhspxx.setProcessStatus("1");
        dkkhspxxService.update(dkkhspxx, dkkhspxxUpdateWrapper);
    }

    @Override
    public void doFinalPass(BusinessInfoVo businessInfoVo) {
        //全部审批通过之后的操作
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        IDkkhspxxService dkkhspxxService = SpringContextUtils.getBean(IDkkhspxxService.class);
        IDkkhghlsbService dkkhghlsbService = SpringContextUtils.getBean(IDkkhghlsbService.class);
        String tableId = businessInfoVo.getTableId();

        List<Dkkhghlsb> dkkhghlsbInsertList = new ArrayList<>();
        LambdaQueryWrapper<Dkkhspxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dkkhspxx::getTableId, tableId);
        List<Dkkhspxx> dkkhspxxList = dkkhspxxService.list(queryWrapper);

        dkkhspxxList.forEach(spxx-> {
            //更新原管户人的结束日期
            UpdateWrapper<Dkkhghlsb> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", spxx.getId());
            Dkkhghlsb dkkhghlsb = new Dkkhghlsb();
            dkkhghlsb.setJsrq(DateUtil.addDays(spxx.getYjrq(), -1));
            dkkhghlsb.setLrbz(2);
            dkkhghlsb.setBz("移交给" + spxx.getGhr());
            dkkhghlsbService.update(dkkhghlsb, updateWrapper);

            Dkkhghlsb dkkhghlsbInsert = new Dkkhghlsb();
            dkkhghlsbInsert.setJgdm(spxx.getJgdm());
            dkkhghlsbInsert.setKhbh(spxx.getKhbh());
            dkkhghlsbInsert.setHth(spxx.getHth());
            if (spxx.getYwlx() == 2) {
                //管户移交
                dkkhghlsbInsert.setGhlx(2);
            } else if (spxx.getYwlx() == 4) {
                //包收移交
                dkkhghlsbInsert.setGhlx(3);
            }

            dkkhghlsbInsert.setGhr(spxx.getGhr());
            dkkhghlsbInsert.setGhbl(spxx.getGhbl());
            dkkhghlsbInsert.setKsrq(spxx.getYjrq());
            dkkhghlsbInsert.setLrbz(1);
            dkkhghlsbInsert.setLrr(loginUser.getUsername());
            dkkhghlsbInsert.setBz("从" + spxx.getYghr() + "移交");
            dkkhghlsbInsertList.add(dkkhghlsbInsert);
        });
        dkkhghlsbService.saveBatch(dkkhghlsbInsertList);
        LambdaUpdateWrapper<Dkkhspxx> dkkhspxxUpdateWrapper = new LambdaUpdateWrapper<>();
        dkkhspxxUpdateWrapper.eq(Dkkhspxx::getTableId, tableId);
        dkkhspxxUpdateWrapper.ne(Dkkhspxx::getProcessStatus, "3");
        Dkkhspxx dkkhspxx = new Dkkhspxx();
        dkkhspxx.setProcessStatus("2");
        dkkhspxx.setProcessInfo("流程完成");
        dkkhspxxService.update(dkkhspxx, dkkhspxxUpdateWrapper);

    }

}
