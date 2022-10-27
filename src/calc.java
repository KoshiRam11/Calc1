import java.util.Scanner;

public class calc {
    // на вход подается строка, мы её принимаем и считываем, разделяем
    //
    public static void main(String[] args) throws MyException {
        while (true){
        //считывание строки
        String inputLine = input();
        //разделяем строку
        //выводим массив строк -> char
        // рабоать с каждым эл-ом
        //взять 1 элемент массива и если это не число то ошибка, если это число проверяем арабское или римское
        char[] sign = {'+', '-', '*', '/'};
        String[] signForSplit = {"\\+", "-", "\\*", "/"};
        int singIndex = -1;
        int counter = 0;

        for (int i = 0; i < sign.length; i++) { //вошли в массив переборки знаков
            for (int j = 0; j < inputLine.length(); j++) { //вошли в строку выражения и проверяем кол-во знака
                if (sign[i] == inputLine.charAt(j)) {
                    singIndex = i;
                    counter++;
                    if (counter > 1) {//обработка если в выражении больше 1 арифм знака
                        break;
                    }
                }
            }
        }
        if (counter > 1) {
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            throw new MyException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (counter == 0) {
            System.out.println("Cтрока не является математической операцией");
            throw new MyException("Cтрока не является математической операцией");
        }

        String[] res = new String[0];//стринг чтобы увидел рез,

        res = inputLine.split(signForSplit[singIndex]);
        for (int i = 0; i < res.length; i++) {
        }


        boolean checkRom = false;
        boolean checkRom1 = false;
        char[] Roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        for (int i = 0; i < Roman.length; i++) {
            if (res[0].charAt(0) == Roman[i]) {
                checkRom = true;

            }
            if (res[1].charAt(0) == Roman[i]) {
                checkRom1 = true;
            }
        }
        if (checkRom == true && checkRom1 == true) {
            //считаем для римских
            ///res0 и res1
            int a = romanToInt(res[0]);//получаем 1 число расспличенного массива в арабском представлении
            int b = romanToInt(res[1]);
            if (a > 10 || b > 10) {
                System.out.println("Вы ввели число больше 10, калькулятор может считать только числа до 10 включительно");
                throw new MyException("Вы ввели число больше 10, калькулятор может считать только числа до 10 включительно");

            } else {
                int result;
                switch (sign[singIndex]) {
                    case '+':
                        result = a + b;
                        System.out.println(intToRoman(result));//РАБОТАЕТ ДЛЯ +
                        break;
                    case '-':
                        if (a > b) {
                            result = a - b;
                            System.out.println(intToRoman(result));
                        } else {
                            System.out.println("в римской системе нет отрицательных чисел");
                            throw new MyException(" В римской системе нет отрицательных чисел");
                        }
                        break;
                    case '*':
                        result = a * b;
                        System.out.println(intToRoman(result));
                        break;
                    case '/':
                        result = a / b;
                        System.out.println(intToRoman(result));
                        break;
                }

            }

        } else if (checkRom == false && checkRom1 == false) {//cчитаем для арабских
            int a = Integer.parseInt(res[0]);
            int b = Integer.parseInt(res[1]);
            if (a > 10 || b > 10) {
                System.out.println("Вы ввели число больше 10, калькулятор может считать только числа до 10 включительно");
                throw new MyException("Вы ввели число больше 10, калькулятор может считать только числа до 10 включительно");
            } else {
                int result;
                switch (sign[singIndex]) {
                    case '+':
                        result = a + b;
                        System.out.println(result);
                        break;
                    case '-':
                        result = a - b;
                        System.out.println(result);
                        break;

                    case '*':
                        result = a * b;
                        System.out.println(result);
                        break;
                    case '/':
                        result = a / b;
                        System.out.println(result);
                        break;
                }
            }

        } else {
            System.out.println("Используются одновременно разные системы счисления");
            throw new MyException("Используются одновременно разные системы счисления");
        }


    }

}
        static String input () {
            Scanner scanner = new Scanner(System.in);
            String inputLine = scanner.nextLine();
            return inputLine;
            //    возвратили нам
        }
        static int inArabian ( char roman){
            if ('I' == roman) return 1;
            else if ('V' == roman) return 5;
            else if ('X' == roman) return 10;
            else if ('L' == roman) return 50;
            else if ('C' == roman) return 100;
            else if ('D' == roman) return 500;
            else if ('M' == roman) return 1000;
            return 0;
        }
        static int romanToInt (String roman){
            int end = roman.length() - 1;//INT END =2;
            char[] riman = roman.toCharArray();//{IV}
            int arabian;
            int result = inArabian(riman[end]);//5  //5
            for (int i = end - 1; i >= 0; i--) {//X//x  //I
                arabian = inArabian(riman[i]);//10  //10    //1
                if (arabian >= inArabian(riman[i + 1])) {//10>=5 10=10
                    result = result + arabian;//25
                } else {
                    result = result - arabian;
                }
            }
            return result;
        }

        static String intToRoman ( int number){
            int[] arabianValue = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] romanValue = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            String res = new String();
            for (int i = 0; i < arabianValue.length; i++) {
                while (number >= arabianValue[i]) {
                    number = number - arabianValue[i];
                    res = res + romanValue[i];
                }
            }
            return res;
        }
}



