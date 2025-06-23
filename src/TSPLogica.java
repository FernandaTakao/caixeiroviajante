import java.io.Serializable;
import java.util.ArrayList;

public class TSPLogica {

    public static class Resultado implements Serializable {
        public int[] rota;
        public int distancia;

        public Resultado(int[] rota, int distancia) {
            this.rota = rota;
            this.distancia = distancia;
        }

        public String rotaString() {
            StringBuilder sb = new StringBuilder();
            for (int cidade : rota) {
                sb.append(cidade).append(" ");
            }
            sb.append(rota[0]); // volta à cidade de origem
            return sb.toString();
        }
    }

    public static Resultado buscarMelhorRota(ArrayList<int[]> permutacoes, int[][] matriz) {
        int melhorDistancia = Integer.MAX_VALUE;
        int[] melhorRota = null;

        for (int[] rota : permutacoes) {
            int distancia = calcularDistancia(rota, matriz);
            if (distancia < melhorDistancia) {
                melhorDistancia = distancia;
                melhorRota = rota;
            }
        }

        return new Resultado(melhorRota, melhorDistancia);
    }

    public static int calcularDistancia(int[] rota, int[][] matriz) {
        int distancia = 0;
        for (int i = 0; i < rota.length - 1; i++) {
            distancia += matriz[rota[i]][rota[i + 1]];
        }
        distancia += matriz[rota[rota.length - 1]][rota[0]]; // volta à origem
        return distancia;
    }

    public static ArrayList<int[]> gerarPermutacoes(int[] array) {
        ArrayList<int[]> resultado = new ArrayList<>();
        permutar(array, 1, resultado); // fixar a cidade 0 no início
        return resultado;
    }

    private static void permutar(int[] array, int inicio, ArrayList<int[]> resultado) {
        if (inicio == array.length) {
            resultado.add(array.clone());
            return;
        }
        for (int i = inicio; i < array.length; i++) {
            trocar(array, inicio, i);
            permutar(array, inicio + 1, resultado);
            trocar(array, inicio, i);
        }
    }

    private static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
