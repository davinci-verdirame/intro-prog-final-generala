import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

class Generala {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nLa Generala: \n");
        JOptionPane.showMessageDialog(
            null,
            "Bienvenido al juego de La Generala!\n  El juego se desarrollara desde su consola.. Que se divierta!!",
            "La Generala",
            JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(Generala.class.getResource("img/generala.png"))
        );
        boolean continuar = true;
        while (continuar) {
            System.out.println(
                "1. Jugar\n" +
                "2. Ver reglas del juego\n" + 
                "3. Salir");
            int opcion = Funciones.SolicitarNumero( "\nElija una opcion: ", 3);

            switch (opcion) {
                case 1:
                    Jugar(sc);
                break;
                case 2:
                System.out.println("\n --- Aviso: Es probable que necesite minimizar sus ventanas para poder ver la imagen con las reglas del juego ---\n");
                    Funciones.VerReglas();
                break;
                default:
                    continuar = false;
                break;
            }
        }

        System.out.println("Saliendo...");
        sc.close();
    }


    public static void Jugar(Scanner sc) {
        int[] dados = new int[5];
        int puntajeTotal = 0;
        int numeroDeTurno = 0;

        //Se utilizan para mostrar al usuario las categorias disponibles y para poder "deshabilitar" las que van saliendo.
        String[] nombreCategorias = {"GENERALA DOBLE", "1", "2", "3", "4", "5", "6", "GENERALA", "POKER", "FULL", "ESCALERA"};
        boolean[] categoriasDisponibles = new boolean[nombreCategorias.length];

        String[] numbersToString = {"primer", "segundo", "tercer", "cuarto", "quinto"}; //Sólo se utiliza para poder mostrar un mensaje mas claro al usuario.

        //Se disponibilizan todas las categorias
        for(int i = 0; i < categoriasDisponibles.length; i++){
            categoriasDisponibles[i] = true;
        }

        //Bucle para continuar jugando si el usuario asi lo quiere. (O cortar el juego si ya no quedan categorias disponibles).
        boolean continuarJugando = true;
        while(continuarJugando){
            int[] indicesDadosATirar = {0,1,2,3,4};
            
            System.out.print("\nCuando este listo para tirar los dados presione enter: ");
            sc.nextLine();
            
            dados = Funciones.TirarDados(dados, indicesDadosATirar);
            Funciones.MostrarDados(dados);

            // Se valida si es Generala Servida, en ese caso gana la partida.
            int[] dadosOrdenados = Funciones.OrdenarDados(dados);
            if (EsGeneralaServida(dadosOrdenados)) {
                return;
            }

            //Si al volver a tirar sale Generala Servida (Tirando los 5 dados de nuevo), gana la partida
            if(VolverATirar(dados, dadosOrdenados, indicesDadosATirar, numbersToString)){
                return;
            }
            numeroDeTurno++;

            //Se obtiene la categoria (El indice de la categoria)
            int categoria = ObtenerCategoria(dadosOrdenados, categoriasDisponibles, nombreCategorias);

            
            puntajeTotal += ObtenerPuntaje(categoria, dados, indicesDadosATirar);
            boolean quedanCategoriasDisponibles = true;
            
            //Se deshabilita la categoria conseguida para que el usuario no pueda volver a escogerla en un proximo turno. 
            //No se tiene en cuenta la 11 para esto porque no pertenece a ninguna Categoria
            if(categoria != 11){
                categoriasDisponibles[categoria] = false;
            }

            //A partir del tiro numero 10 se empieza a comprobar si quedan categorias disponibles
            if(numeroDeTurno >= 0){
                for (int i = 1; i <= 10; i++) {
                    if (categoriasDisponibles[i]) {
                        //Si al menos hay una disponible, el juego puede continuar (Si ignora la genera)
                        quedanCategoriasDisponibles = true;
                        break;
                    }
                    else{
                        quedanCategoriasDisponibles = false;
                    }
                }
            }
            if(quedanCategoriasDisponibles){
                System.out.println("Desea seguir jugando?");
                int continuarJugandoInt = Funciones.SolicitarNumero("1. Si \n2. No\nElija una opcion(1-2): ", 2);
                if (continuarJugandoInt == 2) {
                    JOptionPane.showMessageDialog(
                            null,
                            "\nSu puntaje total es: " + puntajeTotal + "\nGracias por jugar!",
                            "Fin del Juego",
                            JOptionPane.INFORMATION_MESSAGE,
                            new ImageIcon(Generala.class.getResource("img/generala.png")));
                    System.out.println("\nSu puntaje total es " + puntajeTotal);
                    System.out.println("\nFin del juego.\n\n");
                    continuarJugando = false;
                }
            }
            else{
                JOptionPane.showMessageDialog(
                            null,
                            "\nYa no quedan categorias disponibles. \nSu puntaje total es: " + puntajeTotal + "\nGracias por jugar!",
                            "Fin del Juego",
                            JOptionPane.INFORMATION_MESSAGE,
                            new ImageIcon(Generala.class.getResource("img/generala.png")));
                System.out.println("\nYa no quedan categorias disponibles. \nSu puntaje total es: " + puntajeTotal);
                System.out.println("\nFin del juego.\n\n");
                continuarJugando = false;
            }
        }
        return;
    }

    public static int ObtenerPuntaje(int categoria, int[] dados, int[] indicesDadosATirar){
        // puntajeTotal += ObtenerPuntaje(categoria, dados, indicesDadosATirar, )
            String mensaje = "";
            int puntaje = 0;

            //Se usan solo para una mejor comunicacion con el usuario
            String[] servido_a = { " Servida", " Servido" };
            int indiceServido = 0;

            boolean esCategoriaMayor = true;
            //Se asigna puntaje dependiendo de la categoria conseguida.
            switch (categoria) {
                case 0: // GENERALA DOBLE
                    puntaje = 100;
                    mensaje = "Felicitaciones, ha logrado una Generala Doble";
                    break;
                case 7: // GENERALA
                    puntaje = 50;
                    mensaje = "Felicitaciones, ha logrado una Generala";
                    break;
                case 8: // POKER
                    puntaje = 40;
                    mensaje = "Felicitaciones, ha logrado un Poker";
                    indiceServido = 1;
                    break;
                case 9: // FULL
                    puntaje = 30;
                    mensaje = "Felicitaciones, ha logrado un Full";
                    indiceServido = 1;
                    break;
                case 10: // ESCALERA
                    puntaje = 20;
                    mensaje = "Felicitaciones, ha logrado una Escalera";
                    break;
                case 11:
                    puntaje = 0;
                    mensaje = "Tampoco tiene categorias de Número disponible";
                    esCategoriaMayor = false;
                    break;
                default: //NUMEROS
                    puntaje = calcularCategoriaNumeros(dados, categoria);
                    mensaje = "Eligió la categoría del numero " + categoria;
                    esCategoriaMayor = false;
                    break;
            }

            // Si es categoria mayor y se tiraron los 5 dados juntos es Categoria Mayor Servida
            if (esCategoriaMayor && indicesDadosATirar.length == 5) { 
                puntaje += 5;
                System.out.println("\n" + mensaje + servido_a[indiceServido] + "!!\nConsiguió " + puntaje + " puntos");
            } else {
                System.out.println("\n" + mensaje + "\nConsiguió " + puntaje + " puntos\n");
            }
        return puntaje;
    }

    //Se obtiene la mejor categoria mayor o las categorias de numeros disponible. Retorna un int correspondiente al indice de la categoria.
    public static int ObtenerCategoria(int[] dadosOrdenados, boolean[] categoriasDisponibles, String[] nombreCategorias){
        int categoriaElegida = 0;

        if(EsGenerala(dadosOrdenados)){
            if(categoriasDisponibles[7]){
                return 7;
            }
            if(categoriasDisponibles[0]){
                return 0;
            }
        }
        else if(EsPoker(dadosOrdenados)){
            if(categoriasDisponibles[8]){
                return 8;
            }
        }
        else if(EsFull(dadosOrdenados)){
            if(categoriasDisponibles[9]){
                return 9;
            }
        }
        else if(EsEscalera(dadosOrdenados)){
            if(categoriasDisponibles[10]){
                return 10;
            }
        }
        
        System.out.println("\nNo consiguio ninguna categoria mayor disponible");
        
        //Comprobacion de Categorias de numeros disponibles
        boolean hayNumeroDisponible = false;
        for (int i = 1; i <= 6; i++) {
            if (categoriasDisponibles[i]) {
                hayNumeroDisponible = true;
            }
        }        
        if(hayNumeroDisponible){
            boolean eleccionDisponible = false;
            do {
                System.out.println("Estas son las categorias de numero disponibles: ");
                for (int i = 1; i <= 6; i++) {
                    if (categoriasDisponibles[i]) {
                        System.out.println("- " + nombreCategorias[i]);
                    }
                }
                //Se pregunta a que numero desea cargar los puntos
                categoriaElegida = Funciones.SolicitarNumero("\nElija un numero al cual cargar el puntaje correspondiente: ", 0);
                if (categoriasDisponibles[categoriaElegida]) {
                    eleccionDisponible = true;
                } else {
                    System.out.println("\nNumero de categoria no disponible.. \n");
                }
            } while (!eleccionDisponible);

            return categoriaElegida;
        }

        return 11; //No pudo conseguir ninguna categoria de las disponibles.
    }

    public static boolean VolverATirar(int[] dados, int[] dadosOrdenados, int[] indicesDadosATirar,
        String[] numbersToString) {
        // Bucle para posibilitar al usuario volver a tirar los dados 1 o 2 veces mas.
        int contador = 0;
        boolean continuar = true;
        while (continuar && contador < 2) {
            System.out.println("\n¿Desea tirar de nuevo los dados?");
            int tirarDeNuevo = Funciones.SolicitarNumero("\n1. Si\n2. No\nElija una opcion (1-2): ", 2);

            if (tirarDeNuevo == 1) {
                int cantDados = Funciones.SolicitarNumero("\n¿Cuantos dados desea volver a tirar? (1-5): ", 5);
                indicesDadosATirar = new int[cantDados]; // Se inicializa un array con tamaño acorde a la cantidad de dados a tirar

                // Si quiere tirar los 5 dados de nuevo, no se pregunta que dados quiere tirar
                if (cantDados != 5) {
                    int nroDadoElegido;
                    int[] auxNroDadosElegidos = new int[cantDados]; // Variable auxiliar para validar que no se repitan numeros de dado
                    for (int i = 0; i < cantDados; i++) {

                        boolean dadoDisponible;
                        // do-while para validar que no se repitan los nros de dados
                        do {
                            dadoDisponible = true;
                            nroDadoElegido = Funciones.SolicitarNumero("\nIngrese el numero del " + numbersToString[i]
                                    + " dado que desea volver a tirar (1-5): ", 5);
                            auxNroDadosElegidos[i] = nroDadoElegido; // Se guarda el numero elegido dentro del array para luego poder comparar

                            if (i != 0) { // Si es la primer vuelta no se valida

                                for (int e = 0; e < i; e++) { // El nro de vueltas va a ser de la cantidad de dados que ya se hayan elegido - 1

                                    if (nroDadoElegido == auxNroDadosElegidos[e]) {// Se valida que no se repita un mismo numero de dado ya elegido
                                        dadoDisponible = false;
                                        System.out.println("Ese numero de dado ya fue elegido, elija otro por favor..");
                                        break;
                                    }
                                }
                            }
                        } while (!dadoDisponible); // Mientras el dado no este disponible, se vuelve a pedir el numero de dado

                        // El indice que indica que dado se vuelve a tirar siempre es uno menor al que
                        // elige el usuario (Por el comienzo del array en 0)
                        indicesDadosATirar[i] = nroDadoElegido - 1;
                    }
                } else {
                    for (int i = 0; i < indicesDadosATirar.length; i++) {
                        indicesDadosATirar[i] = i;
                    }
                }

                dados = Funciones.TirarDados(dados, indicesDadosATirar);
                Funciones.MostrarDados(dados);
                dadosOrdenados = Funciones.OrdenarDados(dados);

                // Si tira los 5 dados, validar si es Generala. En ese caso no se pregunta si
                // quiere volver a tirar y directamente gana la partida
                if (indicesDadosATirar.length == 5) {
                    if (EsGeneralaServida(dadosOrdenados)) {
                        return true;
                    }
                }
                contador++;
            } else {
                continuar = false;
            }
        }
        return false;
    }
    

    // --- Importante tener en cuenta que los dados estan ordenados para comprender los proximos metodos ---

    public static boolean EsGeneralaServida(int[] dados){
        if (EsGenerala(dados)) {
            System.out.println("\nFelicitaciones, has sacado una Generala Servida, la mejor categoria posible");
            System.out.println("¿Que quieres hacer?");
            return true;
        }
        return false;
    }

    public static boolean EsGenerala(int dados[]) {
        if(dados[0] == dados[dados.length-1]){
            return true;
        }
        return false;
    }

    public static boolean EsPoker(int dados[]){
        //Se descarta la generala
        if(dados[0] != dados[dados.length-1]){
            //Si el primero es igual al anteultimo, o el segundo es igual al ultimo, es Poker
            if(dados[0] == dados[dados.length-2] || dados[1] == dados[dados.length-1]){
                return true;
            }
        }
        return false;
    }

    public static boolean EsFull(int dados[]){
        //Si del primero al tercero son iguales y los ultimos dos tambien o a la inversa, es Full
        if(dados[0] == dados[2] && dados[3] == dados[4]){
            return true;
        }
        if(dados[0] == dados[1] && dados[2] == dados[4]){
            return true;
        }
        return false;
    }

    public static boolean EsEscalera(int dados[]){
        for(int i = 0; i < dados.length-1; i++){
            //Si a todos al restarle 1 al numero siguiente es igual al anterior, es mayor solo por 1, por lo tanto es escalera.
            if(dados[i] != dados[i+1]-1){
                return false;
            }
        }
        return true;
    }

    //Metodo generico para todas las categorias de Numeros
    public static int calcularCategoriaNumeros(int[]dados, int numeroCategoria){
        int contador = 0;
        //Segun la categoria elegida por el usuario, se calcula cuantos dados hay de ese numero para hacer el calculo
        for (int i = 0; i < dados.length; i++) {
            if (dados[i] == numeroCategoria) {
                contador++;
            }
        }
        return contador * numeroCategoria;
    }
}