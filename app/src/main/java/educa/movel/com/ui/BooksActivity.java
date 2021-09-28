package educa.movel.com.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import educa.movel.com.R;
import educa.movel.com.model.Book;
import educa.movel.com.rv.RvBooks;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView rv_books;
    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        initUI();
        initBooks();
    }

    private void initUI() {
        rv_books = findViewById(R.id.rv_books);
    }

    private void initBooks() {
        for (int i = 0; i < 6; i++) {
            bookList.add(new Book("i", "Livro de Portugues","Portugues", "s"));
        }
        RvBooks rvNews = new RvBooks(this, bookList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        rv_books.setLayoutManager(layoutManager);
        rv_books.setAdapter(rvNews);
    }
}