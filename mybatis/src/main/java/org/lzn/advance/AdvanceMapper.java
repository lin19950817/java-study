package org.lzn.advance;

import org.lzn.dto.CompanyDTO;
import org.lzn.dto.CustomerDTO;

/**
 * 一对一，一对多
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/10/18 22:28
 */
public interface AdvanceMapper {

    /**
     * 查询公司与地址信息，使用 resultType 来进行一对一结果映射
     *
     * @param id 公司id
     * @return org.lzn.dto.CompanyDTO
     * @author LinZhenNan lin_hehe@qq.com
     */
    CompanyDTO getCompanyByResultType(Long id);

    /**
     * 查询公司与地址信息，使用 resultMap 来进行一对一结果映射
     *
     * @param id 公司id
     * @return org.lzn.dto.CompanyDTO
     * @author LinZhenNan lin_hehe@qq.com
     */
    CompanyDTO getCompanyByResultMap(Long id);

    /**
     * 通过id查询客户及订单信息，一对多
     *
     * @param id 客户id
     * @return org.lzn.dto.CustomerDTO
     * @author LinZhenNan lin_hehe@qq.com
     */
    CustomerDTO getCustomer(Long id);

    /**
     * 延迟加载查询，查询客户信息赖加载订单信息
     *
     * @param id 客户id
     * @return org.lzn.dto.CustomerDTO
     * @author LinZhenNan lin_hehe@qq.com
     */
    CustomerDTO getCustomerLazyLoad(Long id);
}
