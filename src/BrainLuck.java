public class BrainLuck {
    private final byte[] memory = new byte[5000];
    private final Character[] commandTape;
    private int dataPointer = 0;

    public BrainLuck(String code) {
        commandTape = code.chars()
                .mapToObj(i -> (char) i)
                .toArray(Character[]::new);
    }

    public String process(String input) {
        var inputIterator = input.chars().iterator();
        StringBuilder output = new StringBuilder();

        var commandTapeCursor = 0;
        while (commandTapeCursor < commandTape.length) {

            var c = commandTape[commandTapeCursor];
            switch (c) {
                case '>' -> {
                    dataPointer++;
                    commandTapeCursor++;
                }
                case '<' -> {
                    dataPointer--;
                    commandTapeCursor++;
                }
                case '+' -> {
                    memory[dataPointer]++;
                    commandTapeCursor++;
                }
                case '-' -> {
                    memory[dataPointer]--;
                    commandTapeCursor++;
                }
                case '[' -> {
                    commandTapeCursor++;
                    if (memory[dataPointer] == 0) {
                        int depth = 1;
                        while (depth > 0) {
                            char closingCommand = commandTape[commandTapeCursor++];
                            if (closingCommand == '[') {
                                depth++;
                            }
                            if (closingCommand == ']') {
                                depth--;
                            }
                        }
                    }
                }
                case ']' -> {
                    if (memory[dataPointer] != 0) {
                        int depth = 1;
                        while (depth > 0) {
                            char closingCommand = commandTape[--commandTapeCursor];
                            if (closingCommand == ']') {
                                depth++;
                            }
                            if (closingCommand == '[') {
                                depth--;
                            }
                        }
                    }
                    commandTapeCursor++;
                }
                case '.' -> {
                    output.append((char) memory[dataPointer]);
                    commandTapeCursor++;
                }
                case ',' -> {
                    if (inputIterator.hasNext()) {
                        memory[dataPointer] = inputIterator.next().byteValue();
                    }
                    commandTapeCursor++;
                }
            }
        }
        return output.toString();
    }
}
