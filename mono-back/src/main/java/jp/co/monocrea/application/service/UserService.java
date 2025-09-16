package jp.co.monocrea.application.service;

import java.util.List;
import java.util.Optional;
import jp.co.monocrea.domain.model.User;
import jp.co.monocrea.presentation.model.response.UserResponse;

public interface UserService {

  public List<UserResponse> getUserList(
      String condition, String keyword, String sort, String order);

  public Optional<UserResponse> getUserById(int userId);

  public UserResponse createUser(User user);

  public UserResponse updateUser(User user);

  public UserResponse deleteUser(int userId);
}
