package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    // Campo donde se muestran los números y resultados
    private JTextField txtResultado;

    // Variables para la lógica
    private double num1 = 0;
    private double num2 = 0;
    private String operador = "";

    public Calculadora() {

        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(350, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== CAMPO DE TEXTO =====
        txtResultado = new JTextField();
        txtResultado.setPreferredSize(new Dimension(300, 50));
        txtResultado.setFont(new Font("Arial", Font.BOLD, 24));
        txtResultado.setHorizontalAlignment(JTextField.RIGHT);
        txtResultado.setEditable(false); // No permitir escribir con teclado
        add(txtResultado, BorderLayout.NORTH);

        // ===== PANEL DE BOTONES =====
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4, 5, 5));

        // Arreglo con los textos de los botones
        String[] botones = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "C", "0", "=", "/"
        };

        // Crear botones dinámicamente
        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setPreferredSize(new Dimension(80, 80));
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this); // Evento
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
    }

    // ===== MANEJO DE EVENTOS =====
    @Override
    public void actionPerformed(ActionEvent e) {

        String textoBoton = e.getActionCommand();

        // Si es un número, concatenamos
        if (textoBoton.matches("[0-9]")) {
            txtResultado.setText(txtResultado.getText() + textoBoton);
        }

        // Botón limpiar
        else if (textoBoton.equals("C")) {
            txtResultado.setText("");
            num1 = 0;
            num2 = 0;
            operador = "";
        }

        // Operadores
        else if (textoBoton.equals("+") || textoBoton.equals("-")
                || textoBoton.equals("*") || textoBoton.equals("/")) {

            num1 = Double.parseDouble(txtResultado.getText());
            operador = textoBoton;
            txtResultado.setText("");
        }

        // Igual
        else if (textoBoton.equals("=")) {

            num2 = Double.parseDouble(txtResultado.getText());
            double resultado = 0;

            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    resultado = num1 / num2;
                    break;
            }

            txtResultado.setText(String.valueOf(resultado));
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        new Calculadora().setVisible(true);
    }
}

