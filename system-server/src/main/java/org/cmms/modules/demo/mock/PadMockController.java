package org.cmms.modules.demo.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/padjson")
@Slf4j
public class PadMockController {

	private final String JSON_PATH = "classpath:org/cmms/modules/demo/mock/padjson";

	/**
	 * 通用json访问接口
	 * 格式： http://localhost:8080/cmms/api/json/{filename}
	 * @param filename
	 * @return
	 */
	@RequestMapping(value = "/{filename}", method = RequestMethod.GET)
	public String getJsonData(@PathVariable String filename) {
		String jsonpath = "classpath:org/cmms/modules/demo/mock/padjson/"+filename+".json";
		return readJson(jsonpath);
	}

	@PostMapping(value = "/login")
	public String login() {
		return readJson(JSON_PATH + "/login.json");
	}

	@GetMapping(value = "/userinfo")
	public String userinfo() {
		return readJson(JSON_PATH + "/userinfo.json");
	}

	@GetMapping(value = "/tablelist")
	public String tablelist() {
		return readJson(JSON_PATH + "/tablelist.json");
	}

	@GetMapping(value = "/townlist")
	public String townlist() {
		return readJson(JSON_PATH + "/townlist.json");
	}

	@GetMapping(value = "/ranklist")
	public String ranklist() {
		return readJson(JSON_PATH + "/ranklist.json");
	}

	@GetMapping(value = "/appupdate")
	public String appupdate() {
		return readJson(JSON_PATH + "/appupdate.json");
	}

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


}
