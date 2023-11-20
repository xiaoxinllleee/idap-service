package org.cmms.modules.dklldj.csszgl.xmgzsz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import org.cmms.modules.dklldj.csszgl.xmgzsz.entity.Xmgzsz;
import org.cmms.modules.dklldj.csszgl.xmgzsz.service.IXmgzszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 项目规则设置
 * @Author: Penghr
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目规则设置")
@RestController
@RequestMapping("/csszgl/xmgzsz")
public class XmgzszController extends JeecgController<Xmgzsz, IXmgzszService> {
	@Autowired
	private IXmgzszService xmgzszService;
	@Autowired
    private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	 @Autowired
	 private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 * @param xmgzsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "项目规则设置-分页列表查询")
	@ApiOperation(value = "项目规则设置-分页列表查询", notes = "项目规则设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xmgzsz xmgzsz,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								   HttpServletRequest req) {
		String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
		xmgzsz.setQydm(qydm);
		Result<IPage<Xmgzsz>> result = new Result<IPage<Xmgzsz>>();
		QueryWrapper<Xmgzsz> queryWrapper = QueryGenerator.initQueryWrapper(xmgzsz, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IXmgzszService.class, xmgzszService, pageNo, pageSize, queryWrapper, "zbid", "zbgzid", "qydm");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param xmgzsz
	 * @return
	 */
	@AutoLog(value = "项目规则设置-添加")
	@ApiOperation(value="项目规则设置-添加", notes="项目规则设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xmgzsz xmgzsz) {
	    try {
	    	String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	        xmgzsz.setQydm(qydm);
            xmgzszService.save(xmgzsz);
        } catch (Exception e) {
            log.error("项目规则添加失败",e.getMessage());
            return Result.error("项目规则添加失败!");
        }
		return Result.ok("项目规则添加成功！");
	}

	/**
	 * 编辑
	 * @param xmgzsz
	 * @return
	 */
	@AutoLog(value = "项目规则设置-编辑")
	@ApiOperation(value="项目规则设置-编辑", notes="项目规则设置-编辑")
	@PutMapping(value = "/edit")
	public Result<Xmgzsz> edit(@RequestBody Xmgzsz xmgzsz) {
	    Result<Xmgzsz> result = new Result<>();
	    String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	    QueryWrapper<Xmgzsz> updateWrapper = new QueryWrapper<>();
	    updateWrapper.eq("qydm", qydm);
	    updateWrapper.eq("zbid", xmgzsz.getZbid());
	    updateWrapper.eq("zbgzid", xmgzsz.getZbgzid());
	    boolean okFlag = xmgzszService.update(xmgzsz, updateWrapper);
        if (okFlag) {
            result.success("考核项目编辑成功!");
        } else {
            result.error500("考核项目编辑失败");
        }
	    return result;
	}

	/**
	 * 通过QYDM、ZBID、ZBGZID删除
	 * @param zbid,zbgzid
	 * @return
	 */
	@AutoLog(value = "项目规则设置-通过QYDM、ZBID、ZBGZID删除")
	@ApiOperation(value="项目规则设置-通过QYDM、ZBID、ZBGZID删除", notes="项目规则设置-通过QYDM、ZBID、ZBGZID删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="zbid",required=true) String zbid,
                            @RequestParam(name="zbgzid",required=true) String zbgzid) {
	    try {
	    	String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	    	QueryWrapper<Xmgzsz> queryWrapper = new QueryWrapper<>();
	    	queryWrapper.eq("zbid",zbid);
	    	queryWrapper.eq("zbgzid",zbgzid);
	    	queryWrapper.eq("qydm",qydm);
	        xmgzszService.remove(queryWrapper);
        } catch (Exception e) {
            log.error("删除失败"+e.getMessage());
            return Result.error("删除失败!");
        }
		return Result.ok("删除成功!");
	}

    /**
     * 导出excel
     * @param request
     * @param xmgzsz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Xmgzsz xmgzsz) {
      return super.exportXls(request, xmgzsz, Xmgzsz.class, "项目规则设置");
    }
}
