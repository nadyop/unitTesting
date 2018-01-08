import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.List;

/**
 * Created by ssh on 1/08/2018
 */
public class TodoRepositoryTest {
    @Mock
    TodoRepository todoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAllTest() throws Exception {
        List<Todo> result = todoRepository.getAll();

        Assert.assertThat(result, Matchers.notNullValue());
    }

    @Test
    public void saveTodoTest() {
        boolean result = todoRepository.store(new Todo("Todoo", TodoPriority.HIGH));

        Assert.assertThat(result, Matchers.equalTo(false));
    }
}
