package edge.security.app

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * Security Role Constants
 *
 * @author medge
 */
abstract class Roles {

  static final String APP_USER_ROLE = "ROLL_APP_USER" // Exists for the @Secured annotation of Spring Security
  static final GrantedAuthority APP_USER = new SimpleGrantedAuthority(APP_USER_ROLE)

}
