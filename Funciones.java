import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Funciones{
    
    public static void VerReglas(){
        JOptionPane.showMessageDialog(
            null,
            null,
            "Instrucciones",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(Generala.class.getResource("img/Instrucciones-Generala.jpg"))
        );
    }

    public static int SolicitarNumero(String textoSolicitud) {
        Scanner sc = new Scanner(System.in);
        //El ciclo se va repetir hasta que el valor ingresado efectivamente sea un numero.
        while (true) {
            System.out.print(textoSolicitud);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("No has ingresado una opcion correcta, vuelve a intentar");
                sc.next(); //En caso que no sea un numero, consumo ese valor ingresado para liberar y volver a solicitar otro numero.
            }
        }
    }

    public static int SolicitarNumero(String textoSolicitud, boolean limit) {
        Scanner sc = new Scanner(System.in);
        //El ciclo se va repetir hasta que el valor ingresado efectivamente sea un numero.
        while (true) {
            System.out.print(textoSolicitud);
            if (sc.hasNextInt()) {
                if(limit){
                    int numero = sc.nextInt();
                    if(!(numero < 1 || numero > 5)){
                        return numero;
                    }
                    System.out.println("Por favor ingresa un numero del 1 al 5:");
                }
                else{
                    return sc.nextInt();
                }
            } else {
                System.out.println("No has ingresado una opcion correcta, vuelve a intentar");
                sc.next(); //En caso que no sea un numero, consumo ese valor ingresado para liberar y volver a solicitar otro numero.
            }
        }
    }

    public static int SolicitarNumero(String textoSolicitud, boolean limit, int[] nuevosDados) {
        Scanner sc = new Scanner(System.in);
        //El ciclo se va repetir hasta que el valor ingresado efectivamente sea un numero.
        while (true) {
            System.out.print(textoSolicitud);
            if (sc.hasNextInt()) {
                if(limit){
                    int numero = sc.nextInt();
                    if(!(numero < 1 || numero > 5)){
                        boolean dadoNoDisponible = false;
                        for(int i = 0; i < nuevosDados.length; i++){
                            if((numero == nuevosDados[i])){
                                dadoNoDisponible = true;
                            }
                        }
                        if(!dadoNoDisponible){
                            return numero;
                        }
                        System.out.println("Ese numero de dado ya fue elegido, elija otro por favor: ");
                    }
                    else{
                        System.out.println("Por favor ingresa un numero del 1 al 5:");
                    }
                }
                else{
                    return sc.nextInt();
                }
            } else {
                System.out.println("No has ingresado una opcion correcta, vuelve a intentar");
                sc.next(); //En caso que no sea un numero, consumo ese valor ingresado para liberar y volver a solicitar otro numero.
            }
        }
    }
}