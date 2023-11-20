package org.cmms.modules.khxxgl.khywxx.qhywxx.controller;

import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.base.entity.CkmxTable;
import org.cmms.common.system.base.entity.DkmxTable;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DictTextToValusUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.khgl.khxx.entity.KhywxxEtcPc;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khgl.khxx.entity.KhywxxWsyhPc;
import org.cmms.modules.khgl.khxx.service.IKhywxxEtcPcService;
import org.cmms.modules.khgl.khxx.service.IKhywxxSjyhPcService;
import org.cmms.modules.khgl.khxx.service.IKhywxxWsyhPcService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.Qhywxx;
import org.cmms.modules.khxxgl.khywxx.qhywxx.service.IQhywxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSbk;
import org.cmms.modules.pad.nhxxgl.service.IKhywxxSbkService;
import org.cmms.modules.pad.shxxgl.entity.Fxezh;
import org.cmms.modules.pad.shxxgl.entity.XykVO;
import org.cmms.modules.pad.shxxgl.service.IFxezhService;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.sjxf.qtxt.djkxt.kpzlb.service.IKpzlbService;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.WgxxtjVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.IWgxxtjService;

import org.cmms.modules.tjfx.wgtjfx.wgywtj.entity.ZnzdVo;
import org.cmms.modules.tjfx.wgtjfx.wgywtj.service.IWgywtjZnzdService;
import org.cmms.modules.tjfx.wgywsjtj.service.IVKhxxglTjfxWgywsjtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cmms.common.constant.CommonConstant;

