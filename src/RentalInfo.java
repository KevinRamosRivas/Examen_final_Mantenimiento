import java.util.HashMap;

public class RentalInfo {
  // Metodo que permite generar el reporte de renta
  public String statement(Customer customer) {
    HashMap<String, Movie> movies = createMovieMap();
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental r : customer.getRentals()) {
      Movie movie = movies.get(r.getMovieId());
      double thisAmount = calculateAmount(movie, r.getDays());

      frequentEnterPoints += calculateFrequentEnterPoints(movie, r.getDays());

      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  // Metodo que permite crear un mapa de peliculas
  private HashMap<String, Movie> createMovieMap() {
    final String REGULAR = "regular";
    HashMap<String, Movie> movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", REGULAR));
    movies.put("F002", new Movie("Matrix", REGULAR));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));
    return movies;
  }

  // Metodo que permite calcular el monto de la renta
  private double calculateAmount(Movie movie, int days) {
    switch (movie.getCode()) {
      case "regular" -> {
        double amount = 2;
        if (days > 2) {
          amount += (days - 2) * 1.5;
        }
        return amount;
      }
      case "new" -> {
        return days * 3;
      }
      case "childrens" -> {
        double amount = 1.5;
        if (days > 3) {
          amount += (days - 3) * 1.5;
        }
        return amount;
      }
    }
    return 0;
  }

  // Metodo que permite calcular los puntos de renta
  private int calculateFrequentEnterPoints(Movie movie, int days) {
    int points = 1;
    if (movie.getCode().equals("new") && days > 2) {
      points++;
    }
    return points;
  }

}
