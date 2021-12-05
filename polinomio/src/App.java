import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String sCadena, sCadena2, sCadena3; // "10-15x^5+4x^3-2x" 4 terminos// "-15x^5+4x^3-2x+10 4 terminos"
        // 8x+9x^7+4x^5-10 4 terminos
        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "---- Menu ----\n" + "\n1. Forma  1" + "\n2. Forma  2" + "\n3. Forma  3" + "\n4. Suma F1+F2=F3"+"\n5. Multiplicacion F1xF3=F2"+"\n6. Salir\n\n\n"));
            switch (opcion) {
            case 1:
                JOptionPane.showMessageDialog(null, "----Polinomio Forma 1----");
                sCadena = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");
                
                // OBTENEMOS EL GRADO RECORRIENDO EL VECTOR PROCESADO
                String[] vectorProcesado = Pf1.Ingreso(sCadena);
                int grado = Pf1.gradoCalc(vectorProcesado);

                // constructor
                Pf1 polinomiof1 = new Pf1(grado + 1);
                polinomiof1.ingresarTerminos(vectorProcesado);

                // menu para la forma 1
                int opc=0;
                while (opc != 8) {
                    opc = Integer.parseInt(JOptionPane.showInputDialog(
                            "---- Menu FORMA 1----\n" + "\n1. Insertar termino" + "\n2. Eliminar termino"
                                    + "\n3. Mostrar forma" + "\n4. Mostrar polinomio" + "\n5. Evaluar segun valor de x"
                                    + "\n6. Sumar" + "\n7. Multiplicar" + "\n8. Salir\n\n"));
                    polinomiof1.menu(opc);
                }
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "----Polinomio Forma 2----");
                int nterm = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de terminos:"));
                sCadena2 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");   
                //Constructor
                Pf2 polinomiof2 = new Pf2(nterm);
                String[] vectorProcesado2 = Pf1.Ingreso(sCadena2);
                vectorProcesado2 = Pf2.eliminarNulls(vectorProcesado2);
                Pf1.leerVector(vectorProcesado2);
                Pf1.leerVector(vectorProcesado2);
                polinomiof2.ingresarTerminos(vectorProcesado2);
                Pf1.leerVector(polinomiof2.getVF2());
                // menu para la forma 2
                int opc2=0;
                while (opc2 != 8) {
                    opc2 = Integer.parseInt(JOptionPane.showInputDialog(
                            "---- Menu FORMA 2----\n" + "\n1. Insertar termino" + "\n2. Eliminar termino"
                                    + "\n3. Mostrar forma" + "\n4. Mostrar polinomio" + "\n5. Evaluar segun valor de x"
                                    + "\n6. Sumar" + "\n7. Multiplicar" + "\n8. Salir\n\n"));
                    polinomiof2.menu(opc2);
                }
                break;
                

            case 3:
                JOptionPane.showMessageDialog(null, "----Polinomio forma 3 o en lista----");
                sCadena3 = JOptionPane.showInputDialog(null, "Ingrese el valor del polinomio: ");   
                //Constructor
                String[] vectorProcesado3 = Pf1.Ingreso(sCadena3);
                vectorProcesado3 = Pf2.eliminarNulls(vectorProcesado3);
                Pf1.leerVector(vectorProcesado3);
                PL polinomiof3 = new PL();
                polinomiof3.ingresarTerminoPF3(vectorProcesado3);
                //menu forma 3
                int opc3 =0;
                while (opc3 != 8) {
                    opc3 = Integer.parseInt(JOptionPane.showInputDialog(
                            "---- Menu FORMA 3----\n" + "\n1. Insertar termino" + "\n2. Eliminar termino"
                                    + "\n3. Mostrar forma" + "\n4. Mostrar polinomio" + "\n5. Evaluar segun valor de x"
                                    + "\n6. Sumar" + "\n7. Multiplicar" + "\n8. Salir\n\n"));
                    polinomiof3.menu(opc3);
                }
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "----Suma forma 1 + forma 2 presentado en forma 3----");
                forma1masforma2.principal();
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "----Multiplicacion de polinomio\nforma 3 x forma 1\nPresentado en forma 2");
                forma1xforma3.principal();
                break;

            case default:
                JOptionPane.showMessageDialog(null, "----Gracias----");
                break;
            }
        } while (opcion != 6);
    }
}
