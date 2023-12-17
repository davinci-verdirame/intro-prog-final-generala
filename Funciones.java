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
            new ImageIcon(Generala.class.getResource("img/Instrucciones-Generala.png"))
        );
    }

    public static int SolicitarNumero(String textoSolicitud, int limit) {
        Scanner sc = new Scanner(System.in);
        //El ciclo se va repetir hasta que el valor ingresado efectivamente sea un numero.
        while (true) {
            System.out.print(textoSolicitud);
            if (sc.hasNextInt()) {
                int numero = sc.nextInt();
                if (limit > 0) {
                    // Se limitan las opciones que el usuario elije acorde a las que hay disponibles en el momento. (No se limita si limit = 0)
                    if (numero > 0 && numero <= limit) {
                        return numero;
                    }
                    System.out.println("Numero incorrecto.. Por favor ingresa un numero del 1 al " + limit + ": ");
                }
                else{
                    return numero;
                }
            } else {
                System.out.println("No has ingresado una opcion correcta, vuelve a intentar");
                sc.next(); //En caso que no sea un numero, consumo ese valor ingresado para liberar y volver a solicitar otro numero.
            }
        }
    }

    //Se generan aleatoriamente solo los dados que el usuario quiere
    //El parametro dadosATirar contiene los numero de indice de los dados a volver a tirar
    public static int[] TirarDados(int[] dados, int[] dadosATirar){
        for(int i = 0; i < dadosATirar.length; i++){
            dados[dadosATirar[i]] = (int)(Math.random()*6) + 1;
        }
        return dados;
    }

    public static void MostrarDados(int[] dados){
        System.out.println("\nSus dados son: ");
        for(int i = 0; i<dados.length; i++){
            System.out.print("Dado numero " + (i+1) + " -> " + dados[i] + "\n");
        }
    }

    //Se ordenan los dados de forma ascendente para facilitar la verificacion de categoria obtenida
    public static int[] OrdenarDados(int dados[]){
        int[] dadosOrdenados = new int[dados.length];
        for(int i = 0; i < dados.length; i++){
            dadosOrdenados[i] = dados[i];
        }
        boolean huboCambio;
        for(int i = 0; i < dadosOrdenados.length; i++){
            int dadoAux;
            huboCambio = false;
            for(int e = 0; e < dadosOrdenados.length-1; e++){
                if(dadosOrdenados[e] > dadosOrdenados[e+1]){
                    dadoAux = dadosOrdenados[e];
                    dadosOrdenados[e] = dadosOrdenados[e+1];
                    dadosOrdenados[e+1] = dadoAux;
                    huboCambio = true;
                }
            }
            if(!huboCambio){
                break;
            }
        }
        return dadosOrdenados;
    }
}