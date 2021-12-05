import javax.swing.JOptionPane;

public class PL {
    private Nodo cab;

    public PL() {
        cab = null;
    }

    public Nodo getCab() {
        return cab;
    }

    public void setCab(Nodo cab) {
        this.cab = cab;
    }

    public void mostrar() {
        Nodo p = getCab();
        
        if (cab == null) {
            System.out.println("El polinomio esta vacio");

        } else {
            String concatenador = "";
            while (p != null) {
                if (p.getCoef() > 0 && p != getCab()) {
                    concatenador += "+" + p.getCoef() + "X^" + p.getExp();
                } else {
                    concatenador += p.getCoef() + "X^" + p.getExp();
                }
                p = p.getLiga();
            }
        }
    }

    public void almacenarTermino(int coe, int exp) {
        Nodo x, ant = null, p = cab;
        while (p != null && p.getExp() > exp) {
            ant = p;
            p = p.getLiga();
        }
        if (p != null && p.getExp() == exp) {
            System.out.println("Ya existe un termino con ese exponente");
            if(p.getCoef()+coe!=0){
                p.setCoef(coe+p.getCoef());
            }else{
                eliminarTerm(exp);
            }
        } else {
            x = new Nodo(coe, exp);
            x.setLiga(p);
            if (p == getCab()) {
                setCab(x);
            } else {
                ant.setLiga(x);
            }
        }
    }

    // ingresar n cantidad de terminos teniendo en cuenta
    // el tamano del string
    public void ingresarTerminoPF3(String[] cadena) {
        int exp, coef;
        for (int i = 0; i < cadena.length; i += 2) {
            coef = Integer.parseInt(cadena[i]);
            exp = Integer.parseInt(cadena[i + 1]);
            this.almacenarTermino(coef, exp);
        }
    }

    // evaluar segun valor x
    public int evaluar(int x) {
        int r = 0;
        Nodo p = this.getCab();
        if (p == null) {
            JOptionPane.showMessageDialog(null, "El polinomio esta vacio");
        } else {
            while (p != null) {
                r += p.getCoef() * Math.pow(x, p.getExp());
                p = p.getLiga();
            }
        }
        return r;
    }

    // insertar un termino
    public void insertarTerminoPf3(int coef, int exp) {
        if (coef != 0) {
            Nodo x, ant = null, p = cab;
            while (p != null && p.getExp() > exp) {
                ant = p;
                p = p.getLiga();
            }
            if (p != null && p.getExp() == exp) {
                if (p.getCoef() + coef != 0) {
                    p.setCoef(p.getCoef() + coef);
                } else {
                    if (p == this.getCab()) {
                        this.cab = cab.getLiga();
                    } else {
                        ant.setLiga(p.getLiga());
                    }
                }
            } else {
                x = new Nodo(coef, exp);
                x.setLiga(p);
                if (p == this.cab) {
                    setCab(x);;
                } else {
                    ant.setLiga(x);
                }
            }
        }
    }

    // sumar terminos
    public PL sumarPl(PL b) {
        Nodo p, q;
        p = this.cab;
        q = b.getCab();
        PL r = new PL();
        while (p != null && q != null) {
            if (p.getExp() == q.getExp()) {
                if (p.getCoef() + q.getCoef() != 0) {
                    r.insertarTerminoPf3(p.getCoef() + q.getCoef(), p.getExp());
                    p = p.getLiga();
                    q = q.getLiga();
                }
            } else {
                if (p.getExp() > q.getExp()) {
                    r.insertarTerminoPf3(p.getCoef(), p.getExp());
                    p = p.getLiga();
                } else {
                    r.insertarTerminoPf3(q.getCoef(), q.getExp());
                    q = q.getLiga();
                }
            }
        }
        while (p != null) {
            r.insertarTerminoPf3(p.getCoef(), p.getExp());
            p = p.getLiga();
        }
        while (q != null) {
            r.insertarTerminoPf3(q.getCoef(), q.getExp());
            q = q.getLiga();
        }
        return r;
    }

