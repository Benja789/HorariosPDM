package ues.grupo6.horariospdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.metrics.Event;

import java.util.ArrayList;

import ues.grupo6.horariospdm.ciclo_academico.CicloAcademico;
import ues.grupo6.horariospdm.tipo_ciclo.TipoCiclo;
import ues.grupo6.horariospdm.docente.Docente;
import ues.grupo6.horariospdm.escuela.Escuela;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.tipo_grupo.Tipo_Grupo;


public class ControlBD {

    //Campos Aura

    //Campos Benjamin
    private static final String[]camposDocente = new String [] {"id_docente","docente_primer_nombre","docente_segundo_nombre", "docente_primer_apellido", "docente_segundo_apellido", "docente_apellido_casada", "docente_titulo", "estado_docente"};

    //Campos Cesar

    //Campos Manuel
    private static final String[]camposTipoEvento = new String [] {"id_tipo_evento","nombre_tipo_evento","estado_tipo_evento"};
    private static final String[]camposEvento = new String [] {"id_evento","id_tipo_evento","nombre_evento","estado_evento"};
    private static final String[]camposTipoCiclo = new String [] {"id_tipo_ciclo","nombre_tipo_ciclo","estado_tipo_ciclo"};

    private static final String[]camposCicloAcademico = new String [] {"id_ciclo_academico","id_tipo_ciclo","inicio_ciclo_academico","fin_ciclo_academico","anio_ciclo_academico","estado_ciclo_academico"};
    private static final String[]camposHorario = new String [] {"id_horario","id_solicitud_evento","id_solicitud_horario"};
    private static final String[]camposEstadoHorario = new String [] {"id_estado_horario","id_horario","nombre_estado_horario","estado"};

    //Campos Walter
    private static final String[]camposEscuela = new String [] {"id_escuela","nombre_escuela","prioridad_escuela","estado_escuela"};

    private static final String[]camposTipoGrupo = new String [] {"id_tipo_grupo","nombre_tipo_grupo","estado_tipo_grupo"};


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
            //Tablas Aura

            //Tablas Benjamin

                db.execSQL("create table docente  (\n" +
                        "   id_docente           integer                         not null,\n" +
                        "   docente_primer_nombre varchar2(25)                    not null,\n" +
                        "   docente_segundo_nombre varchar2(25),\n" +
                        "   docente_primer_apellido varchar2(25)                    not null,\n" +
                        "   docente_segundo_apellido varchar2(25),\n" +
                        "   docente_apellido_casada varchar2(25),\n" +
                        "   docente_titulo       varchar2(25)                    not null,\n" +
                        "   estado_docente       number(1)                       not null,\n" +
                        "   constraint pk_docente primary key (id_docente)\n" +
                        ");");
                db.execSQL("create table tipo_funcion  (\n" +
                        "   id_funcion           integer                         not null,\n" +
                        "   nombre_tipo_funcion  varchar2(25)                    not null,\n" +
                        "   estado_tipo_funcion  number(1)                       not null,\n" +
                        "   constraint pk_tipo_funcion primary key (id_funcion)\n" +
                        ");");
                db.execSQL("create table docente_funcion  (\n" +
                        "   id_docente_funcion   integer                         not null,\n" +
                        "   id_docente           integer                         not null,\n" +
                        "   id_funcion           integer                         not null,\n" +
                        "   id_ciclo_academico   integer                         not null,\n" +
                        "   constraint pk_docente_funcion primary key (id_docente_funcion),\n" +
                        "   constraint fk_docente__realiza_docente foreign key (id_docente)\n" +
                        "         references docente (id_docente),\n" +
                        "   constraint fk_docente__es_realiz_tipo_fun foreign key (id_funcion)\n" +
                        "         references tipo_funcion (id_funcion),\n" +
                        "   constraint fk_docente__relations_ciclo_ac foreign key (id_ciclo_academico)\n" +
                        "         references ciclo_academico (id_ciclo_academico)\n" +
                        ");");

            //Tablas Cesar

