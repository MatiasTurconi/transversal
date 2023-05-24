package accesoADatos;

import entidades.Materia;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public void modificarMateria(Materia materia){
        String sql="UPDATE alumno SET nombre=?,año=?,estado=? WHERE idMateria=?";
        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getAño());
                ps.setBoolean(3, materia.isEstado());
                ps.setInt(4, materia.getIdMateria());
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
                mate.setIdMateria(rs.getInt("idMateria"));
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
    public void eliminarMateria(int id){
        String sql="UPDATE materia SET estado=? WHERE idMateria=?";
        try{
            PreparedStatement ps;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            if(ps.executeUpdate()==1){
                System.out.println("Materia eliminada");
            }else{
                System.out.println("Materia no encontrada");
            }
            ps.close();
        }catch(SQLException exep){
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, exep);
        }
    }
    
    public List<Materia> listarMaterias(){
        List<Materia> materias=new ArrayList<>();
        String sql="SELECT * FROM materia WHERE estado=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                materias.add(new Materia(rs.getInt("idMateria"),rs.getString("nombre"),rs.getInt("año"),rs.getBoolean("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        if (materias.size()==0) {
            System.out.println("No hay materias");
        }
        return materias;
    }
}