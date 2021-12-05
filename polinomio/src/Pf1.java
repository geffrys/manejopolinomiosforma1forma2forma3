import javax.swing.JOptionPane;

public class Pf1 {
    int du, VF1[];

    public Pf1(int du) {
        this.du = du;
        VF1 = new int[this.du + 1];
        VF1[0] = du - 1;
    }

    public int getDu() {
        return du;
    }

    public void setDu(int du) {
        this.du = du;
    }

    public int[] getVF1() {
        return VF1;
    }

    public int getVF1(int i) {
        return VF1[i];
    }

    public void setVF1(int[] vF1) {
        VF1 = vF1;
    }

    public void setVF1(int i, String dato) {
        if (i != 0 && i < VF1.length) {
            VF1[i] = Integer.parseInt(dato);
        }
    }

    // lectura
    public void mostrarForma1() {
        String s = "";
        for (int i = 0; i < VF1.length; i++) {
            s += ("   |" + VF1[i] + "|    ");
        }
        System.out.println("\n\n\n");
        JOptionPane.showMessageDialog(null, "Forma 1:\n\n\n" + s);
    }

    // insertar termino
    public void redimensionar(String exp) {
        int k, pos = Integer.parseInt(exp) + 1;
        int aux[] = new int[Integer.parseInt(exp) + 2];
        for (k = VF1[0] + 1; k > 0; k--) {
            aux[pos] = VF1[k];
            pos = pos - 1;
        }
        VF1 = aux;
    }

    public void ajustar() {
        int j = 1, cont = 0;
        while (j < VF1[0] + 2 && VF1[j] == 0) {
            cont++;
            j++;
        }
        for (int k = j; k < VF1[0] + 2; k++) {
            VF1[k - cont] = VF1[k];
        }
        VF1[0] = VF1[0] - cont;
    }

    public void insertartermino(String[] terminos) {
        int pos;
        if (Integer.parseInt(terminos[1]) < 0) {
            JOptionPane.showMessageDialog(null, "El exponente no es válido");
        } else {
            if (Integer.parseInt(terminos[1]) <= this.VF1[0]) {
                pos = (int) VF1[0] + 1 - Integer.parseInt(terminos[1]);
                VF1[pos] = VF1[pos] + Integer.parseInt(terminos[0]);
                this.ajustar();
            } else {
                this.redimensionar(terminos[1]);
                VF1[0] = Integer.parseInt(terminos[1]);
                VF1[1] = Integer.parseInt(terminos[0]);
            }
        }
    }


    //Eliminar

    public void eliminartermino(String termino) {
        int pos = getVF1(0)+1-(Integer.parseInt(termino));      
        if(pos==1){
            setVF1(pos, "0");
            this.ajustar();
            int[] aux = new int[getVF1(0)+2];
            for (int i = 0; i < aux.length; i++) {
                aux[i] = VF1[i];
            }
            VF1 = aux;       
        }else{
            this.setVF1(pos, "0");
        }   
        
    }






    public void mostrarReconstruido() {
        String salida = "<html>";
        int expo;
        for (int i = 1; i < VF1[0] + 2; i++) {
            if (VF1[i] != 0) {
                if (VF1[i] > 0 && i > 1) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo=VF1[0] + 1 - i;
                if(expo == 0 || expo == 1){
                    //termino independiente 
                    if(expo == 0){
                        salida = salida + VF1[i];
                    //termino x^1
                    }else if(expo == 1){
                        salida = salida + VF1[i] + "X";
                    }
                }
                else{
                    salida = salida + VF1[i] + "X<sup>" + ((int) VF1[0] + 1 - i) + "</sup>";
                }
            }
        }
        salida = salida + "</html>";
        JOptionPane.showMessageDialog(null, "polinomio reconstruido\n\n" + salida);
    }
    public String mostrarReconstruidoReturn() {
        String salida = "<html>";
        int expo;
        for (int i = 1; i < VF1[0] + 2; i++) {
            if (VF1[i] != 0) {
                if (VF1[i] > 0 && i > 1) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo=VF1[0] + 1 - i;
                if(expo == 0 || expo == 1){
                    //termino independiente 
                    if(expo == 0){
                        salida = salida + VF1[i];
                    //termino x^1
                    }else if(expo == 1){
                        salida = salida + VF1[i] + "X";
                    }
                }
                else{
                    salida = salida + VF1[i] + "X<sup>" + ((int) VF1[0] + 1 - i) + "</sup>";
                }
            }
        }
        salida = salida + "</html>";
        return salida;
    }

