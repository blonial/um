package solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieDataGenerator {
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MThiZTQ2MzViN2MzZTIzMDI4NGZjMmVjYmEyZWQ0MSIsInN1YiI6IjVmOWJlOGNmZjAzMTc0MDAzNzdmOGUwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.svU-6QIAXeClm9zU3T1yf5BycpQpMv6sqNpg-fKb8No";
    private static final String API_KEY = "618be4635b7c3e230284fc2ecba2ed41";
    private static final String API_URL = "https://api.themoviedb.org/3/movie/";

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        SimpleMovie[] simpleMovies = input.lines().map(line -> {
            String[] data = line.split(",");
            return new SimpleMovie(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        }).toArray(SimpleMovie[]::new);

        BufferedWriter output = new BufferedWriter(new FileWriter("moviesData.txt"));

        for(SimpleMovie simpleMovie : simpleMovies) {
            URL url = new URL(API_URL + simpleMovie.getTmbdMovieId());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            content.setLength(content.length() - 1);
            content.append(",\"ourId\":")
                    .append(simpleMovie.getMovieId())
                    .append("}");

            output.write(content.toString());
            output.newLine();

            con.disconnect();
        }

        output.flush();
    }
}
