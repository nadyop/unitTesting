import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.service.TodoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssh on 1/08/2018
 */
public class TodoServiceTest {

    //instantiate TodoService
    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }

    @After
    public void setDown(){
        Mockito.verifyNoMoreInteractions(todoRepository);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Todo> todoLists = new ArrayList<Todo>();
        Todo firstTodo = new Todo("Monggo", TodoPriority.LOW);
        todoLists.add(firstTodo);

        List<Todo> returnTodo = new ArrayList<Todo>();

        BDDMockito.given(todoRepository.getAll()).willReturn(todoLists);

        returnTodo = todoService.getAll();

        Assert.assertThat(returnTodo, org.hamcrest.Matchers.notNullValue());
        Assert.assertThat(returnTodo.isEmpty(), org.hamcrest.Matchers.equalTo(false));

        BDDMockito.then(todoRepository).should().getAll();
    }

    @Test
    public void saveToDoTest(){
        boolean isTrue = true;

        Todo todo = new Todo("Monggo", TodoPriority.LOW);

        BDDMockito.given(todoRepository.store(todo)).willReturn(isTrue);

        isTrue = todoService.saveTodo("Monggo", TodoPriority.LOW);

        Assert.assertThat(isTrue, Matchers.notNullValue());
        Assert.assertThat(isTrue, Matchers.equalTo(true));

        BDDMockito.then(todoRepository).should().store(todo);
    }
}
