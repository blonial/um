package solution;

import java.io.*;

public class Polygon {
    private final int n;
    private final int k;
    private final PolygonFragment[] polygonParams;

    public Polygon(int n, int k, PolygonFragment[] polygonParams) {
        this.n = n;
        this.k = k;
        this.polygonParams = polygonParams;
    }

    public void applyNewFactors(double[] newFactors) {
        for (int i = 0; i < newFactors.length; i++) {
            polygonParams[i].setFactor(newFactors[i]);
        }
    }

    public double calculate(Vector vector) {
        double result = 0.0;
        for (PolygonFragment fragment : this.polygonParams) {
            double partResult = fragment.getFactor();
            for (Integer paramIndex : fragment.getParamIndexes()) {
                partResult *= vector.getValues()[paramIndex];
            }
            result += partResult;
        }
        return result;
    }

    public PolygonFragment[] getPolygonParams() {
        return polygonParams;
    }

    public static Polygon readFromReader(Reader reader) throws IOException {
        BufferedReader input = new BufferedReader(reader);
        String[] nAndK = input.readLine().split(" ");
        PolygonFragment[] polygonParams = input.lines().map(line -> {
            String[] values = line.split(" ");
            int[] paramIndexes = new int[values.length - 1];
            for (int i = 0; i < paramIndexes.length; i++) {
                paramIndexes[i] = Integer.parseInt(values[i]);
            }
            return new PolygonFragment(paramIndexes, Double.parseDouble(values[values.length - 1]));
        }).toArray(PolygonFragment[]::new);

        return new Polygon(Integer.parseInt(nAndK[0]), Integer.parseInt(nAndK[1]), polygonParams);
    }

    public void printToStream(PrintStream printStream) {
        PrintStream output = new PrintStream(new BufferedOutputStream(printStream));
        output.println(this.n + " " + this.k);
        for (PolygonFragment fragment : this.polygonParams) {
            for (Integer paramIndex : fragment.getParamIndexes()) {
                output.print(paramIndex + " ");
            }
            output.println(fragment.getFactor());
        }
        output.flush();
    }
}
