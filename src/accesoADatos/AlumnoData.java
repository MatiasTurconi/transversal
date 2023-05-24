package accesoADatos;

import entidades.Alumno;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        if(this.buscarAlumnoPorDNI(alumno.getDni()) == null){
            String sql="INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado) VALUES (?,?,?,?,?)";
            try {
                PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, alumno.getDni());
                ps.setString(2, alumno.getApellido());
                ps.setString(3, alumno.getNombre());
                ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
                ps.setBoolean(5, alumno.isEstado());
                ps.executeUpdate();
            } catch (SQLException e) {
                Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
            }
            System.out.println("Alumno cargado");
        }else{
            System.out.println("No se cargo el alumno");
        }

    }
    
    public void modificarAlumno(Alumno alumno){
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
            System.out.println("Alumno modificado");
        } catch (SQLException e) {
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
    
    public Alumno buscarAlumnoPorDNI(int dni){
        Alumno alum=null;
        String sql="SELECT * FROM alumno WHERE dni=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, dni);
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
    
    public void eliminarAlumno(int id){
        String sql="UPDATE alumno SET estado=? WHERE idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            if(ps.executeUpdate()==1){
                System.out.println("Alumno eliminado");
            }else{
                System.out.println("Alumno no encontrado");
            }
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public List<Alumno> alumnosInscriptos(){
        List<Alumno> alumnos=new ArrayList<>();
        String sql="SELECT * FROM alumno WHERE estado=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                alumnos.add(new Alumno(rs.getInt("idAlumno"),rs.getInt("dni"),rs.getString("apellido"),rs.getString("nombre"),rs.getDate("fechaNacimiento").toLocalDate(),rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        if (alumnos.size()==0) {
            System.out.println("No hay alumnos inscriptos");
        }
        return alumnos;
    }
}
