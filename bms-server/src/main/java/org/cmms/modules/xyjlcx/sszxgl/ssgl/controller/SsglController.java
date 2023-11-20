package org.cmms.modules.xyjlcx.sszxgl.ssgl.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.SsglVO;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.mapper.SsglMapper;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.service.ISsglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.xyjlcx.sszxgl.ssgl.verify.SsglImportVerify;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.entity.Zxgl;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.service.IZxglService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 诉讼管理
 * @Author: jeecg-boot
 * @Date: 2021-08-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "诉讼管理")
@RestController
@RequestMapping("/ssgl/ssgl")
public class SsglController extends JeecgController<Ssgl, ISsglService> {
    @Autowired
    private ISsglService ssglService;
    @Autowired
    private IZxglService zxglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private IBwdksjmxService bwdksjmxService;
    @Autowired
    private IDjkdksjmxService djkdksjmxService;
    @Autowired
    private IDkzdkbService dkzdkbService;
    @Autowired
    private SsglImportVerify ssglImportVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ssgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "诉讼管理-分页列表查询")
    @ApiOperation(value = "诉讼管理-分页列表查询", notes = "诉讼管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Ssgl ssgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Ssgl>> result = new Result<IPage<Ssgl>>();
        QueryWrapper<Ssgl> queryWrapper = QueryGenerator.initQueryWrapper(ssgl, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ISsglService.class,ssglService,pageNo,pageSize,queryWrapper,"zh");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 诉讼管理 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
		Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
			// `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_ssglyegx");
            // count_credit_ssglyegx
			boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
			result.setSuccess(completionSignal);
		} else {
			try {
				ssglService.pSsgl();
				result.setSuccess(true);
				return result;
			} catch (Throwable e) {
				log.error("信用记录查询 / 诉讼管理 / 提取失败！"+e.getMessage());
				result.setSuccess(false);
			}
		}
		return result;
    }

    /**
     * 诉讼管理 / 判断账号是否存在
     *
     * @param zh
     * @return
     */
    @RequestMapping(value = "/judgeCkzh", method = RequestMethod.GET)
    public Result<?> judgeCkzh(@Param("kh") String zh) {
        int a = 0;
        //表外贷款数据明细
        QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh", zh);
        Bwdksjmx bwdksjmx = bwdksjmxService.getOne(queryWrapper);
        if (bwdksjmx != null) {
            a = 1;
            HashMap<String, String> map = new HashMap<>();
            map.put("ywjg", bwdksjmx.getJgdm());
            map.put("jkrxm", bwdksjmx.getKhmc());
            map.put("zjhm", bwdksjmx.getZjhm());
            map.put("je", String.valueOf(bwdksjmx.getJkje()));
            map.put("ye", String.valueOf(bwdksjmx.getHxye()));
            return Result.ok(map);

        }
        if (a == 0) {
            //贷记卡数据明细
            QueryWrapper<Djkdksjmx> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("kh", zh);
            Djkdksjmx djkdksjmx = djkdksjmxService.getOne(queryWrapper1);
            if (djkdksjmx != null) {
                a = 1;
                HashMap<String, String> map = new HashMap<>();
                map.put("ywjg", djkdksjmx.getYwjg());
                map.put("jkrxm", djkdksjmx.getKhmc());
                map.put("zjhm", djkdksjmx.getZjhm());
                map.put("je", String.valueOf(djkdksjmx.getSxje()));
                map.put("ye", String.valueOf(djkdksjmx.getTzye()));
                return Result.ok(map);
            }
        }
        if (a == 0) {
            //贷款余额表
            QueryWrapper<Dkzdkb> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("acct_no", zh);
            Dkzdkb dkzdkb = dkzdkbService.getOne(queryWrapper2);
            if (dkzdkb != null) {
                a = 1;
                HashMap<String, String> map = new HashMap<>();
                map.put("ywjg", dkzdkb.getBrNo());
                map.put("jkrxm", dkzdkb.getCustName());
                map.put("zjhm", dkzdkb.getIdentNo());
                map.put("je", String.valueOf(dkzdkb.getAdvVal()));
                map.put("ye", String.valueOf(dkzdkb.getLoanBal()));
                return Result.ok(map);
            }
        }
        return Result.error("账号/卡号不存在，请核实！");
    }

    /**
     * 诉讼管理 / 添加
     *
     * @param ssgl
     * @return
     */
    @AutoLog(value = "诉讼管理-添加")
    @ApiOperation(value = "诉讼管理-添加", notes = "诉讼管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Ssgl ssgl) {
        QueryWrapper<Ssgl> ssglQueryWrapper = new QueryWrapper<>();
        ssglQueryWrapper.eq("zh",ssgl.getZh());
        Ssgl ssgl1 = ssglService.getOne(ssglQueryWrapper);
        if (ssgl1 != null){
            return Result.error("账号信息已存在,请勿重复添加！");
        }
        ssgl.setLrr(getLoginUser().getRealname());
        ssgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        ssgl.setLrbz(1);
        ssglService.save(ssgl);
        return Result.ok("添加成功！");
    }

    /**
     * 诉讼管理 / 编辑
     *
     * @param ssgl
     * @return
     */
    @AutoLog(value = "诉讼管理-编辑")
    @ApiOperation(value = "诉讼管理-编辑", notes = "诉讼管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Ssgl ssgl) {
        QueryWrapper<Ssgl> queryWrapper = new QueryWrapper<Ssgl>();
        queryWrapper.eq("zh", ssgl.getZh());
        List<Ssgl> ssglList = ssglService.list(queryWrapper);
        if (ssglList.isEmpty()){
            return Result.error("该账号信息不存在！");
        }
        Ssgl updateSsgl = ssglList.get(0);
        UpdateWrapper<Ssgl> ssglUpdateWrapper = new UpdateWrapper<>();
        ssglUpdateWrapper.eq("zh",ssgl.getZh());
        //表主键不能更新
        updateSsgl.setZh(null);
        updateSsgl.setHgscrq(ssgl.getHgscrq());
        updateSsgl.setZhlxr(ssgl.getZhlxr());
        updateSsgl.setJe(ssgl.getJe());
        updateSsgl.setBsqzxr(ssgl.getBsqzxr());
        updateSsgl.setQsrq(ssgl.getQsrq());
        updateSsgl.setSxflwsh(ssgl.getSxflwsh());
        updateSsgl.setAjslf(ssgl.getAjslf());
        updateSsgl.setLsf(ssgl.getLsf());
        updateSsgl.setKhdwzxyq(ssgl.getKhdwzxyq());
        updateSsgl.setBz(ssgl.getBz());
        updateSsgl.setLrr(getLoginUser().getRealname());
        updateSsgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        updateSsgl.setLrbz(2);
        ssglService.update(updateSsgl,ssglUpdateWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 诉讼管理 / 添加执行信息
     *
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/addZxxx")
    public Result<?> addZxxx(@RequestBody JSONObject jsonObject){
        String ywjg = jsonObject.getString("ywjg");
        String zjhm = jsonObject.getString("zjhm");
        String jkrxm = jsonObject.getString("jkrxm");
        String zh = jsonObject.getString("zh");
        Date sqzxrq = jsonObject.getDate("sqzxrq");
        String zxbj = jsonObject.getString("zxbj");
        String zxlx = jsonObject.getString("zxlx");
        String dzxje = jsonObject.getString("dzxje");
        String zxah = jsonObject.getString("zxah");
        String dqzxfy = jsonObject.getString("dqzxfy");
        String kgzxcce = jsonObject.getString("kgzxcce");
        String dydbr = jsonObject.getString("dydbr");
        String bz = jsonObject.getString("bz");
        BigDecimal bj = new BigDecimal(zxbj);
        BigDecimal lx = new BigDecimal(zxlx);
        BigDecimal je = new BigDecimal(dzxje);
        Zxgl zxgl = new Zxgl();
        zxgl.setYwjg(ywjg);
        zxgl.setZjhm(zjhm);
        zxgl.setJkrxm(jkrxm);
        zxgl.setZh(zh);
        zxgl.setSqzxrq(sqzxrq);
        zxgl.setZxbj(bj);
        zxgl.setZxlx(lx);
        zxgl.setDzxje(je);
        zxgl.setZxah(zxah);
        zxgl.setDqzxfy(dqzxfy);
        zxgl.setKgzxcce(kgzxcce);
        zxgl.setDydbr(dydbr);
        zxgl.setBz(bz);
        zxgl.setLrbz(1);
        zxgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        zxgl.setLrr(getLoginUser().getUsername());
        zxglService.save(zxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 诉讼管理 / 删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "诉讼管理-删除")
    @ApiOperation(value = "诉讼管理-删除", notes = "诉讼管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("zh") String zh) {
        QueryWrapper<Ssgl> queryWrapper = new QueryWrapper<Ssgl>();
        queryWrapper.eq("zh", zh);
        ssglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ssgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Ssgl ssgl) {
        return super.exportXls(request, ssgl, Ssgl.class, "诉讼管理");
    }

    /**
     * 导出模板
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, Ssgl.class,SsglVO.class, ssglImportVerify);
    }

}
