package org.vosao.service.back;

import java.util.List;
import java.util.Map;

import org.vosao.entity.UserEntity;
import org.vosao.service.ServiceResponse;
import org.vosao.service.vo.UserVO;

public interface LimitedUserService {

  public abstract List<UserVO> select();

//  public abstract ServiceResponse remove(List<String> ids);

//  public abstract UserEntity getById(Long id);

  public abstract ServiceResponse save(Map<String, String> vo);

  public abstract UserEntity getLoggedIn();

//  public abstract List<UserVO> selectByGroup(String groupId);

//  public abstract ServiceResponse disable(Long userId, boolean disable);

  public abstract List<String> getTimezones();

}