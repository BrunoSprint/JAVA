import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    private static final String FILE_NAME = "inventario_ti.csv";

    public static List<Equipamento> lerEquipamentos() {
        List<Equipamento> lista = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // pular header
            while ((line = br.readLine()) != null) {
                Equipamento eq = Equipamento.fromCSV(line);
                if (eq != null) {
                    lista.add(eq);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro lendo arquivo CSV: " + e.getMessage());
        }
        return lista;
    }

    public static void salvarEquipamentos(List<Equipamento> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            pw.println("id,nome,tipo,marca,modelo,ativo,valor,dataAquisicao,portadorAtual");
            for (Equipamento eq : lista) {
                pw.println(eq.toCSV());
            }
            System.out.println("Inventário salvo em " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Erro salvando arquivo CSV: " + e.getMessage());
        }
    }
}
