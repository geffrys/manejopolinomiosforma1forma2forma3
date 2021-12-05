import javax.swing.JOptionPane;

public class forma1masforma2 {
    public static void principal() {
        // increso del polinomio forma 1
        JOptionPane.showMessageDialog(null, "----Polinomio Forma 1----");
        String sCadena = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");

        // OBTENEMOS EL GRADO RECORRIENDO EL VECTOR PROCESADO
        String[] vectorProcesado = Pf1.Ingreso(sCadena);
        int grado = Pf1.gradoCalc(vectorProcesado);

        // constructor
        Pf1 polinomiof1 = new Pf1(grado + 1);
        polinomiof1.ingresarTerminos(vectorProcesado);

        // ingreso del polinomio forma 2
        JOptionPane.showMessageDialog(null, "----Polinomio Forma 2----");
        int nterm = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de terminos:"));
        String sCadena2 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
        // Constructor
        Pf2 polinomiof2 = new Pf2(nterm);
        String[] vectorProcesado2 = Pf1.Ingreso(sCadena2);
        vectorProcesado2 = Pf2.eliminarNulls(vectorProcesado2);
        polinomiof2.ingresarTerminos(vectorProcesado2);

        suma(polinomiof1, polinomiof2);
    }

    public static void suma(Pf1 a, Pf2 b) {

        int k = 1, j = 1, expA, expB, my;
        // Se define el mayor entre los dos para dimensionar vector
        
        // definimos una instancia para el Resultado
        // con el resultado de exponente mayor + 1 para definir bien los datos utiles
        PL R = new PL();
        int sumaPos;
        while (k < a.VF1[0] + 2 && j < b.getVF2().length + 1) {
            expA = a.VF1[0] + 1 - k;
            expB = b.getVF2(j);
            if (expA == expB) {
                sumaPos = a.VF1[k] + b.getVF2(j + 1);
                if (sumaPos != 0) {
                    R.almacenarTermino(sumaPos, expA);
                }
                k++;
                j += 2;
            } else {
                if (expA > expB) {
                    R.almacenarTermino(a.getVF1(k), expA);
                    k++;
                } if(expB > expA){
                    R.almacenarTermino(b.getVF2(j + 1), expB);
                    j += 2;
                }
            }

            //if (expA == 0) {
            //    Nodo p = R.getCab();
            //    Nodo ant = null;
            //    while (p.getLiga() != null) {
            //        ant = p;
            //        p = p.getLiga();
            //    }
            //    if (p.getExp() == expA) {
            //        p.setCoef(a.getVF1(k) + p.getCoef());
            //    } else {
            //        Nodo x = new Nodo(a.getVF1(k), 0);
            //        x.setLiga(null);
            //        ant.setLiga(x);
            //    }
            //} if (expB == 0) {
            //    Nodo pi = R.getCab();
            //    Nodo ant = null;
            //    while (pi.getLiga() != null) {
            //        ant = pi;
            //        pi = pi.getLiga();
            //    }
            //    if (pi.getExp() == expB) {
            //        pi.setCoef(b.getVF2(j+1) + pi.getCoef());
            //    } else {
            //        Nodo x = new Nodo(b.getVF2(j+1), 0);
            //        x.setLiga(null);
            //        ant.setLiga(x);
            //    }
            //}
        }
        R.mostrarForma3();
        a.mostrarForma1();
        b.mostrarPolinomioF2();
        JOptionPane.showMessageDialog(null, "\n" + a.mostrarReconstruidoReturn() + "\n\n"
                + b.mostrarReconstruidoReturn() + "\n\n _______________\n" + R.mostrarReconstruidoReturn());

    }

}
