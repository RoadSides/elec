package cn.itcast.bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;

public class AddServlet extends HttpServlet {

	private BookService service=new BookService();
	private CategoryService categoryservice=new CategoryService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		DiskFileItemFactory factory=new DiskFileItemFactory(40*1024,new File("f:/"));
		
		ServletFileUpload sfu=new ServletFileUpload(factory);
		
		sfu.setFileSizeMax(40*1024);
		
		try {
			List<FileItem> fileitem=sfu.parseRequest(request);
			
			Map<String,String> map=new HashMap<String, String>();
			
			for(FileItem file:fileitem){
				if(file.isFormField()){
					map.put(file.getFieldName(), file.getString("utf-8"));
				}
			}
			Book book=CommonUtils.toBean(map, Book.class);
			
			book.setBid(CommonUtils.uuid());
			
			Category category=CommonUtils.toBean(map, Category.class);
			
			book.setCategory(category);

			
			String savepath=this.getServletContext().getRealPath("/book_img");
			
			String filename=CommonUtils.uuid()+"_"+fileitem.get(1).getName();
			
			if(!filename.toLowerCase().endsWith(".jpg")){
				request.setAttribute("msg","必须上传jpg文件");
				request.setAttribute("categoryList", categoryservice.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return ;
			}
			
			
			File destfile=new File(savepath, filename);
			
			fileitem.get(1).write(destfile);
			
			Image image=new ImageIcon(destfile.getAbsolutePath()).getImage();
			if(image.getWidth(null)>400||image.getHeight(null)>400){
				destfile.delete();
				request.setAttribute("msg","宽和高必须限制在200px内");
				request.setAttribute("categoryList", categoryservice.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return ;
			}
			
			book.setImage("book_img/"+filename);
			
			
			service.add(book);
			
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg","你上传的文件超过15kb");
				request.setAttribute("categoryList", categoryservice.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
	}

}
