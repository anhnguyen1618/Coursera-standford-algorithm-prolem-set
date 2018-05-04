import java.util.ArrayList;
import java.util.List;

public class StringGenerator {
    public static List<String> generateAllPossibleValue(String input) {
        List<String> result = new ArrayList<>();
        // hamming distance = 1
        for (int i=0; i < input.length(); i++) {
            StringBuilder inputBuilder = new StringBuilder(input);
            inputBuilder.setCharAt(i, replaceBit(input.charAt(i)));
            result.add(inputBuilder.toString());
        }

        // hamming distance = 1
        int inputLength = input.length();
        for (int i=0 ; i <  inputLength - 1; i++) {
            for (int j = i+1; j < inputLength; j++) {
                StringBuilder inputBuilder = new StringBuilder(input);
                inputBuilder.setCharAt(i, replaceBit(input.charAt(i)));
                inputBuilder.setCharAt(j, replaceBit(input.charAt(j)));
                result.add(inputBuilder.toString());
            }
        }
        return result;
    }

    public static char replaceBit(char bit) {
        return bit == '0' ? '1': '0';
    }
}
