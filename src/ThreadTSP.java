public class ThreadTSP extends Thread {
    private int[][] matriz;
    private java.util.ArrayList<int[]> permutacoes;
    public TSPLogica.Resultado resultado;

    public ThreadTSP(java.util.ArrayList<int[]> permutacoes, int[][] matriz) {
        this.permutacoes = permutacoes;
        this.matriz = matriz;
    }

    @Override
    public void run() {
        resultado = TSPLogica.buscarMelhorRota(permutacoes, matriz);
    }
}
