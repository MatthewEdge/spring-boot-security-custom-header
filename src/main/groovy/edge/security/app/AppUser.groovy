package edge.security.app

import groovy.transform.Immutable

/**
 * Simple model to represent an authenticated User.
 *
 * Note: this class is the component of AppAuthToken#getPrincipal that is injected
 *       into the SecurityContext so any post-authentication relevant information
 *       should go here
 *
 * @author medge
 */
@Immutable
class AppUser {

  String authId

}
