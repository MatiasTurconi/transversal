package Vistas;

import accesoADatos.AlumnoData;
import accesoADatos.InscripcionData;
import entidades.Alumno;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args){
        AlumnoData alumD=new AlumnoData();///CONEXION
        //Alumno alum=new Alumno(37648595,"Manuel","Rodriguez",LocalDate.of(1995, 05, 20),true);///ALUMNO SIN ID PARA CARGAR
        //Alumno alum=new Alumno(4,37648595,"Manuel","Rodriguez",LocalDate.of(1997, 05, 20),false);///ALUMNO CON ID PARA MODIFICAR
        //alumD.guardarAlumno(alum);///CARGAR ALUMNO
        //alumD.modificarAlumno(alum);///MODIFICAR ALUMNO
        //System.out.println(alumD.buscarAlumno(6));///BUSCAR ALUMNO CON ID
        //alumD.bajaAlumno(1);
        
        for (Map.Entry<Integer, Alumno> en : alumD.alumnosInscriptos().entrySet()) {
            Alumno alum = en.getValue();
            System.out.println(alum);
        }
        
        InscripcionData insD=new InscripcionData();
        insD.modificarInscripcion(1, 1, 4, 1);
    }
}
