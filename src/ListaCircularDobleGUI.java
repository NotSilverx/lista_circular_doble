import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ListaCircularDobleGUI {
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton ELIMINARButton;
    private JButton BUSCARButton;
    private JButton AGREGARButton;
    private JPanel pGENERAL;
    private JButton ORDENARYVISULIZARButton;

    private final ListaCircularDoble lista;

    public ListaCircularDobleGUI() {
        lista = new ListaCircularDoble();

        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dato = textField1.getText();
                if (!dato.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(dato);
                        lista.insertar(valor);
                        textArea1.append("Elemento " + valor + " agregado.\n");
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        textArea1.append("Por favor, ingresa un número válido.\n");
                    }
                } else {
                    textArea1.append("Por favor, ingresa un valor.\n");
                }
            }
        });

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dato = textField1.getText();
                if (!dato.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(dato);
                        lista.eliminar(valor);
                        textArea1.append("Elemento " + valor + " eliminado.\n");
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        textArea1.append("Por favor, ingresa un número válido.\n");
                    }
                } else {
                    textArea1.append("Por favor, ingresa un valor.\n");
                }
            }
        });

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dato = textField1.getText();
                if (!dato.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(dato);
                        Nodo resultado = lista.buscarAvanzada(valor, Comparator.naturalOrder());
                        if (resultado != null) {
                            textArea1.append("Elemento " + valor + " encontrado.\n");
                        } else {
                            textArea1.append("Elemento " + valor + " no encontrado.\n");
                        }
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        textArea1.append("Por favor, ingresa un número válido.\n");
                    }
                } else {
                    textArea1.append("Por favor, ingresa un valor.\n");
                }
            }
        });

        ORDENARYVISULIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.ordenar(Comparator.naturalOrder());
                textArea1.append("Lista ordenada (ascendente):\n");
                Nodo actual = lista.cabeza;
                if (actual != null) {
                    do {
                        textArea1.append(actual.dato + " ");
                        actual = actual.siguiente;
                    } while (actual != lista.cabeza);
                    textArea1.append("\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Circular Doble");
        frame.setContentPane(new ListaCircularDobleGUI().pGENERAL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
