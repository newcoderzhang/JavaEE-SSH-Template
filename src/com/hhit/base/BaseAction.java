package com.hhit.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.hhit.service.ITestService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 基础Action类
 * @author bob
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	// =============== ModelDriven的支持 ==================
	protected T model;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============== Service实例的声明 ==================
	@Resource
	protected ITestService testService;// 测试

	/**
	 * 获取当前登录的用户
	 */
//	protected User getCurrentUser() {
//		return (User) ActionContext.getContext().getSession().get("user");
//	}
	
	// ============== 分页用的参数 =============
	
	protected int pageNum=1;
	protected int pageSize=10;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
