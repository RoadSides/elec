package cn.itcast.shop.product.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	private Product product=new Product();
	private ProductService productService;
	private CategorySecondService categorySecondService;
	//文件上传需要的参数
	private File upload;//这个名字与jsp上的表单中的名字一致,上传的文件
	private String uploadFileName;//接收文件上传的文件名
	private String uploadContextType;//接收文件上传的MIME的类型
	private int page;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setPage(int page) {  
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public Product getModel() {
		return product;
	}

	public String findAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSuccess";
	}
	
	public String addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	public String save() {	
		product.setPdate(new Date());
		System.out.println("我进来啦");
		if(upload!=null){
			//获取文件上传磁盘的绝对路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile=new File(realPath+"//"+uploadFileName);
			//文件上传
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	public String delete(){
		product=productService.findById(product.getPid());
		String path=product.getImage();
		if(path!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	public String edit(){
		product=productService.findById(product.getPid());
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	public String update() throws IOException{
		product.setPdate(new Date());
		if(upload!=null){
			
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
