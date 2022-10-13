import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload {
    /**
     * 使用java中的文件流下载网上的图片，保存到本地
     *
     * @author JiaMing
     * @since 2021/12/28/0028 下午 15:06
     **/
        public void fileDownload() throws Exception {
            //定义一个URL对象，就是你想下载的图片的URL地址
            URL url = new URL("https://img1.baidu.com/it/u=1246988772,2703020567&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500");
            //打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为10秒
            conn.setConnectTimeout(10 * 1000);
            //通过输入流获取图片数据
            InputStream is = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(is);
            //创建一个文件对象用来保存图片，默认保存当前工程根目录，起名叫Copy.jpg
            File imageFile = new File("Copy.jpg");
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流，释放资源
            outStream.close();
        }

        public byte[] readInputStream(InputStream inStream) throws Exception {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[6024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            //把outStream里的数据写入内存
            return outStream.toByteArray();
        }
    }
