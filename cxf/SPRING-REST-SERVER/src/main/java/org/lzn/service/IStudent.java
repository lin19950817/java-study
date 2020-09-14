package org.lzn.service;

import org.lzn.pojo.Student;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 学生接口
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/09/12 17:19
 */
@WebService
@Path("/student")
public interface IStudent {

    /**
     * 通过id查询学生<br>
     *     @GET：指定请求方式
     *     @Produces：指定服务数据类型
     *
     * @param id id
     * @return org.lzn.pojo.Student
     * @author LinZhenNan lin_hehe@qq.com
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    Student get(@PathParam("id") Long id);
}
