package org.window;

import java.sql.*;
import java.util.*;

public class App {

	public static void main(String[] args) {
		App app = new App();

		float total = app.valorDivisa("LVL");
		System.out.println(total);
	}

	public List<String> obtenerDatosDeColumna() {
		List<String> datos = new ArrayList<>();
		String url;
		Connection conexion;
		Statement instruccion;
		String sql;
		ResultSet resultado;

		try {
			// ulr MySql
			url = "jdbc:mysql://localhost:3306/divisa?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

			// Conexion BD
			conexion = DriverManager.getConnection(url, "root", "2010");

			// Sentences Sql
			instruccion = (Statement) conexion.createStatement();

			sql = "SELECT moneda FROM monedas";// creo una consulto
			resultado = ((java.sql.Statement) instruccion).executeQuery(sql); // excute sql

			while (resultado.next()) {
				datos.add(resultado.getString("moneda"));
			}

			// close conexion
			resultado.close();
			instruccion.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return datos;
	}

	@SuppressWarnings("finally")
	public float valorDivisa(String divisa) {
		String url;
		Connection conexion;
		PreparedStatement instruccion;
		String sql;
		ResultSet resultado;
		float valorEncontrado = 0;

		try {

			// ulr MySql
			url = "jdbc:mysql://localhost:3306/divisa?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

			// Conexion BD
			conexion = DriverManager.getConnection(url, "root", "2010");

			sql = "SELECT valor FROM monedas WHERE moneda = ?";// creo una consultoa

			instruccion = conexion.prepareStatement(sql);

			instruccion.setString(1, divisa);
			resultado = instruccion.executeQuery();

			if (resultado.next()) {
				valorEncontrado = resultado.getFloat("valor");

			}

			resultado.close();
			instruccion.close();
			conexion.close();

		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		} finally {
			return valorEncontrado;
		}

	}

	public float totalCalculado(float euro, float valorMoneda) {
		float total = Math.round((euro * valorMoneda));
//		DecimalFormat df = new DecimalFormat("#.##");
//
//		// Formatear el resultado con dos decimales
//		String resultadoFormateado = df.format(total);
		return total;

	}

}