    // SUMA
    public Pf1 sumar(Pf1 B) {
        int k = 1, j = 1, expA, expB, posR, my;
        // Se define el mayor entre los dos para dimensionar vector
        if (VF1[0] > B.VF1[0]) {
            my = this.VF1[0];
        } else {
            my = B.VF1[0];
        }
        // definimos una instancia para el Resultado
        // con el resultado de exponente mayor + 1 para definir bien los datos utiles
        Pf1 R = new Pf1(my + 1);
        String concatenar = "";
        int sumaPos;
        while (k < VF1[0] + 2 && j < B.VF1[0] + 2) {
            expA = this.VF1[0] + 1 - k;
            expB = B.VF1[0] + 1 - j;
            if (expA == expB) {
                posR = R.VF1[0] + 1 - expA;
                sumaPos = this.VF1[k] + B.VF1[j];
                concatenar += sumaPos;
                R.setVF1(posR, concatenar);
                concatenar = "";
                k++;
                j++;
            } else {
                if (expA > expB) {
                    posR = R.VF1[0] + 1 - expA;
                    concatenar += VF1[k];
                    R.setVF1(posR, concatenar);
                    concatenar = "";
                    k++;
                } else {
                    posR = R.VF1[0] + 1 - expB;
                    concatenar += B.VF1[j];
                    R.setVF1(posR, concatenar);
                    concatenar = "";
                    j++;
                }
            }
        }
        R.ajustar();
        return R;
    }

    // SOBRECARGA DEL METODO PARA LEER, RESULTADOS DE OPERACIONES
    public void mostrarReconstruido(Pf1 b, Pf1 res) {
        String salida = "<html>";
        int expo;
        salida += "<h3>Operador 1</h3><br>";
        for (int i = 1; i < this.VF1[0] + 2; i++) {
            if (this.VF1[i] != 0) {
                if (this.VF1[i] > 0 && i > 1) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo=VF1[0] + 1 - i;
                if(expo == 0 || expo == 1){
                    //termino independiente 
                    if(expo == 0){
                        salida = salida + VF1[i];
                    //termino x^1
                    }else if(expo == 1){
                        salida = salida + VF1[i] + "X";
                }
                }else{
                   salida = salida + this.VF1[i] + "X<sup>" + ((int) VF1[0] + 1 - i) + "</sup>";
                }             
            }
        }
        salida += "<br><br><br><br><h3>Operador 2</h3>";
        salida += "<br>";
        for (int i = 1; i < b.VF1[0] + 2; i++) {
            if (b.VF1[i] != 0) {
                if (b.VF1[i] > 0 && i > 1) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo=b.VF1[0] + 1 - i;
                if(expo == 0 || expo == 1){
                    //termino independiente 
                    if(expo == 0){
                        salida = salida + b.VF1[i];
                    //termino x^1
                    }else if(expo == 1){
                        salida = salida + b.VF1[i] + "X";
                }}else{
                salida = salida + b.VF1[i] + "X<sup>" + ((int) b.VF1[0] + 1 - i) + "</sup>";}

            }
        }
        salida += "<br><br><br><br><h3>Resultado</h3>";
        salida += "<br>";
        for (int i = 1; i < res.VF1[0] + 2; i++) {
            if (res.VF1[i] != 0) {
                if (res.VF1[i] > 0 && i > 1) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo=res.VF1[0] + 1 - i;
                if(expo == 0 || expo == 1){
                    //termino independiente 
                    if(expo == 0){
                        salida = salida + res.VF1[i];
                    //termino x^1
                    }else if(expo == 1){
                        salida = salida + res.VF1[i] + "X";
                }}
                else{
                salida = salida + res.VF1[i] + "X<sup>" + ((int) res.VF1[0] + 1 - i) + "</sup>";
                }
            }
        }
        salida = salida + "<br></html>";
        JOptionPane.showMessageDialog(null, "RESULTADO DE LA OPERACION ELEGIDA\n\n\n\n" + salida + "\n\n");
    }

