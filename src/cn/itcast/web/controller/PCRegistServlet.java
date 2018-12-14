package cn.itcast.web.controller;

import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/*
在新机器注册新用户,新机器会先向一个固定的服务器发出请求，该服务器是无状态的，只负责随机选取一台活跃节点让新机器同步数据
同步数据后，新机器再参与到数据修正，因为同步的数据可能是错误的
 */
/*
 * @从制定URL下载文件并保存到指定目录
 * @param filePath 文件将要保存的目录
 * @param method 请求方法，包括POST和GET
 * @param url 请求的路径
 * @return
 */


@WebServlet("/PCRegistServlet")
public class PCRegistServlet extends javax.servlet.http.HttpServlet {
    BusinessServiceImpl service = new BusinessServiceImpl();
    UserDaoImpl dao = new UserDaoImpl();
    //定义一个维护URL的线程，用多线程的方式同时向网络中所有主机发送区块链的URL
    class Thread_Http_Get extends Thread{
        private String httpurl;

        public Thread_Http_Get(String httpurl){
            this.httpurl = httpurl;
        }

        public void http_Get(){
            try {

                HttpURLConnection connection = null;
                URL url = new URL(httpurl);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                System.out.println(connection.getResponseCode());
                connection.disconnect();
            }catch(MalformedURLException e){
                //e.printStackTrace();
            }catch(IOException e){
                //e.printStackTrace();
            }
        }

        public void run() {
            http_Get();
        }

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int res = saveUrlAs("http://39.106.194.129:8080/blockS/BlockDownloadServlet","/home/upfiles/blockdown/","get");
        if(res == 1){
            response.setStatus(200);
        }else{
            response.setStatus(201);
        }
    }

    public int saveUrlAs(String url, String filePath, String method){
        //System.out.println("fileName---->"+filePath);
        //创建不同的文件夹目录
        File file=new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            //conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {

                filePath += "/";

            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath+"123.png");
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
            return 0;
        }

        return 1;

    }
}
