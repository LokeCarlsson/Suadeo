package suadeo;

class Pearson {
    double calc(User A, User B) {
        double sum1 = 0;
        double sum2 = 0;
        double sum1sq = 0;
        double sum2sq = 0;
        double pSum = 0;
        double n = 0;

        for (int rA : A.ratings.keySet()) {
            for (int rB : B.ratings.keySet()) {
                if (rA == rB) {
                    sum1 += rA;
                    sum2 += rB;
                    sum1sq += Math.pow(rA, 2);
                    sum2sq += Math.pow(rB, 2);
                    pSum += rA * rB;
                    n++;
                }
            }
        }

        if (n == 0) return 0;

        double num = pSum - (sum1  * sum2 / n);
        double den = Math.sqrt((sum1sq - Math.pow(sum1, 2) / n) * (sum2sq - Math.pow(sum2, 2) / n));

        return den == 0 ? 0 : num / den;

    }
}
