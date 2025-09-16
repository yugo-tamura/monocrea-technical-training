package jp.co.monocrea.presentation.model.request;

import jp.co.monocrea.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

  private int id;

  private String name;

  public User toDomainModel() {

    return new User(this.id, this.name);
  }
}
