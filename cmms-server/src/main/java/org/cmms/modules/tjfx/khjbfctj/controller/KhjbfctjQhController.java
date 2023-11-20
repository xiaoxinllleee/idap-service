package org.cmms.modules.tjfx.khjbfctj.controller;

import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjQh;
import org.cmms.modules.tjfx.khjbfctj.entity.KhdjEntity;
import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjQhService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.tjfx.khjbfctj.service.KhdjService;
import org.cmms.modules.util.EtlUtil;
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
import org.cmms.common.system.base.controller.JeecgController;

/**
 * @Description: 客户级别分层统计_全行
 * @Author: cmms
 * @Date: 2019-12-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户级别分层统计_全行")
@RestController
@RequestMapping("/khfctj/khfctjQh")
public class KhjbfctjQhController {
    @Autowired
    private IKhjbfctjQhService iKhjbfctjQhService;
    @Autowired
    private KhdjService iKhdjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;

    /**
     * 分页列表查询：全行
     *
     * @param khjbfctjQh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户级别分层统计_全行-分页列表查询")
    @ApiOperation(value = "客户级别分层统计_全行-分页列表查询", notes = "客户级别分层统计_全行-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<KhjbfctjQh>> queryPageList(KhjbfctjQh khjbfctjQh,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        Result<IPage<KhjbfctjQh>> result = new Result<IPage<KhjbfctjQh>>();
        QueryWrapper<KhjbfctjQh> queryWrapper = QueryGenerator.initQueryWrapper(khjbfctjQh, req.getParameterMap());
        Page<KhjbfctjQh> page = new Page<KhjbfctjQh>(pageNo, pageSize);
        IPage<KhjbfctjQh> pageList = iKhjbfctjQhService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel：全行
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjbfctjQh> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjbfctjQh khjbfctjQh = JSON.parseObject(deString, KhjbfctjQh.class);
                queryWrapper = QueryGenerator.initQueryWrapper(khjbfctjQh, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjbfctjQh> pageList = iKhjbfctjQhService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "客户级别分层统计_全行");
        mv.addObject(NormalExcelConstants.CLASS, KhjbfctjQh.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户级别分层统计_全行数据", "导出人:Hnran", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据：全行
     * @param request
     * @param response
     * @return
     */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<KhjbfctjQh> listKhjbfctjQhs = ExcelImportUtil.importExcel(file.getInputStream(), KhjbfctjQh.class, params);
              iKhjbfctjQhService.saveBatch(listKhjbfctjQhs);
              return Result.ok("文件导入成功！数据行数:" + listKhjbfctjQhs.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }*/

    /**
     * 客户级别分层数据提取
     *
     * @param jsonObject
     * @return
     */
    @PutMapping(value = "/extract")
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                //同步oracle到impala
                EtlUtil.SHcallEtlRc(10, true, false, false, "khdj_khdjpd", "idap");
                //调用python脚本
                String tjwd = jsonObject.getString("tjwd");
                String tjrq = jsonObject.getString("jrq");
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_khfcthqh.py --fiscal_date " + tjrq + " --tjwd " + tjwd + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_KHFCTJ_QH_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_KHFCTJ_QH_IMPORT.sh");
            } else {
                Map<String, String> param = new HashMap<>();
                param.put("tjwd", jsonObject.getString("tjwd"));
                param.put("tjrq", jsonObject.getString("tjrq"));
                System.out.println("客户级别分层数据提取 =>| tjwd：" + jsonObject.getString("tjwd"));
                System.out.println("客户级别分层数据提取 =>| tjrq：" + jsonObject.getString("tjrq"));
                iKhjbfctjQhService.extract(param);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), "统计失败！");
            return Result.error(exception.getMessage());
        }
        return Result.ok("统计成功！");
    }

    /**
     * 查询全行最新数据：月
     *
     * @return
     */
    @RequestMapping(value = "/MList")
    public JSONObject getQhDataM() {
        String tjwd = "MM";
        Date maxDate = iKhjbfctjQhService.getMaxDateM();
        /*List<KhjbfctjQh> qhDataMM = iKhjbfctjQhService.getQhDataM();*/
        List<String> djName = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray();
        KhdjEntity khdjEntity = new KhdjEntity();
        Map<String, String[]> khdjMap = new HashMap<>();
        QueryWrapper<KhdjEntity> khdjWrapper = QueryGenerator.initQueryWrapper(khdjEntity, khdjMap);
        khdjWrapper.orderByAsc("DJBH");
        List<KhdjEntity> khdjEntityList = iKhdjService.list(khdjWrapper);
        // 获取客户等级分层饼状图图例列表数据
        for (KhdjEntity khdj : khdjEntityList) {
            KhjbfctjQh khjbfctjQh = new KhjbfctjQh();
            // 统计维度：月
            khjbfctjQh.setTjwd(tjwd);
            // 最新统计日期
            khjbfctjQh.setTjrq(maxDate);
            khjbfctjQh.setKhdj(khdj.getDjbh());
            Map<String, String[]> qhDataMap = new HashMap<>();
            QueryWrapper<KhjbfctjQh> qhDataWrapper = QueryGenerator.initQueryWrapper(khjbfctjQh, qhDataMap);
            List<KhjbfctjQh> qhData = iKhjbfctjQhService.list(qhDataWrapper);
            JSONObject jsonObject = new JSONObject();
            if (qhData.size() == 0) {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", "0");
            } else {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", qhData.get(0).getKhs());
            }
            djName.add(khdj.getDjmc());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("DataM", jsonArray);
        jsonObject.put("djName", djName);
        jsonObject.put("MaxMonth", maxDate);
        jsonObject.put("success", true);
        return jsonObject;
    }

    /**
     * 查询全行最新数据：季
     *
     * @return
     */
    @RequestMapping(value = "/QList")
    public JSONObject getQhDataQ() {
        String tjwd = "Q";
        Date maxDate = iKhjbfctjQhService.getMaxDateQ();
        //List<KhjbfctjQh> qhDataQ = iKhjbfctjQhService.getQhDataQ();
        List<String> djName = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray();
      /*for (KhjbfctjQh khjbfctjQh : qhDataQ) {
          KhdjEntity KhdjEntity = new KhdjEntity();
          KhdjEntity.setDjbh(khjbfctjQh.getKhdj());

          Map<String, String[]> map = new HashMap<>();
          QueryWrapper<KhdjEntity> queryWrapper = QueryGenerator.initQueryWrapper(KhdjEntity, map);
          List<KhdjEntity> khdjEntityList = iKhdjService.list(queryWrapper);

          JSONObject jsonObject = new JSONObject();
          jsonObject.put("name", khdjEntityList.get(0).getDjmc());
          jsonObject.put("value", khjbfctjQh.getKhs());
          jsonArray.add(jsonObject);

          // djName.add(khdjEntityList.get(0).getDjmc());
      }*/
        KhdjEntity khdjEntity = new KhdjEntity();
        Map<String, String[]> khdjMap = new HashMap<>();
        QueryWrapper<KhdjEntity> khdjWrapper = QueryGenerator.initQueryWrapper(khdjEntity, khdjMap);
        khdjWrapper.orderByAsc("DJBH");
        List<KhdjEntity> khdjEntityList = iKhdjService.list(khdjWrapper);
        // 获取客户等级分层饼状图图例列表数据
        for (KhdjEntity khdj : khdjEntityList) {
            KhjbfctjQh khjbfctjQh = new KhjbfctjQh();
            // 统计维度：季度
            khjbfctjQh.setTjwd(tjwd);
            // 最新统计日期
            khjbfctjQh.setTjrq(maxDate);
            khjbfctjQh.setKhdj(khdj.getDjbh());
            Map<String, String[]> qhDataMap = new HashMap<>();
            QueryWrapper<KhjbfctjQh> qhDataWrapper = QueryGenerator.initQueryWrapper(khjbfctjQh, qhDataMap);
            List<KhjbfctjQh> qhData = iKhjbfctjQhService.list(qhDataWrapper);
            JSONObject jsonObject = new JSONObject();
            if (qhData.size() == 0) {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", "0");
            } else {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", qhData.get(0).getKhs());
            }
            djName.add(khdj.getDjmc());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("DataQ", jsonArray);
        jsonObject.put("djName", djName);
        jsonObject.put("MaxQuarter", maxDate);
        jsonObject.put("success", true);
        return jsonObject;
    }

    /**
     * 查询全行最新数据：年
     *
     * @return
     */
    @RequestMapping(value = "/YList")
    public JSONObject getQhDataY() {
        String tjwd = "YYYY";
        Date maxDate = iKhjbfctjQhService.getMaxDateY();
        /*List<KhjbfctjQh> qhDataY = iKhjbfctjQhService.getQhDataY();*/
        List<String> djName = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray();
      /*for (KhjbfctjQh khjbfctjQh : qhDataY) {
          KhdjEntity KhdjEntity = new KhdjEntity();
          KhdjEntity.setDjbh(khjbfctjQh.getKhdj());

          Map<String, String[]> map = new HashMap<>();
          QueryWrapper<KhdjEntity> queryWrapper = QueryGenerator.initQueryWrapper(KhdjEntity, map);
          List<KhdjEntity> khdjEntityList = iKhdjService.list(queryWrapper);

          JSONObject jsonObject = new JSONObject();
          jsonObject.put("name", khdjEntityList.get(0).getDjmc());
          jsonObject.put("value", khjbfctjQh.getKhs());
          jsonArray.add(jsonObject);

          // djName.add(khdjEntityList.get(0).getDjmc());
      }*/
        KhdjEntity khdjEntity = new KhdjEntity();
        Map<String, String[]> khdjMap = new HashMap<>();
        QueryWrapper<KhdjEntity> khdjWrapper = QueryGenerator.initQueryWrapper(khdjEntity, khdjMap);
        khdjWrapper.orderByAsc("DJBH");
        List<KhdjEntity> khdjEntityList = iKhdjService.list(khdjWrapper);
        // 获取客户等级分层饼状图图例列表数据
        for (KhdjEntity khdj : khdjEntityList) {
            KhjbfctjQh khjbfctjQh = new KhjbfctjQh();
            // 统计维度：年
            khjbfctjQh.setTjwd(tjwd);
            // 最新统计日期
            khjbfctjQh.setTjrq(maxDate);
            khjbfctjQh.setKhdj(khdj.getDjbh());
            Map<String, String[]> qhDataMap = new HashMap<>();
            QueryWrapper<KhjbfctjQh> qhDataWrapper = QueryGenerator.initQueryWrapper(khjbfctjQh, qhDataMap);
            List<KhjbfctjQh> qhData = iKhjbfctjQhService.list(qhDataWrapper);
            JSONObject jsonObject = new JSONObject();
            if (qhData.size() == 0) {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", "0");
            } else {
                jsonObject.put("name", khdj.getDjmc());
                jsonObject.put("value", qhData.get(0).getKhs());
            }
            djName.add(khdj.getDjmc());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("DataY", jsonArray);
        jsonObject.put("djName", djName);
        jsonObject.put("MaxYear", maxDate);
        jsonObject.put("success", true);
        return jsonObject;
    }

}
