public class BrainLuck {
    private final byte[] memory = new byte[255];
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
        var innerLoopCounter = 0;
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
                    if (memory[dataPointer] == 0) {
                        for (int i = commandTapeCursor + 1; i < commandTape.length; i++) {
                            if (commandTape[i] == ']') {
                                if (innerLoopCounter == 0) {
                                    break;
                                } else {
                                    innerLoopCounter--;
                                }
                                commandTapeCursor++;
                            } else if (commandTape[i] == '[') {
                                innerLoopCounter++;
                                commandTapeCursor++;
                            } else {
                                commandTapeCursor++;
                            }
                        }
                    } else {
                        commandTapeCursor++;
                    }
                }
                case ']' -> {
                    if (memory[dataPointer] != 0) {
                        for (int i = commandTapeCursor - 1; i > 0; i--) {
                            if (commandTape[i] == '[') {
                                if (innerLoopCounter == 0) {
                                    break;
                                } else {
                                    innerLoopCounter--;
                                }
                                commandTapeCursor--;
                            } else if (commandTape[i] == ']') {
                                innerLoopCounter++;
                                commandTapeCursor--;
                            } else {
                                commandTapeCursor--;
                            }
                        }
                    } else {
                        commandTapeCursor++;
                    }
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
