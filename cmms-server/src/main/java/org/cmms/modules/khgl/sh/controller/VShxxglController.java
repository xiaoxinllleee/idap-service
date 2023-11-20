package org.cmms.modules.khgl.sh.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import org.cmms.modules.khgl.khhmc.service.IKhhmcService;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khgl.sh.entity.*;
import org.cmms.modules.khgl.sh.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.KhglKhhmcxx;
import org.cmms.modules.pad.nhxxgl.entity.NhJtcyxx;
import org.cmms.modules.pad.shxxgl.entity.Fxezh;
import org.cmms.modules.pad.shxxgl.entity.KhglShGxrxx;
import org.cmms.modules.pad.shxxgl.entity.Xjlghjc;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.service.IFxezhService;
import org.cmms.modules.pad.shxxgl.service.IXjlghjcService;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.ejyxdygl.service.IEjyxdyglService;
import org.cmms.modules.yxdygl.sjyxdygl.service.ISjyxdyglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 商户信息管理
 * @Author: jeecg-boot
 * @Date: 2020-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "商户信息管理")
@RestController
@RequestMapping("sh/vShxxgl")
public class VShxxglController extends JeecgController<VShxxgl, IVShxxglService> {
    @Autowired
    private IVShxxglService vShxxglService;
    @Autowired
    private IshhmcxxService shhmcxxService;
    @Autowired
    private ITjfxCsszService iTjfxcsszService;
    @Autowired
    private IEjyxdyglService iEjyxdyglService;
    @Autowired
    private ISjyxdyglService iSjyxdyglService;
    @Autowired
    private IShglrxxService shglrxxService;
    @Autowired
    private IShfcxxService shfcxxService;
    @Autowired
    private IShzllbService shzllbService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IShpjsxxxService shpjsxxxService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    private IDksjmxService dksjmxService;
    @Autowired
    private IEtcService etcService;
    @Autowired
    private ISjyhService sjyhService;
    @Autowired
    private IWsyhService wsyhService;
    @Autowired
    private IKhhmcService khhmcService;
    @Autowired
    private IYwhywwlxxService ywhywwlxxService;
    @Autowired
    private IShglYwhywwlxxService iShglYwhywwlxxService;
    @Autowired
    private IFxezhService fxezhService;
    @Autowired
    private IXykService xykService;
    @Autowired
    private IXjlghjcService xjlghjcService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    /**
     * 分页列表查询
     *
     * @param vShxxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "商户信息管理-分页列表查询")
    @ApiOperation(value = "商户信息管理-分页列表查询", notes = "商户信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VShxxgl vShxxgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<VShxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vShxxgl, req.getParameterMap());
        Page<VShxxgl> page = new Page<VShxxgl>(pageNo, pageSize);
        IPage<VShxxgl> pageList = vShxxglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param vShxxgl
     * @return
     */
    @AutoLog(value = "商户信息管理-添加")
    @ApiOperation(value = "商户信息管理-添加", notes = "商户信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody VShxxgl vShxxgl) {
        vShxxglService.save(vShxxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vShxxgl
     * @return
     */
    @AutoLog(value = "商户信息管理-编辑")
    @ApiOperation(value = "商户信息管理-编辑", notes = "商户信息管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody VShxxgl vShxxgl) {
        vShxxglService.updateById(vShxxgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "商户信息管理-通过id删除")
    @ApiOperation(value = "商户信息管理-通过id删除", notes = "商户信息管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        vShxxglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "商户信息管理-批量删除")
    @ApiOperation(value = "商户信息管理-批量删除", notes = "商户信息管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.vShxxglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "商户信息管理-通过id查询")
    @ApiOperation(value = "商户信息管理-通过id查询", notes = "商户信息管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        VShxxgl vShxxgl = vShxxglService.getById(id);
        return Result.ok(vShxxgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vShxxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VShxxgl vShxxgl) {
        return super.exportXls(request, vShxxgl, VShxxgl.class, "商户信息管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, VShxxgl.class);
  }*/

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户花名册导入模板");
        mv.addObject(NormalExcelConstants.CLASS, shhmcxxImport.class);
        ExportParams exportParams = new ExportParams("商户花名册导入模板", "模板信息");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<shhmcxxImport>());
        return mv;
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
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(true);

            InputStream fis = null;
            Workbook workbook = null;
            HSSFWorkbook newBook = null;
            try {
                log.info("开始导入：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                List<shhmcxx> shhmcxxList = ExcelImportUtil.importExcel(file, shhmcxx.class, params);
                log.info("导入完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                List<String> ids = new ArrayList<String>();
                List<shhmcxx> insertList = new ArrayList<shhmcxx>();
                fis = new FileInputStream(baseFilePath);

                newBook = new HSSFWorkbook(new POIFSFileSystem(fis));
                HSSFSheet sheet = newBook.getSheetAt(0);
                HSSFRow hssfRow = null;
                int rCi = 0, rCii = 0;
                int i = 2;
                Map<String, String> insertMap = new HashMap<String, String>();
                for (shhmcxx shhmcxx : shhmcxxList) {
                    hssfRow = sheet.getRow(i++);
                    if (rCi == 0) {
                        rCi = hssfRow.getLastCellNum();
                        rCii = rCi + 1;
                    }
                    HSSFCell resultCell = hssfRow.getCell(rCi);
                    if (resultCell == null) resultCell = hssfRow.createCell(rCi);
                    HSSFCell resultCellInfo = hssfRow.getCell(rCii);
                    if (resultCellInfo == null) resultCellInfo = hssfRow.createCell(rCii);

                    String result = "导入成功";
                    String resultInfo = "";


                    log.info("校验区域编码是否存在：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                    log.info("校验证件号码是否存在重复的记录：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                    //判断数据库是否存在重复的记录
					 /*QueryWrapper<shhmcxx> queryWrapper = new QueryWrapper<>();
					 queryWrapper.eq("yyzz",shhmcxx.getYyzz()).eq("tyshxydm",shhmcxx.getTyshxydm());
					 shhmcxx  shhmcExist =shhmcxxService.getOne(queryWrapper);
					 if (shhmcExist != null){
						 ids.add(shhmcExist.getId());
					 }*/
                    resultCell.setCellValue(result);
                    resultCellInfo.setCellValue(resultInfo);
                    insertList.add(shhmcxx);
                    log.info("校验完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                    //如果存在营业执照和统一社会信用代码都一致的数据，则删掉已经存在的数据，以导入为准
                }
                obj.put("filePath", filePath);
                log.info("删除已经存在的数据：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                //先删除已经存在的数据
				 /*if (!ids.isEmpty()) {
					 shhmcxxService.removeByIds(ids);
				 }*/
                log.info("保存数据：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                FileOutputStream fos = new FileOutputStream(baseFilePath);
                newBook.write(fos);
                fos.flush();
                fos.close();
                shhmcxxService.saveBatch(insertList);
                log.info("保存数据完成：" + DateUtil.formatDateTime("yyyy-MM-dd HH:mm:ss"));
                //提取数据
                //khhmcService.initKhhmcxx();

                return Result.ok("文件导入成功！数据行数:" + shhmcxxList.size() + "，导入成功行数：" + insertList.size() + "，失败行数：" + (shhmcxxList.size() - insertList.size()), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Throwable ignored) {
                    }
                }
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 附件信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/img", method = RequestMethod.GET)
    public Result<?> img(@RequestParam(name = "id", required = true) String id) {
        JSONObject json = new JSONObject();

        try {
            Map<String, String[]> map = new HashMap<>();
            JSONArray brsfzArray = new JSONArray();
            JSONArray posfzArray = new JSONArray();
            JSONArray jhzArray = new JSONArray();
            JSONArray rxzpArray = new JSONArray();
            JSONArray fwpzArray = new JSONArray();
            JSONArray qzpzArray = new JSONArray();
            JSONArray qczpArray = new JSONArray();
            JSONArray qtzcArray = new JSONArray();
            JSONArray jycsArray = new JSONArray();
            JSONArray cjzpzArray = new JSONArray();

            Shzllb shzllb = new Shzllb();
            shzllb.setShid(id);
            QueryWrapper<Shzllb> queryWrapper = QueryGenerator.initQueryWrapper(shzllb, map);
            List<Shzllb> fjglList = shzllbService.list(queryWrapper);
            for (Shzllb fjgl1 : fjglList) {
                if (fjgl1.getZllx() != null) {
                    JSONObject jsonObject = new JSONObject();
                    if (fjgl1.getZllx().equals("1")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        brsfzArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("2")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        posfzArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("3")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        jhzArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("4")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        rxzpArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("5")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        fwpzArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("6")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        qzpzArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("7")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        qczpArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("8")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        qtzcArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("9")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        jycsArray.add(jsonObject);
                    } else if (fjgl1.getZllx().equals("10")) {
                        jsonObject.put("uid", fjgl1.getShid());
                        jsonObject.put("name", fjgl1.getZlmc());
                        jsonObject.put("status", "done");
                        jsonObject.put("url", fjgl1.getFwlj());
                        cjzpzArray.add(jsonObject);
                    }
                }
            }
            json.put("brsfzArray", brsfzArray);
            json.put("posfzArray", posfzArray);
            json.put("jhzArray", jhzArray);
            json.put("rxzpArray", rxzpArray);
            json.put("fwpzArray", fwpzArray);
            json.put("qzpzArray", qzpzArray);
            json.put("qczpArray", qczpArray);
            json.put("qtzcArray", qtzcArray);
            json.put("jycsArray", jycsArray);
            json.put("cjzpzArray", cjzpzArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(json);
    }

    @GetMapping(value = "/query")
    public Result<?> query(@Param("shid") String shid) {
        JSONObject jsonObject = new JSONObject();
        try {
            //查询商户花名册信息
            QueryWrapper<shhmcxx> shhmcxxQueryWrapper = new QueryWrapper<>();
            shhmcxxQueryWrapper.eq("id", shid);
            shhmcxx shhmcxx = shhmcxxService.getOne(shhmcxxQueryWrapper);
            //查询关联人信息
            QueryWrapper<Shglrxx> shglrxxQueryWrapper = new QueryWrapper<>();
            shglrxxQueryWrapper.eq("sh_id", shid);
            List<Shglrxx> khglShglrxxList = shglrxxService.list(shglrxxQueryWrapper);
            for (Shglrxx shglrxx : khglShglrxxList) {
                shglrxx.setXb(shglrxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", shglrxx.getXb()));
                shglrxx.setHyzk(shglrxx.getHyzk() == null ? " " : sysDictService.queryDictTextByKey("hyzk", shglrxx.getHyzk()));
            }
            //查询附件信息
            QueryWrapper<Shzllb> shzllbQueryWrapper = new QueryWrapper<>();
            shzllbQueryWrapper.eq("shid", shid);
            List<Shzllb> shzllbList = shzllbService.list(shzllbQueryWrapper);

            //查询房产信息
            QueryWrapper<Shfcxx> shfcxxQueryWrapper = new QueryWrapper<>();
            shfcxxQueryWrapper.eq("shid", shid);
            List<Shfcxx> shfcxxList = shfcxxService.list(shfcxxQueryWrapper);



            //查询评级授信信息
            QueryWrapper<Shpjsxxx> shpjsxxxQueryWrapper = new QueryWrapper<>();
            shpjsxxxQueryWrapper.eq("shid", shid);
            Shpjsxxx shpjsxxx = shpjsxxxService.getOne(shpjsxxxQueryWrapper);

            Map<String,Object> fcxxmap = shfcxxService.queryFcxx(shid);
            Double fcjz = 0.0;
            if ( fcxxmap != null){
                BigDecimal fcjz1 =(BigDecimal)fcxxmap.get("FCJZ");
                fcjz =  fcjz1.doubleValue();
            }

            Map<String,Object> pjsxmap = shpjsxxxService.queryZcxx(shid);
            Double zcqk = 0.0;
            Double fzqk = 0.0;

            if (pjsxmap !=null){
                if (pjsxmap.get("FCJZ") != null){
                    BigDecimal zcqk1 =(BigDecimal)pjsxmap.get("ZCQK");
                    zcqk = zcqk1.doubleValue();
                }
                if (pjsxmap.get("FZQK") != null){
                    BigDecimal fzqk1 =(BigDecimal)pjsxmap.get("FZQK");
                    fzqk = fzqk1.doubleValue();
                }
            }

            double jtzzc =  fcjz + zcqk ;

            jsonObject.put("fz",fzqk);
            jsonObject.put("jtzzc",jtzzc);

            //获取资产信息
            JSONArray shzcxx = new JSONArray();
            JSONArray shfzxx = new JSONArray();
            if (shpjsxxx != null) {
                //资产情况
                JSONObject dcJo = new JSONObject();
                dcJo.put("zclx", "地产");
                dcJo.put("zcsl", shpjsxxx.getDcsl());
                dcJo.put("zcjz", shpjsxxx.getDcjz());
                dcJo.put("zcsm", shpjsxxx.getDcxqsm());
                JSONObject jtgjJo = new JSONObject();
                jtgjJo.put("zclx", "交通工具");
                jtgjJo.put("zcsl", shpjsxxx.getJtgjsl());
                jtgjJo.put("zcjz", shpjsxxx.getJtgjjz());
                jtgjJo.put("zcsm", shpjsxxx.getJtgjxqsm());
                JSONObject ckJo = new JSONObject();
                ckJo.put("zclx", "存款");
                ckJo.put("zcsl", shpjsxxx.getCksl());
                ckJo.put("zcjz", shpjsxxx.getCkjz());
                ckJo.put("zcsm", shpjsxxx.getCkxqsm());
                JSONObject yjdzJo = new JSONObject();
                yjdzJo.put("zclx", "有价单证");
                yjdzJo.put("zcsl", shpjsxxx.getYjdzsl());
                yjdzJo.put("zcjz", shpjsxxx.getYjdzjz());
                yjdzJo.put("zcsm", shpjsxxx.getYjdzxqsm());
                JSONObject gqJo = new JSONObject();
                gqJo.put("zclx", "股权");
                gqJo.put("zcsl", shpjsxxx.getGqsl());
                gqJo.put("zcjz", shpjsxxx.getGqjz());
                gqJo.put("zcsm", shpjsxxx.getGqxqsm());
                JSONObject qtzcJo = new JSONObject();
                qtzcJo.put("zclx", "其他资产");
                qtzcJo.put("zcsl", shpjsxxx.getQtzcsl());
                qtzcJo.put("zcjz", shpjsxxx.getQtzcjz());
                qtzcJo.put("zcsm", shpjsxxx.getQtzcxqsm());
                JSONObject nzsrJo = new JSONObject();
                nzsrJo.put("zclx", "年总收入");
                nzsrJo.put("zcsl", shpjsxxx.getNzsrsl());
                nzsrJo.put("zcjz", shpjsxxx.getNzsrjz());
                nzsrJo.put("zcsm", shpjsxxx.getNzsrxqsm());
                shzcxx.add(dcJo);
                shzcxx.add(jtgjJo);
                shzcxx.add(ckJo);
                shzcxx.add(yjdzJo);
                shzcxx.add(gqJo);
                shzcxx.add(qtzcJo);
                shzcxx.add(nzsrJo);
                //负债情况
                JSONObject bxtJo = new JSONObject();
                bxtJo.put("jkfs", "本系统");
                bxtJo.put("zqr", shpjsxxx.getBxtjkzqr());
                bxtJo.put("jkje", shpjsxxx.getBxtjksl());
                bxtJo.put("jksm", shpjsxxx.getBxtjkxqsm());
                JSONObject thJo = new JSONObject();
                thJo.put("jkfs", "他行");
                thJo.put("zqr", shpjsxxx.getThjkzqr());
                thJo.put("jkje", shpjsxxx.getThjksl());
                thJo.put("jksm", shpjsxxx.getThjkxqsm());
                JSONObject xykJo = new JSONObject();
                xykJo.put("jkfs", "信用卡");
                xykJo.put("zqr", shpjsxxx.getXykzqr());
                xykJo.put("jkje", shpjsxxx.getXyksl());
                xykJo.put("jksm", shpjsxxx.getXykxqsm());
                JSONObject qtfzJo = new JSONObject();
                qtfzJo.put("jkfs", "其他负债");
                qtfzJo.put("zqr", shpjsxxx.getQtfzzqr());
                qtfzJo.put("jkje", shpjsxxx.getQtfzsl());
                qtfzJo.put("jksm", shpjsxxx.getQtfzxqsm());
                JSONObject jtnkzJo = new JSONObject();
                jtnkzJo.put("jkfs", "家庭年开支");
                jtnkzJo.put("zqr", shpjsxxx.getJtnkzzqr());
                jtnkzJo.put("jkje", shpjsxxx.getJtnkzsl());
                jtnkzJo.put("jksm", shpjsxxx.getJtnkzxqsm());

                shfzxx.add(bxtJo);
                shfzxx.add(thJo);
                shfzxx.add(xykJo);
                shfzxx.add(qtfzJo);
                shfzxx.add(jtnkzJo);
            }
            if (shhmcxx.getTyshxydm() != null) {
                //业务往来信息
                QueryWrapper<ShglYwhywwlxx> shglYwhywwlxxQueryWrapper = new QueryWrapper<>();
                shglYwhywwlxxQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
                List<ShglYwhywwlxx> shglYwhywwlxxList = iShglYwhywwlxxService.list(shglYwhywwlxxQueryWrapper);

                //贷款业务信息
                QueryWrapper<Dksjmx> dksjmxQueryWrapper = new QueryWrapper<>();
                dksjmxQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
                List<Dksjmx> dksjmxList = dksjmxService.list(dksjmxQueryWrapper);
                for (Dksjmx dksjmx : dksjmxList) {
                    dksjmx.setDyzrr(dksjmx.getDyzrr() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getDyzrr()));
                    dksjmx.setKhjlbz(dksjmx.getKhjlbz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getKhjlbz()));
                    dksjmx.setDkpz(dksjmx.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmx.getDkpz()));
                    dksjmx.setDkxt(dksjmx.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", dksjmx.getDkxt()));
                    dksjmx.setDbfs(dksjmx.getDbfs() ==null ? ""  : sysDictService.queryDictTextByKey("dbfs", dksjmx.getDbfs()));
                }


                //手机银行
                QueryWrapper<Sjyh> sjyhQueryWrapper = new QueryWrapper<>();
                sjyhQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
                List<Sjyh> sjyhList = sjyhService.list(sjyhQueryWrapper);
                for (Sjyh sjyh : sjyhList) {
                    sjyh.setCancelGyh(sjyh.getCancelGyh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", sjyh.getCancelGyh()));
                    sjyh.setStatus(sjyh.getStatus() == null ? "" :sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyh.getStatus()));
                    sjyh.setOpenType(sjyh.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", sjyh.getOpenType()));
                }


                //ETC
                QueryWrapper<Etc> etcQueryWrapper = new QueryWrapper<>();
                etcQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
                List<Etc> etcList = etcService.list(etcQueryWrapper);



                if (shhmcxx.getFrzjhm()!=null){
                    //福祥E支付
                    QueryWrapper<Fxezh> fxezhQueryWrapper= new QueryWrapper<>();
                    fxezhQueryWrapper.eq("drzjhm",shhmcxx.getFrzjhm());
                    List<Fxezh> fxezhList = fxezhService.list(fxezhQueryWrapper);

                    //信用卡
                    QueryWrapper<Xyk> xykQueryWrapper= new QueryWrapper<>();
                    xykQueryWrapper.eq("zjhm",shhmcxx.getFrzjhm());
                    List<Xyk> xykList = xykService.list(xykQueryWrapper);

                    //现金流归行检测
                    QueryWrapper<Khhmc> khhmcxxQueryWrapper = new QueryWrapper<>();
                    khhmcxxQueryWrapper.eq("zjhm",shhmcxx.getFrzjhm());
                    Khhmc khhmcxx = khhmcService.getOne(khhmcxxQueryWrapper);

                    if (khhmcxx != null){
                        List<Xjlghjc> xjlghjcList = xjlghjcService.queryXjlGhjc(khhmcxx.getHhbm());
                        jsonObject.put("xjlghjcList",xjlghjcList);
                    }

                    jsonObject.put("fxezhList",fxezhList);
                    jsonObject.put("xykList",xykList);
                }else {
                    List<Xyk> xykList = new ArrayList<>();
                    List<Fxezh> fxezhList = new ArrayList<>();
                    List<Xjlghjc> xjlghjcList = new ArrayList<>();
                    jsonObject.put("fxezhList",fxezhList);
                    jsonObject.put("xykList",xykList);
                    jsonObject.put("xjlghjcList",xjlghjcList);
                }

                jsonObject.put("ywwlxxList", shglYwhywwlxxList);
                jsonObject.put("dksjmxList", dksjmxList);
                jsonObject.put("sjyhList", sjyhList);
                jsonObject.put("etcList", etcList);
            }
            jsonObject.put("glrxxList", khglShglrxxList);
            jsonObject.put("shzllbList", shzllbList);
            jsonObject.put("shfcxxList", shfcxxList);
            jsonObject.put("shpjsx", shpjsxxx);
            jsonObject.put("shzcxx", shzcxx);
            jsonObject.put("shfzxx", shfzxx);

            return Result.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }

    }


    //根据法人证件号码查询法人家庭信息
    @GetMapping(value = "/selectByFrzjhm")
    public Result<?> selectByFrzjhm(@Param("frzjhm") String frzjhm) {
        List<Khhmc> list = khhmcService.selectByFrzjhm(frzjhm);
        JSONObject jsonObject = new JSONObject();
        List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
        for (int i = 0; i < list.size(); i++) {
            NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
            BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
            String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
            nhJtcyxx1.setYhzgx(yhzgx);
            String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
            nhJtcyxx1.setXb(xb);
            nhJtcyxx.add(nhJtcyxx1);
            List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
            if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                if (nhJtcyxx.size() > 0) {
                    ywhywwlxxes.get(0).setId(list.get(i).getId());
                    BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
                }
            }
        }
        jsonObject.put("list", nhJtcyxx);
        return Result.ok(jsonObject);
    }

    //根据法人证件号码查询法人家庭信息
    @GetMapping(value = "/queryShjbxx")
    public Result<?> queryShjbxx(@Param("id") String id) {
        QueryWrapper<VShxxgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        VShxxgl vShxxgl = vShxxglService.getOne(queryWrapper);
        return Result.ok(vShxxgl);
    }
}
