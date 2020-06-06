package com.example.miinventario.modelos;

import io.realm.annotations.PrimaryKey;

public class InventarioItem {

    private String Tituloitem;
    private String CantidadItem;
    private int ImagenItem;

    public InventarioItem(String tituloitem, String cantidadItem, int imagenItem) {
        Tituloitem = tituloitem;
        CantidadItem = cantidadItem;
        ImagenItem = imagenItem;
    }

    public String getTituloitem() {
        return Tituloitem;
    }

    public void setTituloitem(String tituloitem) {
        Tituloitem = tituloitem;
    }

    public String getCantidadItem() {
        return CantidadItem;
    }

    public void setCantidadItem(String cantidadItem) {
        CantidadItem = cantidadItem;
    }

    public int getImagenItem() {
        return ImagenItem;
    }

    public void setImagenItem(int imagenItem) {
        ImagenItem = imagenItem;
    }
}
