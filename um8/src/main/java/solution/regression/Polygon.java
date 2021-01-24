package solution.regression;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Polygon {
    private final int n;
    private final int k;

    private final PolygonFragment[] polygonParams;

    public Polygon(int n, int k, PolygonFragment[] polygonParams) {
        this.n = n;
        this.k = k;
        this.polygonParams = polygonParams;
    }

    public Polygon(int n, int k) {
        this(n, k, false);
    }

    public Polygon(int n, int k, boolean removeZeroFragment) {
        this.n = n;
        this.k = k;
        List<PolygonFragment> params = new ArrayList<>();

        for (int i = n; i >= 0; i--) {
            findAllPossibleCombinationsOfParameters(k, i, 0, params, new PolygonFragment(new int[k], Math.random()));
        }

        if (removeZeroFragment) {
            params = params.stream().filter(polygonFragment -> !polygonFragment.isZero()).collect(Collectors.toList());
        }

        this.polygonParams = params.toArray(new PolygonFragment[0]);
    }

    private void findAllPossibleCombinationsOfParameters(int k,
            int n,
            int poz,
            List<PolygonFragment> params,
            PolygonFragment current) {
        if (poz + 1 + 1 > k) {
            current.getParamIndexes()[poz] = n;
            params.add(current);
        } else {
            for (int i = n; i >= 0; i--) {
                current = new PolygonFragment(Arrays.copyOf(current.getParamIndexes(), k), Math.random());
                current.getParamIndexes()[poz] = n;
                this.findAllPossibleCombinationsOfParameters(k, i, poz + 1, params, current);
            }
        }
    }

    public double applyNewFactors(double[] newFactors, double learningRate) {
        double loss = 0.0;
        for (int i = 0; i < newFactors.length; i++) {
            double stepSize = newFactors[i] * learningRate;
            loss = Math.max(loss, Math.abs(stepSize));
            polygonParams[i].setFactor(polygonParams[i].getFactor() - stepSize);
        }
        return loss;
    }

    public double calculate(Vector vector) {
        double result = 0.0;
        for (PolygonFragment fragment : this.polygonParams) {
            result += fragment.getY(vector);
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

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }
}
