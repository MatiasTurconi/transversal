package accesoADatos;

import static Vistas.Main.alumD;
import static Vistas.Main.matD;
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

/**
 *
 * @author Usuario
 */
public class InscripcionData {
    private Connection con;

    public InscripcionData() {
        con = Conexion.getConexion();
    }

    public void guardarInscripcion(Inscripcion inscrip){
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
            System.out.println("Inscripto correctamente");
        }else{
            System.out.println("No se pudo inscribir");
        }
    }
    
    public void actualizarNota(int idAlum,int idMat,int nota){
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
    
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> inscrip=new ArrayList<>();
        String sql="SELECT * FROM inscripcion";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Alumno alum=alumD.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat=matD.buscarMateria(rs.getInt("idMateria"));
                inscrip.add(new Inscripcion(rs.getInt("idInscripto"),rs.getInt("nota"),alum,mat));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscrip;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){
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
            System.out.println("Inscripcion borrada");
        }else{
            System.out.println("Inscripcion no encontrada");
        }
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        List<Inscripcion> inscrip=new ArrayList<>();
        
        return inscrip;
    }
    
}