    public PL multiplicar(PL b) {
        Nodo p = cab, q = b.getCab();
        if (p == null || q == null) {
            System.out.println("un polinomio esta vacio por tanto no hay que sumar");
        } else {
            PL r = new PL();
            while (q != null) {
                p = cab;
                while (p != null) {
                    r.insertarTerminoPf3(p.getCoef() * q.getCoef(), p.getExp() + q.getExp());
                    p = p.getLiga();
                }
                q = q.getLiga();
            }
            return r;
        }
        return null;
    }
    //metodo para eliminar
    public void eliminarTerm(int exp){
        Nodo p = this.getCab(), anterior = null;
        if(p!=null){
            while(p!=null && p.getExp()<exp){
                //recorremos con p hasta encontrar el termino que buscamos
                anterior = p;
                p=p.getLiga();
            }
            if(p!=null && p.getExp()==exp){
                if(p==this.getCab()){
                    setCab(p.getLiga());
                }else{
                    anterior.setLiga(p.getLiga());
                }
            }

        }else{
            JOptionPane.showMessageDialog(null, "El polinomio esta vacio");
        }
    }
    //menu
    public void menu(int opt) {
        switch (opt) {
        case 0:
            break;
        // Ingresar termino melo
        case 1:
            String termino = JOptionPane.showInputDialog(
                    "Escriba el termino a insertar, en este formato (nx^e)\n asi sea termino independiente(ej: \n10x^0)\n\n");
                    String[] vectorProcesado = Pf1.Ingreso(termino);
                    vectorProcesado = Pf2.eliminarNulls(vectorProcesado);
                    this.ingresarTerminoPF3(vectorProcesado);
            break;
        // eliminar terminos
        case 2:
            int exp = Integer.parseInt(JOptionPane.showInputDialog("escriba el exponente del termino a eliminar"));
            eliminarTerm(exp);            
            break;

        // mostrar forma
        case 3:
            this.mostrarForma3();
            break;
        // mostrar reconstruido
        case 4:
            this.mostrarReconstruido();
            break;

        // evaluar segun valor de x melo
        case 5:
            int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el valor de X a evaluar\n\n\n"));
            double res;
            res = this.evaluar(x);
            JOptionPane.showMessageDialog(null, "El resultado es: \n\n" + res);
            break;
        // sumar
        case 6:
            String sCadena3 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
            // Constructor
            PL b = new PL();
            String[] vectorProcesado3 = Pf1.Ingreso(sCadena3);
            vectorProcesado3 = Pf2.eliminarNulls(vectorProcesado3);
            Pf1.leerVector(vectorProcesado3);
            // Pf2.ordenamiento(vectorProcesado2);
            Pf1.leerVector(vectorProcesado3);
            b.ingresarTerminoPF3(vectorProcesado3);
            PL respuesta = sumarPl(b);
            mostrarReconstruido(b, respuesta);
            break;
        // multiplicar
        case 7:

            String sCadena4 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
            // Constructor
            PL c = new PL();
            String[] vectorProcesado4 = Pf1.Ingreso(sCadena4);
            vectorProcesado4 = Pf2.eliminarNulls(vectorProcesado4);
            Pf1.leerVector(vectorProcesado4);
            c.ingresarTerminoPF3(vectorProcesado4);
            PL r = multiplicar(c);
            mostrarReconstruido(c, r);
            break;
        case 8:
            break;
        }
    }

    // mostrar forma 3
    public void mostrarForma3() {
        String salida = "";
        Nodo p = this.getCab();
        if (p != null) {
            while (p != null) {
                salida += "{" + p.getCoef() + " | " + p.getExp() + "} --|-> ";
                p = p.getLiga();
            }
            JOptionPane.showMessageDialog(null, "polinomio de la forma 3 \n\n\n" + salida+"null" + "\n\n");
        } else {
            JOptionPane.showMessageDialog(null, "el polinomio esta vacio");
        }
    }

    // mostrar reconstruido
    public void mostrarReconstruido() {
        Nodo p = this.getCab();
        String salida = "<html>";
        int expo;
        int i = 0;
        while (p != null) {
            if (p.getCoef() != 0) {
                if (p.getCoef() > 0 && i > 0) {
                    salida = salida + " + ";
                } 
                if(p.getCoef()<0) {
                    salida = salida + " ";
                }
                expo = p.getExp();
                if (expo == 0 || expo == 1) {
                    // termino independiente
                    if (expo == 0) {
                        salida = salida + p.getCoef();
                        // termino x^1
                    } else if (expo == 1) {
                        salida = salida + p.getCoef() + "X";
                    }
                } else {
                    if(p.getCoef()!=1){
                        salida = salida + p.getCoef() + "X<sup>" + p.getExp() + "</sup>";
                    }else{
                        salida = salida + "X<sup>" + p.getExp() + "</sup>";
                    }
                }
            }
            p = p.getLiga();
            i++;
        }
        salida = salida + "</html>";
        JOptionPane.showMessageDialog(null, "polinomio reconstruido\n\n" + salida);
    }

