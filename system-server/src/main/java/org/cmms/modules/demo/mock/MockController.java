package org.cmms.modules.demo.mock;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.io.IOUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
@Slf4j
public class MockController {

	private final String JSON_PATH = "classpath:org/cmms/modules/demo/mock/json";

	/**
	 * 通用json访问接口
	 * 格式： http://localhost:8080/cmms/api/json/{filename}
	 * @param filename
	 * @return
	 */
	@RequestMapping(value = "/json/{filename}", method = RequestMethod.GET)
	public String getJsonData(@PathVariable String filename) {
		String jsonpath = "classpath:org/cmms/modules/demo/mock/json/"+filename+".json";
		return readJson(jsonpath);
	}

	@GetMapping(value = "/asynTreeList")
	public String asynTreeList(String id) {
		return readJson(JSON_PATH + "/asyn_tree_list_" + id + ".json");
	}

	@GetMapping(value = "/user")
	public String user() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/user.json");
	}

	/**
	 * 老的登录获取用户信息接口
	 * @return
	 */
	@GetMapping(value = "/user/info")
	public String userInfo() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/user_info.json");
	}

	@GetMapping(value = "/role")
	public String role() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/role.json");
	}

	@GetMapping(value = "/service")
	public String service() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/service.json");
	}

	@GetMapping(value = "/permission")
	public String permission() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/permission.json");
	}

	@GetMapping(value = "/permission/no-pager")
	public String permission_no_page() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/permission_no_page.json");
	}

	/**
	 * 省市县
	 */
	@GetMapping(value = "/area")
	public String area() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/area.json");
	}

	/**
	  * 测试报表数据
	 */
	@GetMapping(value = "/report/getYearCountInfo")
	public String getYearCountInfo() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/getCntrNoCountInfo.json");
	}
	@GetMapping(value = "/report/getMonthCountInfo")
	public String getMonthCountInfo() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/getCntrNoCountInfo.json");
	}
	@GetMapping(value = "/report/getCntrNoCountInfo")
	public String getCntrNoCountInfo() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/getCntrNoCountInfo.json");
	}
	@GetMapping(value = "/report/getCabinetCountInfo")
	public String getCabinetCountInfo() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/getCntrNoCountInfo.json");
	}

	/**
	   * 实时磁盘监控
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/queryDiskInfo")
	public Result<List<Map<String,Object>>> queryDiskInfo(HttpServletRequest request, HttpServletResponse response){
		Result<List<Map<String,Object>>> res = new Result<>();
		try {
			// 当前文件系统类
	        FileSystemView fsv = FileSystemView.getFileSystemView();
	        // 列出所有windows 磁盘
	        File[] fs = File.listRoots();
	        log.info("查询磁盘信息:"+fs.length+"个");
	        List<Map<String,Object>> list = new ArrayList<>();

	        for (int i = 0; i < fs.length; i++) {
	        	if(fs[i].getTotalSpace()==0) {
	        		continue;
	        	}
	        	Map<String,Object> map = new HashMap<>();
	        	map.put("name", fsv.getSystemDisplayName(fs[i]));
	        	map.put("max", fs[i].getTotalSpace());
	        	map.put("rest", fs[i].getFreeSpace());
	        	map.put("restPPT", fs[i].getFreeSpace()*100/fs[i].getTotalSpace());
	        	list.add(map);
	        	log.info(map.toString());
	        }
	        res.setResult(list);
	        res.success("查询成功");
		} catch (Exception e) {
			res.error500("查询失败"+e.getMessage());
		}
		return res;
	}

	//-------------------------------------------------------------------------------------------
	/**
	 * 工作台首页的数据
	 * @return
	 */
	@GetMapping(value = "/list/search/projects")
	public String projects() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/workplace_projects.json");
	}

	@GetMapping(value = "/workplace/activity")
	public String activity() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/workplace_activity.json");
	}

	@GetMapping(value = "/workplace/teams")
	public String teams() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/workplace_teams.json");
	}

	@GetMapping(value = "/workplace/radar")
	public String radar() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/workplace_radar.json");
	}

	@GetMapping(value = "/task/process")
	public String taskProcess() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/task_process.json");
	}
	//-------------------------------------------------------------------------------------------

	//author:lvdandan-----date：20190315---for:添加数据日志json----
	public String sysDataLogJson() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/sysdatalog.json");
	}
	//author:lvdandan-----date：20190315---for:添加数据日志json----

	/**
	 * 读取json格式文件
	 * @param
	 * @return
	 */
	/*private String readJson(String jsonSrc) {
		String json = "";
		try {
			//File jsonFile = ResourceUtils.getFile(jsonSrc);
			//json = FileUtils.re.readFileToString(jsonFile);
			//换个写法，解决springboot读取jar包中文件的问题
			InputStream stream = getClass().getClassLoader().getResourceAsStream(jsonSrc.replace("classpath:", ""));
			json = IOUtils.toString(stream);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return json;
	}*/
	public static String readJson(String urlString) {
		StringBuffer strBuf = new StringBuffer();
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				strBuf.append(line + " ");
			}
			reader.close();
		} catch (MalformedURLException e) {
			log.error("[DownloadUtil.getJsonStringAt] 下载文件异常,exp:{}",e);
		} catch (IOException e) {
			log.error("[DownloadUtil.getJsonStringAt] 下载文件异常,exp:{}",e);
		}
		return strBuf.toString();
	}

	@GetMapping(value = "/zg")
	public String zg() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/yjyxdygl.json");
	}

	@GetMapping(value = "/yxdygl/ejyxdygl/list")
	public String ejyxdyglList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ejyxdygl.json");
	}

	@GetMapping(value = "/cqjm/list")
	public String cqjmList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khgxgl_cqjm.json");
	}
	@GetMapping(value = "/khnlfctj/list")
	public String khnlfctjList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/tjfx_khnlfctj.json");
	}


	@GetMapping(value = "/khdj/khgxpd/list")
	public String khgxpdList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khdj_khgxpd.json");
	}

	@GetMapping(value = "/khdj/khdjsz/list")
	public String khdjszList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khdj_khdjsz.json");
	}


	@GetMapping(value = "/khdj/khdjpdgzsz/list")
	public String khdjpdgzszList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khdj_khdjpdgzsz.json");
	}


	@GetMapping(value = "/ckjk/ckpldjk/list")
	public String ckpldjkList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ckjk_ckpldjk.json");
	}

