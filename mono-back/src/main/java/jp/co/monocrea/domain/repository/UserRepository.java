package jp.co.monocrea.domain.repository;

import java.util.Optional;
import java.util.stream.Stream;
import jp.co.monocrea.domain.model.User;
import jp.co.monocrea.infrastructure.entity.UserEntity;

public interface UserRepository {

  public Stream<User> findUsers(String condition, String keyword, String sort, String order);

  public Optional<User> findById(int userId);

  public UserEntity getById(int userId);

  public void save(User user);

  public void update(User user);

  public void remove(int userId);
}
