package com.example.miinventario.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.miinventario.Adaptadores.MyAdapter;
import com.example.miinventario.R;
import com.example.miinventario.modelos.InventarioItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
private RecyclerView recyclerView;
private RecyclerView.LayoutManager layoutManager;
private MyAdapter mAdapter;
private List<InventarioItem> inventarioItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaracion de elementos
        this.inventarioItems=getAllitems();

        //setup RecyclerView
        setUpRecyclerView();

    }

    public List<InventarioItem> getAllitems(){
        return new ArrayList<InventarioItem>(){{
            add(new InventarioItem("iphone","1",R.drawable.iphone));
            add(new InventarioItem("Samsung","10",R.drawable.iphone));
            add(new InventarioItem("Pixel","23",R.drawable.iphone));
            add(new InventarioItem("Lumia","4",R.drawable.iphone));
            add(new InventarioItem("Microsoft","2",R.drawable.iphone));
            add(new InventarioItem("Mago de Oz","5",R.drawable.iphone));
            add(new InventarioItem("Leche","10",R.drawable.iphone));
            add(new InventarioItem("Manzanas","2",R.drawable.iphone));
        }};

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);

        MenuItem searchitem =menu.findItem(R.id.search);
        SearchView searchView= (SearchView) searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAll:
                deleteAll();
                return true;

            default:return super.onOptionsItemSelected(item);
        }
    }

    //Eliminar todos los elementos
    private void deleteAll() {
    }


    private void setUpRecyclerView(){
        recyclerView=findViewById(R.id.recyclerview);
        //Acciones del recyclerview
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this,2);
        mAdapter= new MyAdapter(R.layout.inventario_item, inventarioItems, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(InventarioItem invitem, int position) {
                Toast.makeText(MainActivity.this, "Hola ",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
