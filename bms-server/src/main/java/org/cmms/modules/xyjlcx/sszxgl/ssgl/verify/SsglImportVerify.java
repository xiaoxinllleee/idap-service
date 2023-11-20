package org.cmms.modules.xyjlcx.sszxgl.ssgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.controller.SsglController;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.service.ISsglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SsglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private ISsglService ssglService;
    @Autowired
    private IBwdksjmxService bwdksjmxService;
    @Autowired
    private IDjkdksjmxService djkdksjmxService;
    @Autowired
    private IDkzdkbService dkzdkbService;
    @Override
    public String[] getNeedVerifyFields() {
        return null;
    }

    @Override
    public void setNeedVerifyFields(String[] var1) {

    }

    @Override
    public ExcelVerifyHanlderResult verifyHandler(Object var1, String var2, Object var3) {
        ExcelVerifyHanlderResult result = new ExcelVerifyHanlderResult(true, "");
        Ssgl ssgl = (Ssgl) var1;
        if (StringUtils.isEmpty(ssgl.getZh())) {
            result.setSuccess(false);
            result.setMsg("账号/卡号不能为空");
            return result;
        }
            int a = 0;
            if (a == 0) {
                //表外贷款数据明细
                QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("dkzh", ssgl.getZh());
                Bwdksjmx bwdksjmx = bwdksjmxService.getOne(queryWrapper);
                if (bwdksjmx != null) {
                    a = 1;
                    ssgl.setYwjg(bwdksjmx.getJgdm());
                    ssgl.setJkrxm(bwdksjmx.getKhmc());
                    ssgl.setZjhm(bwdksjmx.getZjhm());
                    ssgl.setYe(bwdksjmx.getHxye());
                }
                if (a == 0) {
                    //贷记卡数据明细
                    QueryWrapper<Djkdksjmx> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("kh", ssgl.getZh());
                    Djkdksjmx djkdksjmx = djkdksjmxService.getOne(queryWrapper1);
                    if (djkdksjmx != null) {
                        a = 1;
                        ssgl.setYwjg(djkdksjmx.getYwjg());
                        ssgl.setJkrxm(djkdksjmx.getKhmc());
                        ssgl.setZjhm(djkdksjmx.getZjhm());
                        ssgl.setYe(djkdksjmx.getTzye());
                    }
                }
                if (a == 0) {
                    //贷款余额表
                    QueryWrapper<Dkzdkb> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq("acct_no", ssgl.getZh());
                    Dkzdkb dkzdkb = dkzdkbService.getOne(queryWrapper2);
                    if (dkzdkb != null) {
                        a = 1;
                        ssgl.setYwjg(dkzdkb.getBrNo());
                        ssgl.setJkrxm(dkzdkb.getCustName());
                        ssgl.setZjhm(dkzdkb.getIdentNo());
                        ssgl.setYe(dkzdkb.getLoanBal());
                    }

                }
            }
            //以上三张表都不存在该账号时，返回以下信息
            if (a == 0) {
                result.setSuccess(false);
                result.setMsg("账号错误或账号信息不存在！");
                return result;

        }
        QueryWrapper<Ssgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zh",ssgl.getZh());
        Ssgl ssgl1 = ssglService.getOne(queryWrapper);
        if (ssgl1 != null){
            ssglService.remove(queryWrapper);
        }
        return result;
    }
}
