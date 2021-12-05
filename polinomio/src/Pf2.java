import javax.swing.JOptionPane;

public class Pf2 {
    int VF2[], cantidadterminos;

    public int getCantidadterminos() {
        return cantidadterminos;
    }

    public void setCantidadterminos(int cantidadterminos) {
        this.cantidadterminos = cantidadterminos;
    }

    public int[] getVF2() {
        return VF2;
    }

    public int getVF2(int n) {
        return VF2[n];
    }

    public void setVF2(int[] vF2) {
        VF2 = vF2;
    }

    public void setVF2(int pos, int dato) {
        VF2[pos] = dato;
    }

    // metodo constructor
    public Pf2(int cantidadterminos) {
        this.cantidadterminos = cantidadterminos;
        VF2 = new int[cantidadterminos * 2 + 1];
        VF2[0] = this.cantidadterminos;
    }

    // redimensionar vector
    public void redimensionar() {
        int n = getVF2(0) + 1;
        int aux[] = new int[n * 2 + 1];
        for (int j = 0; j < getVF2(0) * 2 + 1; j++) {
            aux[j] = this.getVF2(j);
        }
        this.setVF2(aux);
        this.setVF2(0, n);
    }

    // Método para almacenar un término
    public void almacenarTerm(int coef, int exp) {
        int k = 1, j;
        // int suma;
        while (k < getVF2(0) * 2 + 1 && getVF2(k) > exp) {
            k = k + 2;
        }
        // en caso de que se repitan terminos simplemente se sumaran
        if ((k < getVF2(0) * 2 + 1 && getVF2(k) == exp && getVF2(k + 1) != 0)) {
            /*
             * suma = getVF2(k + 1) + coef; if (suma != 0) { setVF2(k + 1, suma); } else {
             * // movemos una posicion a la izquierda todos los teerminos para suplir el //
             * termino eliminado for (int i = k + 2; i < getVF2(0) * 2 + 1; i++) { setVF2(i
             * - 2, getVF2(i)); } setVF2(0, getVF2(0) - 1);
             */
            // }
            System.out.println("Termino repetido");
            JOptionPane.showMessageDialog(null,
                    "Hay un termino repetido en el polinomio\npor lo cual no se puede ingresar");
        } else {
            // this.redimensionar();
            for (j = getVF2(0) * 2 - 1; j > k; j--) {
                this.setVF2(j + 1, getVF2(j - 1));
            }
            this.setVF2(k, exp);
            this.setVF2(k + 1, coef);
            // setCantidadterminos(getVF2(0));
        }
    }

    // Método para ingresar los términos del polinomio
    public void ingresarTerminos(String[] arreglo) {
        int k, exp;
        int coef;
        for (k = 0; k < arreglo.length; k += 2) {
            coef = Integer.parseInt(arreglo[k]);
            exp = Integer.parseInt(arreglo[k + 1]);
            this.almacenarTerm(coef, exp);
        }
    }

    // para la suma
    public void ingresarTerminosint(String[] arreglo) {
        int k, exp;
        int coef;
        for (k = 0; k < arreglo.length; k += 2) {
            coef = Integer.parseInt(arreglo[k]);
            exp = Integer.parseInt(arreglo[k + 1]);
            this.ingresarunterminoint(coef, exp);
        }
    }

    // mostrar forma 2
    public void mostrarPolinomioF2() {
        String concat = "";
        for (int i = 0; i < this.VF2.length; i++) {
            concat += getVF2(i) + "   ";
        }
        JOptionPane.showMessageDialog(null,
                "Polinomio de la forma 2\n\n\n" + concat + "\n\ncon tamano de arreglo " + getVF2().length);
    }

    // evaluar con valor x
    public double evaluar(int x) {
        double res = 0;
        for (int k = 1; k < getVF2(0) * 2 + 1; k += 2) {
            res = res + getVF2(k + 1) * (Math.pow(x, getVF2(k)));
        }
        return (res);
    }

