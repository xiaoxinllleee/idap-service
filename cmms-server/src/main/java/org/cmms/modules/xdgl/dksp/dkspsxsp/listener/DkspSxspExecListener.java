package org.cmms.modules.xdgl.dksp.dkspsxsp.listener;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.cmms.modules.xdgl.dksp.dkspsxsp.entity.DkspSxsp;
import org.cmms.modules.xdgl.dksp.dkspsxsp.service.IDkspSxspService;

/**
 * @author Exrickx
 */
@Slf4j
public class DkspSxspExecListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("============dkspsxsp executionListener start============");
        // 获取关联业务表ID变量(启动流程代码里已存入tableId，此处直接获取即可)
        String tableId = (String) execution.getVariable("tableId");
        String currentActivityId = execution.getCurrentActivityId();
        String currentActivityName = execution.getCurrentActivityName();
        String eventName = execution.getEventName();
        String processBusinessKey = execution.getProcessBusinessKey();

        //获取贷款种类与贷款金额
        IDkspSxspService dkspSxspService = SpringUtil.getBean(IDkspSxspService.class);
        DkspSxsp dkspSxsp = dkspSxspService.getById(tableId);
//        execution.setVariable("dkje", dkspKhzc.getDkje());
//        execution.setVariable("dkzl", dkspKhzc.getDkzl());
        execution.setVariable("zzbz",dkspSxsp.getZzbz());
//        System.out.println("=====executionListener===tableId===" + tableId);
//        System.out.println("=====executionListener===currentActivityId===" + currentActivityId);
//        System.out.println("=====executionListener===currentActivityName===" + currentActivityName);
//        System.out.println("=====executionListener===eventName===" + eventName);
//        System.out.println("=====executionListener===processBusinessKey===" + processBusinessKey);
        System.out.println("============dkspsxsp executionListener end============");
//        System.out.println(dkspSxsp);
//        System.out.println(execution);
        // 监听器中设置流程变量示例
        // delegateTask.setVariable("xboot", "xboot");
    }
}
