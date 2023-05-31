package org.infantaelena.vista;

import org.infantaelena.modelo.entidades.Pokemon;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private JTextField textFieldNumero;
    private JLabel etiquetaNumero;
    private JPanel principal;
    private JButton buttonGuardar;
    private JButton buttonBorrar;
    private JTextField textFieldNombre;
    private JTextField textFieldTipo2;
    private JTextField textFieldTipo1;
    private JButton Buttonbuscar;
    private JLabel etiquetaNombre;
    private JLabel etiquetaTipo1;
    private JLabel etiquetaTipo2;
    private JButton buttonbuscarTodos;
    private JTextField textFieldAtaque;
    private JTextField textFieldVida;
    private JTextField textFieldDefensa;
    private JTextField textFieldVelocidad;
    private JLabel EtiquetaAtaque;
    private JLabel EtiquetaVida;
    private JLabel EtiquetaDefensa;
    private JLabel EtiquetaVelocidad;
    private JTextArea textArea1;
    private JComboBox textoTipo1;
    private JComboBox textoTipo2;
    private JButton buttonActualizar;
    private JTextPane textPane1;

    public class VistaPokemon extends JFrame {

    }

    public Vista() {
        super("ApiPokemon");

        setContentPane(principal);
        setSize(900, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTextoTipo1(this.textoTipo1);
        setTextoTipo2(this.textoTipo2);


    }


    public Vista(String title) throws HeadlessException {
        super(title);
    }

    public Vista(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    public JLabel getEtiquetaNumero() {
        return etiquetaNumero;
    }

    public void setEtiquetaNumero(JLabel etiquetaNumero) {
        this.etiquetaNumero = etiquetaNumero;
    }

    public JPanel getPrincipal() {
        return principal;
    }

    public void setPrincipal(JPanel principal) {
        this.principal = principal;
    }

    public JTextField getTextFieldAtaque() {
        return textFieldAtaque;
    }

    public void setTextFieldAtaque(JTextField textFieldAtaque) {
        this.textFieldAtaque = textFieldAtaque;
    }

    public JTextField getTextFieldVida() {
        return textFieldVida;
    }

    public void setTextFieldVida(JTextField textFieldVida) {
        this.textFieldVida = textFieldVida;
    }

    public JTextField getTextFieldDefensa() {
        return textFieldDefensa;
    }

    public void setTextFieldDefensa(JTextField textFieldDefensa) {
        this.textFieldDefensa = textFieldDefensa;
    }

    public JTextField getTextFieldVelocidad() {
        return textFieldVelocidad;
    }

    public void setTextFieldVelocidad(JTextField textFieldVelocidad) {
        this.textFieldVelocidad = textFieldVelocidad;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JButton getButtonGuardar() {
        return buttonGuardar;
    }

    public void setButtonGuardar(JButton buttonGuardar) {
        this.buttonGuardar = buttonGuardar;
    }

    public JTextField getTextFieldNumero() {
        return textFieldNumero;
    }

    public void setTextFieldNumero(JTextField textFieldNumero) {
        this.textFieldNumero = textFieldNumero;
    }

    public JButton getButtonBorrar() {
        return buttonBorrar;
    }

    public void setButtonBorrar(JButton buttonBorrar) {
        this.buttonBorrar = buttonBorrar;
    }

    public JTextField getTextFieldNombre() {
        return textFieldNombre;
    }

    public void setTextFieldNombre(JTextField textFieldNombre) {
        this.textFieldNombre = textFieldNombre;
    }

    public JTextField getTextFieldTipo2() {
        return textFieldTipo2;
    }

    public void setTextFieldTipo2(JTextField textFieldTipo2) {
        this.textFieldTipo2 = textFieldTipo2;
    }

    public JTextField getTextFieldTipo1() {
        return textFieldTipo1;
    }

    public void setTextFieldTipo1(JTextField textFieldTipo1) {
        this.textFieldTipo1 = textFieldTipo1;
    }

    public JButton getButtonbuscar() {
        return Buttonbuscar;
    }

    public void setButtonbuscar(JButton buttonbuscar) {
        Buttonbuscar = buttonbuscar;
    }

    public JLabel getEtiquetaNombre() {
        return etiquetaNombre;
    }

    public void setEtiquetaNombre(JLabel etiquetaNombre) {
        this.etiquetaNombre = etiquetaNombre;
    }

    public JComboBox getTextoTipo1() {
        return textoTipo1;
    }

    public void setTextoTipo1(JComboBox textoTipo1) {
        textoTipo1.setModel(new DefaultComboBoxModel<>(Pokemon.tipos.values()));
        this.textoTipo1 = textoTipo1;
    }

    public JComboBox getTextoTipo2() {
        return textoTipo2;
    }

    public void setTextoTipo2(JComboBox textoTipo2) {
        textoTipo2.setModel(new DefaultComboBoxModel<>(Pokemon.tipos.values()));
        this.textoTipo2 = textoTipo2;
    }

    public JButton getButtonActualizar() {
        return buttonActualizar;
    }

    public void setButtonActualizar(JButton buttonActualizar) {
        this.buttonActualizar = buttonActualizar;
    }

    public JButton getButtonbuscarTodos() {
        return buttonbuscarTodos;
    }

    public void setButtonbuscarTodos(JButton buttonbuscarTodos) {
        this.buttonbuscarTodos = buttonbuscarTodos;
    }
    public void mostrarVentana(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void mostrarVentanaError(String mensaje) {

        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmacion() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el pokemon?", "Confirmación", JOptionPane.YES_NO_OPTION);


        return confirmacion;
    }
    public void setTextoClase(JTextArea textoClase) {

    }

}