    // inseertar termino
    public void ingresaruntermino(String coef, String exp) {
        if (Integer.parseInt(coef) != 0) {
            int k = 1;
            while ((k < getVF2(0) * 2 + 1) && getVF2(k) > Integer.parseInt(exp)) {
                k += 2;
            }
            if (k < getVF2(0) * 2 + 1 && getVF2(k) == Integer.parseInt(exp)) {
                if (getVF2(k + 1) + Integer.parseInt(coef) != 0) {
                    setVF2(k + 1, getVF2(k + 1) + Integer.parseInt(coef));
                } else {
                    for (int i = k; i < getVF2(0) * 2 - 1; i += 2) {
                        setVF2(i, getVF2(i + 2));
                        setVF2(i + 1, getVF2(i + 3));
                    }
                    setVF2(0, getVF2(0) - 1);
                }
            } else {
                if (getVF2(0) * 2 == getVF2().length) {
                    redimensionar();
                }
                // abre el hueco para la insercion del nuevo termino
                for (int i = getVF2(0) * 2 - 4; i >= k; i--) {
                    setVF2(i + 2, getVF2(i));
                }
                setVF2(k, Integer.parseInt(exp));
                setVF2(k + 1, Integer.parseInt(coef));
                setVF2(0, getVF2(0) + 1);
            }
        }
    }

    // sobrecarga del metodo insertar termino
    public void ingresarunterminoint(int coef, int exp) {
        if (coef != 0) {
            int k = 1, i = 0;
            // k queda posicionado en la posicion del exponente ingresado
            while ((k < getCantidadterminos() * 2 + 1) && this.getVF2(k) > exp) {
                k += 2;
            }
            // verificamos que k haya quedado en una posicion valida
            // y luego comparamos si la posicion de k es igual al exponente
            if (k < this.getVF2(0) * 2 + 1 && this.getVF2(k) == exp) {
                // si la suma del coeficiente (que es la posicion k+1)
                // con el coeficiente ingresado es diferente de 0
                // simplemente se suma
                if (this.getVF2(k + 1) + coef != 0) {
                    this.setVF2(k + 1, this.getVF2(k + 1) + coef);
                }
                // en caso de que de 0 procedemos a eliminar termino
                else {
                    // se reduce 1 termino desplazando el arreglo a la izquierda
                    for (i = k; i < getVF2(0) * 2 - 1; i += 2) {
                        setVF2(i, getVF2(i + 2));
                        setVF2(i + 1, getVF2(i + 3));
                    }
                    // le restamos un dato util
                    this.setVF2(0, getVF2(0) - 1);
                    // this.setCantidadterminos(getCantidadterminos() - 1);
                }
            }
            // en caso de que no encuentre el exponente
            else {
                // En caso que los datos utiles +1 sean iguales al tamano del arreglo le
                // agregamos una posicion
                if (this.getVF2(0) * 2 + 1 == getVF2().length) {
                    this.redimensionar();
                }
                // abre espacio para insertar un dato
                for (i = getVF2().length - 1 - 3; i >= k; i--) {
                    setVF2(i + 2, getVF2(i));
                }
                this.setVF2(k, exp);
                this.setVF2(k + 1, coef);
                this.setVF2(0, getVF2(0) + 1);
                // this.setCantidadterminos(getVF2(0) * 2 + 1);
            }

        }
    }

    // para ordenar lo primero que haremos es eliminar los null de la cadena
    public static String[] eliminarNulls(String[] cadena) {
        int k = 0;
        // recorremos la cadena hasta el valor nulo
        while (cadena[k] != null) {
            k++;
        }
        String[] aux = new String[k];
        for (int i = 0; i < cadena.length; i++) {
            if (cadena[i] != null) {
                aux[i] = cadena[i];
            } else {
                break;
            }
        }
        return aux;
    }

    // mostrar reconstruido
    public void mostrarReconstruido() {
        String salida = "<html>";
        int expo;
        int k = 1;
        while (k < getVF2().length) {
            if (k + 1 < getVF2().length) {
                if (getVF2(k + 1) != 0) {
                    if (getVF2(k + 1) > 0 && k > 1) {
                        salida = salida + " + ";
                    } else {
                        salida = salida + " ";
                    }
                    expo = getVF2(k);
                    if (expo == 0 || expo == 1) {
                        // termino independiente
                        if (expo == 0) {
                            salida = salida + getVF2(k + 1);
                            // termino x^1
                        } else if (expo == 1) {
                            salida = salida + getVF2(k + 1) + "X";
                        }
                    } else {
                        salida = salida + getVF2(k + 1) + "X<sup>" + getVF2(k) + "</sup>";
                    }
                }
            }
            k += 2;
        }
        salida = salida + "</html>";
        JOptionPane.showMessageDialog(null, "polinomio reconstruido\n\n" + salida);
    }

