public class Tarefa {
    private String curtaDuracao;
    private String dataInicioHora;
    private String dataHoraTermino;

    public Tarefa() {
    }

    public Tarefa(String curtaDuracao, String dataInicioHora, String dataHoraTermino) {
        this.curtaDuracao = curtaDuracao;
        this.dataInicioHora = dataInicioHora;
        this.dataHoraTermino = dataHoraTermino;
    }
}