    public Pf1 multiplicar(Pf1 B) {
        int k, j, expA, expB, expR, posR;
        int coeR;
        Pf1 R = new Pf1((int) (VF1[0] + B.VF1[0] + 1));

        for (j = 1; j < B.VF1[0] + 2; j++) {
            expB = (int) B.VF1[0] + 1 - j;
            for (k = 1; k < VF1[0] + 2; k++) {
                expA = VF1[0] + 1 - k;
                expR = expA + expB;
                coeR = VF1[k] * B.VF1[j];
                posR = R.VF1[0] + 1 - expR;
                String aux = "";
                aux += R.VF1[posR] + coeR;
                R.setVF1(posR, aux);
            }
        }
        return (R);
    }

    public int evaluarX(int x) {
        int resul = 0;

        for (int k = 1; k < this.VF1[0] + 2; k++) {
            resul += this.VF1[k] * (float) (Math.pow(x, (this.VF1[0] + 1 - k)));
        }
        return resul;
    }

    // MENU
    public void menu(int opc) {
        switch (opc) {
        case 0:
            break;
        // Ingresar termino
        case 1:
            String exponente = JOptionPane.showInputDialog(
                    "Escriba el termino a insertar, en este formato (nx^e)\n asi sea termino independiente(ej: \n10x^0)\n\n");
            String[] terminos = Pf1.Ingreso(exponente);
            Pf1.leerVector(terminos);
            this.insertartermino(terminos);
            break;
        case 2:
            String termino = JOptionPane.showInputDialog("Escriba el exponente del termino a eliminar\n\n");
            this.eliminartermino(termino);
            break;
        case 3:
            System.out.println();
            this.mostrarForma1();
            break;
        case 4:
            this.mostrarReconstruido();
            break;
        // evaluar segun valor de x
        case 5:
            int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el valor de X a evaluar\n\n\n"));
            x = this.evaluarX(x);
            JOptionPane.showMessageDialog(null, "El resultado es: \n\n" + x);
            break;
        case 6:
            String sCadena = JOptionPane.showInputDialog(null,
                    "Ingrese el valor del polinomio a sumar: \nEn caso de insertar termino independiente"
                            + "\nhacerlo de la form ax^0");
            String[] vectorProcesado = Pf1.Ingreso(sCadena);
            int grado = Pf1.gradoCalc(vectorProcesado);
            Pf1 B = new Pf1(grado + 1);
            B.ingresarTerminos(vectorProcesado);
            Pf1 resultado = this.sumar(B);
            mostrarReconstruido(B, resultado);
            break;
        case 7:
            String sCadena2 = JOptionPane.showInputDialog(null,
                    "Ingrese el valor del polinomio a multiplicar: \nEn caso de insertar termino independiente"
                            + "\nhacerlo de la form ax^0");
            String[] vectorProcesado2 = Pf1.Ingreso(sCadena2);
            int grado2 = Pf1.gradoCalc(vectorProcesado2);
            Pf1 B2 = new Pf1(grado2 + 1);
            B2.ingresarTerminos(vectorProcesado2);
            Pf1 resultado2 = this.multiplicar(B2);
            mostrarReconstruido(B2, resultado2);
            break;
        case 8:

            break;
        }
    }

    //Ingresar terminos a partir de la cadena procesada
    public void ingresarTerminos(String[] sCadena) {
        String coef;
        String exp;
        int i = 0;
        while (i != sCadena.length && sCadena[i] != null) {
            coef = sCadena[i];
            exp = sCadena[i + 1];
            this.almacenarTerm(coef, exp);
            if (i < sCadena.length) {
                i += 2;
            } else {
                break;
            }
        }
    }

    // Método almacenar término en el arreglo del objetp
    public void almacenarTerm(String coef, String exp) {
        int pos;
        if (Integer.parseInt(exp) >= 0 && Integer.parseInt(exp) <= VF1[0]) {
            pos = this.VF1[0] + 1 - Integer.parseInt(exp);
            if (VF1[pos] == 0) {
                VF1[pos] = Integer.parseInt(coef);
            } else {
                //en caso de que el dato este repetido simplemente se suma el coeficiente
                VF1[pos] = VF1[pos] + Integer.parseInt(coef);
            }
        }
    }

