package buildPattern;

import lombok.Builder;
import lombok.ToString;

/**
 * @author: gaochen
 * Date: 2019/1/15
 */
@ToString
@Builder
public class User {
    private String username;
    private String password;
    private Integer age;
}
