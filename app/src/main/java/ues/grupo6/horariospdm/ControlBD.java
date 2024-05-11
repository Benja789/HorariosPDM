package ues.grupo6.horariospdm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ues.grupo6.horariospdm.tipo_evento.TipoEvento;
import ues.grupo6.horariospdm.evento.Evento;


public class ControlBD {
    private static final String[]camposTipoEvento = new String [] {"id_tipo_evento","nombre_tipo_evento","estado_tipo_evento"};
    private static final String[]camposEvento = new String [] {"id_evento","id_tipo_evento","nombre_evento","estado_evento"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "HorariosPDM115.s3db";
        private static final int VERSION = 1;
        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE tipo_evento(id_tipo_evento INTEGER PRIMARY KEY AUTOINCREMENT, nombre_tipo_evento VARCHAR(25) NOT NULL, estado_tipo_evento NUMERIC(1) NOT NULL);");
                db.execSQL("CREATE TABLE evento(id_evento INTEGER PRIMARY KEY AUTOINCREMENT, id_tipo_evento INTEGER NOT NULL, nombre_evento VARCHAR(50) NOT NULL, estado_evento NUMERIC(1) NOT NULL, FOREIGN KEY (id_tipo_evento) REFERENCES tipo_evento(id_tipo_evento));");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
        }
    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        //return;
    }
    public void cerrar(){
        DBHelper.close();
    }
    public String insertarTipoEvento(TipoEvento tipoEvento){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues tpEvento = new ContentValues();


        tpEvento.put("nombre_tipo_evento", tipoEvento.getNombre_tipo_evento());
        tpEvento.put("estado_tipo_evento", tipoEvento.getEstado_tipo_evento());

        contador=db.insert("tipo_evento", null, tpEvento);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String insertarEvento(Evento evento){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues event = new ContentValues();
        event.put("nombre_evento", evento.getNombre_evento());
        event.put("estado_evento", evento.getEstado_evento());
        event.put("id_tipo_evento", evento.getId_tipo_evento());

        contador=db.insert("evento", null, event);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizar(TipoEvento tipoEvento){
        if(verificarIntegridad(tipoEvento, 4)){
            String[] id = {tipoEvento.getId_tipo_evento()+""};
            ContentValues cv = new ContentValues();
            cv.put("nombre_tipo_evento", tipoEvento.getNombre_tipo_evento());
            cv.put("estado_tipo_evento", tipoEvento.getEstado_tipo_evento());
            db.update("tipo_evento", cv, "id_tipo_evento = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id tipo de evento " + tipoEvento.getId_tipo_evento() + " no existe";
        }



    }

    public String actualizar(Evento evento){
        return null;
    }

    public String eliminar(TipoEvento tipoEvento){

        if(verificarIntegridad(tipoEvento, 4)){
            String[] id = {tipoEvento.getId_tipo_evento()+""};
            ContentValues cv = new ContentValues();
            cv.put("estado_tipo_evento", 0);
            db.update("tipo_evento", cv, "id_tipo_evento = ?", id);
            return "Registro eliminado Correctamente";
        }else{
            return "Registro con id tipo de evento " + tipoEvento.getId_tipo_evento() + " no existe";
        }

    }

    public String eliminar(Evento evento){
        return null;
    }

    public TipoEvento consultar(int id_tipo_evento){
        String[] id = {id_tipo_evento+""};
        Cursor cursor = db.query("tipo_evento", camposTipoEvento, "id_tipo_evento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setId_tipo_evento(cursor.getInt(0));
            tipoEvento.setNombre_tipo_evento(cursor.getString(1));
            tipoEvento.setEstado_tipo_evento(cursor.getInt(2));
            return tipoEvento;
        }else{
            return null;
        }

    }
    public ArrayList<TipoEvento> consultarTiposEventosActivos() {
        ArrayList<TipoEvento> listado = new ArrayList<TipoEvento>();
        Cursor cursor = db.rawQuery("SELECT * FROM tipo_evento WHERE estado_tipo_evento = 1", null);
        while (cursor.moveToNext()) {
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setId_tipo_evento(cursor.getInt(0));
            tipoEvento.setNombre_tipo_evento(cursor.getString(1));
            tipoEvento.setEstado_tipo_evento(cursor.getInt(2));
            listado.add(tipoEvento);
        }
        cursor.close();
        return listado;


    }

    public Evento consultarEvento(int id_evento){
        return null;
    }

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){

            case 1:
            {
                //Verificar que al insertar un evento exista un tipo evento
                Evento evento = (Evento) dato;
                //Se agregó erl +"" para convertir el ID a String, debido a que el query requiere que el parametrop del id1 sea String
                String[] id1 = {evento.getId_tipo_evento()+""};

                //abrir();
                Cursor cursor1 = db.query("tipo_evento", null, "id_tipo_evento = ?", id1, null, null, null);

                if(cursor1.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 2:
            {
                //verificar que al modificar un evento exista un tipo de evento y un evento
                Evento evento = (Evento) dato;
                String[] ids = {evento.getId_tipo_evento()+"", evento.getId_evento()+""};
                abrir();
                Cursor c = db.query("evento", null, "id_tipo_evento = ? AND id_evento", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 3:
            {
                //Verificador para el tipo de evento
                TipoEvento tipoEvento = (TipoEvento) dato;
                Cursor c = db.query(true, "evento", new String[] {"id_tipo_evento" }, "id_tipo_evento='" + tipoEvento.getId_tipo_evento() + "'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }
            case 4:
            {
                //verificar que exista tipoEvento
                TipoEvento tipoEvento2 = (TipoEvento) dato;
                String[] id = {tipoEvento2.getId_tipo_evento()+""};
                abrir();
                Cursor c2 = db.query("tipo_evento", null, "id_tipo_evento = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro el tipo de evento
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }
}
