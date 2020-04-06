package base.org.lzn.web.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建动作类的第二种方式
 * 创建一个普通类，实现 Action 接口，实现接口中的方法
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/06 0:40
 */
public class Demo2Action implements Action, ServletRequestAware, ServletResponseAware, ServletContextAware {
    @Override
    public String execute() throws Exception {
        // 获取 ServletApi 第一种方式，通过 ServletActionContext 对象获取
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletContext application = ServletActionContext.getServletContext();
        System.out.println(request);
        System.out.println(response);
        System.out.println(application);
        return null;
    }

    /**
     * 获取 ServletApi
     * 第二种，通过依赖注入形式，需要实现 ServletRequestAware, ServletResponseAware, ServletContextAware 接口
     */
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private ServletContext app;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.req = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.resp = response;
    }

    @Override
    public void setServletContext(ServletContext context) {
        this.app = context;
    }
}
