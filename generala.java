import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class Generala{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Bienvenido al juego La Generala\n Con que opcion desea comenzar?: ");
        System.out.println("1. Jugar\n2. Ver reglas del juego\n3. Salir");

        JOptionPane.showMessageDialog(
            null,
            "mensaje",
            "Titulo",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(Generala.class.getResource("img/Instrucciones-Generala.jpg"))
        );
    }
}