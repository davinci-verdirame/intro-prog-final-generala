import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Funciones{
    
    public static void VerReglas(){
        JOptionPane.showMessageDialog(
            null,
            "mensaje",
            "Titulo",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(Funciones.class.getResource("img/Instrucciones-Generala.jpg"))
        );
    }
}