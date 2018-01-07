package suadeo;

class Euclidean {
    double calc(User A, User B) {
        double sim = 0.0;
        int cnt_sim = 0;

        for (int rA : A.ratings.keySet()) {
            for (int rB : B.ratings.keySet()) {
                if (rA == rB) {
                    sim += Math.pow(rA - rB, 2);
                    cnt_sim++;
                }
            }
        }

        return cnt_sim == 0 ? 0 : 1.0 / (1.0 + sim);
    }
}