    // sobrecarga del metodo reconstruido para mostrar la respuesta de operaciones
    public void mostrarReconstruido(Pf2 b, Pf2 r) {
        String salida = "<html>";
        int expo;
        int k = 1;
        while (k < getVF2().length) {
            if (k + 1 < getVF2().length) {
                if (getVF2(k + 1) != 0) {
                    if (getVF2(k + 1) > 0 && k > 1) {
                        salida = salida + " + ";
                    } else {
                        salida = salida + " ";
                    }
                    expo = getVF2(k);
                    if (expo == 0 || expo == 1) {
                        // termino independiente
                        if (expo == 0) {
                            salida = salida + getVF2(k + 1);
                            // termino x^1
                        } else if (expo == 1) {
                            salida = salida + getVF2(k + 1) + "X";
                        }
                    } else {
                        salida = salida + getVF2(k + 1) + "X<sup>" + getVF2(k) + "</sup>";
                    }
                }
            }
            k += 2;
        }
        salida += "<br>";
        salida += "<br>";
        int j = 1;
        while (j < b.getVF2().length) {
            if (j + 1 < b.getVF2().length) {
                if (b.getVF2(j + 1) != 0) {
                    if (b.getVF2(j + 1) > 0 && j > 1) {
                        salida = salida + " + ";
                    } else {
                        salida = salida + " ";
                    }
                    expo = b.getVF2(j);
                    if (expo == 0 || expo == 1) {
                        // termino independiente
                        if (expo == 0) {
                            salida = salida + b.getVF2(j + 1);
                            // termino x^1
                        } else if (expo == 1) {
                            salida = salida + b.getVF2(j + 1) + "X";
                        }
                    } else {
                        salida = salida + b.getVF2(j + 1) + "X<sup>" + b.getVF2(j) + "</sup>";
                    }
                }
            }
            j += 2;
        }
        salida += "<br>";
        salida += "<br>";
        int i = 1;
        while (i < r.getVF2().length) {
            if (i + 1 < r.getVF2().length) {
                if (r.getVF2(i + 1) != 0) {
                    if (r.getVF2(i + 1) > 0 && i > 1) {
                        salida = salida + " + ";
                    } else {
                        salida = salida + " ";
                    }
                    expo = r.getVF2(i);
                    if (expo == 0 || expo == 1) {
                        // termino independiente
                        if (expo == 0) {
                            salida = salida + r.getVF2(i + 1);
                            // termino x^1
                        } else if (expo == 1) {
                            salida = salida + r.getVF2(i + 1) + "X";
                        }
                    } else {
                        salida = salida + r.getVF2(i + 1) + "X<sup>" + r.getVF2(i) + "</sup>";
                    }
                }
            }
            i += 2;
        }

        salida = salida + "</html>";
        JOptionPane.showMessageDialog(null, "Resultado de la operacion elegida\n\n" + salida + "\n\n\n");
    }

    // mostrar reconstruido con return
    public String mostrarReconstruidoReturn() {
        String salida = "<html>";
        int expo;
        int k = 1;
        while (k < getVF2().length) {
            if (k + 1 < getVF2().length) {
                if (getVF2(k + 1) != 0) {
                    if (getVF2(k + 1) > 0 && k > 1) {
                        salida = salida + " + ";
                    } else {
                        salida = salida + " ";
                    }
                    expo = getVF2(k);
                    if (expo == 0 || expo == 1) {
                        // termino independiente
                        if (expo == 0) {
                            salida = salida + getVF2(k + 1);
                            // termino x^1
                        } else if (expo == 1) {
                            salida = salida + getVF2(k + 1) + "X";
                        }
                    } else {
                        salida = salida + getVF2(k + 1) + "X<sup>" + getVF2(k) + "</sup>";
                    }
                }
            }
            k += 2;
        }
        salida = salida + "</html>";
        return salida;
    }

