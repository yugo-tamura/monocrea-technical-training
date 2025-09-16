package jp.co.monocrea.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jp.co.monocrea.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  public static UserEntity fromDomainModel(User user) {
    UserEntity entity = new UserEntity(user.getId(), user.getName());
    return entity;
  }

  public User toDomainModel() {
    return new User(this.id, this.name);
  }
}
