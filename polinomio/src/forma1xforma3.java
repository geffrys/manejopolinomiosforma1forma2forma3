import javax.swing.JOptionPane;

public class forma1xforma3 {
    public static void principal() {
        // INSERTAMOS LA FORMA 1
        JOptionPane.showMessageDialog(null, "----Polinomio forma 1----");
        String sCadena = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
        // OBTENEMOS EL GRADO RECORRIENDO EL VECTOR PROCESADO
        String[] vectorProcesado = Pf1.Ingreso(sCadena);
        int grado = Pf1.gradoCalc(vectorProcesado);
        // constructor
        Pf1 polinomiof1 = new Pf1(grado + 1);
        polinomiof1.ingresarTerminos(vectorProcesado);
        // INSERTAMOS LA FORMA 3
        JOptionPane.showMessageDialog(null, "----Polinomio forma 3 o en lista----");
        String sCadena3 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
        // Constructor
        String[] vectorProcesado3 = Pf1.Ingreso(sCadena3);
        vectorProcesado3 = Pf2.eliminarNulls(vectorProcesado3);
        Pf1.leerVector(vectorProcesado3);
        PL polinomiof3 = new PL();
        polinomiof3.ingresarTerminoPF3(vectorProcesado3);
        // UNA VEZ INGRESADOS LOS TERMINOS SE PROCEDE A SUMARLOS
        multiplicacion(polinomiof1, polinomiof3);

    }

    public static void multiplicacion(Pf1 a, PL b) {
        int expA, expB, expR, coeR;
        Nodo p = b.getCab();
        int cantidad;
        if (a.getVF1(0) > b.cuantosterminos()) {
            cantidad = (a.getVF1(0) - b.cuantosterminos()) + a.getVF1(0) + 2;
        } else {
            cantidad = (b.cuantosterminos() - a.getVF1(0)) + a.getVF1(0) + 2;
        }
        Pf2 c = new Pf2(cantidad);
        while (p != null) {
            expB = p.getExp();
            for (int k = 1; k < a.getVF1(0) + 2; k++) {
                expA = a.getVF1(0) + 1 - k;
                expR = expA + expB;
                coeR = a.getVF1(k) * p.getCoef();
                c.ingresarunterminoint(coeR, expR);;
                ;

            }
            p = p.getLiga();
        }
        // mostramos los resultados
        JOptionPane.showMessageDialog(null, "\n\n" + a.mostrarReconstruidoReturn() + "\n\n"
                + b.mostrarReconstruidoReturn() + "\n\n" + c.mostrarReconstruidoReturn());
        c.mostrarPolinomioF2();
    }
}
