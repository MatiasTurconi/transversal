package Vistas;

import accesoADatos.AlumnoData;
import accesoADatos.InscripcionData;
import accesoADatos.MateriaData;
import entidades.Alumno;
import entidades.Materia;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args){
        AlumnoData alumD=new AlumnoData();///CONEXION
//        System.out.println(alumD.buscarAlumno(3));
//        Alumno alum=new Alumno(37648595,"Manuel","Rodriguez",LocalDate.of(1995, 05, 20),true);///ALUMNO SIN ID PARA CARGAR
//        Alumno alum=new Alumno(4,37648595,"Manuel","Rodriguez",LocalDate.of(1995, 05, 20),false);///ALUMNO CON ID PARA MODIFICAR
//        alumD.guardarAlumno(alum);///CARGAR ALUMNO
//        alumD.modificarAlumno(alum);///MODIFICAR ALUMNO
//        System.out.println(alumD.buscarAlumno(4));///BUSCAR ALUMNO CON ID
//        System.out.println(alumD.buscarAlumnoPorDNI(37465234));///BUSCAR ALUMNO CON DNI
//        alumD.eliminarAlumno(2);
//        for (Alumno alumno : alumD.alumnosInscriptos()) {
//            System.out.println(alumno);
//        }
        
        InscripcionData insD=new InscripcionData();///CONEXION
//        insD.modificarInscripcion(1, 1, 4, 1);
        
        MateriaData matD=new MateriaData();///CONEXION
//        System.out.println(matD.buscarMateria(2));
//        matD.eliminarMateria(2);
//        for(Materia materia:matD.listarMaterias()){
//            System.out.println(materia);
//        }
    }
}