/**
 * @Description: 全行业务信息
 * @Author: jeecg-boot
 * @Date: 2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "全行业务信息")
@RestController
@RequestMapping("/qhywxx/qhywxx")
public class QhywxxController extends JeecgController<Qhywxx, IQhywxxService> {
    @Autowired
    private IQhywxxService qhywxxService;
    @Autowired
    IKhywxxSjyhPcService khywxxSjyhPcService;
    @Autowired
    IKhywxxWsyhPcService khywxxWsyhPcService;
    @Autowired
    IKhywxxEtcPcService khywxxEtcPcService;
    @Autowired
    IXykService xykService;
    @Autowired
    IFxezhService fxezhService;
    @Autowired
    IKpzlbService kpzlbService;
    @Autowired
    private IWgxxtjService wgxxtjService;
    @Autowired
    IKhywxxSbkService khywxxSbkService;
    @Autowired
    private IVKhxxglTjfxWgywsjtjService wgywsjtjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IWgywtjZnzdService wgywtjZnzdService;

    /**
     * 分页列表查询
     *
     * @param qhywxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "全行业务信息-分页列表查询")
    @ApiOperation(value = "全行业务信息-分页列表查询", notes = "全行业务信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qhywxx qhywxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qhywxx> queryWrapper = QueryGenerator.initQueryWrapper(qhywxx, req.getParameterMap());
        Page<Qhywxx> page = new Page<Qhywxx>(pageNo, pageSize);
        IPage<Qhywxx> pageList = qhywxxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qhywxx
     * @return
     */
    @AutoLog(value = "全行业务信息-添加")
    @ApiOperation(value = "全行业务信息-添加", notes = "全行业务信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Qhywxx qhywxx) {
        qhywxxService.save(qhywxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param qhywxx
     * @return
     */
    @AutoLog(value = "全行业务信息-编辑")
    @ApiOperation(value = "全行业务信息-编辑", notes = "全行业务信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Qhywxx qhywxx) {
        qhywxxService.updateById(qhywxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行业务信息-通过id删除")
    @ApiOperation(value = "全行业务信息-通过id删除", notes = "全行业务信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        qhywxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "全行业务信息-批量删除")
    @ApiOperation(value = "全行业务信息-批量删除", notes = "全行业务信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.qhywxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行业务信息-通过id查询")
    @ApiOperation(value = "全行业务信息-通过id查询", notes = "全行业务信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Qhywxx qhywxx = qhywxxService.getById(id);
        return Result.ok(qhywxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qhywxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qhywxx qhywxx) {
        return super.exportXls(request, qhywxx, Qhywxx.class, "全行业务信息");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Qhywxx.class);
    }


    @RequestMapping("/getYwsjBySjrq")
    public Result<?> getYwsjBySjrq(String rq, String wgbh, String type, String jqlx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
        if (StringUtils.isBlank(rq))
            return Result.error("数据日期不能为空");
        rq = rq.replaceAll("-", "");
        if (StringUtils.isBlank(wgbh))
            return Result.error("网格编号不能为空");
        Page<Qhywxx> page = new Page<Qhywxx>(pageNo, pageSize);
        IPage ywsjBySjrq = service.getYwsjBySjrq(page, rq, wgbh, type, jqlx,qydm);
        return Result.ok(ywsjBySjrq);
    }

    @RequestMapping("/getWktYwsjBySjrq")
    public Result<?> getWktYwsjBySjrq(String rq, String wgbh, String type, String jqlx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        if (StringUtils.isBlank(rq))
            return Result.error("数据日期不能为空");
        rq = rq.replaceAll("-", "");
        if (StringUtils.isBlank(wgbh))
            return Result.error("网格编号不能为空");
        Page<Qhywxx> page = new Page<Qhywxx>(pageNo, pageSize);
        IPage wktYwsjBySjrq = service.getWktYwsjBySjrq(page, rq, wgbh, type, jqlx);
        return Result.ok(wktYwsjBySjrq);
    }

    @RequestMapping("/getYwsjBySjyh")
    public Result<?> getYwsjBySjyh(String wgbh, String type,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        String qybm = getRedisQydm();
        Page<KhywxxSjyhPc> page = new Page<KhywxxSjyhPc>(pageNo, pageSize);
        if (StringUtils.isBlank(wgbh))
            return Result.error("网格编号不能为空");
        if ("sjyh".equals(type)) {
            IPage<KhywxxSjyhPc> byWgbh = khywxxSjyhPcService.getByWgbh(page, Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if ("wsyh".equals(type)) {
            IPage<KhywxxWsyhPc> byWgbh = khywxxWsyhPcService.getByWgbh(page, Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if ("etc".equals(type)) {
            IPage<KhywxxEtcPc> byWgbh = khywxxEtcPcService.getByWgbh(page, Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if ("xyk".equals(type)) {
            IPage<XykVO> byWgbh = xykService.getByWgbh(page, Arrays.asList(wgbh.split(",")));
//			 List<XykVO> xykVOList = byWgbh.getRecords();
//			 if (xykVOList != null && !xykVOList.isEmpty()) {
//				 xykVOList = xykVOList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(XykVO::getCustrNbr))), ArrayList::new));
//				 byWgbh.setRecords(xykVOList);
//			 }
            return Result.ok(byWgbh);
        }
        if ("fxezf".equals(type)) {
            IPage<Fxezh> byWgbh=null;
            if ("095".equals(qybm)){
                byWgbh = fxezhService.getByWgbhTy(page, Arrays.asList(wgbh.split(",")));
            }else{
                byWgbh = fxezhService.getByWgbh(page, wgbh);
            }
            return Result.ok(byWgbh);
        }
        if ("tpjjch".equals(type)) {
            IPage<WgxxtjVo> pageList = wgxxtjService.tpjjch(page, Arrays.asList(wgbh.split(",")));
            return Result.ok(pageList);
        }
        if ("whsbk".equals(type)) {
            Page<KhywxxSbk> sbkPage = new Page<KhywxxSbk>(pageNo, pageSize);
            IPage<KhywxxSbk> byWgbh = khywxxSbkService.getByWgbh(sbkPage, Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if("znzd".equals(type)){
            String tableName = wgywtjZnzdService.getNnzdTableName();
            if (StringUtils.isNotBlank(tableName)){
                Page<ZnzdVo> znzdVoPage=new Page<>(pageNo,pageSize);
                IPage<ZnzdVo> znzdVoIPageList=wgywtjZnzdService.getZnzdInfoByWgbh(znzdVoPage,tableName,Arrays.asList(wgbh.split(",")));
                return Result.ok(znzdVoIPageList);
            }
            return Result.ok();
        }

        return Result.ok();
    }

    @RequestMapping("/getWktYwsjBySjyh")
    public Result<?> getWktYwsjBySjyh(String wgbh, String type,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<KhywxxSjyhPc> page = new Page<KhywxxSjyhPc>(pageNo, pageSize);
        if (StringUtils.isBlank(wgbh))
            return Result.error("网格编号不能为空");
        if ("sjyh".equals(type)) {
            IPage<Nhxq> byWgbh = khywxxSjyhPcService.getWktByWgbh(page,Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if ("wsyh".equals(type)) {
            IPage<Nhxq> byWgbh = khywxxWsyhPcService.getWktByWgbh(page, Arrays.asList(wgbh.split(",")));
            return Result.ok(byWgbh);
        }
        if ("xyk".equals(type)) {
            IPage<Nhxq> byWgbh = xykService.getWktByWgbh(page, Arrays.asList(wgbh.split(",")));
//			 List<XykVO> xykVOList = byWgbh.getRecords();
//			 if (xykVOList != null && !xykVOList.isEmpty()) {
//				 xykVOList = xykVOList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(XykVO::getCustrNbr))), ArrayList::new));
//				 byWgbh.setRecords(xykVOList);
//			 }
            return Result.ok(byWgbh);
        }
        if ("whsbk".equals(type)) {
            Page<Nhxq> sbkPage = new Page<Nhxq>(pageNo, pageSize);
            IPage<Nhxq> byWgbh = qhywxxService.getSbkWktByWgbh(sbkPage, wgbh);
            return Result.ok(byWgbh);
        }

        return Result.ok();
    }

    @RequestMapping("/mxdc")
    public void mxdc(String rq, String wgbh, String type, String jqlx
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        if ("4".equals(type) || "1".equals(type) || "2".equals(type) || "3".equals(type)) {
            rq = rq.replaceAll("-", "");
            List<Qhywxx> listYwsjBySjrq = service.getListYwsjBySjrq(rq, wgbh, type, jqlx);

            if ("4".equals(type)) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("存款信息明细");
                CkmxTable ckmxTable = new CkmxTable(workbook, sheet);
                ckmxTable.setTableName("存款信息明细");
                ckmxTable.setTableHeader();
                if (CollUtil.isNotEmpty(listYwsjBySjrq)) {
                    List<List<String>> ckmxList = getCkmxList(listYwsjBySjrq);
                    ckmxTable.setTableData(ckmxList);
                }
                workbookWrite(request, response, "存款信息明细", workbook);
            } else if ("1".equals(type) || "2".equals(type) || "3".equals(type)) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("贷款信息明细");
                DkmxTable ckmxTable = new DkmxTable(workbook, sheet);
                ckmxTable.setTableName("贷款信息明细");
                ckmxTable.setTableHeader();
                if (CollUtil.isNotEmpty(listYwsjBySjrq)) {
                    List<List<String>> ckmxList = getDkmxList(listYwsjBySjrq);
                    ckmxTable.setTableData(ckmxList);
                }
                workbookWrite(request, response, "贷款信息明细", workbook);
            }
        } else if ("sjyh".equals(type)){
            List<KhywxxSjyhPc> byWgbhList = khywxxSjyhPcService.getByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("手机银行明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"开户机构"
                    ,"开户日期"
                    ,"开户类型"
                    ,"销户姓名"
                    ,"状态"
                    ,"本月交易金额"
                    ,"本月交易笔数"
                    ,"本年交易金额"
                    ,"本年交易笔数"
                    ,"历史交易金额"
                    ,"历史交易笔数"
            );
            simpleStandardTable.setTableName("手机银行明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getSjyhList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "手机银行明细", workbook);
        } else if ("wsyh".equals(type)){
            List<KhywxxWsyhPc> byWgbhList = khywxxWsyhPcService.getByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("网上银行明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"开户机构"
                    ,"开户日期"
                    ,"开户类型"
                    ,"销户姓名"
            );
            simpleStandardTable.setTableName("网上银行明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getWsyhList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "网上银行明细", workbook);
        } else if ("etc".equals(type)){
            List<KhywxxEtcPc> byWgbh = khywxxEtcPcService.getByWgbh(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("ETC明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"操作网点"
                    ,"绑定时间"
                    ,"操作人员"
                    ,"账户类型"
                    ,"开户机构"
                    ,"账号"
                    ,"车牌号码"
                    ,"状态"
                    ,"预解绑日期"
            );
            simpleStandardTable.setTableName("ETC明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbh)){
                List<List<String>> sjyhList = getEtcList(byWgbh);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "ETC明细", workbook);
        } else if ("xyk".equals(type)){
            List<XykVO> byWgbhList = xykService.getByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("信用卡明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"发卡日期"
                    ,"卡号"
            );
            simpleStandardTable.setTableName("信用卡明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getXykList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "信用卡明细", workbook);
        } else if ("fxezf".equals(type)){
            List<Fxezh> byWgbhList = fxezhService.getByWgbhList(wgbh);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("福祥E支付明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "商户名称"
                    ,"商户类型"
                    ,"法人代表姓名"
                    ,"法人代表证件号"
                    ,"商户联系电话"
                    ,"营业地区"
                    ,"商户入网状态"
                    ,"交易状态"
                    ,"渠道"
            );
            simpleStandardTable.setTableName("福祥E支付明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getFxezfList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "福祥E支付明细", workbook);
        } else if ("sbkbh".equals(type)) {
            List<Kjbxx> byWgbhList = wgywsjtjService.getKjbxxListByWgbh(wgbh);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("我行社保卡明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "卡号"
                    ,"证件号码"
                    ,"客户名称"
                    ,"状态"
                    ,"电话号码"
                    ,"地址"
            );
            simpleStandardTable.setTableName("我行社保卡明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getSbkwhList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "我行社保卡明细", workbook);
        } else if ("sbkwb".equals(type)) {
            List<WgxxtjVo> byWgbhList = wgxxtjService.wbsbk(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("外部社保卡明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户名称"
                    ,"证件号码"
                    ,"银行名称"
                    ,"银行卡号"
                    ,"卡状态"
                    ,"医保用卡记录"
                    ,"疑似风险卡"
                    ,"联系电话"
                    ,"联系地址"
                    ,"单位"
                    ,"网点名称"
            );
            simpleStandardTable.setTableName("外部社保卡明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getSbkwbList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "外部社保卡明细", workbook);
        }
    }

    //未开通明细导出
    @RequestMapping("/wktmxdc")
    public void wktmxdc(String rq, String wgbh, String type, String jqlx
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        if ("4".equals(type) || "1".equals(type) || "2".equals(type) || "3".equals(type)) {
            rq = rq.replaceAll("-", "");
            List<Nhxq> listYwsjBySjrq = service.getListWktYwsjBySjrq(rq, wgbh, type, jqlx);

            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"性别"
                    ,"年龄"
                    ,"联系方式"
                    ,"地址"
            );
            if ("4".equals(type)) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("存款未开通明细");
                SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);

                simpleStandardTable.setTableName("存款未开通明细", list.size());
                simpleStandardTable.setTableHeader(list);
                if (CollUtil.isNotEmpty(listYwsjBySjrq)) {
                    List<List<String>> ckmxList = getWktmxList(listYwsjBySjrq);
                    simpleStandardTable.setTableData(ckmxList);
                }
                workbookWrite(request, response, "存款未开通明细", workbook);
            } else if ("1".equals(type) || "2".equals(type) || "3".equals(type)) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("贷款未开通明细");
                SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
                simpleStandardTable.setTableName("贷款未开通明细", list.size());
                simpleStandardTable.setTableHeader(list);
                if (CollUtil.isNotEmpty(listYwsjBySjrq)) {
                    List<List<String>> ckmxList = getWktmxList(listYwsjBySjrq);
                    simpleStandardTable.setTableData(ckmxList);
                }
                workbookWrite(request, response, "贷款未开通明细", workbook);
            }
        } else if ("sjyh".equals(type)){
            List<Nhxq> byWgbhList = khywxxSjyhPcService.getWktByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("手机银行未开通明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"性别"
                    ,"年龄"
                    ,"联系方式"
                    ,"地址"
            );
            simpleStandardTable.setTableName("手机银行未开通明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getWktmxList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "手机银行未开通明细", workbook);
        } else if ("wsyh".equals(type)){
            List<Nhxq> byWgbhList = khywxxWsyhPcService.getWktByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("网上银行未开通明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"性别"
                    ,"年龄"
                    ,"联系方式"
                    ,"地址"
            );
            simpleStandardTable.setTableName("网上银行未开通明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getWktmxList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "网上银行未开通明细", workbook);
        } else if ("xyk".equals(type)){
            List<Nhxq> byWgbhList = xykService.getWktByWgbhList(Arrays.asList(wgbh.split(",")));
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("信用卡未开通明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"性别"
                    ,"年龄"
                    ,"联系方式"
                    ,"地址"
            );
            simpleStandardTable.setTableName("信用卡未开通明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getWktmxList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "信用卡未开通明细", workbook);
        } else if ("sbkwkt".equals(type)){
            List<Nhxq> byWgbhList = qhywxxService.getSbkWktListByWgbh(wgbh);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("社保卡未开通明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
            List<String> list = Lists.newArrayList(
                    "客户姓名"
                    ,"证件号码"
                    ,"性别"
                    ,"年龄"
                    ,"联系方式"
                    ,"地址"
            );
            simpleStandardTable.setTableName("社保卡未开通明细",list.size());
            simpleStandardTable.setTableHeader(list);
            if (CollUtil.isNotEmpty(byWgbhList)){
                List<List<String>> sjyhList = getSbkwktList(byWgbhList);
                simpleStandardTable.setTableData(sjyhList);
            }
            workbookWrite(request, response, "社保卡未开通明细", workbook);
        }

    }

    public List<List<String>> getWktmxList(List<Nhxq> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Nhxq nhxq = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(nhxq.getKhmc())) {
                colList.add(nhxq.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(nhxq.getZjhm())) {
                colList.add(nhxq.getZjhm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getXb())) {
                colList.add(DictTextToValusUtil.xb(nhxq.getXb()));
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getNl())) {
                colList.add(nhxq.getNl());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getSjhm())) {
                colList.add(nhxq.getSjhm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getHjdz())) {
                colList.add(nhxq.getHjdz());
            } else {
                colList.add("");
            }
            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public List<List<String>> getCkmxList(List<Qhywxx> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Qhywxx qhywxx = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(qhywxx.getKhmc())) {
                colList.add(qhywxx.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(qhywxx.getZjhm())) {
                colList.add(qhywxx.getZjhm());
            } else {
                colList.add("");
            }

            if (qhywxx.getCkye() != null) {
                colList.add(qhywxx.getCkye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getCkrpye() != null) {
                colList.add(qhywxx.getCkrpye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getCknrpye() != null) {
                colList.add(qhywxx.getCknrpye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getHqckye() != null) {
                colList.add(qhywxx.getHqckye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getHqckrpye() != null) {
                colList.add(qhywxx.getHqckrpye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getHqcknrpye() != null) {
                colList.add(qhywxx.getHqcknrpye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getDqckye() != null) {
                colList.add(qhywxx.getDqckye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getDqckrpye() != null) {
                colList.add(qhywxx.getDqckrpye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getDqcknrpye() != null) {
                colList.add(qhywxx.getDqcknrpye().toString());
            } else {
                colList.add("");
            }
            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public List<List<String>> getDkmxList(List<Qhywxx> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Qhywxx qhywxx = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(qhywxx.getKhmc())) {
                colList.add(qhywxx.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(qhywxx.getZjhm())) {
                colList.add(qhywxx.getZjhm());
            } else {
                colList.add("");
            }

            if (qhywxx.getDkje() != null) {
                colList.add(qhywxx.getDkje().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getDkye() != null) {
                colList.add(qhywxx.getDkye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getZjdkdqrq() != null) {
                Date zjdkdqrq = qhywxx.getZjdkdqrq();
                String format = DateUtil.format(zjdkdqrq, DatePattern.NORM_DATE_PATTERN);
                colList.add(format);
            } else {
                colList.add("");
            }

            if (qhywxx.getBldkye() != null) {
                colList.add(qhywxx.getBldkye().toString());
            } else {
                colList.add("");
            }

            if (qhywxx.getBwbldkye() != null) {
                colList.add(qhywxx.getBwbldkye().toString());
            } else {
                colList.add("");
            }


            listArrayList.add(colList);
        }
        return listArrayList;
    }
    public List<List<String>> getSjyhList(List<KhywxxSjyhPc> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            KhywxxSjyhPc entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZjhm())) {
                colList.add(entity.getZjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getOpenJgdm())) {
                colList.add(entity.getOpenJgdm());
            } else {
                colList.add("");
            }

            if (entity.getOpenDate() != null) {
                Date openDate = entity.getOpenDate();
                String format = DateUtil.format(openDate, DatePattern.NORM_DATE_PATTERN);
                colList.add(format);
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getOpenType())) {
                colList.add(entity.getOpenType());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getCancelGyh())) {
                colList.add(entity.getCancelGyh());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getStatus())) {
                colList.add(entity.getStatus());
            } else {
                colList.add("");
            }

            if (entity.getJyjeBy() != null) {
                colList.add(entity.getJyjeBy().toString());
            } else {
                colList.add("");
            }

            if (entity.getJybsBy() != null) {
                colList.add(entity.getJybsBy().toString());
            } else {
                colList.add("");
            }

            if (entity.getJyjeBn() != null) {
                colList.add(entity.getJyjeBn().toString());
            } else {
                colList.add("");
            }
            if (entity.getJybsBn() != null) {
                colList.add(entity.getJybsBn().toString());
            } else {
                colList.add("");
            }

            if (entity.getJyjeLs() != null) {
                colList.add(entity.getJyjeLs().toString());
            } else {
                colList.add("");
            }
            if (entity.getJybsLs() != null) {
                colList.add(entity.getJybsLs().toString());
            } else {
                colList.add("");
            }



            listArrayList.add(colList);
        }
        return listArrayList;
    }
    public List<List<String>> getWsyhList(List<KhywxxWsyhPc> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            KhywxxWsyhPc entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZjhm())) {
                colList.add(entity.getZjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getOpenJgdm())) {
                colList.add(entity.getOpenJgdm());
            } else {
                colList.add("");
            }

            if (entity.getOpenDate() != null) {
                Date openDate = entity.getOpenDate();
                String format = DateUtil.format(openDate, DatePattern.NORM_DATE_PATTERN);
                colList.add(format);
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getOpenType())) {
                colList.add(entity.getOpenType());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getCancelGyh())) {
                colList.add(entity.getCancelGyh());
            } else {
                colList.add("");
            }
            listArrayList.add(colList);
        }
        return listArrayList;
    }
    public List<List<String>> getEtcList(List<KhywxxEtcPc> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            KhywxxEtcPc entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZjhm())) {
                colList.add(entity.getZjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getOpenJgdm())) {
                colList.add(entity.getOpenJgdm());
            } else {
                colList.add("");
            }

            if (entity.getWorkDate() != null) {
                Date openDate = entity.getWorkDate();
                String format = DateUtil.format(openDate, DatePattern.NORM_DATE_PATTERN);
                colList.add(format);
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getOperYggh())) {
                colList.add(entity.getOperYggh());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZhlx())) {
                colList.add(entity.getZhlx());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getOpenJgdm())) {
                colList.add(entity.getOpenJgdm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getZh())) {
                colList.add(entity.getZh());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getCphm())) {
                colList.add(entity.getCphm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getStatus())) {
                colList.add(entity.getStatus());
            } else {
                colList.add("");
            }

            if (entity.getYjbrq() != null) {
                Date openDate = entity.getYjbrq();
                String format = DateUtil.format(openDate, DatePattern.NORM_DATE_PATTERN);
                colList.add(format);
            } else {
                colList.add("");
            }
            listArrayList.add(colList);
        }
        return listArrayList;
    }
    public List<List<String>> getXykList(List<XykVO> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            XykVO entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getCustrNbr())) {
                colList.add(entity.getCustrNbr());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getIssueDay())) {
                colList.add(entity.getIssueDay());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getCardNbr())) {
                colList.add(entity.getCardNbr());
            } else {
                colList.add("");
            }


            listArrayList.add(colList);
        }
        return listArrayList;
    }
    public List<List<String>> getFxezfList(List<Fxezh> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Fxezh entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getShmc())) {
                colList.add(entity.getShmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getShlx())) {
                colList.add(entity.getShlx());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getFrdbxx())) {
                colList.add(entity.getFrdbxx());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getDrzjhm())) {
                colList.add(entity.getDrzjhm());
            } else {
                colList.add("");
            }


            if (StringUtils.isNotBlank(entity.getShlxdh())) {
                colList.add(entity.getShlxdh());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getYydq())) {
                colList.add(entity.getYydq());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getSsrwzt())) {
                colList.add(entity.getSsrwzt());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getJyzt())) {
                colList.add(entity.getJyzt());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getQd())) {
                colList.add(entity.getQd());
            } else {
                colList.add("");
            }

            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public List<List<String>> getSbkwhList(List<Kjbxx> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Kjbxx entity = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(entity.getNo())) {
                colList.add(entity.getNo());
            } else {
                colList.add("");
            }


            if (StringUtils.isNotBlank(entity.getCustomer())) {
                colList.add(entity.getCustomer());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getName1())) {
                colList.add(entity.getName1());
            } else {
                colList.add("");
            }

            if (entity.getStatus() != null) {
                colList.add(String.valueOf(entity.getStatus()));
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getPhone())) {
                colList.add(entity.getPhone());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getAddress1())) {
                colList.add(entity.getAddress1());
            } else {
                colList.add("");
            }



            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public List<List<String>> getSbkwbList(List<WgxxtjVo> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            WgxxtjVo entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZjhm())) {
                colList.add(entity.getZjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getYhmc())) {
                colList.add(entity.getYhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getYhkh())) {
                colList.add(entity.getYhkh());
            } else {
                colList.add("");
            }


            if (StringUtils.isNotBlank(entity.getKzt())) {
                colList.add(entity.getKzt());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getYbykjl())) {
                colList.add(entity.getYbykjl());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getYsfxk())) {
                colList.add(entity.getYsfxk());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getLxdh())) {
                colList.add(entity.getLxdh());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getLxdz())) {
                colList.add(entity.getLxdz());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getDw())) {
                colList.add(entity.getDw());
            } else {
                colList.add("");
            }


            if (StringUtils.isNotBlank(entity.getWdmc())) {
                colList.add(entity.getWdmc());
            } else {
                colList.add("");
            }


            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public List<List<String>> getSbkwktList(List<Nhxq> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Nhxq entity = list.get(i);
            List<String> colList = Lists.newArrayList();
            if (StringUtils.isNotBlank(entity.getKhmc())) {
                colList.add(entity.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getZjhm())) {
                colList.add(entity.getZjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getXb())) {
                colList.add(DictTextToValusUtil.xb(entity.getXb()));
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getNl())) {
                colList.add(entity.getNl());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getSjhm())) {
                colList.add(entity.getSjhm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(entity.getHjdz())) {
                colList.add(entity.getHjdz());
            } else {
                colList.add("");
            }

            listArrayList.add(colList);
        }
        return listArrayList;
    }
}
