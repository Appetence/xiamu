package com.umpay.rms.gpd.user.mapper;



import com.umpay.rms.gpd.user.api.entity.TbAccountCodeNumber;
import com.umpay.rms.gpd.user.api.entity.vo.ChannelBindingInfoCriteriaDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TbAccountCodeNumber)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 10:50:02
 */
public interface TbAccountCodeNumberMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAccountCodeNumber queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAccountCodeNumber> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

/**
     * 查询指所有
     *
     * @return 对象列表
     */
    List<TbAccountCodeNumber> queryAll();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 对象列表
     */
    List<TbAccountCodeNumber> queryAll(TbAccountCodeNumber tbAccountCodeNumber);

    /**
     * 新增数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 影响行数
     */
    int insert(TbAccountCodeNumber tbAccountCodeNumber);

    /**
     * 修改数据
     *
     * @param tbAccountCodeNumber 实例对象
     * @return 影响行数
     */
    int update(TbAccountCodeNumber tbAccountCodeNumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int insertCodeNumber(List<TbAccountCodeNumber> codeNumber);

    int deleteByAccountId(String accountId);

    List<TbAccountCodeNumber> checkCodeNumber(TbAccountCodeNumber tbAccountCodeNumber);

    List<TbAccountCodeNumber> selectByPrimaryKeyOrAccountId(@Param("id") Integer id, @Param("accountId") String accountId);

    int updateBindingInfo(ChannelBindingInfoCriteriaDTO channelBindingInfoCriteriaDTO);

    /**
     * 统计通道使用数量
     * @param id
     * @return
     */
    int countUseChannel(String id);

    int statisticalVmsCode(@Param("vmsCode") String vmsCode, @Param("ciId") String ciId);
}