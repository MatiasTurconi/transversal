package accesoADatos;

import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql="INSERT INTO inscripcion(nota,idAlumno,idMateria) VALUES (?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscrip.getNota());
            ps.setInt(2, inscrip.getAlumno().getIdAlumno());
            ps.setInt(3, inscrip.getMateria().getIdMateria());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                System.out.println("Inscripcion exitosa");
            }else{
                System.out.println("No se pudo inscribir");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }
    
    public void modificarInscripcion(int nota,int idAlum,int idMat,int idInscripto){
        String sql="UPDATE inscripcion SET nota=?,idAlumno=?,idMateria=? WHERE idInscripto=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nota);
            ps.setInt(2,idAlum);
            ps.setInt(3, idMat);
            ps.setInt(4, idInscripto);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public void modificarNota(int nota,int idAlum,int idMat,int idInscripto){
        String sql="UPDATE inscripcion SET nota=?,idAlumno=?,idMateria=? WHERE idInscripto=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, nota);
            ps.setInt(2,idAlum);
            ps.setInt(3, idMat);
            ps.setInt(4, idInscripto);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    
}