            //Tablas Manuel
                db.execSQL("CREATE TABLE tipo_evento(id_tipo_evento INTEGER PRIMARY KEY AUTOINCREMENT, nombre_tipo_evento VARCHAR(25) NOT NULL, estado_tipo_evento NUMERIC(1) NOT NULL);");
                db.execSQL("CREATE TABLE evento(id_evento INTEGER PRIMARY KEY AUTOINCREMENT, id_tipo_evento INTEGER NOT NULL, nombre_evento VARCHAR(50) NOT NULL, estado_evento NUMERIC(1) NOT NULL, FOREIGN KEY (id_tipo_evento) REFERENCES tipo_evento(id_tipo_evento));");
                db.execSQL("CREATE TABLE tipo_ciclo(id_tipo_ciclo INTEGER PRIMARY KEY AUTOINCREMENT, nombre_tipo_ciclo VARCHAR(25) NOT NULL, estado_tipo_ciclo NUMERIC(1) NOT NULL);");
                db.execSQL("CREATE TABLE ciclo_academico(id_ciclo_academico INTEGER PRIMARY KEY AUTOINCREMENT, id_tipo_ciclo INTEGER NOT NULL,inicio_ciclo_academico Date NOT NULL, fin_ciclo_academico Date NOT NULL,anio_ciclo_academico Date NOT NULL,estado_ciclo_academico INTEGER NOT NULL, FOREIGN KEY (id_tipo_ciclo) REFERENCES tipo_ciclo(id_tipo_ciclo));");
                db.execSQL("CREATE TABLE horario(id_horario INTEGER PRIMARY KEY AUTOINCREMENT, id_solicitud_evento INTEGER NOT NULL, id_solicitud_horario INTEGER NOT NULL,FOREIGN KEY (id_solicitud_evento) REFERENCES solicitud_evento(id_solicitud_evento),FOREIGN KEY (id_solicitud_horario) REFERENCES solicitud_horario(id_solicitud_horario));");
                db.execSQL("CREATE TABLE estado_horario(id_estado_horario INTEGER PRIMARY KEY AUTOINCREMENT, id_horario INTEGER NOT NULL, nombre_estado_horario VARCHAR(50) NOT NULL, estado NUMERIC(1) NOT NULL, FOREIGN KEY (id_horario) REFERENCES horario(id_horario));");

            //Tablas Walter

                db.execSQL("create table escuela  (\n" +
                        "   id_escuela           integer                         not null,\n" +
                        "   nombre_escuela       varchar2(25)                    not null,\n" +
                        "   prioridad_escuela    numeric(2)                       not null,\n" +
                        "   estado_escuela       numeric(1)                       not null,\n" +
                        "   constraint pk_escuela primary key (id_escuela)\n" +
                        ");");
                db.execSQL("create table asignatura  (\n" +
                        "   id_asignatura        integer                         not null,\n" +
                        "   id_escuela           integer                         not null,\n" +
                        "   nombre_asignatura    varchar2(25)                    not null,\n" +
                        "   codigo_asignatura    varchar2(7)                     not null,\n" +
                        "   estado_asignatura    numeric(1),\n" +
                        "   constraint pk_asignatura primary key (id_asignatura),\n" +
                        "   constraint fk_asignatu_contiene_escuela foreign key (id_escuela)\n" +
                        "         references escuela (id_escuela)\n" +
                        ");");
                db.execSQL("create table tipo_grupo  (\n" +
                        "   id_tipo_grupo        integer                         not null,\n" +
                        "   nombre_tipo_grupo    varchar2(25)                    not null,\n" +
                        "   estado_tipo_grupo    numeric(1)                      not null,\n" +
                        "   constraint pk_tipo_grupo primary key (id_tipo_grupo)\n" +
                        ");");
                db.execSQL("create table grupo  (\n" +
                        "   id_grupo             integer                         not null,\n" +
                        "   id_tipo_grupo        integer                         not null,\n" +
                        "   id_asignatura        integer                         not null,\n" +
                        "   id_docente           integer                         not null,\n" +
                        "   num_grupo            numeric(2)                      not null,\n" +
                        "   estado_grupo         numeric(1)                      not null,\n" +
                        "   constraint pk_grupo primary key (id_grupo),\n" +
                        "   constraint fk_grupo_contiene__tipo_gru foreign key (id_tipo_grupo)\n" +
                        "         references tipo_grupo (id_tipo_grupo),\n" +
                        "   constraint fk_grupo_relations_asignatu foreign key (id_asignatura)\n" +
                        "         references asignatura (id_asignatura),\n" +
                        "   constraint fk_grupo_relations_docente foreign key (id_docente)\n" +
                        "         references docente (id_docente)\n" +
                        ");");
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

    //CRUD Aura

    //CRUD Benja

    public  String insertarDocente ( Docente dataTeacher) {
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues docenteData = new ContentValues();
        docenteData.put("docente_primer_nombre",dataTeacher.getFirstName());
        docenteData.put("docente_segundo_nombre",dataTeacher.getSecondName());
        docenteData.put("docente_primer_apellido",dataTeacher.getFirstLastName());
        docenteData.put("docente_segundo_apellido",dataTeacher.getSecondLastName());
        docenteData.put("docente_apellido_casada", dataTeacher.getMarriedName());
        docenteData.put("docente_titulo",dataTeacher.getProfession());
        if ( dataTeacher.getActive()) docenteData.put("estado_docente", 1);
        else  docenteData.put("estado_docente", 0);

        contador=db.insert("docente", null, docenteData);
        if(contador==-1 || contador==0) regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        else  regInsertados=regInsertados+contador;
        return regInsertados;
    }

