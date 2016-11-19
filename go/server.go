    package main

    import (
        "net/http"
        "encoding/json"
        "time"
        "io"
    )

    type TodoItem struct {
        Title       string
        CreatedAt   time.Time
    }

    type Todos []TodoItem

    var todos Todos

    func handler(w http.ResponseWriter, r *http.Request) {
        body, _ := json.Marshal(todos)
        w.Header().Set("Content-Type", "application/json")
        io.WriteString(w, string(body))
    }

    func main() {
        todos = Todos{
            TodoItem{Title: "Do some stuff", CreatedAt: time.Now() },
            TodoItem{Title: "Do more stuff", CreatedAt: time.Now() },
            TodoItem{Title: "Even more stuff", CreatedAt: time.Now() },
            TodoItem{Title: "Yihaa", CreatedAt: time.Now() },
            TodoItem{Title: "Java is great! Or isn't it?", CreatedAt: time.Now() },
            TodoItem{Title: "Foo Bar", CreatedAt: time.Now() },
            TodoItem{Title: "Lorem ipsum", CreatedAt: time.Now() },
            TodoItem{Title: "Dolor sit amet", CreatedAt: time.Now() },
        }
        http.HandleFunc("/rest/todo", handler)
        http.ListenAndServe(":8080", nil);
    }