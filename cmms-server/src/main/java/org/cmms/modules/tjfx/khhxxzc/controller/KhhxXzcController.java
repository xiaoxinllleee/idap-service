package org.cmms.modules.tjfx.khhxxzc.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SshUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.khhxxzc.entity.KhhxXzc;
import org.cmms.modules.tjfx.khhxxzc.service.IKhhxXzcService;
import org.cmms.modules.tjfx.xdbmdhz.entity.XdbmdhzXzz;
import org.cmms.modules.tjfx.xdbmdhz.service.IXdbmdhzXzzService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.WordUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户画像_行政村
 * @Author: jeecg-boot
 * @Date: 2022-07-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户画像_行政村")
@RestController
@RequestMapping("/tjfx/khhxXzc")
public class KhhxXzcController extends JeecgController<KhhxXzc, IKhhxXzcService> {
    @Autowired
    private IKhhxXzcService khhxXzcService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IXdbmdhzXzzService xdbmdhzXzzService;

    /**
     * 分页列表查询
     *
     * @param khhxXzc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户画像_行政村-分页列表查询")
    @ApiOperation(value = "客户画像_行政村-分页列表查询", notes = "客户画像_行政村-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhhxXzc khhxXzc,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<KhhxXzc> queryWrapper = QueryGenerator.initQueryWrapper(khhxXzc, req.getParameterMap());
        Page<KhhxXzc> page = new Page<KhhxXzc>(pageNo, pageSize);
        IPage<KhhxXzc> pageList = khhxXzcService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param khhxXzc
     * @return
     */
    @AutoLog(value = "客户画像_行政村-添加")
    @ApiOperation(value = "客户画像_行政村-添加", notes = "客户画像_行政村-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhhxXzc khhxXzc) {
        khhxXzcService.save(khhxXzc);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khhxXzc
     * @return
     */
    @AutoLog(value = "客户画像_行政村-编辑")
    @ApiOperation(value = "客户画像_行政村-编辑", notes = "客户画像_行政村-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhhxXzc khhxXzc) {
        khhxXzcService.updateById(khhxXzc);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户画像_行政村-通过id删除")
    @ApiOperation(value = "客户画像_行政村-通过id删除", notes = "客户画像_行政村-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khhxXzcService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户画像_行政村-批量删除")
    @ApiOperation(value = "客户画像_行政村-批量删除", notes = "客户画像_行政村-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khhxXzcService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户画像_行政村-通过id查询")
    @ApiOperation(value = "客户画像_行政村-通过id查询", notes = "客户画像_行政村-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhhxXzc khhxXzc = khhxXzcService.getById(id);
        return Result.ok(khhxXzc);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khhxXzc
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhhxXzc khhxXzc) {
        return super.exportXls(request, khhxXzc, KhhxXzc.class, "客户画像_行政村");
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
        return super.importExcel(request, response, KhhxXzc.class);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/extract")
    public Result<?> extract() {
        Result result = new Result<>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<XdbmdhzXzz> queryWrapper=new QueryWrapper<>();
                xdbmdhzXzzService.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khgl_nhhzxxgl","khxxgl_ywhywwlxx_h","cams_zcsx_nhbkbpy","khxxgl_khxq_nh").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true,false,false, item, "idap");
                });
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python tjfxczxxtj.py'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_CZXXTJ_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_CZXXTJ_IMPORT.sh");
            }
            khhxXzcService.init();
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/download")
    public void download(KhhxXzc khhxXzc, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        OutputStream pdfOutPutStream = null;
        try {
            Map<String, Object> data = new HashMap<>();
            QueryWrapper<KhhxXzc> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("wgbh", khhxXzc.getWgbh());
            List<KhhxXzc> khhxXzcList = khhxXzcService.list(queryWrapper);
            outputStream = response.getOutputStream();
            if (!khhxXzcList.isEmpty()) {
                KhhxXzc khhxXzcData = khhxXzcList.get(0);
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(khhxXzcData, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
                jsonObject.put("wgmc", sysDictService.queryTableDictTextByKey("yxdygl_main", "wgmc", "wgbh", khhxXzcData.getWgbh()));
                data.put("data", jsonObject);


                String docFileName = IdUtil.simpleUUID() + "客户画像报告.docx";
                String docFilePath = uploadpath + File.separator + "khhxbg" + File.separator + docFileName;
                FileUtil.mkParentDirs(docFilePath);
                String resourceName = "客户画像报告.ftl";
                WordUtils.generateWord(data, docFilePath, resourceName);

                FileInputStream fileInputStream = new FileInputStream(docFilePath);
                byte[] bys = new byte[fileInputStream.available()];
                fileInputStream.read(bys);
                response.setContentType("application/force-download");// 设置强制下载不打开            
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(docFileName.getBytes("UTF-8"), "iso-8859-1"));
                outputStream.write(bys);
                outputStream.flush();
                outputStream.close();
            } else {
                Result result = new Result<>();
                result.setCode(200);
                result.setSuccess(false);
                result.setMessage("未找到此网格对应的客户画像信息");
                JSON.toJSONString(result);
                response.setContentType("application/json");
                outputStream.write(JSON.toJSONString(result).getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result<>();
            result.setSuccess(false);
            result.setMessage("系统错误");
            JSON.toJSONString(result);
            try {
                if (outputStream != null) {
                    response.setContentType("application/json");
                    outputStream.write(JSON.toJSONString(result).getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
            IoUtil.close(pdfOutPutStream);
        }
    }
}
