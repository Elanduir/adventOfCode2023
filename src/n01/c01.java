package n01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class c01 {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("./src/n01/i01.txt"));){
            String line = reader.readLine();
            while(line != null){
                lines.add(line);
                line = reader.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Optional<Integer> res = lines.stream()
                .map(e -> Arrays.stream(e.split(""))
                        .filter(d -> d.matches("[0-9]"))
                        .collect(Collectors.joining("")))
                .map(e -> Integer.valueOf(e.split("")[0] + e.split("")[e.length()-1]))
                .reduce(Integer::sum);
        System.out.println(res.get());

        // c02 -> one, two, three, four, five, six, seven, eight, nine
        List<String> nums = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        var r2 = lines.stream().map(e -> {
            for (int i = 0; i < nums.size(); i++) {
                e = e.replaceAll(nums.get(i), String.valueOf(i+1));
            }
            return e;
        }).map(e -> Arrays.stream(e.split(""))
                        .filter(d -> d.matches("[0-9]"))
                        .collect(Collectors.joining("")))
                .map(e -> Integer.valueOf(e.split("")[0] + e.split("")[e.length()-1]))
                .reduce(Integer::sum);

        System.out.println(r2.get());
    }
}
