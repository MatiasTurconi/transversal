package Vistas;

import Control.AlumnoData;
import Control.MateriaData;
import Modelo.Alumno;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args){
        //Alumno alum=new Alumno(37648595,"Manuel","Rodriguez",LocalDate.of(1995, 05, 20),true);///ALUMNO SIN ID PARA CARGAR
        //Alumno alum=new Alumno(4,37648595,"Manuel","Rodriguez",LocalDate.of(1997, 05, 20),true);///ALUMNO CON ID PARA MODIFICAR
        AlumnoData alumD=new AlumnoData();///CONEXION
        //alumD.guardarAlumno(alum);///CARGAR ALUMNO
        //alumD.actualizarAlumno(alum);///MODIFICAR ALUMNO
        System.out.println(alumD.buscarAlumno(4));///BUSCAR ALUMNO CON ID
        //MateriaData mat = new MateriaData();
        //mat.bajaMateria(2, false);
    }
}
