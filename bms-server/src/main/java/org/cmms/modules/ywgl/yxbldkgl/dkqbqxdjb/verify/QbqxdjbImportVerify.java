package org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.entity.Qbqxdjb;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.service.IDkQbqxdjbService;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.vo.QbqxdjbImportVO;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class QbqxdjbImportVerify implements IExcelVerifyHandler {

    @Autowired
    private IDkQbqxdjbService iDkQbqxdjbService;
    @Autowired
    private IDkzdkbService iDkzdkbService;
    @Autowired
    private ILsdksjglService iLsdksjglService;

    @Override
    public void setNeedVerifyFields(String[] arr) {}

    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    /**
     *
     * @param obj 当前对象
     * @param columnname 当前字段名称
     * @param columnvalue 当前值
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object obj, String columnname, Object columnvalue) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        Date dataDate = getCurrentDataDate("yyyy-MM-dd");

        QbqxdjbImportVO table = (QbqxdjbImportVO) obj;
        Date dataDate = table.getDataDate();
//        System.out.println("当前字段名称: "+columnname);
//        System.out.println("当前值: "+columnvalue);

//        QueryWrapper<Dkzdkb> cbsbormbaseQW = new QueryWrapper<>();
//        cbsbormbaseQW.eq("acct_no", table.getAcctNo());
//        Dkzdkb cbsbormbase = iDkzdkbService.getOne(cbsbormbaseQW,false);
//        if (cbsbormbase == null) {
//            QueryWrapper<Lsdksjgl> erpbaslsdksjQW = new QueryWrapper<>();
//            erpbaslsdksjQW.eq("acct_no", table.getAcctNo());
//            Lsdksjgl erpbaslsdksj = iLsdksjglService.getOne(erpbaslsdksjQW,false);
//            if (erpbaslsdksj == null) {
//                result.setMsg("贷款账号不存在，请核实！");
//                result.setSuccess(false);
//                return result;
//            }
//        }

        // 导入时贷款类型设置为 普通贷款
        table.setDklx(1);
        // 导入时状态标识默认为0，已结清则在后面处理为1
        table.setZtbs(0);
        table.setLrbz(0);
        table.setLrr(loginUser.getUsername());
        table.setLrsj(new Date());

        //@ExcelVerify(****,interHandler = true)可能会导致字段值无法写入，此处手工SET值
//        table.setJxzzr(DateUtil.string2Date(columnvalue.toString(),"yyyyMMdd"));

        // 若导入数据已存在，则以最新数据为准。
//        QueryWrapper<Qbqxdjb> yxbldkdkqbqxdjbQW = new QueryWrapper<>();
//        yxbldkdkqbqxdjbQW.eq("data_date", dataDate);
//        yxbldkdkqbqxdjbQW.eq("acct_no",table.getAcctNo());
//        yxbldkdkqbqxdjbQW.eq("qs", table.getQs());
//        Qbqxdjb yxbldkdkqbqxdjb = iDkQbqxdjbService.getOne(yxbldkdkqbqxdjbQW,false);
//        if (yxbldkdkqbqxdjb != null) {
//            iDkQbqxdjbService.remove(yxbldkdkqbqxdjbQW);
//        }

        // 若原数据存在（数据日期、账号、期数）条目在导入数据中不存在，则更新原数据状态标识为已结清
//        yxbldkdkqbqxdjbQW = new QueryWrapper<>();
//        yxbldkdkqbqxdjbQW.eq("data_date", dataDate);
//        yxbldkdkqbqxdjbQW.eq("acct_no", table.getAcctNo());
//        yxbldkdkqbqxdjbQW.ne("qs", table.getQs());
//        yxbldkdkqbqxdjb = iDkQbqxdjbService.getOne(yxbldkdkqbqxdjbQW,false);
//        if (yxbldkdkqbqxdjb != null) {
//            Qbqxdjb qbqxdjb = new Qbqxdjb();
//            qbqxdjb.setZtbs(1);
//            qbqxdjb.setLrbz(2);
//            qbqxdjb.setLrsj(new Date());
//            UpdateWrapper<Qbqxdjb> updateWrapper = new UpdateWrapper();
//            updateWrapper.eq("data_date", dataDate);
//            updateWrapper.eq("acct_no", yxbldkdkqbqxdjb.getAcctNo());
//            updateWrapper.eq("qs", yxbldkdkqbqxdjb.getQs());
//            iDkQbqxdjbService.update(qbqxdjb, updateWrapper);
//        }

        return result;
    }

    //判断当前系统日期是否为指定日期
    public static boolean isThisDay(Long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    //获取指定日期: 当前月份21日
    public static Date getCurrentDataDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date currentDataDate = DateUtil.string2Date(sdf.format(new Date()).substring(0, 8) + "21", pattern);
        return currentDataDate;
    }
}
