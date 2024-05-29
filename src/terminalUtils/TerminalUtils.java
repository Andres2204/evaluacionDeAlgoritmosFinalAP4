package terminalUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class TerminalUtils {

    /*
    *
    * Version: 0.1
    *
    */



    private int width = 0;
    private String[] printFormat;
    private final ArrayList<ColumnFormat> columnsFormat;

    public TerminalUtils(ArrayList<ColumnFormat> columnsFormat) {
        this.columnsFormat = columnsFormat;
        getFormat();
    }

    /*
     * Traces
     */

    public static void successTrace(String m) {
        successTrace(m, false);
    }

    public static void successTrace(String m, boolean f) {
        System.out.println(Colours.G + "[+] " + (f ? "Success: " + Colours.RT + m : m + Colours.RT));
    }

    public static void infoTrace(String m) {
        infoTrace(m, false);
    }

    public static void infoTrace(String m, boolean f) {
        System.out.println(Colours.C + "[i] " + (f ? "Info: " + Colours.RT + m : m + Colours.RT));
    }

    public static void warningTrace(String m) {
        warningTrace(m, false);
    }

    public static void warningTrace(String m, boolean f) {
        System.out.println(Colours.Y + "[!] " + (f ? "Warning: " + Colours.RT + m : m + Colours.RT));
    }

    public static void errorTrace(String m) {
        errorTrace(m, false);
    }

    public static void errorTrace(String m, boolean f) {
        System.out.println(Colours.R + "[x] " + (f ? "Error: " + Colours.RT + m : m + Colours.RT));
    }

    /*
     * TABLE
     */

    private void getFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("|");

        printFormat = new String[columnsFormat.size()];

        for (int i = 0; i < columnsFormat.size(); i++) {
            ColumnFormat c = columnsFormat.get(i);
            printFormat[i] = sb.append(" ").append(c.getColour()).append("%-").append(c.getWidth()).append("s" + Colours.RT + " |").toString();
            sb.delete(0, sb.length());
            width += c.getWidth();
        }

        System.out.println("[!] Formato: " + Arrays.stream(printFormat).toList());
    }

    private void getFormatNoConfig() {
    } //TODO: no configuracion, encontrar max dato

    public String printTable(String[][] data, boolean sepRow) {

        StringBuilder sb = new StringBuilder();

        //HEADER
        sb.append(printRowSeparator());

        for (int i = 0; i < printFormat.length; i++) {
            sb.append(String.format(printFormat[i], columnsFormat.get(i).getColumnName()));
        }
        sb.append("\n");
        sb.append(printRowSeparator());
        
        // DATA
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < printFormat.length; j++) {
                sb.append(String.format(String.format(printFormat[j], data[i][j])));
            }
            sb.append(sepRow ? "\n" + printRowSeparator() : "\n");
        }
        if (!sepRow) sb.append(printRowSeparator());
        return sb.toString();
    }

    private String printRowSeparator() {

        StringBuilder sb = new StringBuilder();
        for (ColumnFormat columnFormat : columnsFormat) {
            int columnWidth = columnFormat.getWidth();
            sb.append("+");
            for (int i = 0; i < columnWidth; i++) {
                sb.append("-");
            }
            sb.append("--");
        }
        sb.append("+\n");
        return sb.toString();
    }

    /*
    * ProgressBar
    */

    public static void printProgressBar(int current, int total, boolean colour) {
        int barLength = 50; // Longitud de la barra de progreso en caracteres
        int progress = (int) ((double) current / total * barLength);

        StringBuilder bar = new StringBuilder();

        bar.append(colour ? Colours.R : Colours.B);
        bar.append("\r["); // \r es el retorno de carro que mueve el cursor al inicio de la línea
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }

        bar.append("] ");
        bar.append(String.format("%d%%", (int) ((double) current / total * 100)));
        bar.append(Colours.RT);

        System.out.print(bar);
    }

    //TODO: resolver el bug: cuando no se tiene suficinte espacio, se duplica la barra en otra linea

    public static void printMultipleProgressBar(Thread[] processes, ProgressBar[] processProgress) throws IOException, InterruptedException {
        int processesNumber = processes.length;

        // Start processes
        for (Thread p : processes) p.start();

        // Main Thread
        final int[] cursorPos = {Integer.parseInt(getCursorPosition())};
        Thread multiProgressBarThread = new Thread(() -> {
            while (true) {
                System.out.println("CURSOR ROW -> " + cursorPos[0]);
                boolean allSortingComplete = true;
                for (int i = 0; i < processesNumber; i++) {
                    if (!processProgress[i].isSortingComplete()) {
                        allSortingComplete = false;
                    }
                    // TODO: solucion incompleta. (mover pamtalla hacia arriba talves) Check if we're near the bottom of the terminal and reset if necessary
                    if (cursorPos[0] + i >= 30) { // Assuming the terminal height is 30 rows
                        cursorPos[0] = 1;
                    }
                    moveToLine(Integer.parseInt(String.valueOf(cursorPos[0])) + i + 1);
                    printProgressBar(processProgress[i].getProgress(), processProgress[i].getTotalSteps(), true);
                }
                if (allSortingComplete) {
                    break;
                }
                try {
                    Thread.sleep(500); // Actualizar las barras de progreso cada 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Asegurarse de que todas las barras de progreso lleguen al 100%
            for (int i = 0; i < processesNumber; i++) {
                moveToLine(Integer.parseInt(String.valueOf(cursorPos[0])) + i + 1);
                printProgressBar(processProgress[i].getTotalSteps(), processProgress[i].getTotalSteps(), false);
            }
            System.out.println("\nSorting Complete!");
        });

        multiProgressBarThread.start();

        // Esperar a que todos los hilos de ordenamiento terminen
        for (Thread thread : processes) {
            thread.join();
        }
        multiProgressBarThread.join();
    }

    public static void enableRawMode() throws IOException, InterruptedException {
        // Ejecutar el comando para habilitar el modo raw
        new ProcessBuilder("sh", "-c", "stty raw -echo < /dev/tty").inheritIO().start().waitFor();
    }

    public static void disableRawMode() throws IOException, InterruptedException {
        // Ejecutar el comando para deshabilitar el modo raw
        new ProcessBuilder("sh", "-c", "stty sane < /dev/tty").inheritIO().start().waitFor();
    }

    public static String getCursorPosition() throws IOException, InterruptedException {
        
        enableRawMode();
        
        // Obtener el flujo de entrada y salida de la consola
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;

        // Enviar la secuencia de escape ANSI para obtener la posición del cursor
        byte[] getCursorPosCmd = "\033[6n".getBytes();
        outputStream.write(getCursorPosCmd);
        outputStream.flush();

        // Leer la respuesta de la consola
        StringBuilder response = new StringBuilder();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            response.append((char) ch);
            if (ch == 'R') {
                break;
            }
        }

        String positionResponse = response.toString();
        
        disableRawMode();
        return parseCursorPosition(positionResponse);
    }

    public static void moveToLine(int line) {
        System.out.printf("\033[%d;0H", line);
    }
    
    public static String parseCursorPosition(String positionResponse) {
        // Extraer la parte relevante de la respuesta
        if (positionResponse.startsWith("\033[")) {
            positionResponse = positionResponse.substring(2, positionResponse.length() - 1);
            String[] positions = positionResponse.split(";");
            if (positions.length == 2) {
                return positions[0];
            }
        }
        return "Unknown position";
    }

}
