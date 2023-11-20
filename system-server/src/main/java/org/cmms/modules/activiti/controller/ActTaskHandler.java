package org.cmms.modules.activiti.controller;

import org.cmms.modules.activiti.vo.BusinessInfoVo;

public abstract class ActTaskHandler {
    //提交审批
    public void doSubmit(BusinessInfoVo businessInfoVo) {

    }

    //每步通过
    public void doPass(BusinessInfoVo businessInfoVo) {
        //子类实现
    }

    //最终通过
    public void doFinalPass(BusinessInfoVo businessInfoVo) {

    }
}
