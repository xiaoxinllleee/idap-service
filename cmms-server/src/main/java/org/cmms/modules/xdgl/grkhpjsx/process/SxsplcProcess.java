package org.cmms.modules.xdgl.grkhpjsx.process;

import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.activiti.controller.ActTaskHandler;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.springframework.beans.factory.annotation.Autowired;

public class SxsplcProcess extends ActTaskHandler {
    @Override
    public void doPass(BusinessInfoVo businessInfoVo) {
        //反射获取的对象，不能通过Autowired注入，通过Spring上下文获取
        IActXendSplsService iActXendSplsService = SpringContextUtils.getBean(IActXendSplsService.class);
        IGrkhpjsxService grkhpjsxService = SpringContextUtils.getBean(IGrkhpjsxService.class);

        ActXendSpls spls= new ActXendSpls();
        spls.setSpid(businessInfoVo.getSpId());
        spls.setProcInstId(businessInfoVo.getProcInstId());
        spls.setTaskid(businessInfoVo.getTaskId());
        spls.setHhbm(businessInfoVo.getHhbm());
        spls.setZjhm(businessInfoVo.getZjhm());
        spls.setJyed(businessInfoVo.getJysxed());
        spls.setPddj(businessInfoVo.getPddj());
        spls.setSpyj(businessInfoVo.getSpyj());
        spls.setUserid(businessInfoVo.getUserId());
        spls.setYggh(businessInfoVo.getYggh());
        iActXendSplsService.save(spls);

        grkhpjsxService.updateGrpjsxed(businessInfoVo.getPddj(), businessInfoVo.getJysxed(),businessInfoVo.getSpyj(), businessInfoVo.getHhbm());
        grkhpjsxService.updateGrpjsxjled(businessInfoVo.getPddj(), businessInfoVo.getJysxed(), businessInfoVo.getSpyj() ,businessInfoVo.getSpId());
    }
}
