package step07_22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Q2 {
    static class Person {
        int age;
        String name;
        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Person> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int age = Integer.parseInt(input[0]);
            String name = input[1];
            list.add(new Person(age, name));
        }

        // 나이 기준 정렬 (안정 정렬 보장: 입력 순서 유지)
        list.sort(Comparator.comparingInt(p -> p.age));

        for (Person p : list) {
            System.out.println(p.age + " " + p.name);
        }
    }
}

