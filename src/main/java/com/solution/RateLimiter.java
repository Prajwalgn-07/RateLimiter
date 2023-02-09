package com.solution;

import java.util.ArrayList;
import java.util.List;

public class RateLimiter {

    public List<String> getRateLimiterOptions(List<String> requests) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < requests.size(); i++) {
            int repeatCount = 1;
            boolean requestApplied = false;
            for (int j = i - 1; j >= 0; j--) {
                if (requests.get(i).equalsIgnoreCase(requests.get(j))) {
                    repeatCount++;
                    if (i - j < 5 && repeatCount > 2) {
                        requestApplied = true;
                        answer.add("{status:429, message: Too many requests}");
                        break;
                    }

                    if (i - j < 30 && repeatCount > 5) {
                        requestApplied = true;
                        answer.add("{status:429, message: Too many requests}");
                        break;
                    }
                }
            }
            if (!requestApplied) {
                answer.add("{status:200, message: Ok}");
            }
        }
        return answer;
    }
}
