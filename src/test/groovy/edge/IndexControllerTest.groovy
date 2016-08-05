package edge

import edge.security.TestAppUserResolver
import edge.security.app.AppUser
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * @author medge
 */
class IndexControllerTest {

  MockMvc mockMvc

  AppUser mockUser = new AppUser("testAuthId")

  @Before
  void setup() {
    this.mockMvc =
      standaloneSetup(new IndexController())
        .setCustomArgumentResolvers(new TestAppUserResolver(mockUser))
        .alwaysDo(print())
      .build()
  }

  @Test
  void "it should return the authenticated user's ID"() {
    mockMvc.perform(
      get("/")
    )
    .andExpect(status().is(200))
    .andExpect(content().json("{\"userId\":\"${mockUser.authId}\"}"))
  }

}
