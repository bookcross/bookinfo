package test.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import test.model.BCUser;
import test.model.BCUserExample;

public interface BCUserMapper {
    int countByExample(BCUserExample example);

    int deleteByExample(BCUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(BCUser record);

    int insertSelective(BCUser record);

    List<BCUser> selectByExample(BCUserExample example);

    BCUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") BCUser record, @Param("example") BCUserExample example);

    int updateByExample(@Param("record") BCUser record, @Param("example") BCUserExample example);

    int updateByPrimaryKeySelective(BCUser record);

    int updateByPrimaryKey(BCUser record);
}