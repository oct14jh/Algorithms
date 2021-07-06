import java.util.*;

class Solution {
    private class Person implements Comparable<Person> {
        int no;
        int count = 0;
        
        public Person(){};
        public Person(int no, int count) { 
            this.no = no;
            this.count = count;
        }
        
        @Override
        public int compareTo(Person p) {
            if(this.count == p.count)
                return this.no - p.no;
            return p.count - this.count;
        }
    }
    
    // 메인메소드
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        Person[] giveUpPerson = new Person[3];
        for(int p = 0; p < 3; p++) {
            giveUpPerson[p] = new Person(p+1, 0);
        }
        
        int limit = countingAnswer(answers, answer, giveUpPerson);
        
        if(limit == 1) {
            int[] temp = new int[1];
            temp[0] = answer[0];
            return temp;
        } else if(limit == 2) {
            int[] temp = new int[2];
            temp[0] = answer[0];
            temp[1] = answer[1];
            return temp;
        }else {
            return answer;
        }
    }
    
    private int countingAnswer(int[] answers, int[] answer, Person[] giveUpPerson) {
        int[][] pattern = {{1,2,3,4,5},
                           {2,1,2,3,2,4,2,5},
                           {3,3,1,1,2,2,4,4,5,5}
                          };
        
        // 문제별 정답확인
        for(int i = 0; i < answers.length; i++) {
            int presentAnswer = answers[i];

            if(pattern[0][i%5] == presentAnswer) giveUpPerson[0].count++;
            if(pattern[1][i%8] == presentAnswer) giveUpPerson[1].count++;
            if(pattern[2][i%10] == presentAnswer) giveUpPerson[2].count++;
        }
        
        Arrays.sort(giveUpPerson);
        for(int rank = 0; rank < 3; rank++) {
            answer[rank] = giveUpPerson[rank].no;
        }
        
        if(giveUpPerson[0].count == giveUpPerson[1].count) {
            if(giveUpPerson[1].count == giveUpPerson[2].count) {
                return 3;
            }
            return 2;
        }
        return 1;
    }
    
}