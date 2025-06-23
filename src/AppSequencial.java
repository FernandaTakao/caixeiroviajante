import java.util.ArrayList;

public class AppSequencial {
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

        long inicio = System.currentTimeMillis();

        ArrayList<int[]> permutacoes = TSPLogica.gerarPermutacoes(cidades);
        TSPLogica.Resultado resultado = TSPLogica.buscarMelhorRota(permutacoes, matriz);

        long fim = System.currentTimeMillis();

        System.out.println("Melhor rota (sequencial): " + resultado.rotaString());
        System.out.println("Distância total: " + resultado.distancia);
        System.out.println("Tempo de execução (sequencial): " + (fim - inicio) + " ms");
    }
}
