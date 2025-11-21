private static void countWords() {
    System.out.println("Loading data ...");
    try {
        String content = readFile();
        // Split by whitespace (one or more spaces)
        long wordCount = Arrays.stream(content.trim().split("\\s+"))
                               .filter(s -> !s.isEmpty())
                               .count();
        System.out.println(wordCount + " word(s) found, total chars: " + content.length());
    } catch (Exception e) {
        System.out.println("Error reading file.");
    }
    System.out.println("Data Loaded.");
}
