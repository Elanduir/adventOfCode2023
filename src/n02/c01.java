package n02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.InputReader.readFile;

public class c01 {
    public static void main(String[] args) {
        List<String> lines = readFile("n02i01.txt");
        c1(lines);
    }

    public static void c1(List<String> lines){
        List<Game> games = lines.stream().map(l -> {
            Pattern idPat = Pattern.compile("(?<=(Game ))\\d+(?=(:))");
            Matcher cmatcher = idPat.matcher(l);
            cmatcher.find();
            int id = Integer.parseInt(cmatcher.group());

            l = l.split(":")[1];
            List<String> sep = List.of(l.split(";"));

            List<Integer> blList = new ArrayList<>();
            List<Integer> grList = new ArrayList<>();
            List<Integer> reList = new ArrayList<>();

            sep.forEach(g -> {
                Pattern bluePat = Pattern.compile(("(\\d+(?=( blue)))"));
                Matcher matcher = bluePat.matcher(g);
                int blue = 0;
                if(matcher.find()){
                    blue = Integer.parseInt(matcher.group());
                }
                blList.add(blue);


                Pattern greenPat = Pattern.compile(("(\\d+(?=( green)))"));
                matcher = greenPat.matcher(g);
                int green = 0;
                if(matcher.find()){
                    green = Integer.parseInt(matcher.group());
                }
                grList.add(green);


                Pattern redPat = Pattern.compile(("(\\d+(?=( red)))"));
                matcher = redPat.matcher(g);
                int red = 0;
                if(matcher.find()){
                    red = Integer.parseInt(matcher.group());
                }
                reList.add(red);
            });


            Map<String, Integer> values = new HashMap<>();
            values.put("blue", blList.stream().reduce(Math::max).get());
            values.put("green", grList.stream().reduce(Math::max).get());
            values.put("red", reList.stream().reduce(Math::max).get());

            return new Game(id, values);
        }).toList();
        int sum = games.stream()
                .filter(g -> g.values.get("green") <= 13 && g.values.get("red") <= 12 && g.values.get("blue") <= 14)
                .map(e -> e.id)
                .reduce(Integer::sum).get();
        int sum2 = games.stream()
                .map(e -> e.values.getOrDefault("green", 1) * e.values.getOrDefault("red", 1) * e.values.getOrDefault("blue", 1))
                .reduce(Integer::sum).get();
        System.out.println("C1: " + sum + " C2: " + sum2);
    }



    private record Game(int id, Map<String, Integer> values){}
}
