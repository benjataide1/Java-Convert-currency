package org.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

	private JFrame frame;
	private JTextField inputEuro;

	private JLabel Titulo;
	private JLabel Titulo_Euro;
	private JLabel Titulo_Currency;
	private JButton Button_Convert;
	private JComboBox BoxDivisas;
	private JLabel RespuestaCurrency;
	private App app;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		app = new App();
		List<String> array = app.obtenerDatosDeColumna();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(71, 125, 152));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Titulo = new JLabel("Currency Converter");
		Titulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		Titulo.setBounds(141, 11, 171, 23);
		frame.getContentPane().add(Titulo);

		Titulo_Euro = new JLabel("Euro €");
		Titulo_Euro.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Titulo_Euro.setBounds(86, 74, 46, 14);
		frame.getContentPane().add(Titulo_Euro);

		Titulo_Currency = new JLabel("Select currency");
		Titulo_Currency.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Titulo_Currency.setBounds(249, 74, 105, 14);
		frame.getContentPane().add(Titulo_Currency);

		inputEuro = new JTextField();
		inputEuro.setBounds(86, 99, 56, 20);
		frame.getContentPane().add(inputEuro);
		inputEuro.setColumns(10);

		Button_Convert = new JButton("Convert");
		Button_Convert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String divisa = (String) BoxDivisas.getSelectedItem();
				float totalDivisa = app.valorDivisa(divisa);
				float totalEuro = Float.parseFloat(inputEuro.getText());
				if (totalEuro == 0 || inputEuro.getText().isEmpty()) {
					RespuestaCurrency.setText("Error");
				} else {
					float total = app.totalCalculado(totalEuro, totalEuro);
					RespuestaCurrency.setText("" + total);
				}

			}
		});
		Button_Convert.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Button_Convert.setBounds(156, 189, 89, 23);
		frame.getContentPane().add(Button_Convert);

		BoxDivisas = new JComboBox();
		String[] nombresArray = array.toArray(new String[0]);
		BoxDivisas.setModel(new DefaultComboBoxModel<>(nombresArray));
		BoxDivisas.setBounds(249, 98, 98, 22);
		frame.getContentPane().add(BoxDivisas);

		RespuestaCurrency = new JLabel("00.00");
		RespuestaCurrency.setBounds(185, 142, 60, 14);
		frame.getContentPane().add(RespuestaCurrency);
	}
}
