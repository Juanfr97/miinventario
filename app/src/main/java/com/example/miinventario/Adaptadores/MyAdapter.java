package com.example.miinventario.Adaptadores;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miinventario.R;
import com.example.miinventario.modelos.InventarioItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
private int layout;
private List<InventarioItem> listitems;
private List<InventarioItem> listitemsfull;
private OnItemClickListener itemClickListener;
private Context context;

    public MyAdapter(int layout, List<InventarioItem> listitems, OnItemClickListener itemClickListener) {
        this.layout = layout;
        this.listitems = listitems;
        this.itemClickListener = itemClickListener;
        listitemsfull=new ArrayList<>(listitems);
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context=parent.getContext();
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.bind(listitems.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombreitem,cantidaditem;
        public ImageView imgitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombreitem=itemView.findViewById(R.id.titulo);
            this.cantidaditem=itemView.findViewById(R.id.cantidad);
            this.imgitem=itemView.findViewById(R.id.img);
        }

        public void bind(final InventarioItem inventarioItem,final OnItemClickListener listener){
            this.nombreitem.setText(inventarioItem.getTituloitem());
            this.cantidaditem.setText(inventarioItem.getCantidadItem());

            Picasso.with(context).load(inventarioItem.getImagenItem()).fit().into(imgitem);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   listener.onItemClick(inventarioItem,getAdapterPosition());
               }
           });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(InventarioItem invitem, int position);
    }

    //Filtro de busqueda
    @Override
    public Filter getFilter() {
        return listfilter;
    }

    private Filter listfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List <InventarioItem>  listafiltrada = new ArrayList<>();

            if(constraint==null || constraint.length()==0){
                listafiltrada.addAll(listitemsfull);
            }

            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(InventarioItem item :listitemsfull){
                    if (item.getTituloitem().toLowerCase().contains(filterPattern))
                        listafiltrada.add(item);
                }
            }

            FilterResults results= new FilterResults();
            results.values=listafiltrada;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listitems.clear();
            listitems.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



}
