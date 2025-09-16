package jp.co.monocrea.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.stream.Stream;
import jp.co.monocrea.domain.model.User;
import jp.co.monocrea.domain.repository.UserRepository;
import jp.co.monocrea.infrastructure.entity.UserEntity;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepository<UserEntity> {

  @Override
  public Stream<User> findUsers(String condition, String keyword, String sort, String order) {

    StringBuilder query = new StringBuilder("SELECT entity FROM UserEntity entity");
    Parameters params = new Parameters();

    if (keyword != null) {
      switch (condition) {
        case "name" -> {
          keyword = "%" + keyword + "%";
          query.append(" WHERE entity.name ILIKE :keyword");
          params = params.and("keyword", keyword);
        }
        case "id" -> {
          if (keyword.matches("\\d+")) {
            query.append(" WHERE entity.id = :keyword");
            params = params.and("keyword", keyword);
          } else {
            return Stream.empty();
          }
        }
        default -> {
          return Stream.empty();
        }
      }
    }

    return params.map().isEmpty()
        ? stream(query.toString(), sortOption(sort, order)).map(UserEntity::toDomainModel)
        : stream(query.toString(), sortOption(sort, order), params).map(UserEntity::toDomainModel);
  }

  private Sort sortOption(String sort, String order) {
    return switch (order) {
      case "asc" -> Sort.by(sort);
      case "desc" -> Sort.descending(sort);
      default -> Sort.by(sort);
    };
  }

  @Override
  public Optional<User> findById(int userId) {

    return find("id", userId).singleResultOptional().map(UserEntity::toDomainModel);
  }

  @Override
  public UserEntity getById(int userId) {

    return find("id", userId).singleResult();
  }

  @Override
  public void save(User user) {

    persist(UserEntity.fromDomainModel(user));
  }

  @Override
  public void update(User user) {

    UserEntity entity = getById(user.getId());
    entity.setName(user.getName());
  }

  @Override
  public void remove(int userId) {

    UserEntity entity = getById(userId);
    delete(entity);
  }
}
