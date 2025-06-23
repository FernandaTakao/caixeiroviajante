import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class AppCliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            @SuppressWarnings("unchecked")
            ArrayList<int[]> permutacoes = (ArrayList<int[]>) in.readObject();
            int[][] matriz = (int[][]) in.readObject();

            TSPLogica.Resultado resultado = TSPLogica.buscarMelhorRota(permutacoes, matriz);

            out.writeObject(resultado);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
