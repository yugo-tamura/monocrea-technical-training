package jp.co.monocrea.presentation.model.response;

import jp.co.monocrea.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private int id;

  private String name;

  public static UserResponse fromUser(User user) {

    return new UserResponse(user.getId(), user.getName());
  }
}
