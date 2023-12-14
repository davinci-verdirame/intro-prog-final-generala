import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class Generala{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Jugar(sc);
        // System.out.println("Bienvenido al juego La Generala\n Con que opcion desea comenzar?: ");
        // System.out.println("1. Jugar\n2. Ver reglas del juego\n3. Salir");
        // String opcion = sc.next();
        // switch (opcion) {
        //     case "1":
        //         Jugar();
        //         break;
        //     case "2":
        //         Funciones.VerReglas();
        //         break;
        //     case "3":
        //         System.out.println("Saliendo..");
        //         break;
        //     default:
        //         break;
        // }
    }

    public static void Jugar(Scanner sc){
        int[] dados = new int[5];
        int[] nrosDeDados = {0,1,2,3,4};
        String[] numbersToString = {"primer", "segundo", "tercer", "cuarto", "quinto"}; //Sólo se utiliza para poder mostrar un mensaje mas claro al usuario.

        System.out.print("Cuando este listo para tirar los dados presione enter: ");
        sc.nextLine();

        dados = TirarDados(dados, nrosDeDados);
        MostrarDados(dados);

        boolean continuar = true;
        int contador = 0;
        int nroDadoElegido;
        while(continuar && contador < 2){
            int tirarDeNuevo = Funciones.SolicitarNumero("\n¿Desea tirar de nuevo los dados?\n1. Si\n2. No\nElija una opcion (1-2): ");

            if(tirarDeNuevo == 1){

                int cantDados = Funciones.SolicitarNumero("\n¿Cuantos dados desea volver a tirar? (1-5): ", true);
                int[] nuevosDados = new int[cantDados]; //Se inicializa un array con tamaño acorde a la cantidad de dados a tirar
                int[] auxNroDadosElegidos = new int[cantDados]; //variable auxiliar para validar que no se repitan numeros de dado

                for(int i = 0; i < cantDados; i++){

                    boolean dadoDisponible;
                    //do-while para validar que no se repitan los nros de dados
                    do{
                        dadoDisponible = true;
                        nroDadoElegido = Funciones.SolicitarNumero("\nIngrese el numero del " + numbersToString[i] + " dado que desea volver a tirar (1-5): ", true);
                        auxNroDadosElegidos[i] = nroDadoElegido; //Se guarda el numero elegido dentro del array para luego poder comparar

                        if(i != 0){ //Si es la primer vuelta no se valida

                            for (int e = 0; e < i; e++) { // El nro de vueltas va a ser de la cantidad de dados que ya se hayan elegido - 1

                                if (nroDadoElegido == auxNroDadosElegidos[e]) {// Se valida que no se repita un mismo numero de dado ya elegido
                                    dadoDisponible = false;
                                    System.out.println("Ese numero de dado ya fue elegido, elija otro por favor..");
                                    break;
                                }

                            }
                        }
                    }
                    while(!dadoDisponible); //Mientras el dado no este disponible, se vuelve a pedir el numero de dado

                    nuevosDados[i] = nroDadoElegido - 1;
                }

                dados = TirarDados(dados, nuevosDados);
                MostrarDados(dados);
                contador++;
            }
            else{
                continuar = false;
            }
        }
    }

    public static int[] TirarDados(int[] dados, int[] nuevosDados){
        
        for(int i = 0; i < nuevosDados.length; i++){
            dados[nuevosDados[i]] = (int)(Math.random()*6) + 1;
        }
        return dados;
    }

    public static void MostrarDados(int[] dados){
        System.out.println("\nSus dados son: ");
        for(int i = 0; i<dados.length; i++){
            System.out.print("Dado numero " + (i+1) + " -> " + dados[i] + "\n");
        }
    }
}