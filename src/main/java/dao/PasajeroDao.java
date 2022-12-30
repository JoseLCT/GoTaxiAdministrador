package dao;

import conexion.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class PasajeroDao {

    private Connection connection;

    public PasajeroDao() {
        connection = Conexion.conectar().getConnection();
    }

    public DefaultTableModel getDatosAsc() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM get_valoraciones_pasajeros ORDER BY 3 ASC");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            modelo.addColumn("USUARIO");
            modelo.addColumn("PASAJERO");
            modelo.addColumn("VALORACION");

            while (rs.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }

    public DefaultTableModel getDatosDesc() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM get_valoraciones_pasajeros ORDER BY 3 DESC");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            modelo.addColumn("USUARIO");
            modelo.addColumn("PASAJERO");
            modelo.addColumn("VALORACION");

            while (rs.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }
}
