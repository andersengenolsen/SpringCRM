package springcrm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import springcrm.entity.User;
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

    @Test
    public void testAddNewUserShowForm() throws Exception {
        mockMvc.perform(get(ADMIN_URL + ADD_USER_URL))
                .andExpect(view().name(USER_FORM_VIEW));
    }

    @Test
    public void testErrorInFormReturnsForm() throws Exception {
        User expected = getValidUser();
        expected.setUsername(null);

        mockMvc.perform(post(ADMIN_URL + UPDATE_USER_URL)
                .flashAttr("user", expected))
                .andExpect(model().attribute("user", expected))
                .andExpect(view().name(USER_FORM_VIEW));
    }

    @Test
    public void testUpdateUserReturnsToAdminView() throws Exception {
        User expected = getValidUser();

        mockMvc.perform(post(ADMIN_URL + UPDATE_USER_URL)
                .flashAttr("user", expected))
                .andExpect(model().attribute("user", expected))
                .andExpect(view().name(REDIRECT_ADMIN_VIEW));
    }

    private User getValidUser() {
        User user = new User();
        user.setUsername("theusername");
        user.setPassword("thepassword");
        user.setPasswordVerif("thepassword");

        return user;
    }
}
