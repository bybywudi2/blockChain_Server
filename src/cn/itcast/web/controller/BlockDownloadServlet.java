package cn.itcast.web.controller;

import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.XmlUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

//处理登录请求
@WebServlet("/BlockDownloadServlet")
public class BlockDownloadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File f = new File("/home/upfiles/block/blocks.xml");
		if(f.exists()){
			FileInputStream  fis = new FileInputStream(f);
			String filename= URLEncoder.encode(f.getName(),"utf-8"); //解决中文文件名下载后乱码的问题
			byte[] b = new byte[fis.available()];
			fis.read(b);
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition","attachment; filename="+filename+"");
			//获取响应报文输出流对象
			ServletOutputStream  out =response.getOutputStream();
			//输出
			out.write(b);
			out.flush();
			out.close();
		}
		/*
		//获取需要下载的文件名
		String filename = request.getParameter("filename");
		//解决中文乱码
		filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");
		//指定用户要下载的类型，客户端通过文件的MIME类型去区分
		response.setContentType(this.getServletContext().getMimeType(filename));

		//告诉用户该文件不是直接解析
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);

		//获取上下文Servlet对象
		ServletContext context = this.getServletContext();

		//获取需要下载的文件的绝对路径
		//String realPath = context.getRealPath(XmlUtils.getDownloadpath() + filename);
		String realPath = "/home/upfiles/block/blocks.xml";
		//获取输入流
		InputStream is = new FileInputStream(realPath);
		//获取客户端的输出流
		ServletOutputStream out = response.getOutputStream();
		int length = 0;
		byte[] b = new byte[1024];
		while((length=is.read()) > 0){
			out.write(b, 0, length);
		}
		is.close();*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}