    // menu del polinomio 2
    public void menu(int opc) {
        switch (opc) {
            case 0:
                break;
            // Ingresar termino melo
            case 1:
                String exponente = JOptionPane.showInputDialog(
                        "Escriba el termino a insertar, en este formato (nx^e)\n asi sea termino independiente(ej: \n10x^0)\n\n");
                String[] terminos = Pf1.Ingreso(exponente);
                Pf1.leerVector(terminos);
                terminos = Pf2.eliminarNulls(terminos);
                String coef, exp;
                if (terminos.length < 2) {
                    exp = terminos[1];
                    coef = terminos[0];
                    this.ingresaruntermino(coef, exp);
                } else {
                    for (int i = 0; i < terminos.length; i += 2) {
                        exp = terminos[i + 1];
                        coef = terminos[i];
                        this.ingresaruntermino(coef, exp);
                    }
                }

                break;
            // eliminar terminos
            case 2:
                String Exp = JOptionPane.showInputDialog(null, "Escriba el exponete del termino a eliminar: ");
                int expo = Integer.parseInt(Exp);
                this.eliminarTermino(expo);
                break;

            // mostrar forma melo
            case 3:
                this.mostrarPolinomioF2();
                break;
            // mostrar reconstruido melo
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
                int nterm1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de terminos:"));
                String sCadena3 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
                // Constructor
                Pf2 b = new Pf2(nterm1);
                String[] vectorProcesado3 = Pf1.Ingreso(sCadena3);
                vectorProcesado3 = Pf2.eliminarNulls(vectorProcesado3);
                Pf1.leerVector(vectorProcesado3);
                // el termino 2 ingresa perfectamente
                b.ingresarTerminos(vectorProcesado3);
                Pf1.leerVector(b.getVF2());

                Pf2 respuesta = sumarPf2(b);
                this.mostrarReconstruido(b, respuesta);
                break;
            // multiplicar
            case 7:
                int nterm2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de terminos:"));
                String sCadena4 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
                // Constructor
                Pf2 c = new Pf2(nterm2);
                String[] vectorProcesado4 = Pf1.Ingreso(sCadena4);
                vectorProcesado4 = Pf2.eliminarNulls(vectorProcesado4);
                Pf1.leerVector(vectorProcesado4);
                // Pf2.ordenamiento(vectorProcesado2);
                Pf1.leerVector(vectorProcesado4);
                c.ingresarTerminos(vectorProcesado4);
                Pf1.leerVector(c.getVF2());
                Pf2 r = multiplicar(c);
                this.mostrarReconstruido(c, r);
                break;
            case 8:
                break;
        }
    }

    // suma
    public Pf2 sumarPf2(Pf2 b) {
        int k = 1, j = 1, expA, expB, coeA, coeB;
        Pf2 respuesta = new Pf2(getVF2(0));
        while ((k < getVF2(0) * 2 + 1) && (j < b.getVF2(0) * 2 + 1)) {
            expA = getVF2(k);
            expB = b.getVF2(j);
            coeA = getVF2(k + 1);
            coeB = b.getVF2(j + 1);
            if (expA == expB) {
                if ((coeA + coeB) != 0) {
                    respuesta.ingresarunterminoint(coeA + coeB, expA);
                }
                // en caso de que sean iguales y su suma sea igual a cero
                // se procede a reducir la cantidad de terminos
                else {
                    respuesta.setVF2(0, getVF2(0) - 1);

                }
                k = k + 2;
                j = j + 2;
            } else {
                if (expA > expB) {
                    respuesta.ingresarunterminoint(coeA, expA);
                    k += 2;
                } else {
                    respuesta.ingresarunterminoint(coeB, expB);
                    j += 2;
                }
            }
        }
        while (j < b.getVF2(0) * 2 + 1) {
            respuesta.ingresarunterminoint(b.getVF2(j + 1), b.getVF2(j));
            j += 2;
        }
        while (k < this.getVF2(0) * 2 + 1) {
            respuesta.ingresarunterminoint(this.getVF2(k + 1), this.getVF2(k));
            k += 2;
        }
        return respuesta;
    }

    // multiplicar
    public Pf2 multiplicar(Pf2 b) {
        int j, expa, expb, expr, coefr;
        Pf2 r = new Pf2(this.getVF2(0) * 2 + 3);
        for (j = 1; j < (b.getVF2(0) * 2 + 1); j += 2) {
            expb = b.getVF2(j);
            for (int i = 1; i < getVF2(0) * 2 + 1; i += 2) {
                expa = getVF2(i);
                expr = expa + expb;
                coefr = getVF2(i + 1) * b.getVF2(j + 1);
                r.ingresarunterminoint(coefr, expr);
            }
        }
        return r;
    }

    //eliminar
    public void eliminarTermino(int expo){
        for (int i = 1; i < getVF2(0)*2+1; i+=2) {
            if(getVF2(i)== expo){
                setVF2(i, 0);
                setVF2(i+1, 0);
            }
        }
        
    }
}
