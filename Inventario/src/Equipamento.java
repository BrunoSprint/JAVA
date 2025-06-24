public class Equipamento {
    private int id;
    private String nome;
    private String tipo;
    private String marca;
    private String modelo;
    private boolean ativo;
    private double valor;
    private String dataAquisicao;
    private String portadorAtual;

    public Equipamento(int id, String nome, String tipo, String marca, String modelo,
                       boolean ativo, double valor, String dataAquisicao, String portadorAtual) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.ativo = ativo;
        this.valor = valor;
        this.dataAquisicao = dataAquisicao;
        this.portadorAtual = portadorAtual;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(String dataAquisicao) { this.dataAquisicao = dataAquisicao; }
    public String getPortadorAtual() { return portadorAtual; }
    public void setPortadorAtual(String portadorAtual) { this.portadorAtual = portadorAtual; }

    // Método para exportar em formato CSV
    public String toCSV() {
        return String.format("%d,%s,%s,%s,%s,%b,%.2f,%s,%s",
                id, nome, tipo, marca, modelo, ativo, valor, dataAquisicao, portadorAtual);
    }

    // Método para criar Equipamento a partir de linha CSV
    public static Equipamento fromCSV(String linha) {
        String[] parts = linha.split(",");
        if (parts.length != 9) return null;
        try {
            int id = Integer.parseInt(parts[0].trim());
            String nome = parts[1].trim();
            String tipo = parts[2].trim();
            String marca = parts[3].trim();
            String modelo = parts[4].trim();
            boolean ativo = Boolean.parseBoolean(parts[5].trim());
            double valor = Double.parseDouble(parts[6].trim());
            String dataAquisicao = parts[7].trim();
            String portadorAtual = parts[8].trim();
            return new Equipamento(id, nome, tipo, marca, modelo, ativo, valor, dataAquisicao, portadorAtual);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Tipo: " + tipo + "\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Ativo: " + (ativo ? "Sim" : "Não") + "\n" +
                "Valor: R$ " + String.format("%.2f", valor) + "\n" +
                "Data de Aquisição: " + dataAquisicao + "\n" +
                "Portador Atual: " + portadorAtual + "\n";
    }
}
