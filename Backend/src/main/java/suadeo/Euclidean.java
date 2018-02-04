package suadeo;

class Euclidean {
    double calc(User A, User B) {
        double sim = 0.0;
        int cnt_sim = 0;

        for (Integer rA : A.getRatings().keySet()) {
            for (Integer rB : B.getRatings().keySet()) {
                if (rA.equals(rB) && B.getRatings().get(rB) != 0 && A.getRatings().get(rA) != 0) {
                    sim += Math.pow(A.getRatings().get(rA) - B.getRatings().get(rB), 2);
                    cnt_sim++;
                }
            }
        }

        return cnt_sim == 0 ? 0 : 1.0 / (1.0 + sim);
    }
}