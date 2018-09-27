package springcrm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import springcrm.entity.User;
import springcrm.model.AppUser;
import springcrm.model.FormModel;
import springcrm.service.RoleService;
import springcrm.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static springcrm.controller.AdminController.*;

/**
 * Test for the {@link AdminController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestContext.class})
public class AdminControllerTest {

    private AdminController controller;
    private MockMvc mockMvc;

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() {
        controller = new AdminController(service, roleService);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testControllerReturnsCorrectView() throws Exception {
        mockMvc.perform(get(ADMIN_URL))
                .andExpect(view().name(ADMIN_VIEW));
    }

}
