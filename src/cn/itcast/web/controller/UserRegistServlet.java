package cn.itcast.web.controller;

import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.ProblemBlock;
import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
/*
在老机器中注册新用户（用户可能没有接入）
 */
@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends javax.servlet.http.HttpServlet {
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
//==============================前戏===========================
        //获得主机IP
        String host = service.getUserIp();
        //获得网络内机器的IP列表
        List<String> list = service.getUserIps();
        String[] ips = new String[list.size()];
        list.toArray(ips);
//===========================================================
        int ipLenth = ips.length;
        int j = 0;

        String ip = request.getParameter("ip");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        //分配初始任务
        for (int i = 0; i < ipLenth; i++) {
            Thread_Http_Get t = new Thread_Http_Get("http://" + ips[i] + "/block/AddUserServlet" + "?ip=" + ip + "&username=" + username + "&password=" + password + "&type=" + type);
            t.start();
        }

        User user = new User();
        user.setIp(ip);
        user.setUsername(username);
        user.setPassword(password);
        user.setType(Integer.parseInt(type));
        dao.addNewUser(user);
        return;
    }

}
