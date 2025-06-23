import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class AppServidor {
    public static void main(String[] args) {
        int[][] matriz = {
            {0, 42, 67, 25, 88, 31, 54, 73},
            {42, 0, 39, 90, 62, 77, 84, 59},
            {67, 39, 0, 46, 71, 60, 78, 33},
            {25, 90, 46, 0, 29, 38, 93, 61},
            {88, 62, 71, 29, 0, 85, 47, 53},
            {31, 77, 60, 38, 85, 0, 66, 40},
            {54, 84, 78, 93, 47, 66, 0, 27},
            {73, 59, 33, 61, 53, 40, 27, 0},
        };

        int[] cidades = {0, 1, 2, 3, 4, 5, 6, 7};
        ArrayList<int[]> permutacoes = TSPLogica.gerarPermutacoes(cidades);

        // Divide as permutações entre dois clientes
        ArrayList<int[]> parte1 = new ArrayList<>();
        ArrayList<int[]> parte2 = new ArrayList<>();
        for (int i = 0; i < permutacoes.size(); i++) {
            if (i % 2 == 0) parte1.add(permutacoes.get(i));
            else parte2.add(permutacoes.get(i));
        }

        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor aguardando conexões...");

            long inicio = System.currentTimeMillis();

            // Conecta com o primeiro cliente
            Socket cliente1 = servidor.accept();
            ObjectOutputStream out1 = new ObjectOutputStream(cliente1.getOutputStream());
            ObjectInputStream in1 = new ObjectInputStream(cliente1.getInputStream());
            out1.writeObject(parte1);
            out1.writeObject(matriz);

            // Conecta com o segundo cliente
            Socket cliente2 = servidor.accept();
            ObjectOutputStream out2 = new ObjectOutputStream(cliente2.getOutputStream());
            ObjectInputStream in2 = new ObjectInputStream(cliente2.getInputStream());
            out2.writeObject(parte2);
            out2.writeObject(matriz);

            // Recebe os resultados dos clientes
            TSPLogica.Resultado resultado1 = (TSPLogica.Resultado) in1.readObject();
            TSPLogica.Resultado resultado2 = (TSPLogica.Resultado) in2.readObject();

            long fim = System.currentTimeMillis();

            // Escolhe o melhor resultado
            TSPLogica.Resultado melhor = resultado1.distancia < resultado2.distancia ? resultado1 : resultado2;

            System.out.println("Melhor rota (distribuída): " + melhor.rotaString());
            System.out.println("Distância total: " + melhor.distancia);
            System.out.println("Tempo de execução (distribuído): " + (fim - inicio) + " ms");

            servidor.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
