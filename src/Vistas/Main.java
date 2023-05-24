package Vistas;

import accesoADatos.AlumnoData;
import accesoADatos.InscripcionData;
import accesoADatos.MateriaData;
import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Main {
    
    public static void main(String[] args){
        AlumnoData alumD=new AlumnoData();///CONEXION AlumnoData
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

        MateriaData matD=new MateriaData();///CONEXION MateriaData
        Materia mat=new Materia("Matematica 2",3,true);
//        System.out.println(matD.buscarMateria(2));
//        matD.eliminarMateria(2);
//        for(Materia materia:matD.listarMaterias()){
//            System.out.println(materia);
//        }
        
        InscripcionData insD=new InscripcionData();///CONEXION InscripcionData
//        Inscripcion inscrip=new Inscripcion(0,alumD.buscarAlumno(1),matD.buscarMateria(1));///PARA CARGAR INSCRIPCION
//        insD.guardarInscripcion(inscrip);///CARGAR INSCRIPCION
//        insD.borrarInscripcionMateriaAlumno(1, 1);///BORRAR INSCRIPCION
//        insD.actualizarNota(1, 4, 0);///ACTUALIZAR NOTA
    }
}
