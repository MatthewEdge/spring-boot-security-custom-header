package edge

import edge.security.AuthUser
import edge.security.app.AppUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author medge
 */
@RestController
class IndexController {

  @GetMapping("/")
  Map<String, String> index(@AuthUser AppUser user) {
    ["userId": user.authId]
  }

}