    //CRUD Cesar

    //CRUD Manuel

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
        if(contador==-1 || contador==0) regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        else regInsertados=regInsertados+contador;
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

    public String actualizarEvento(Evento evento){
        if(verificarIntegridad(evento, 41)){
            String[] id = {evento.getId_evento()+""};
            ContentValues cv = new ContentValues();
            cv.put("nombre_evento", evento.getNombre_evento());
            cv.put("id_tipo_evento", evento.getId_tipo_evento());
            cv.put("estado_evento", evento.getEstado_evento());
            db.update("evento", cv, "id_evento = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id evento " + evento.getId_evento() + " no existe";
        }
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

    public String eliminarEvento(Evento evento){
        if(verificarIntegridad(evento, 41)){
            String[] id = {evento.getId_evento()+""};
            ContentValues cv = new ContentValues();
            cv.put("estado_evento", 0);
            db.update("evento", cv, "id_evento = ?", id);
            return "Registro eliminado Correctamente";
        }else{
            return "Registro con id evento " + evento.getId_evento() + " no existe";
        }
    }

    public TipoEvento consultarTipoEvento(int id_tipo_evento){
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
        String[] id = {id_evento+""};
        Cursor cursor = db.query("evento", camposEvento, "id_evento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Evento evento = new Evento();
            evento.setId_evento(cursor.getInt(0));
            evento.setId_tipo_evento(cursor.getInt(1));
            evento.setNombre_evento(cursor.getString(2));
            evento.setEstado_evento(cursor.getInt(3));
            return evento;
        }else{
            return null;
        }
    }


    //CRUD Walter

        //Escuela
    public String insertar(Escuela escuela){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues esc = new ContentValues();
        esc.put("nombre_escuela", escuela.getNombre_escuela());
        esc.put("prioridad_escuela", escuela.getPrioridad_esccuela());
        esc.put("estado_escuela", escuela.getEstado_escuela());

        contador=db.insert("escuela", null, esc);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public Escuela consultarEscuela(int id_escuela){
        String[] id = {id_escuela+""};
        Cursor cursor = db.query("escuela", camposEscuela, "id_escuela = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Escuela escuela = new Escuela();
            escuela.setId_escuela(cursor.getInt(0));
            escuela.setNombre_escuela(cursor.getString(1));
            escuela.setPrioridad_esccuela(cursor.getInt(2));
            escuela.setEstado_escuela(cursor.getInt(3));
            return escuela;
        }else{
            return null;
        }
    }
    public String actualizarEscuela(Escuela escuela){
        if(verificarIntegridad(escuela, 50)){
            String[] id = {escuela.getId_escuela()+""};
            ContentValues cv = new ContentValues();
            cv.put("nombre_escuela", escuela.getNombre_escuela());
            cv.put("prioridad_escuela", escuela.getPrioridad_esccuela());
            cv.put("estado_escuela", escuela.getEstado_escuela());
            db.update("escuela", cv, "id_escuela = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id Escuela " + escuela.getId_escuela() + " no existe";
        }
    }
    public String eliminarEscuela(Escuela escuela){

        if(verificarIntegridad(escuela, 50)){
            String[] id = {escuela.getId_escuela()+""};
            ContentValues cv = new ContentValues();
            cv.put("estado_escuela", 0);
            db.update("escuela", cv, "id_escuela = ?", id);
            return "Registro eliminado Correctamente";
        }else{
            return "Registro con id Escuela " + escuela.getId_escuela() + " no existe";
        }
    }

        //Tipo Grupo
    public String insertar(Tipo_Grupo tipoGrupo){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues tpGrupo = new ContentValues();
        tpGrupo.put("nombre_tipo_grupo", tipoGrupo.getNombre_tipo_grupo());
        tpGrupo.put("estado_tipo_grupo", tipoGrupo.getEstado_tipo_grupo());

        contador=db.insert("tipo_grupo", null, tpGrupo);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public Docente consultarDocente (int idDocente) {
        String[] id = {idDocente+""};
        Cursor cursor = db.query("docente", camposDocente, "id_docente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente = new Docente();
            docente.setIdDocente(cursor.getInt(0));
            docente.setFirstName(cursor.getString(1));
            docente.setSecondName(cursor.getString(2));
            docente.setFirstLastName(cursor.getString(3));
            docente.setSecondLastName(cursor.getString(4));
            docente.setMarriedName(cursor.getString(5));
            docente.setProfession(cursor.getString(6));
            docente.setActive(cursor.getInt(7));
            return docente;
        }else{
            return null;
        }
    }
    public String eliminateDocent(Docente docente){

        if(verificarIntegridad(docente, 20)){
            String[] id = {docente.getIdDocente()+""};
            ContentValues cv = new ContentValues();
            cv.put("id_docente", 0);
            db.update("docente", cv, "id_docente = ?", id);
            return "Registro eliminado Correctamente";
        } else {
            return "Docente con el ID " + docente.getIdDocente() + " no existe";
        }
    }

    public Boolean updateTeacher ( Docente docente ) {
        if(verificarIntegridad(docente, 20)){
            String[] id = {docente.getIdDocente()+""};
            ContentValues cv = new ContentValues();
            cv.put("docente_primer_nombre", docente.getFirstName());
            cv.put("docente_segundo_nombre", docente.getSecondName());
            cv.put("docente_primer_apellido", docente.getFirstLastName());
            cv.put("docente_segundo_apellido", docente.getSecondLastName());
            cv.put("docente_apellido_casada", docente.getMarriedName());
            cv.put("docente_titulo", docente.getProfession());
            cv.put("estado_docente", docente.getActive());
            db.update("docente", cv, "id_docente = ?", id);
            return true;
        } else return false;
    }

    public Tipo_Grupo consultarTipoGrupo(int id_tipo_grupo){
        String[] id = {id_tipo_grupo+""};
        Cursor cursor = db.query("tipo_grupo", camposTipoGrupo, "id_tipo_grupo = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Tipo_Grupo tipoGrupo = new Tipo_Grupo();
            tipoGrupo.setId_tipo_grupo(cursor.getInt(0));
            tipoGrupo.setNombre_tipo_grupo(cursor.getString(1));
            tipoGrupo.setEstado_tipo_grupo(cursor.getInt(2));
            return tipoGrupo;
        }else{
            return null;
        }
    }
    public String actualizarTipoGrupo(Tipo_Grupo tipoGrupo){
        if(verificarIntegridad(tipoGrupo, 530)){
            String[] id = {tipoGrupo.getId_tipo_grupo()+""};
            ContentValues cv = new ContentValues();
            cv.put("nombre_tipo_grupo", tipoGrupo.getNombre_tipo_grupo());
            cv.put("estado_tipo_grupo", tipoGrupo.getEstado_tipo_grupo());
            db.update("tipo_grupo", cv, "id_tipo_grupo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con id tipo de grupo " + tipoGrupo.getId_tipo_grupo() + " no existe";
        }
    }
    public String eliminarTipoGrupo(Tipo_Grupo tipoGrupo){

        if(verificarIntegridad(tipoGrupo, 530)){
            String[] id = {tipoGrupo.getId_tipo_grupo()+""};
            ContentValues cv = new ContentValues();
            cv.put("estado_tipo_grupo", 0);
            db.update("tipo_grupo", cv, "id_tipo_grupo = ?", id);
            return "Registro eliminado Correctamente";
        }else{
            return "Registro con id tipo de evento " + tipoGrupo.getId_tipo_grupo() + " no existe";
        }
    }

    //INTEGRIDAD DE LOS DATOS
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){

        //Case tablas Aura (empiezan con 1, ejemplo 10,11,110,111)

        //Case tablas Cesar (empiezan con 3, ejemplo 30,31,310,311)

        //Case tablas Manuel (empiezan con 4, ejemplo 40,41,410,411)

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
            case 41:
            {
                //verificar que exista tipoEvento
                Evento evento = (Evento) dato;
                String[] id = {evento.getId_tipo_evento()+""};
                abrir();
                Cursor c2 = db.query("evento", null, "id_evento = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro el evento
                    return true;
                }
                return false;
            }
            //Case tablas Benjamin (empiezan con 2, ejemplo 20,21,210,211)
            case 20: {
                Docente docente = (Docente) dato;
                String[] id = {docente.getIdDocente()+""};
                abrir();
                Cursor c2 = db.query("docente", null, "id_docente = ?", id, null, null, null);
                //Se encontro el tipo de evento
                return c2.moveToFirst();
            }
        //Case tablas Walter (empiezan con 5, ejemplo 50,51,510,511)
            case 50:  {
                //verificar que exista tipoGrupo
                Tipo_Grupo tipoGrupo = (Tipo_Grupo) dato;
                String[] id = {tipoGrupo.getId_tipo_grupo()+""};
                abrir();
                Cursor c2 = db.query("tipo_grupo", null, "id_tipo_grupo = ?", id, null, null, null);
                return c2.moveToFirst();
            }
            default: {
                return false;
            }
        }
    }
}
