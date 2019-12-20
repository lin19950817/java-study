package com.lzn.springcloud.controller;import com.lzn.springcloud.entities.Dept;import com.lzn.springcloud.service.IDeptService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.cloud.client.ServiceInstance;import org.springframework.cloud.client.discovery.DiscoveryClient;import org.springframework.web.bind.annotation.*;import java.util.List;@RestController@RequestMapping("/dept")public class DeptController {    @Autowired    private IDeptService deptService;    @Autowired    private DiscoveryClient client;    @PostMapping("/add")    public boolean add(@RequestBody Dept dept) {        return deptService.add(dept);    }    @PostMapping("/get/{id}")    public Dept get(@PathVariable("id") Long id) {        return deptService.get(id);    }    @PostMapping("/list")    public List<Dept> list() {        return deptService.list();    }    /**     * @Description 服务发现     * @Author LinZhenNan     * @Date 2019/7/27     * @Time 9:29     */    @GetMapping("/discovery")    public Object discovery() {        List<String> list = client.getServices();        System.out.println("*********" + list);        List<ServiceInstance> serviceInstances = client.getInstances("MICROSERVICECLOUD-DEPT");        for (ServiceInstance element : serviceInstances) {            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"                    + element.getUri());        }        return this.client;    }}