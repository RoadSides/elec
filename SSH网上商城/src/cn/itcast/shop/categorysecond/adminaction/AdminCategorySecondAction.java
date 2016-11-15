package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond=new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	private int page;

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	@Override
	public CategorySecond getModel() {
		
		return categorySecond;
	}
	
	
	public String addPage(){
		List<Category> cList=categoryService.findAll();
		
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		return "addPageSuccess";
	}
	
	public String save(){
		categorySecondService.save(categorySecond);
		return "savepageSuccess";
	}
	
	public String delete(){
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deletepageSuccess";
	}
	
	public String edit(){
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editpageSuccess";
	}
	
	public String update(){
		categorySecondService.update(categorySecond);
		return "updatepageSuccess";
	}
}
