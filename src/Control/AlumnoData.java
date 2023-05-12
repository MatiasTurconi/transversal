package Control;

import Modelo.Alumno;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class AlumnoData {
    private Connection con;

    public AlumnoData() {
        con = Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno) {
        String sql="INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
            }else{
                System.out.println("No se cargo el alumno");
            }
        } catch (Exception e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void actualizarAlumno(Alumno alumno){
        String sql="UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNacimiento=?,estado=? WHERE idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno alum=null;
        String sql="SELECT * FROM alumno WHERE idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                alum=new Alumno();
                alum.setIdAlumno(rs.getInt("idAlumno"));
                alum.setDni(rs.getInt("dni"));
                alum.setApellido(rs.getString("apellido"));
                alum.setNombre(rs.getString("nombre"));
                alum.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alum.setEstado(rs.getBoolean("estado"));
            }else{
                System.out.println("Alumno no encontrado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alum;
    }
}