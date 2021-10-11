package educa.movel.com.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import educa.movel.com.R;
import educa.movel.com.model.Book;
import educa.movel.com.rv.RvBooks;
import educa.movel.com.utils.InitFirebase;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView rv_books;
    private List<Book> bookList = new ArrayList<>();
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        initUI();
        initBooks();

    }



    private void setBook() {
        String uid = "";
        for (int i = 0; i < 10; i++) {
            uid = UUID.randomUUID().toString();
            Book book = new Book(uid, "Titulo", "", "");
            InitFirebase.initFirebase()
                    .child("educa_movel")
                    .child("books")
                    .child(uid)
                    .setValue(book);
        }
    }

    private void initUI() {
        progress = findViewById(R.id.progress);
        rv_books = findViewById(R.id.rv_books);
    }


    private void initBooks() {

        InitFirebase.initFirebase()
                .child("educa_movel")
                .child("books")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot objSnapshot: snapshot.getChildren()) {
                            Book book = objSnapshot.getValue(Book.class);
                            bookList.add(book);
                        }
                        progress.setVisibility(View.GONE);
                        RvBooks rvNews = new RvBooks(BooksActivity.this, bookList);
                        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                        rv_books.setLayoutManager(layoutManager);
                        rv_books.setAdapter(rvNews);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
}