package n01;

import java.util.*;
import java.util.stream.Collectors;
import static util.InputReader.*;

public class c01 {
    public static void main(String[] args) {
        List<String> lines = readFile("n01i01.txt");
        Optional<Integer> res = lines.stream()
                .map(e -> Arrays.stream(e.split(""))
                        .filter(d -> d.matches("[0-9]"))
                        .collect(Collectors.joining("")))
                .map(e -> Integer.valueOf(e.split("")[0] + e.split("")[e.length()-1]))
                .reduce(Integer::sum);
        System.out.println(res.get());

        // c02 -> one, two, three, four, five, six, seven, eight, nine
        List<String> nums = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Map<String, String> values = new HashMap<>();
        int count = 0;
        for(String l : lines){
            System.out.print(l + " -> ");
            for(String n : nums){
                l = l.replaceAll(n, n + String.valueOf(nums.indexOf(n) + 1) + n);
            }
            System.out.print(l + " -> ");
            l = l.replaceAll("[a-zA-Z]", "");
            System.out.print(l + " -> ");
            l = l.split("")[0] + l.split("")[l.length()-1];
            System.out.print(l);
            System.out.println();
            count += Integer.parseInt(l);
        }
        System.out.println(count);
    }
}
