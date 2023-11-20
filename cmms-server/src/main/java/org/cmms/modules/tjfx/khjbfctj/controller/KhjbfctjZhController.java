package org.cmms.modules.tjfx.khjbfctj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
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

import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjZh;
import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjZhService;
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

/**
 * @Description: 客户级别分层统计_支行
 * @Author: cmms
 * @Date: 2019-12-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户级别分层统计_支行")
@RestController
@RequestMapping("/khfctj/KhjbfctjZh")
public class KhjbfctjZhController {
    @Autowired
    private IKhjbfctjZhService iKhjbfctjZhService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;

    /**
     * 分页列表查询
     *
     * @param KhjbfctjZh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户级别分层统计_支行-分页列表查询")
    @ApiOperation(value = "客户级别分层统计_支行-分页列表查询", notes = "客户级别分层统计_支行-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<KhjbfctjZh>> queryPageList(KhjbfctjZh KhjbfctjZh,
                                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest req) {
        Result<IPage<KhjbfctjZh>> result = new Result<IPage<KhjbfctjZh>>();
        QueryWrapper<KhjbfctjZh> queryWrapper = QueryGenerator.initQueryWrapper(KhjbfctjZh, req.getParameterMap());
        Page<KhjbfctjZh> page = new Page<KhjbfctjZh>(pageNo, pageSize);
        IPage<KhjbfctjZh> pageList = iKhjbfctjZhService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     * @param KhjbfctjZh
     * @return
     */
	/*@AutoLog(value = "客户级别分层统计_支行-添加")
	@ApiOperation(value="客户级别分层统计_支行-添加", notes="客户级别分层统计_支行-添加")
	@PostMapping(value = "/add")
	public Result<KhjbfctjZh> add(@RequestBody KhjbfctjZh KhjbfctjZh) {
		Result<KhjbfctjZh> result = new Result<KhjbfctjZh>();
		try {
			iKhjbfctjZhService.save(KhjbfctjZh);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}*/

    /**
     * 编辑
     * @param KhjbfctjZh
     * @return
     */
	/*@AutoLog(value = "客户级别分层统计_支行-编辑")
	@ApiOperation(value="客户级别分层统计_支行-编辑", notes="客户级别分层统计_支行-编辑")
	@PutMapping(value = "/edit")
	public Result<KhjbfctjZh> edit(@RequestBody KhjbfctjZh KhjbfctjZh) {
		Result<KhjbfctjZh> result = new Result<KhjbfctjZh>();
		KhjbfctjZh KhjbfctjZhEntity = iKhjbfctjZhService.getById(KhjbfctjZh.getId());
		if(KhjbfctjZhEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iKhjbfctjZhService.updateById(KhjbfctjZh);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}*/

    /**
     * 通过id删除
     * @param id
     * @return
     */
	/*@AutoLog(value = "客户级别分层统计_支行-通过id删除")
	@ApiOperation(value="客户级别分层统计_支行-通过id删除", notes="客户级别分层统计_支行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			iKhjbfctjZhService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}*/

    /**
     * 批量删除
     * @param ids
     * @return
     */
	/*@AutoLog(value = "客户级别分层统计_支行-批量删除")
	@ApiOperation(value="客户级别分层统计_支行-批量删除", notes="客户级别分层统计_支行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhjbfctjZh> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhjbfctjZh> result = new Result<KhjbfctjZh>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iKhjbfctjZhService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}*/

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户级别分层统计_支行-通过id查询")
    @ApiOperation(value = "客户级别分层统计_支行-通过id查询", notes = "客户级别分层统计_支行-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<KhjbfctjZh> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<KhjbfctjZh> result = new Result<KhjbfctjZh>();
        KhjbfctjZh KhjbfctjZh = iKhjbfctjZhService.getById(id);
        if (KhjbfctjZh == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(KhjbfctjZh);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjbfctjZh> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjbfctjZh KhjbfctjZh = JSON.parseObject(deString, KhjbfctjZh.class);
                queryWrapper = QueryGenerator.initQueryWrapper(KhjbfctjZh, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjbfctjZh> pageList = iKhjbfctjZhService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "客户级别分层统计_支行");
        mv.addObject(NormalExcelConstants.CLASS, KhjbfctjZh.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户级别分层统计_支行数据", "导出人:Hnran", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
                List<KhjbfctjZh> listKhjbfctjZhs = ExcelImportUtil.importExcel(file.getInputStream(), KhjbfctjZh.class, params);
                iKhjbfctjZhService.saveBatch(listKhjbfctjZhs);
                return Result.ok("文件导入成功！数据行数:" + listKhjbfctjZhs.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.ok("文件导入失败！");
    }

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
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_khfcthzh.py --fiscal_date " + tjrq + " --tjwd " + tjwd + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_KHFCTJ_ZH_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_KHFCTJ_ZH_IMPORT.sh");
            } else {
                Map<String, String> param = new HashMap<>();
                param.put("tjwd", jsonObject.getString("tjwd"));
                param.put("tjrq", jsonObject.getString("tjrq"));
                System.out.println("客户级别分层数据提取 =>| tjwd：" + jsonObject.getString("tjwd"));
                System.out.println("客户级别分层数据提取 =>| tjrq：" + jsonObject.getString("tjrq"));
                iKhjbfctjZhService.extract(param);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), "统计失败！");
            return Result.error(exception.getMessage());
        }
        return Result.ok("统计成功！");
    }

}
