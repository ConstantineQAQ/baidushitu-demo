import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import com.baidu.aip.ocr.AipOcr;

public class TestApi {
    //设置APPID/AK/SK
    public static final String APP_ID = "27581898";
    public static final String API_KEY = "si9zApy2NQEQ1hLNYhHFCNKo";
    public static final String SECRET_KEY = "UZRKNWNRHW3LF4q31jDCW6NZ8BVDQ6lF";

    public static void main(String[] args) throws Exception {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
/*        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理*/

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        //下载图片
        FileDownload fl = new FileDownload();
        fl.fileDownload();

        // 调用接口
        String path = "src/temp.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        JSONArray jsonArray = res.getJSONArray("words_result");
        for (int i = 0; i <jsonArray.length(); i++) {
            System.out.println(jsonArray.getJSONObject(i).get("words").toString());
        }

    }


}