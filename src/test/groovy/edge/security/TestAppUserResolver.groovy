package edge.security

import edge.security.app.AppUser
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * Method Argument Resolver to be used by the Unit-style tests with MockMvc
 *
 * Example Usage:
 *
 *  this.mockMvc =
 *    standaloneSetup(new MyController())
 *      .setCustomArgumentResolvers(new TestUserResolver(testUser))
 *    .build();
 *
 * @author medge
 */
class TestAppUserResolver implements HandlerMethodArgumentResolver {

  private AppUser mockUser

  public TestAppUserResolver(AppUser mockUser) {
    super()

    this.mockUser = mockUser
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(AppUser.class)
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    return mockUser
  }
}
