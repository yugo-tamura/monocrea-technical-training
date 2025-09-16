package jp.co.monocrea.application.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import jp.co.monocrea.application.service.UserService;
import jp.co.monocrea.domain.model.User;
import jp.co.monocrea.domain.repository.UserRepository;
import jp.co.monocrea.presentation.model.response.UserResponse;

@ApplicationScoped
@Transactional
public class UserServiceImpl implements UserService {

  @Inject UserRepository userRepository;

  @Override
  public List<UserResponse> getUserList(
      String condition, String keyword, String sort, String order) {

    Stream<User> users = userRepository.findUsers(condition, keyword, sort, order);

    return users.map(UserResponse::fromUser).toList();
  }

  @Override
  public Optional<UserResponse> getUserById(int userId) {

    Optional<User> user = userRepository.findById(userId);

    return user.map(UserResponse::fromUser);
  }

  @Override
  public UserResponse createUser(User user) {

    userRepository.save(user);

    return UserResponse.fromUser(user);
  }

  @Override
  public UserResponse updateUser(User user) {

    userRepository.update(user);

    return UserResponse.fromUser(user);
  }

  @Override
  public UserResponse deleteUser(int userId) {

    userRepository.remove(userId);

    return new UserResponse();
  }
}
