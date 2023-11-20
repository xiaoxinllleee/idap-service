package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.process;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.activiti.controller.ActTaskHandler;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglLsxx;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglSpxx;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglLsxxService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service.IEtcyxxxglSpxxService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtcyxxxglYjProcess extends ActTaskHandler {
    @Override
    public void doFinalPass(BusinessInfoVo businessInfoVo) {
        //反射获取的对象，不能通过Autowired注入，通过Spring上下文获取
        IEtcyxxxglSpxxService etcyxxxglSpxxService = SpringContextUtils.getBean(IEtcyxxxglSpxxService.class);
        IEtcyxxxglService etcyxxxglService = SpringContextUtils.getBean(IEtcyxxxglService.class);
        IEtcyxxxglLsxxService etcyxxxglLsxxService = SpringContextUtils.getBean(IEtcyxxxglLsxxService.class);
        //最终审批通过之后
        //更新原营销人为新营销人
        String tableId = businessInfoVo.getTableId();
        String [] str = tableId.split(";");
        String businessNumber = str[0];
        String processId = str[1];
        LambdaQueryWrapper<EtcyxxxglSpxx> spxxQueryWrapper = new LambdaQueryWrapper<>();
        spxxQueryWrapper.eq(EtcyxxxglSpxx::getBusinessnumber, businessNumber);
        spxxQueryWrapper.eq(EtcyxxxglSpxx::getProcessid, processId);
        List<EtcyxxxglSpxx> spxxList = etcyxxxglSpxxService.listNewTrans(spxxQueryWrapper);
        List<EtcyxxxglLsxx> lsxxList = new ArrayList<>();
        spxxList.forEach(spxx-> {
            //更新正式表营销人
            Etcyxxxgl etcyxxxgl = new Etcyxxxgl();
            etcyxxxgl.setYxrgh(spxx.getXyxrgh());
            etcyxxxgl.setYxjgdm(spxx.getXyxjgdm());
            etcyxxxgl.setXgsj(new Date());
            etcyxxxgl.setXgr(businessInfoVo.getUserName());
            LambdaUpdateWrapper<Etcyxxxgl> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Etcyxxxgl::getYywdjgm, spxx.getYywdjgm());
            updateWrapper.eq(Etcyxxxgl::getCphm, spxx.getCphm());
            updateWrapper.eq(Etcyxxxgl::getKhsfzh, spxx.getKhsfzh());
            updateWrapper.eq(Etcyxxxgl::getYxrgh, spxx.getYyxrgh());
            etcyxxxglService.updateNewTrans(etcyxxxgl, updateWrapper);

            //存入历史表
            EtcyxxxglLsxx lsxx = new EtcyxxxglLsxx();
            lsxx.setYywdjgm(spxx.getYywdjgm());
            lsxx.setBlqd(spxx.getBlqd());
            lsxx.setKhxm(spxx.getKhxm());
            lsxx.setCphm(spxx.getCphm());
            lsxx.setCpys(spxx.getCpys());
            lsxx.setKhsfzh(spxx.getKhsfzh());
            lsxx.setGlzrrgh(spxx.getYyxrgh());
            lsxx.setGlsjz(new Date());
            lsxx.setLrbz("1");
            lsxx.setLrr(businessInfoVo.getUserName());
            lsxxList.add(lsxx);
        });
        etcyxxxglLsxxService.saveBatchNewTrans(lsxxList);
        //更新审批表流程状态
        EtcyxxxglSpxx etcyxxxglSpxx = new EtcyxxxglSpxx();
        etcyxxxglSpxx.setLczt("流程完成");
        LambdaUpdateWrapper<EtcyxxxglSpxx> spxxLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        spxxLambdaUpdateWrapper.eq(EtcyxxxglSpxx::getProcessid, processId);
        spxxLambdaUpdateWrapper.eq(EtcyxxxglSpxx::getBusinessnumber, businessNumber);
        etcyxxxglSpxxService.updateNewTrans(etcyxxxglSpxx, spxxLambdaUpdateWrapper);
    }
}
