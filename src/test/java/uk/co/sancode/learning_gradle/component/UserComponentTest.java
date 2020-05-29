package uk.co.sancode.learning_gradle.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.sancode.learning_gradle.ComponentTests;
import uk.co.sancode.learning_gradle.api.UserDto;
import uk.co.sancode.learning_gradle.builder.UserBuilder;
import uk.co.sancode.learning_gradle.model.User;
import uk.co.sancode.learning_gradle.utilities.SqlHelper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.sancode.learning_gradle.utilities.RandomUtilities.getRandomInt;

@Category(ComponentTests.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles({"mockdatabase"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserComponentTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SqlHelper sqlHelper;

    @Autowired
    private ModelMapper modelMapper;

    private String baseUrl = "/users";

    @Test
    public void getUsers_returnsUsers() throws Exception {
        // Setup

        var users = IntStream.range(0, getRandomInt(2, 4)).mapToObj(x -> new UserBuilder().build()).collect(Collectors.toList());
        users.forEach(u -> sqlHelper.persistAndFlush(u));

        // Exercise

        var result = mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Verify

        assertNotNull(result);
        var actualDtos = objectMapper.readValue(result, UserDto[].class);
        var type = new TypeToken<List<User>>() {
        }.getType();
        var actual = modelMapper.map(actualDtos, type);

        assertEquals(users, actual);
    }
}