    public static void leerVector(int[] Vs) {
        System.out.println();
        for (int i = 0; i < Vs.length; i++) {
            System.out.print("[" + i + "]" + Vs[i] + " ");
        }
    }

    public static void leerVector(String[] Vs) {
        System.out.println();
        for (int i = 0; i < Vs.length; i++) {
            System.out.print("[" + i + "]" + Vs[i] + " ");
        }
    }

    public static String[] Ingreso(String sCadena) {
        // la estructura del ingreso que hemos realizado en clase.
        int j = 0;
        String s = "";
        sCadena = sCadena.toLowerCase();
        char[] Vc = sCadena.toCharArray();
        String[] Vs = new String[Vc.length];

        for (int i = 0; i < Vc.length; i++) {
            // en caso de que encuentre numero o signo menos entra
            if (Character.isDigit(Vc[i]) || Vc[i] == '-' || Vc[i] == '+') {
                // en caso de que s no este vacio y encuentre un signo lo cual significa que hay
                // un termino independiente;
                // por tales razones agregamos lo que hay en s al arreglo y en la siguiente
                // posicion ponemos un cero
                // esta ingresando en

                if (!s.equals("") && (Vc[i] == '+' || Vc[i] == '-') && i != 0) {
                    Vs[j] = s;
                    j++;
                    Vs[j] = "0";
                    j++;
                    s = "";
                    s += Vc[i];
                }
                // en caso de que sea un coeficiente normalito simplemente concatena;
                else {
                    if (Character.isDigit(Vc[i]) || Vc[i] == '-') {
                        s += Vc[i];
                    }
                }
            } else {
                // en el caso de que sea x
                if (Vc[i] == 'x') {
                    // cuando encuentra una x pero s esta vacio significa que no tiene coeficiente
                    // por tal agregamos un uno
                    if (s.equals("") || s.equals("+")) {
                        Vs[j] = "1";
                        s = ""; // para reiniciar la s pues en algun caso se colaba el +
                        j++;
                    } else {
                        // en caso de que encuentre la s solo con un menos significa que tampoco tiene
                        // coeficiente entonces procedemos a agregarle el 1 y a meterlo en el array
                        if (s.equals("-")) {
                            Vs[j] = "-1";
                            s = "";
                            j++;
                        }
                        // caso general cuando s si tiene contenido entonces lo concatena al arreglo al
                        // encontrar una x
                        else {
                            Vs[j] = s;
                            j++;
                            s = "";
                        }
                    }
                    // lo mismo de la linea 88(if de abajo) pero en un caso intermedio
                    // estando en posicion x revisamos si termina el arreglo
                    if (i + 1 <= Vc.length - 2) {
                        // si la posicion siguiente es diferente de ^ entonces agregamos exponente 1
                        if (Vc[i + 1] != '^') {
                            Vs[j] = "1";
                            j++;
                        }
                    }
                    // cuando se termina en x, arriba en el condicional de linea 90 a 96 se le
                    // agrega el uno como coeficiente pero queda faltando agregar exponente
                    if (Vc[i] == 'x' && i == Vc.length - 1) {
                        Vs[j] = "1";
                        j++;
                        // Vs[j] = "1";
                    }
                } else {
                    if (Vc[i] == '^') {
                        s += Vc[i + 1];
                        i += 1;
                        Vs[j] = s;
                        j++;
                        s = "";
                    }
                }
            }
        }
        // en caso de que s no este vacio significa que hay un termino independiente en
        // ella por tal la agregamos al arreglo y le ponemos su exponente 0
        if (!s.equals("")) {
            Vs[j] = s;
            j++;
            Vs[j] = "0";
        }
        // para imprimir el vector resultante
        for (int i = 0; i < Vs.length; i++) {
            System.out.print("[" + i + "]" + Vs[i] + " ");
        }
        // calcularemos los datos utiles
        // char[] Vc = sCadena.toCharArray();
        return Vs;
    }

    public static int gradoCalc(String[] Vs) {
        int grado = 0;
        for (int i = 1; i < Vs.length; i += 2) {
            if (Vs[i] != null) {
                if (Integer.parseInt(Vs[i]) > grado) {
                    grado = Integer.parseInt(Vs[i]);
                }
            } else {
                break;
            }
        }
        return grado;
    }
}