    public String mostrarReconstruidoReturn() {
        Nodo p = this.getCab();
        String salida = "<html>";
        int expo;
        int i = 0;
        while (p != null) {
            if (p.getCoef() != 0) {
                if (p.getCoef() > 0 && i > 0) {
                    salida = salida + " + ";
                } 
                if(p.getCoef()<0) {
                    salida = salida + " ";
                }
                expo = p.getExp();
                if (expo == 0 || expo == 1) {
                    // termino independiente
                    if (expo == 0) {
                        salida = salida + p.getCoef();
                        // termino x^1
                    } else if (expo == 1) {
                        salida = salida + p.getCoef() + "X";
                    }
                } else {
                    if(p.getCoef()!=1){
                        salida = salida + p.getCoef() + "X<sup>" + p.getExp() + "</sup>";
                    }else{
                        salida = salida + "X<sup>" + p.getExp() + "</sup>";
                    }
                }
            }
            p = p.getLiga();
            i++;
        }
        salida = salida + "</html>";
        return salida;
    }

    // mostrar reconstruido para resultados de ops
    public void mostrarReconstruido(PL b, PL a) {
        Nodo p = this.getCab();
        Nodo q = b.getCab();
        Nodo u = a.getCab();
        String salida = "<html>";
        int expo;
        int i = 0;
        int x = 0;
        int z = 0;
        while (p != null) {
            if (p.getCoef() != 0) {
                if (p.getCoef() > 0 && i > 0) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo = p.getExp();
                if (expo == 0 || expo == 1) {
                    // termino independiente
                    if (expo == 0) {
                        salida = salida + p.getCoef();
                        // termino x^1
                    } else if (expo == 1) {
                        salida = salida + p.getCoef() + "X";
                    }
                } else {
                    if(q.getCoef()!=1){
                        salida = salida + p.getCoef() + "X<sup>" + p.getExp() + "</sup>";
                    }else{
                        salida = salida + "X<sup>" + p.getExp() + "</sup>";
                    }
                }
            }
            p = p.getLiga();
            i++;
        }
        salida += "<br><br>";
        while (q != null) {
            if (q.getCoef() != 0) {
                if (q.getCoef() > 0 && x > 0) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo = q.getExp();
                if (expo == 0 || expo == 1) {
                    // termino independiente
                    if (expo == 0) {
                        salida = salida + q.getCoef();
                        // termino x^1
                    } else if (expo == 1) {
                        salida = salida + q.getCoef() + "X";
                    }
                } else {
                    if(q.getCoef()!=1){
                        salida = salida + q.getCoef() + "X<sup>" + q.getExp() + "</sup>";
                    }else{
                        salida = salida + "X<sup>" + q.getExp() + "</sup>";
                    }
                }
            }
            q = q.getLiga();
            x++;
        }
        salida += "<br>";
        salida += "_________________<br><br>";
        while (u != null) {
            if (u.getCoef() != 0) {
                if (u.getCoef() > 0 && z > 0) {
                    salida = salida + " + ";
                } else {
                    salida = salida + " ";
                }
                expo = u.getExp();
                if (expo == 0 || expo == 1) {
                    // termino independiente
                    if (expo == 0) {
                        salida = salida + u.getCoef();
                        // termino x^1
                    } else if (expo == 1) {
                        salida = salida + u.getCoef() + "X";
                    }
                } else {
                    if(u.getCoef()!=1){
                        salida = salida + u.getCoef() + "X<sup>" + u.getExp() + "</sup>";
                    }else{
                        salida = salida + "X<sup>" + u.getExp() + "</sup>";
                    }
                }
            }
            u = u.getLiga();
            z++;
        }
        salida = salida + "</html>";
        JOptionPane.showMessageDialog(null, "polinomio reconstruido\n\n" + salida);
    }
    public int cuantosterminos(){
        Nodo p = this.getCab();
        int contador=0;
        while (p!=null) {
            contador+=1;
            p=p.getLiga();
        }
        return contador;
    }
}

