package accesoADatos;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class InscripcionData {
    private Connection con;

    public InscripcionData() {
        con = Conexion.getConexion();
    }

    public void guardarInscripcion(Inscripcion inscrip){    ///GUARDAR ALUMNO
        boolean aux=true;
        for (Inscripcion inscrip1 : this.obtenerInscripciones()) {
            if(inscrip.getMateria().getIdMateria()==inscrip1.getMateria().getIdMateria()&&inscrip.getAlumno().getIdAlumno()==inscrip1.getAlumno().getIdAlumno()){
                aux=false;
            }
        }
        if(aux){
            String sql="INSERT INTO inscripcion(nota,idAlumno,idMateria) VALUES (?,?,?)";
            try {
                PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, inscrip.getNota());
                ps.setInt(2, inscrip.getAlumno().getIdAlumno());
                ps.setInt(3, inscrip.getMateria().getIdMateria());
                ps.executeUpdate();
                ResultSet rs=ps.executeQuery();
                System.out.println("Inscripcion exitosa");
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Inscripto Correctamente");
            System.out.println("Inscripto correctamente");
        }else{
            System.out.println("No se pudo inscribir");
        }
    }
    
    public void actualizarNota(int idAlum,int idMat,int nota){  ///ACTUALIZAR NOTA DE ALUMNO, POR ID ALUMNO Y ID MATERIA
        String sql="UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nota);
            ps.setInt(2,idAlum);
            ps.setInt(3, idMat);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){    ///OBTENER TODAS LAS INSCRIPCIONES
        List<Inscripcion> inscrip=new ArrayList<>();
        String sql="SELECT * FROM inscripcion,alumno,materia WHERE inscripcion.idAlumno=alumno.idAlumno AND alumno.estado=true AND inscripcion.idMateria=materia.idMateria AND materia.estado=true";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                inscrip.add(new Inscripcion(rs.getInt("idInscripto"),rs.getInt("nota"),
                        new Alumno(rs.getInt("alumno.idAlumno"),rs.getInt("alumno.dni"),rs.getString("alumno.apellido"),rs.getString("alumno.nombre"),rs.getDate("alumno.fechaNacimiento").toLocalDate(),rs.getBoolean("alumno.estado")),
                        new Materia(rs.getInt("materia.idMateria"),rs.getString("materia.nombre"),rs.getInt("materia.año"),rs.getBoolean("materia.estado"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscrip;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){ ///BORRAR INSCRIPCION
        boolean aux=false;
        for (Inscripcion inscrip1 : this.obtenerInscripciones()) {
            if(idMateria==inscrip1.getMateria().getIdMateria()&&idAlumno==inscrip1.getAlumno().getIdAlumno()){
                aux=true;
            }
        }
        if(aux){
        String sql="DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
            try {
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1, idAlumno);
                ps.setInt(2, idMateria);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Inscripcion anulada");
            System.out.println("Inscripcion anulada");
        }else{
            System.out.println("Inscripcion no encontrada");
        }
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){ ///OBTENER INSCRIPCIONES POR ID ALUMNO
        List<Inscripcion> inscrip=new ArrayList<>();
        String sql="SELECT * FROM inscripcion,alumno,materia WHERE inscripcion.idAlumno=? AND alumno.idAlumno=inscripcion.idAlumno AND alumno.estado=true AND materia.idMateria=inscripcion.IdMateria AND materia.estado=true";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                inscrip.add(new Inscripcion(rs.getInt("inscripcion.idInscripto"),rs.getInt("inscripcion.nota"),
                        new Alumno(rs.getInt("alumno.idAlumno"),rs.getInt("alumno.dni"),rs.getString("alumno.apellido"),rs.getString("alumno.nombre"),rs.getDate("alumno.fechaNacimiento").toLocalDate(),rs.getBoolean("alumno.estado")),
                        new Materia(rs.getInt("materia.idMateria"),rs.getString("materia.nombre"),rs.getInt("materia.año"),rs.getBoolean("materia.estado"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscrip;
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){   ///OBTENER MATERIAS CURSADAS ESTADO TRUE(1)
        List<Materia> mat=new ArrayList<>();
        String sql="SELECT materia.* FROM inscripcion,materia WHERE inscripcion.idAlumno=? AND materia.idMateria=inscripcion.idMateria AND materia.estado=true";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                mat.add(new Materia(rs.getInt("idMateria"),rs.getString("nombre"),rs.getInt("año"),rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mat;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){ ///OBTENER MATERIAS NO CURSADAS ESTADO TRUE(1)
        List<Materia> mat=new ArrayList<>();
        String sql="SELECT materia.* FROM materia WHERE estado=true AND idMateria NOT IN ( SELECT inscripcion.idMateria FROM inscripcion WHERE inscripcion.idAlumno=?);";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                mat.add(new Materia(rs.getInt("idMateria"),rs.getString("nombre"),rs.getInt("año"),rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mat;
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int id){   ///OBTENER ALUMNOS POR ID MATERIA Y ESTADO TRUE(1) DE ALUMNO
        List<Alumno> alum=new ArrayList<>();
        String sql="SELECT alumno.* FROM inscripcion,alumno,materia WHERE inscripcion.idMateria=? AND materia.idMateria=inscripcion.idMateria AND materia.estado=true AND alumno.idAlumno=inscripcion.idAlumno AND alumno.estado=true";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                alum.add(new Alumno(rs.getInt("idAlumno"),rs.getInt("dni"),rs.getString("apellido"),rs.getString("nombre"),rs.getDate("fechaNacimiento").toLocalDate(),rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alum;
    }
}
