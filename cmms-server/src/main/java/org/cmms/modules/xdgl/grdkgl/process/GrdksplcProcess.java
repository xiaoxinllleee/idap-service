package org.cmms.modules.xdgl.grdkgl.process;

import org.cmms.modules.activiti.controller.ActTaskHandler;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.activiti.vo.BusinessInfoVo;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkcjxxService;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.springframework.beans.factory.annotation.Autowired;

public class GrdksplcProcess extends ActTaskHandler {
    @Autowired
    private IActXendSplsService iActXendSplsService;

    @Autowired
    private IGrdkcjxxService grdkcjxxService;

    @Override
    public void doPass(BusinessInfoVo businessInfoVo) {
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

        grdkcjxxService.updateSxed(businessInfoVo.getPddj(), businessInfoVo.getJysxed(),
                businessInfoVo.getSpyj(), businessInfoVo.getZjhm());
        grdkcjxxService.updateSpjled(businessInfoVo.getPddj(), businessInfoVo.getJysxed(),
                businessInfoVo.getSpyj(), businessInfoVo.getSpId());
    }
}
