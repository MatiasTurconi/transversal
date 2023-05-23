package accesoADatos;

import entidades.Materia;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class MateriaData {
    private Connection con;

    public MateriaData() {
        con = Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia) {
        String sql="INSERT INTO materia(nombre,año,estado) VALUES (?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
            }else{
                System.out.println("No se cargo la materia");
            }
        } catch (SQLException e) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void actualizarMateria(Materia materia){
        String sql="UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNacimiento=?,estado=? WHERE idAlumno=?";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getAño());
                ps.setBoolean(3, materia.isEstado());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public Materia buscarMateria(int id){
        Materia mate=null;
        String sql="SELECT * FROM materia WHERE idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                mate=new Materia();
                mate.setNombre(rs.getString("nombre"));
                mate.setAño(rs.getInt("año"));
                mate.setEstado(rs.getBoolean("estado"));
            }else{
                System.out.println("Materia no encontrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mate;
    }
    public void bajaMateria(int id){
        String sql="UPDATE materia SET estado=? WHERE idMateria=?";
        try{
            PreparedStatement ps;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            if(ps.executeUpdate()==1){
                System.out.println("modificado");
            }else{
                System.out.println("no encontrado");
            }
            ps.close();
        }catch(SQLException exep){
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, exep);
        }
    }
}