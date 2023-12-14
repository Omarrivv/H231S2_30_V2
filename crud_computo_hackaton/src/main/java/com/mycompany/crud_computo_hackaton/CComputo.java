/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_computo_hackaton;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class CComputo {
    
    int codigo;
    String tipoEquipo;
    String nombreSerie;
    String marca;
    String modelo;
    String EspesificacionesTecnicas;
    String estadoMantenimiento;

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEspesificacionesTecnicas() {
        return EspesificacionesTecnicas;
    }

    public void setEspesificacionesTecnicas(String EspesificacionesTecnicas) {
        this.EspesificacionesTecnicas = EspesificacionesTecnicas;
    }

    public String getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(String estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }
    
    public void InsertarAlumno(JTextField paramNumeroSerie, JTextField paramMarca, JTextField paramModelo, JTextField paramEspesificacionesTecnicas, JTextField paramEstadoMantenimiento, JTextField paramTipoEquipo){
        setNombreSerie(paramNumeroSerie.getText());
        setMarca(paramMarca.getText());
        setModelo(paramModelo.getText());
        setEspesificacionesTecnicas(paramEspesificacionesTecnicas.getText());
        setEstadoMantenimiento(paramEstadoMantenimiento.getText());
        setTipoEquipo(paramTipoEquipo.getText());
        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO Equipos (tipoEquipo, Marca, Modelo, NumeroSerie, EspecificacionesTecnicas, EstadoMantenimiento) VALUES (?,?,?,?,?,?)";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getTipoEquipo());  // con el set incoroporo el valor y con el get obtengo el valor
            cs.setString(2, getMarca());
            cs.setString(3, getModelo());
            cs.setString(4, getNombreSerie());
            cs.setString(5, getEspesificacionesTecnicas());
            cs.setString(6, getEstadoMantenimiento());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente ala Persona");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno error " + e.toString());
        }
    }
    
    public void MostrarAlumnos(JTable paramTablaListaComputo){
        CConexion objetoConeccion = new CConexion(); // crear coeccion un objeto preparacion
        DefaultTableModel modelo = new DefaultTableModel();
        // TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo); //si le hago click en la cabeserapara q se ordena alfabeticamente como un excel
        // paramTablaListaComputo.setRowSorter(OrdenarTabla); // incorpara el orden de cabesera a :: paramTablaTotalAlumnos
        String sql="";
        modelo.addColumn("Id");
        modelo.addColumn("tipoEquipo");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("NumeroSerie");
        modelo.addColumn("EspecificacionesTecnicas");
        modelo.addColumn("EstadoMantenimiento");
        paramTablaListaComputo.setModel(modelo);
        sql = "select * from Equipos";
        
        String[] datos = new String[7]; //llenar depende de la consulta q aya seleccionado[1,2,3,4,5,6]
        Statement st; // preparando para recien ejecutar
        // longitud de 3
        try {
            st = objetoConeccion.estableceConexion().createStatement(); // realizando la conexion
            ResultSet rs = st.executeQuery(sql); // ejecutar
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                modelo.addRow(datos);         
            }   
            paramTablaListaComputo.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros error " + e.toString());
        }
    }
    
    public void SeleccionarAlumno(JTable paramTablaListaComputo, JTextField paramId, JTextField paramNombreSerie, JTextField paramMarca, JTextField paramModelo, JTextField paramEspecificacionesTecnicas, JTextField paramEstadoManteniiento, JTextField paramTipoEquipo){
        try {
            int fila = paramTablaListaComputo.getSelectedRow();
            if (fila>=0){
                paramId.setText(paramTablaListaComputo.getValueAt(fila, 0).toString());
                paramTipoEquipo.setText(paramTablaListaComputo.getValueAt(fila, 1).toString());
                paramMarca.setText(paramTablaListaComputo.getValueAt(fila, 2).toString());
                paramModelo.setText(paramTablaListaComputo.getValueAt(fila, 3).toString());
                paramNombreSerie.setText(paramTablaListaComputo.getValueAt(fila, 4).toString());
                paramEspecificacionesTecnicas.setText(paramTablaListaComputo.getValueAt(fila, 5).toString());
                paramEstadoManteniiento.setText(paramTablaListaComputo.getValueAt(fila, 6).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada Error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Selleccion : Error : " + e.toString());
        }
        
    }
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombreSerie, JTextField paramMarca, JTextField paramModelo, JTextField paramEspecificacionesTecnicas, JTextField paramEstadoMantenimiento, JTextField paramTipoEquipo){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreSerie(paramNombreSerie.getText());
        setMarca(paramMarca.getText());
        setModelo(paramModelo.getText());
        setEspesificacionesTecnicas(paramEspecificacionesTecnicas.getText());
        setEstadoMantenimiento(paramEstadoMantenimiento.getText());
        setTipoEquipo(paramTipoEquipo.getText());
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Equipos SET tipoEquipo = ?, Marca = ?, Modelo = ?, NumeroSerie = ?, EspecificacionesTecnicas = ?, EstadoMantenimiento = ? WHERE ID=?;";        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getTipoEquipo());
            cs.setString(2, getMarca());
            cs.setString(3, getModelo());
            cs.setString(4, getNombreSerie());
            cs.setString(5, getEspesificacionesTecnicas());
            cs.setString(6, getEstadoMantenimiento());
            cs.setInt(7, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Modifico , error: " + e.toString());
        }
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText())); 
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM Equipos WHERE ID=?;";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Elimino Correctamente Error : " + e.toString());
        }
        
    }
    
    

}
