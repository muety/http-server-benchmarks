package xyz.muetsch.jersey.model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/todo")
public class TodoResource {
    private List<TodoItem> todos;

    public TodoResource() {
        this.todos = new ArrayList<>();
        todos.add(new TodoItem("Do some stuff", new Date()));
        todos.add(new TodoItem("Do more stuff", new Date()));
        todos.add(new TodoItem("Even more stuff", new Date()));
        todos.add(new TodoItem("Yihaa", new Date()));
        todos.add(new TodoItem("Java is great! Or isn't it?", new Date()));
        todos.add(new TodoItem("Foo Bar", new Date()));
        todos.add(new TodoItem("Lorem ipsum", new Date()));
        todos.add(new TodoItem("Dolor sit amet", new Date()));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TodoItem> getAll() {
        return todos;
    }
}
