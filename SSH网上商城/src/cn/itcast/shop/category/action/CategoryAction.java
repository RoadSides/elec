package cn.itcast.shop.category.action;

import cn.itcast.shop.category.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;

public class CategoryAction extends ActionSupport{
		

		@Override
		public String execute() {

			return "index";
		}
}
