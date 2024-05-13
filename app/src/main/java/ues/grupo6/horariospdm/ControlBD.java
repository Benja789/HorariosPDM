package ues.grupo6.horariospdm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ues.grupo6.horariospdm.ciclo_academico.CicloAcademico;
import ues.grupo6.horariospdm.tipo_ciclo.TipoCiclo;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;
import ues.grupo6.horariospdm.evento.Evento;


public class ControlBD {
    private static final String[]camposTipoEvento = new String [] {"id_tipo_evento","nombre_tipo_evento","estado_tipo_evento"};
    private static final String[]camposEvento = new String [] {"id_evento","id_tipo_evento","nombre_evento","estado_evento"};
    private static final String[]camposTipoCiclo = new String [] {"id_tipo_ciclo","nombre_tipo_ciclo","estado_tipo_ciclo"};

    private static final String[]camposCicloAcademico = new String [] {"id_ciclo_academico","id_tipo_ciclo","inicio_ciclo_academico","fin_ciclo_academico","anio_ciclo_academico","estado_ciclo_academico"};
    private static final String[]camposHorario = new String [] {"id_horario","id_solicitud_evento","id_solicitud_horario"};
    private static final String[]camposEstadoHorario = new String [] {"id_estado_horario","id_horario","nombre_estado_horario","estado"};

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
                db.execSQL("CREATE TABLE tipo_ciclo(id_tipo_ciclo INTEGER PRIMARY KEY AUTOINCREMENT, nombre_tipo_ciclo VARCHAR(25) NOT NULL, estado_tipo_ciclo NUMERIC(1) NOT NULL);");
                db.execSQL("CREATE TABLE ciclo_academico(id_ciclo_academico INTEGER PRIMARY KEY AUTOINCREMENT, id_tipo_ciclo INTEGER NOT NULL,inicio_ciclo_academico Date NOT NULL, fin_ciclo_academico Date NOT NULL,anio_ciclo_academico Date NOT NULL,estado_ciclo_academico INTEGER NOT NULL, FOREIGN KEY (id_tipo_ciclo) REFERENCES tipo_ciclo(id_tipo_ciclo));");
                db.execSQL("CREATE TABLE horario(id_horario INTEGER PRIMARY KEY AUTOINCREMENT, id_solicitud_evento INTEGER NOT NULL, id_solicitud_horario INTEGER NOT NULL,FOREIGN KEY (id_solicitud_evento) REFERENCES solicitud_evento(id_solicitud_evento),FOREIGN KEY (id_solicitud_horario) REFERENCES solicitud_horario(id_solicitud_horario));");
                db.execSQL("CREATE TABLE estado_horario(id_estado_horario INTEGER PRIMARY KEY AUTOINCREMENT, id_horario INTEGER NOT NULL, nombre_estado_horario VARCHAR(50) NOT NULL, estado NUMERIC(1) NOT NULL, FOREIGN KEY (id_horario) REFERENCES horario(id_horario));");
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
        public String insertarTipoCiclo(TipoCiclo tipoCiclo){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues tpCiclo = new ContentValues();


            tpCiclo.put("nombre_tipo_evento", tipoCiclo.getNombre_tipo_ciclo());
            tpCiclo.put("estado_tipo_evento", tipoCiclo.getEstado_tipo_ciclo());

        contador=db.insert("tipo_ciclo", null, tpCiclo);
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

    public String eliminar(TipoCiclo tipoCiclo){

        if(verificarIntegridad(tipoCiclo, 4)){
            String[] id = {tipoCiclo.getId_tipo_ciclo()+""};
            ContentValues cv = new ContentValues();
            cv.put("estado_tipo_ciclo", 0);
            db.update("tipo_ciclo", cv, "id_tipo_ciclo = ?", id);
            return "Registro eliminado Correctamente";
        }else{
            return "Registro con id tipo de ciclo " + tipoCiclo.getId_tipo_ciclo() + " no existe";
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
