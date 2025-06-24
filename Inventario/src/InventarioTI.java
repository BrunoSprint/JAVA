import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class InventarioTI {

    private static final int MAX_EQUIPAMENTOS = 250;
    private static List<Equipamento> inventario = new ArrayList<>();

    public static void main(String[] args) {
        carregarCSV();

        Scanner in = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("\nMenu:");
            System.out.println("1 - Listar equipamentos");
            System.out.println("2 - Alterar equipamento pelo ID");
            System.out.println("3 - Cadastrar equipamentos (até 20 por vez)");
            System.out.println("4 - Excluir equipamento pelo ID");
            System.out.println("0 - Sair e salvar");
            System.out.print("Opção: ");

            int opcao = -1;
            try {
                opcao = in.nextInt();
                in.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                in.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    listarEquipamentos();
                    break;
                case 2:
                    alterarEquipamento(in);
                    break;
                case 3:
                    cadastrarEquipamentos(in);
                    break;
                case 4:
                    excluirEquipamento(in);
                    break;
                case 0:
                    System.out.println("Saindo e salvando inventário...");
                    salvarCSV();
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        in.close();
    }

    private static void listarEquipamentos() {
        if (inventario.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado.");
            return;
        }
        int count = 1;
        for (Equipamento eq : inventario) {
            System.out.println("\n--- Equipamento #" + (count++) + " ---");
            System.out.println(eq);
        }
    }

    private static Equipamento buscarPorId(int id) {
        for (Equipamento eq : inventario) {
            if (eq.getId() == id) return eq;
        }
        return null;
    }

    private static void alterarEquipamento(Scanner in) {
        if (inventario.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado para alterar.");
            return;
        }
        System.out.print("Informe o ID do equipamento a alterar: ");
        int id = in.nextInt();
        in.nextLine();
        Equipamento eq = buscarPorId(id);
        if (eq == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }
        System.out.println("Alterando equipamento: ");
        System.out.println(eq);

        System.out.print("Novo nome (" + eq.getNome() + "): ");
        String nome = in.nextLine();
        if (!nome.isBlank()) eq.setNome(nome);

        System.out.print("Novo tipo (" + eq.getTipo() + "): ");
        String tipo = in.nextLine();
        if (!tipo.isBlank()) eq.setTipo(tipo);

        System.out.print("Nova marca (" + eq.getMarca() + "): ");
        String marca = in.nextLine();
        if (!marca.isBlank()) eq.setMarca(marca);

        System.out.print("Novo modelo (" + eq.getModelo() + "): ");
        String modelo = in.nextLine();
        if (!modelo.isBlank()) eq.setModelo(modelo);

        System.out.print("Ativo (true/false) (" + eq.isAtivo() + "): ");
        String ativoStr = in.nextLine();
        if (!ativoStr.isBlank()) eq.setAtivo(Boolean.parseBoolean(ativoStr));

        System.out.print("Novo valor (" + eq.getValor() + "): ");
        String valorStr = in.nextLine();
        if (!valorStr.isBlank()) {
            try {
                double valor = Double.parseDouble(valorStr);
                eq.setValor(valor);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, mantendo o anterior.");
            }
        }

        System.out.print("Nova data de aquisição (" + eq.getDataAquisicao() + "): ");
        String data = in.nextLine();
        if (!data.isBlank()) eq.setDataAquisicao(data);

        System.out.print("Novo portador atual (" + eq.getPortadorAtual() + "): ");
        String portador = in.nextLine();
        if (!portador.isBlank()) eq.setPortadorAtual(portador);

        System.out.println("Equipamento atualizado.");
    }

    private static void excluirEquipamento(Scanner in) {
        if (inventario.isEmpty()) {
            System.out.println("Nenhum equipamento cadastrado para excluir.");
            return;
        }
        System.out.print("Informe o ID do equipamento a excluir: ");
        int id = in.nextInt();
        in.nextLine();

        Equipamento eq = buscarPorId(id);
        if (eq == null) {
            System.out.println("Equipamento não encontrado.");
            return;
        }

        inventario.remove(eq);
        System.out.println("Equipamento removido.");
    }

    private static void cadastrarEquipamentos(Scanner in) {
        if (inventario.size() >= MAX_EQUIPAMENTOS) {
            System.out.println("Inventário cheio, não é possível cadastrar mais equipamentos.");
            return;
        }
        int maxCadastrar = Math.min(20, MAX_EQUIPAMENTOS - inventario.size());
        System.out.print("Quantos equipamentos deseja cadastrar? (máx " + maxCadastrar + "): ");
        int qtd = -1;
        try {
            qtd = in.nextInt();
            in.nextLine();
            if (qtd < 1 || qtd > maxCadastrar) {
                System.out.println("Quantidade inválida.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            in.nextLine();
            return;
        }

        for (int i = 0; i < qtd; i++) {
            System.out.println("\nCadastro do equipamento #" + (inventario.size() + 1));
            System.out.print("ID (único): ");
            int id = in.nextInt();
            in.nextLine();
            if (buscarPorId(id) != null) {
                System.out.println("ID já cadastrado. Tente novamente.");
                i--;
                continue;
            }
            System.out.print("Nome: ");
            String nome = in.nextLine();
            System.out.print("Tipo: ");
            String tipo = in.nextLine();
            System.out.print("Marca: ");
            String marca = in.nextLine();
            System.out.print("Modelo: ");
            String modelo = in.nextLine();
            System.out.print("Ativo (true/false): ");
            boolean ativo = in.nextBoolean();
            in.nextLine();
            System.out.print("Valor: ");
            double valor = in.nextDouble();
            in.nextLine();
            System.out.print("Data de aquisição (ex: 2025-06-22): ");
            String data = in.nextLine();
            System.out.print("Portador atual: ");
            String portador = in.nextLine();

            inventario.add(new Equipamento(id, nome, tipo, marca, modelo, ativo, valor, data, portador));
            System.out.println("Equipamento cadastrado.");
        }
    }

    private static void salvarCSV() {
        CSVUtil.salvarEquipamentos(inventario);
    }

    private static void carregarCSV() {
        List<Equipamento> lista = CSVUtil.lerEquipamentos();
        if (!lista.isEmpty()) {
            inventario = lista;
            System.out.println("Inventário carregado com " + inventario.size() + " equipamentos.");
        } else {
            System.out.println("Nenhum arquivo de inventário encontrado. Vamos começar do zero.");
        }
    }
}