/*
客户存款余额监控
 */
	@GetMapping(value = "/ckjk/khckyejk/list")
	public String khckyejkList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ckjk_khckyejk.json");
	}


	/*
客户大额变动监控
 */
	@GetMapping(value = "/ckjk/khdebdjk/list")
	public String khdebdjkList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ckjk_khdebdjk.json");
	}

		/*
特定人员存款流水监控
 */
	@GetMapping(value = "/ckjk/tdrycklsjk/list")
	public String tdrycklsjkList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ckjk_tdrycklsjk.json");
	}

	/*
客户存款日平监控
 */
	@GetMapping(value = "/ckjk/khckrpjk/list")
	public String khckrpjkList() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/ckjk_khckrpjk.json");
	}

	//机关事业单位
	@GetMapping(value = "/jgsydw")
	public String jgsydw() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/jgsydw.json");
	}

	//企业
	@GetMapping(value = "/qy")
	public String qy() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/qy.json");
	}

	//客户交接
	@GetMapping(value = "/khjj")
	public String khjj() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khjj.json");
	}

	//客户交接
	@GetMapping(value = "/khtycx")
	public String khtycx() {
		return readJson("classpath:org/cmms/modules/demo/mock/json/khtycx.json");
	}


	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		//Step.2 AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "1列表");
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("1列表数据", "导出人:Jeecg", "导出信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, readJson("classpath:org/cmms/modules/demo/mock/json/ejyxdygl.json"));
		return mv;
	}

	/**客户管理/农户信息管理虚拟数据
     * @return getKhglNhxxgl
     */
    @GetMapping(value = "/khgl/nh")
    public String getKhglNhxxgl() {
        return readJson("classpath:org/cmms/modules/demo/mock/json/getKhglNhxxgl.json");
    }
    /**客户管理/农户、商户信息管理虚拟数据
     * @return getKhglShxxgl
     */
    @GetMapping(value = "/khgl/sh")
    public String getKhglShxxgl() {
        return readJson("classpath:org/cmms/modules/demo/mock/json/getKhglShxxgl.json");
    }
    /**统计分析/客户走访统计
     * @return getKhzftj
     */
    @GetMapping(value = "/tjfx/khzftj")
    public String getKhzftj() {
        return readJson("classpath:org/cmms/modules/demo/mock/json/getKhzftj.json");
    }

    @RequestMapping(value = "/mapoverlay/info", method = RequestMethod.GET)
	public String getMapoverlayInfo(@RequestParam(name = "index", required = true)String index){
		return readJson(JSON_PATH + "/mapoverlayInfo/info_" + index + ".json");
	}
}
