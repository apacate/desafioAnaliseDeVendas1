import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Sale> sales = new ArrayList<>();

        System.out.print("Entre o caminho do arquivo: ");
        try (BufferedReader br = new BufferedReader(new FileReader("c:\\temp\\base-de-dados.csv"))) {

            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String monthYear = fields[0];
                String seller = fields[1];
                Integer items = Integer.parseInt(fields[2]);
                Double total = Double.parseDouble(fields[3]);
                sales.add(new Sale(monthYear, seller, items, total));
            }


            List<Sale> topSales2016 = sales.stream()
                    .filter(sale -> sale.getMonthYear().endsWith("2016"))
                    .sorted(Comparator.comparing(Sale::getAveragePrice).reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio:");
            topSales2016.forEach(System.out::println);


            double loganTotal = sales.stream()
                    .filter(sale -> sale.getSeller().equalsIgnoreCase("Logan"))
                    .filter(sale -> sale.getMonthYear().startsWith("1/") || sale.getMonthYear().startsWith("7/"))
                    .mapToDouble(Sale::getTotal)
                    .sum();

            System.out.printf("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f\n", loganTotal);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    }
