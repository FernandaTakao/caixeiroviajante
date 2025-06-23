import java.util.ArrayList;

public class AppParalelo {
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

        int numThreads = 8;
        ArrayList<ThreadTSP> threads = new ArrayList<>();

        // Dividir permutações em 8 fatias
        ArrayList<ArrayList<int[]>> fatias = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) fatias.add(new ArrayList<>());

        for (int i = 0; i < permutacoes.size(); i++) {
            fatias.get(i % numThreads).add(permutacoes.get(i));
        }

        long inicio = System.currentTimeMillis();

        // Criar e iniciar threads
        for (int i = 0; i < numThreads; i++) {
            ThreadTSP t = new ThreadTSP(fatias.get(i), matriz);
            threads.add(t);
            t.start();
        }

        // Esperar todas terminarem
        for (ThreadTSP t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Selecionar melhor resultado
        TSPLogica.Resultado melhor = threads.get(0).resultado;
        for (int i = 1; i < numThreads; i++) {
            if (threads.get(i).resultado.distancia < melhor.distancia) {
                melhor = threads.get(i).resultado;
            }
        }

        long fim = System.currentTimeMillis();

        System.out.println("Melhor rota (paralela): " + melhor.rotaString());
        System.out.println("Distância total: " + melhor.distancia);
        System.out.println("Tempo de execução (paralelo): " + (fim - inicio) + " ms");
    }
}
