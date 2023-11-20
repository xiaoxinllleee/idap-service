package org.cmms.modules.xyjlcx.sszxgl.zxgl.verify;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.entity.Zxgl;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.service.IZxglService;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZxglImportVerify implements IExcelVerifyHandler {
    @Autowired
    private IZxglService zxglService;
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
        Zxgl zxgl = (Zxgl) var1;
        if (StringUtils.isEmpty(zxgl.getZh())) {
            result.setSuccess(false);
            result.setMsg("账号/卡号不能为空");
            return result;
        }
            int a = 0;
            if (a == 0) {
                //表外贷款数据明细
                QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("dkzh", zxgl.getZh());
                Bwdksjmx bwdksjmx = bwdksjmxService.getOne(queryWrapper);
                if (bwdksjmx != null) {
                    a = 1;
                    zxgl.setYwjg(bwdksjmx.getJgdm());
                    zxgl.setJkrxm(bwdksjmx.getKhmc());
                    zxgl.setZjhm(bwdksjmx.getZjhm());
                }
                if (a == 0) {
                    //贷记卡数据明细
                    QueryWrapper<Djkdksjmx> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("kh", zxgl.getZh());
                    Djkdksjmx djkdksjmx = djkdksjmxService.getOne(queryWrapper1);
                    if (djkdksjmx != null) {
                        a = 1;
                        zxgl.setYwjg(djkdksjmx.getYwjg());
                        zxgl.setJkrxm(djkdksjmx.getKhmc());
                        zxgl.setZjhm(djkdksjmx.getZjhm());
                    }
                }
                if (a == 0) {
                    //贷款余额表
                    QueryWrapper<Dkzdkb> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq("acct_no", zxgl.getZh());
                    Dkzdkb dkzdkb = dkzdkbService.getOne(queryWrapper2);
                    if (dkzdkb != null) {
                        a = 1;
                        zxgl.setYwjg(dkzdkb.getBrNo());
                        zxgl.setJkrxm(dkzdkb.getCustName());
                        zxgl.setZjhm(dkzdkb.getIdentNo());
                    }

                }
            }
            //以上三张表都不存在该账号时，返回以下信息
            if (a == 0) {
                result.setSuccess(false);
                result.setMsg("账号错误或账号信息不存在！");
                return result;

        }
        QueryWrapper<Zxgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zh",zxgl.getZh());
        Zxgl zxgl1 = zxglService.getOne(queryWrapper);
        if (zxgl1 != null){
            zxglService.remove(queryWrapper);
        }
        return result;
    }
}
