package springcrm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static springcrm.config.AppSecurityConfig.DENIED_PAGE;
import static springcrm.controller.AdminController.ADMIN_URL;
import static springcrm.controller.AdminController.ADMIN_VIEW;
import static springcrm.controller.LoginController.DENIED_VIEW;

/**
 * Test for the {@link AdminController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestContext.class})
public class AdminControllerTest {

    private AdminController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        controller = new AdminController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testControllerReturnsCorrectView() throws Exception {
        mockMvc.perform(get(ADMIN_URL))
                .andExpect(view().name(ADMIN_VIEW));
    }

}
